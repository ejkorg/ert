#!/usr/bin/env python3
"""Aggressive sanitizer for Mermaid files to ensure Kroki can parse them.

Replacements:
- Collapse newlines inside [ ... ] and ( ... ) into single spaces
- Replace literal backslash-n sequences with space
- Replace curly braces { } with parentheses (they cause issues inside labels)
- Replace multiple spaces with single space
Backs up originals to .mmd.bak_final
"""
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'

def collapse(match):
    inner = match.group(1)
    inner = inner.replace('\\n', ' ').replace('\n', ' ').replace('\r', ' ')
    inner = inner.replace('{', '(').replace('}', ')')
    inner = re.sub(r'\s+', ' ', inner).strip()
    return f'[{inner}]' if match.group(0).startswith('[') else f'({inner})'

def process(p: Path):
    txt = p.read_text()
    new = txt
    # collapse square bracket nodes
    new = re.sub(r"\[([^\]]*?)\]", lambda m: '[' + re.sub(r'\s+', ' ', m.group(1).replace('\\n',' ').replace('\n',' ').replace('{','(').replace('}',')')).strip() + ']', new, flags=re.S)
    # collapse parentheses nodes
    new = re.sub(r"\(([^\)]*?)\)", lambda m: '(' + re.sub(r'\s+', ' ', m.group(1).replace('\\n',' ').replace('\n',' ').replace('{','(').replace('}',')')).strip() + ')', new, flags=re.S)
    new = new.replace('\\"', '"')
    new = re.sub(r'\s+', ' ', new)
    if new != txt:
        bak = p.with_suffix(p.suffix + '.bak_final')
        if not bak.exists():
            p.rename(bak)
            bak.write_text(txt)
            p.write_text(new)
            print(f'Backed up {p.name} -> {bak.name} and sanitized (final)')
        else:
            p.write_text(new)
            print(f'Sanitized {p.name} (final, bak exists)')
    else:
        print(f'No changes for {p.name}')

def main():
    for p in sorted(DOCS.glob('*.mmd')):
        try:
            process(p)
        except Exception as e:
            print(f'ERROR processing {p.name}: {e}')

if __name__ == '__main__':
    main()
