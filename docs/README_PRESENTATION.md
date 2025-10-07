ERT Presentation README

This document explains how to regenerate the presentation decks and convert them to PDF.

Regenerate decks

Requirements:
- Python 3.8+
- pip
- python-pptx

Install dependencies:

```bash
python3 -m pip install --user python-pptx
```

Generate decks:

```bash
cd /path/to/exensioreftables-ws
python3 scripts/generate_presentation.py                        # general deck
python3 scripts/generate_presentation_ert_only.py               # ERT-only deck
python3 scripts/generate_presentation_fullres_with_notes.py     # full-res + notes deck
```

Convert to PDF (local machines)

If you have LibreOffice installed, you can convert PPTX to PDF non-interactively:

```bash
libreoffice --headless --convert-to pdf --outdir docs docs/ERT_presentation_fullres_with_notes.pptx
libreoffice --headless --convert-to pdf --outdir docs docs/ERT_presentation_ert_only.pptx
```

Alternatively, open the PPTX in PowerPoint and export to PDF for best fidelity.

Notes

- The scripts embed PNG images from `docs/` and populate speaker notes with markdown content.
- Large images are embedded as-is; if you need down-sampled versions for smaller PDFs, edit the scripts to resize images before embedding or use image tools like `convert` (ImageMagick).
