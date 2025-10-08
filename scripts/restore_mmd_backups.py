#!/usr/bin/env python3
"""Restore original .mmd files from .bak_final backups in docs/.

For each docs/*.mmd, if a .mmd.bak_final exists, move current .mmd to .mmd.sanitized,
then move .mmd.bak_final back to .mmd. Safe-guards if files missing.
"""
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'

def restore_one(p: Path):
    bak = p.with_suffix(p.suffix + '.bak_final')
    if not bak.exists():
        print(f'No bak_final for {p.name}, skipping')
        return
    sanitized = p.with_suffix(p.suffix + '.sanitized')
    if p.exists():
        p.rename(sanitized)
        print(f'Moved {p.name} -> {sanitized.name}')
    bak.rename(p)
    print(f'Restored {p.name} from {bak.name}')

def main():
    for p in sorted(DOCS.glob('*.mmd')):
        restore_one(p)

if __name__ == '__main__':
    main()
