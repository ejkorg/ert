# SiC Wafer Trace — 5Ws and How

Who
- Sources: BIWMES (YMS), REFDB, eCofA, TORRENT (CZ2); Maine EPI CSV; optional deletes CSV
- Owners: ERT ingestion for Exensio Reference Tables (SiC flows)

What
- Builds wafer genealogy between fab lots, raw SiC slices, epi lots, and pucks
- Produces CSVs grouped by puck and source lots:
  - PUCK2FAB.*.p2f.csv (slice→fab by puck)
  - FAB2PUCK.*.f2p.csv (fab→slice by source lot)
  - EPI2PUCK.*.e2p.csv (epi→slice by source lot)

When
- Time window uses relative days: --start (N days ago) to --end (M days ago)
  - Start at 00:00 of the start day, end at 23:59:59 of the end day
- Can override the window with --slicelist and/or --lotlist for targeted runs

Where
- Databases: BIWPRD (BIWMES/YMS), REFDB (on_slice), eCofA schema (wafer/rawsilicon_lot), TORRENT (CZ4TRQA)
- Files: Output CSVs in --out/tmp then moved to --out; optionally copied to --archivedir

Why
- Provide consistent, enriched wafer genealogy to Exensio:
  - Resolve slice↔wafer mapping from fab starts (CRLT) and epi operations
  - Prefer authoritative data: REFDB first, then eCofA, then computed fallback
  - Handle special cases (Maine EPI overrides, changed wafer mappings, CZ2 epi)

How
- Inputs and setup
  - Required options: --out, --ecoaschema, --start, --end, --meepifile
  - Optional: --usearchive, --archivedir, --deletesfile, --slicelist, --lotlist
  - Load Maine EPI CSV for overrides; load deletes CSV to filter UWB epi attributes
  - Open connections to BIWMES, REFDB, eCofA, TORRENT

- Fab trace branch (getEpiOrFabTraceSQL 'fab')
  - Query BIWMES attrs/info to map slices to wafers (CRLT starts), with filters
  - Apply Maine overrides: if slice present, set epi supplier UWB and take lot/product/slot/date from CSV
  - Skip records where UWB epi attribute was later deleted (not COMETS purge)
  - eCofA lookup by global wafer ID or scribe: get GWID, puck, raw/epi metadata
  - If eCofA epi missing: query TORRENT (CZ2) for epi data for the slice
  - Lookup REFDB.on_slice; if matched via GWID, remap to real slice
  - Handle CHANGED_WAFERS: replace ON_SLICE fab_wafer_id if prior wafer listed
  - Update ON_SLICE if needed: fab_wafer_id, fab_source_lot, global_wafer_id, slice_order, slice_lottype
  - Emit output records for PUCK2FAB and FAB2PUCK, plus EPI2PUCK from this pass

- Epi trace branch (three passes)
  - Iter1 BIWMES epi (getEpiOrFabTraceSQL 'epi'): facilities FBEPI/FM4045/BBLS; detect Bucheon vs internal
  - Iter2 TORRENT CZ2 epi (getCZEpiSQL): by time window or slicelist
  - Iter3 eCofA epi by date (get_eCofA_sliceInfoByDate): exclude CZ2 epi
  - For each: apply Maine overrides and deletes filtering; eCofA lookup for additional meta; REFDB.on_slice read/remap
  - Build EPI2PUCK records by source lot; deduplicate per source lot and slice; supplier precedence prefers eCofA when BK says non-UWB and available

- Output formatting and precedence
  - Prefer REFDB fields when present; else eCofA; else fallback/"NA"
  - RAW_SUPPLIERID 'CZ2' mapped to 'GTAT' for raw supplier output
  - Global wafer ID guessing: from slice by removing '-' or truncating when necessary
  - Write CSVs to --out/tmp; then copy to --archivedir (if set) and move into --out

- Key columns (21 per record; examples)
  - SOURCE_LOT, WAFER, FAB_PRODUCT, LOT_TYPE, LOT_START_DATE, SIC_SLICE, GLOBAL_WAFER_ID
  - RAW_SIC_SUPPLIER, EPI_SUPPLIER, EPI_LOT, EPI_PRODUCT, EPI_SLOT, EPI_START_DATE
  - START_LOT, RAW_WAFER_PRODUCT, LOTTYPEx, PUCKID, RUNID, PUCK_HEIGHT, SLICE_ORDER, SLICE_START_TIME

- Edge cases handled
  - COMETS purge vs manual deletes when counting deletions
  - CZ2 epi excluded from eCofA epi-by-date scans; prefer TORRENT for CZ epi
  - CHANGED_WAFERS support to correct wafer↔slice mapping
  - Maine EPI batches and attribute deletes treated via CSV inputs
