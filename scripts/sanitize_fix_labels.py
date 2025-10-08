#!/usr/bin/env python3
"""Collapse newlines inside node delimiters and remove escaped quotes.

This script targets files that failed Kroki parsing due to multi-line node labels.
It backs up originals to .bak_fix if not present.
"""
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'

DELIMS = [('\[', '\]'), ('\(', '\)'), ('\{', '\}')]  # regex-friendly

def collapse_inner(s: str) -> str:
    # Remove backslash-escaped quotes
    s = s.replace('\\"', '"')
    # For each delimiter pair, collapse newlines inside to single space
    for open_d, close_d in DELIMS:
        pattern = re.compile(open_d + r'(.*?)' + close_d, re.S)
        def repl(m):
            inner = m.group(1)
            inner2 = inner.replace('\\n', ' ').replace('\n', ' ').replace('\r', ' ')
            inner2 = re.sub(r'\s+', ' ', inner2).strip()
            return open_d.replace('\\', '') + inner2 + close_d.replace('\\', '')
        s = pattern.sub(repl, s)
    return s

def process_file(p: Path):
    txt = p.read_text()
    new = collapse_inner(txt)
    if new != txt:
        bak = p.with_suffix(p.suffix + '.bak_fix')
        if not bak.exists():
            p.rename(bak)
            bak.write_text(txt)
            p.write_text(new)
            print(f'Backed up {p.name} -> {bak.name} and collapsed newlines')
        else:
            p.write_text(new)
            print(f'Collapsed newlines in {p.name} (bak exists)')
    else:
        print(f'No changes for {p.name}')

def main():
    targets = [
        'ert-overview.mmd', 'lotg-query.mmd', 'onlot-dataflow.mmd', 'onlot-level2.mmd',
        'onprod-dataflow.mmd', 'onscribe-dataflow.mmd', 'onscribe-level2.mmd',
        'onslice-dataflow.mmd', 'onwmap-level2.mmd'
    ]
    for name in targets:
        p = DOCS / name
        if p.exists():
            try:
                process_file(p)
            except Exception as e:
                print(f'ERROR processing {name}: {e}')
        else:
            print(f'Missing {name}')

if __name__ == '__main__':
    main()
