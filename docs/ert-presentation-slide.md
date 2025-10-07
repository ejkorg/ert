# Exensio Reference Tables (ERT) Web Service — Executive Snapshot

**What it is**
- Spring Boot REST layer standardising lot, product, scribe, slice, and wafer-map reference data across test & manufacturing.
- Oracle/H2-backed cache that fronts slower upstream systems while preserving status provenance.

**How it works**
- Controllers normalise input, log traceability, and emit DTOs with `Status` enums (`FOUND`, `NO_DATA`, `ERROR`, `LOTG_MES_LTM_DW`, etc.).
- Service tier orchestrates repository hits plus conditional enrichment from LotG DB/WS, LTM, Data Warehouse, Torrent/Genesis MES, and VID↔SCRIBE services.
- Configuration tables (`ON_FAB_CONF`, `ON_SITE_CONF`, `ERT_CONF`) toggle which identifiers to pass, which URLs to call, trimming rules, and how fallbacks (calculated wafer IDs) behave.

**Key domains & endpoints**
- `OnLot` `/api/onlot/bylotid` — seeds lot + product cache using LotG, LTM, MES, Data Warehouse.
- `OnProd` `/api/onprod/byproduct` — layers product-centric overrides (PLM, MES) on OnLot outputs.
- `OnScribe` `/api/onscribe/bystdfinfo` — resolves wafer IDs via VID↔SCRIBE or deterministic patterns with cached `sourceLot`.
- `OnSlice` `/api/onslice/byslice` — CRUD hub for slice/puck metadata.
- `OnWmap` `/api/onwmap/bylotid` — surfaces wafer-map references by reusing OnLot/OnProd context.

**Integration highlights**
- LotG native SQL handles multi-format lot probing with site-specific trimming heuristics.
- Torrent/Genesis MES connectors supply product version, technology, process, PTI, mask set when site configs permit.
- Data Warehouse service enriches part taxonomy (mask set, PAL4, mfg area) for both OnLot and OnProd.
- VID↔SCRIBE endpoints (configurable per fab) return laser-scribe data; failures trigger `AttributeUtils.calculateWaferId` fallbacks.

**Why it matters**
- Provides consistent reference data for downstream STDF processing, analytics, and MES integrations.
- Status-driven caching prevents redundant external calls and protects manual overrides.
- Config-driven architecture scales across fabs/sites without code changes.
