#!/usr/bin/env python3
"""Sanitize Mermaid .mmd files for Kroki rendering.

Actions:
- Back up each .mmd to .bak
- Join labels that were split across lines inside [...] or (...) by replacing the interior newlines with literal '\n'
- Replace a small set of problematic unicode arrows/glyphs with ASCII equivalents
"""
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'

REPLACEMENTS = {
    '\u279c': '->',  # ➜
    '\u2192': '->',  # →
    '\u2194': '<->', # ↔
    '➜': '->',
    '→': '->',
    '↔': '<->',
    '—': '-',
}

def sanitize_text(s: str) -> str:
    # Replace unicode glyphs
    for k, v in REPLACEMENTS.items():
        s = s.replace(k, v)
    # Join content between [ ... ] or ( ... ) if it contains newlines by replacing newlines with \n
    # For bracketed nodes: find patterns like Name[... possibly multiline ...]
    def _join(match):
        inner = match.group(1).replace('\n', '\\n')
        inner = inner.replace('\r', '')
        inner = inner.replace('"', '\\"')
        return f'[{inner}]'

    # Handle square bracket labels
    s = re.sub(r"\[([^\]]*?)\]", lambda m: _join(m), s, flags=re.S)
    # Handle parentheses labels (for nodes like A((text))) - conservative: join inner content
    s = re.sub(r"\(([^\)]*?)\)", lambda m: '(' + m.group(1).replace('\n', '\\n') + ')', s, flags=re.S)
    return s

def process_file(p: Path):
    txt = p.read_text()
    new = sanitize_text(txt)
    if new != txt:
        bak = p.with_suffix(p.suffix + '.bak')
        if not bak.exists():
            p.rename(bak)
            bak.write_text(txt)
            p.write_text(new)
            print(f'Sanitized and backed up {p.name} -> {bak.name}')
        else:
            # bak exists, just overwrite original
            p.write_text(new)
            print(f'Sanitized {p.name} (backup already exists)')
    else:
        print(f'No changes for {p.name}')

def main():
    for p in sorted(DOCS.glob('*.mmd')):
        try:
            process_file(p)
        except Exception as e:
            print(f'ERROR processing {p.name}: {e}')

if __name__ == '__main__':
    main()
