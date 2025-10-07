package com.onsemi.cim.apps.exensioreftables.ws.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author fgx8n8x
 */

/**
 * parser/runner for lot trim rules stored in site configuration (OnSiteConf.lotTrimRule).
 * Supported forms:
 *  - trim-last:N             (remove last N chars if length > N)
 *  - suffix:X                (if lot ends with X, remove that suffix)
 *  - regex:<pattern>         (use first capture group as trimmed result)
 *  - regex:<p1>;<p2>;...     (multiple regex patterns separated by semicolon; tried in order)
 *
 * Notes:
 *  - For regex rules the first capturing group (group 1) is used as the trimmed lot.
 *  - Use semicolons to provide multiple regex alternatives. Example:
 *      regex:^(L.{5,})A$;^(K.{5,})A$
 *    This will try the first pattern, and if it doesn't match try the second.
 *
 * Examples:
 *  - trim-last:1
 *      "LMLR8A" -> "LMLR8" (removes last char)
 *  - suffix:_X
 *      "LMLR8A_X" with suffix:_X -> "LMLR8A"
 *  - regex:^(L.{4,})A$
 *      "LMLR8A" -> "LMLR8" (captures group 1)
 *  - regex:^(K.{4,})A$;^(L.{4,})A$
 *      tries K-pattern first, then L-pattern; only trims when a pattern matches
 */
public class LotTrimRule {

    private final RuleType type;
    private final String payload;
    private final Pattern pattern;
    private final Pattern[] compiledPatterns;

    private enum RuleType { TRIM_LAST, SUFFIX, REGEX, NONE }

    private LotTrimRule(RuleType type, String payload, Pattern pattern, Pattern[] compiledPatterns) {
        this.type = type;
        this.payload = payload;
        this.pattern = pattern;
        this.compiledPatterns = compiledPatterns;
    }

    public static LotTrimRule none() {
        return new LotTrimRule(RuleType.NONE, null, null, null);
    }

    public static LotTrimRule parse(String rule) {
        if (StringUtils.isBlank(rule)) return none();
        rule = rule.trim();
        if (rule.startsWith("trim-last:")) {
            String val = rule.substring("trim-last:".length());
            return new LotTrimRule(RuleType.TRIM_LAST, val, null, null);
        }
        if (rule.startsWith("suffix:")) {
            String val = rule.substring("suffix:".length());
            return new LotTrimRule(RuleType.SUFFIX, val, null, null);
        }
        if (rule.startsWith("regex:")) {
            String val = rule.substring("regex:".length());
            // Support multiple regex patterns separated by semicolon. Compile all patterns now
            // so regex syntax errors surface early and we avoid compiling on each apply() call.
            Pattern[] compiled = null;
            try {
                final String[] parts = val.split(";");
                java.util.List<Pattern> list = new java.util.ArrayList<>();
                for (String p : parts) {
                    if (StringUtils.isBlank(p)) continue;
                    list.add(Pattern.compile(p));
                }
                compiled = list.isEmpty() ? null : list.toArray(new Pattern[0]);
            } catch (Exception e) {
                // If compilation fails, treat as NONE so apply() returns empty and callers will log
                // the original rule string where appropriate.
                return new LotTrimRule(RuleType.NONE, null, null, null);
            }
            return new LotTrimRule(RuleType.REGEX, val, null, compiled);
        }
        // fallback: do not treat unknown forms as suffix; return NONE (no-op)
        return none();
    }

    /**
     * Apply rule and return Optional trimmed lot. If rule does not match, return Optional.empty().
     */
    public Optional<String> apply(String lot) {
        if (lot == null) return Optional.empty();
        switch (type) {
            case TRIM_LAST:
                try {
                    final int n = Integer.parseInt(payload);
                    if (n <= 0 || lot.length() <= n) return Optional.empty();
                    return Optional.of(lot.substring(0, lot.length() - n));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            case SUFFIX:
                if (StringUtils.isNotEmpty(payload) && lot.endsWith(payload)) {
                    return Optional.of(lot.substring(0, lot.length() - payload.length()));
                }
                return Optional.empty();
            case REGEX:
                if (compiledPatterns == null || compiledPatterns.length == 0) return Optional.empty();
                try {
                    for (Pattern p : compiledPatterns) {
                        if (p == null) continue;
                        final java.util.regex.Matcher m = p.matcher(lot);
                        if (m.find() && m.groupCount() >= 1) {
                            return Optional.of(m.group(1));
                        }
                    }
                } catch (Exception e) {
                    // defensive: any unexpected regex runtime error should not bubble up
                    // to the caller; return empty so lookup falls back to untrimmed behavior.
                    return Optional.empty();
                }
                return Optional.empty();
            default:
                return Optional.empty();
        }
    }
}
