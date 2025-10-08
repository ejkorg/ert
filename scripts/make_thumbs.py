#!/usr/bin/env python3
"""Generate 300px-wide thumbnails for docs/*.png into docs/thumbs/ for quick visual QA."""
import os
from pathlib import Path

try:
    from PIL import Image
except Exception:
    print("Pillow is not installed. Install with: pip install pillow")
    raise

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / 'docs'
OUT = DOCS / 'thumbs'
OUT.mkdir(parents=True, exist_ok=True)

for p in sorted(DOCS.glob('*.png')):
    try:
        with Image.open(p) as im:
            w = 300
            h = int(im.height * (w / im.width))
            im.thumbnail((w, h))
            out = OUT / p.name
            im.save(out, format='PNG')
            print(f'Wrote {out} ({out.stat().st_size} bytes)')
    except Exception as e:
        print(f'FAILED {p}: {e}')
