#!/usr/bin/env python3
"""Targeted sanitizer to join lines split inside node delimiters and sanitize pipe labels.

Backs up to .mmd.bak3 when changes are made.
"""
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'

def join_split_lines(text: str) -> str:
    lines = text.splitlines()
    out_lines = []
    buffer = None
    openers = {'[':']','(':')','{':'}'}

    for line in lines:
        if buffer is None:
            # start new
            if any(ch in line for ch in openers):
                # check if line has matching closers for every opener
                stack = []
                for ch in line:
                    if ch in openers:
                        stack.append(ch)
                    elif stack and ch == openers[stack[-1]]:
                        stack.pop()
                if stack:
                    buffer = line
                else:
                    out_lines.append(line)
            else:
                out_lines.append(line)
        else:
            buffer += ' ' + line.strip()
            # check if balanced now
            stack = []
            for ch in buffer:
                if ch in openers:
                    stack.append(ch)
                elif stack and ch == openers[stack[-1]]:
                    stack.pop()
            if not stack:
                out_lines.append(buffer)
                buffer = None

    if buffer is not None:
        out_lines.append(buffer)

    return '\n'.join(out_lines)

def sanitize_pipe_labels(text: str) -> str:
    # Replace { and } inside pipe-delimited labels |...{...}...| -> |...( ... )...|
    def repl(match):
        inner = match.group(1)
        inner2 = inner.replace('{', '(').replace('}', ')')
        return '|' + inner2 + '|'
    return re.sub(r'\|([^|]*)\|', repl, text)

def process(p: Path):
    txt = p.read_text()
    new = join_split_lines(txt)
    new = sanitize_pipe_labels(new)
    if new != txt:
        bak = p.with_suffix(p.suffix + '.bak3')
        if not bak.exists():
            p.rename(bak)
            bak.write_text(txt)
            p.write_text(new)
            print(f'Backed up {p.name} -> {bak.name} and sanitized (bak3)')
        else:
            p.write_text(new)
            print(f'Sanitized {p.name} (bak3 exists)')
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
