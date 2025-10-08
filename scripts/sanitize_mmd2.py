#!/usr/bin/env python3
"""Robust sanitizer for Mermaid .mmd files.

Replaces raw newlines inside [], {}, () with literal "\\n" so Kroki's parser sees multiline labels as single-token content.
Backs up originals to .bak if not already backed up.
"""
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'

PAIRS = {'[': ']', '{': '}', '(': ')'}

def replace_inside(s: str) -> str:
    out = []
    i = 0
    L = len(s)
    while i < L:
        ch = s[i]
        if ch in PAIRS:
            close = PAIRS[ch]
            start = i
            depth = 1
            j = i + 1
            while j < L and depth > 0:
                if s[j] == ch:
                    depth += 1
                elif s[j] == close:
                    depth -= 1
                    if depth == 0:
                        break
                j += 1
            if depth == 0:
                inner = s[i+1:j]
                # replace CRLF/CR/newline with literal backslash-n
                inner2 = inner.replace('\r\n', '\\n').replace('\r', '\\n').replace('\n', '\\n')
                out.append(ch)
                out.append(inner2)
                out.append(close)
                i = j + 1
                continue
            else:
                # no matching closer; just append and move on
                out.append(ch)
                i += 1
        else:
            out.append(ch)
            i += 1
    return ''.join(out)

def process(p: Path):
    txt = p.read_text()
    new = replace_inside(txt)
    if new != txt:
        bak = p.with_suffix(p.suffix + '.bak2')
        if not bak.exists():
            p.rename(bak)
            bak.write_text(txt)
            p.write_text(new)
            print(f'Backed up {p.name} -> {bak.name} and sanitized')
        else:
            p.write_text(new)
            print(f'Sanitized {p.name} (bak exists)')
    else:
        print(f'No changes for {p.name}')

def main():
    for p in sorted(DOCS.glob('*.mmd')):
        try:
            process(p)
        except Exception as e:
            print(f'ERROR {p.name}: {e}')

if __name__ == '__main__':
    main()
