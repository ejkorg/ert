# Slide 1 – Title & Context
- **Exensio Reference Tables (ERT) Web Service**
- Preparing for Databricks-enabled reference data orchestration
- Audience: Manufacturing IT leadership, Data Engineering, Product Test teams

---

# Slide 2 – Current-State Snapshot
- Spring Boot 2.x REST API serving `/api/onlot`, `/api/onprod`, `/api/onscribe`, `/api/onslice`, `/api/onwmap`
- Oracle/H2-backed cache with DTO status tracking (`FOUND`, `NO_DATA`, `LOTG_MES_LTM_DW`, etc.)
- Enrichment pipeline fusing LotG DB/WS, LTM, Data Warehouse, Torrent/Genesis MES, VID↔SCRIBE
- Config-driven behaviour via `ON_FAB_CONF`, `ON_SITE_CONF`, `ERT_CONF`

---

# Slide 3 – Key Business Capabilities
- Normalises lot, product, wafer, and slice metadata for downstream STDF and analytics
- Provides deterministic fallbacks (calculated wafer IDs) and manual override protection
- Reduces latency vs. direct MES/PLM calls through cached persistence with provenance
- Scales across fabs/sites without redeploying code (configuration toggles)

---

# Slide 4 – Integration Landscape
- External sources: LotG (DB & WS), LTM WS, Data Warehouse/PLM, Torrent/Genesis MES, VID↔SCRIBE services
- Outbound call orchestration using shared `Caller` helper & utility classes
- Site-specific lot trimming, MES SQL, and credential management via `ON_SITE_CONF`
- Dependencies visualised in `docs/ert-overview.mmd` (controllers → services → repos → upstream systems)

---

# Slide 5 – Pain Points & Migration Drivers
- Tight coupling to on-prem Oracle schemas; limited elasticity for analytics workloads
- Point-to-point service calls create operational risk (throttling, credential rotation)
- Batch analytics rely on downstream extracts instead of near-real-time lakehouse feeds
- Desire to consolidate enrichment logic alongside Databricks unified governance & ML workflows

---

# Slide 6 – Databricks Vision
- Re-platform reference data pipelines onto Delta Lake tables with ACID change tracking
- Expose curated views (Lot, Prod, Scribe, Slice, Wmap) through Databricks SQL endpoints & Delta Live Tables
- Leverage Medallion architecture: Bronze (raw upstream pulls), Silver (harmonised enrichment), Gold (analytics-ready dimensions)
- Support ML/AI workloads for test yield improvement using unified data access

---

# Slide 7 – Target Architecture (Conceptual)
- **Ingestion**: Auto Loader / Structured Streaming pulls from LotG DB dumps, MES change feeds, VID↔SCRIBE APIs
- **Transformation**: Notebooks / Jobs reuse existing enrichment logic rewritten in PySpark/Delta Live pipelines
- **Serving**: Gold tables published to Databricks SQL, Power BI, and REST endpoints via Databricks Model Serving or Unity Catalog Secured Views
- **Governance**: Unity Catalog enforces access tiers; feature store hosts calculated wafer IDs & lot metadata

---

# Slide 8 – Migration Strategy
- Phase 1: Inventory current service logic & configuration tables (already documented in `/docs/*-dataflow.md`)
- Phase 2: Create Delta schema equivalents (ON_LOT, ON_PROD, ON_SCRIBE, ON_SLICE, ON_WMAP, configuration tables)
- Phase 3: Implement enrichment notebooks using modular helper libraries mirroring `Caller` utilities
- Phase 4: Stand up Databricks SQL endpoints & REST facades; pilot with read-only consumers
- Phase 5: Decommission or downgrade Spring service once adoption reaches SLA thresholds

---

# Slide 9 – Workstream Responsibilities
- **Data Engineering**: Build ingestion & transformation pipelines, automate configuration sync
- **Platform Engineering**: Establish Unity Catalog, CI/CD for notebooks, secret scopes for external services
- **App Team**: Translate business rules, validate parity between Spring service & Databricks outputs
- **QA/Operations**: Regression testing (status codes, calculated fields), monitoring, incident runbooks

---

# Slide 10 – Risk & Mitigation
- Data parity drift → implement dual-write / dual-read validation dashboards during transition
- API compatibility gaps → provide thin API gateway (ADF, API Management, or serverless function) proxying Databricks outputs
- Security/Compliance → leverage secret scopes, private link, and audit logs; maintain break-glass manual overrides
- Performance tuning → benchmark Delta tables, optimise Z-ordering & auto-compaction for lot-query workloads

---

# Slide 11 – Timeline (Indicative)
- Q1: Blueprint & proof-of-concept (OnLot + OnProd flows)
- Q2: Expand to OnScribe/OnSlice/OnWmap; configure Unity Catalog governance
- Q3: Parallel run with Spring service, validation harness, consumer onboarding
- Q4: Cutover decision gate, decommission legacy endpoints, post-mortem & optimisation backlog

---

# Slide 12 – Call to Action
- Confirm executive sponsorship & budget for Databricks workspace expansion
- Align teams on migration backlog and milestones
- Kick off parity assessment sprint using existing docs (`lot`, `prod`, `scribe`, `slice`, `overview` flows)
- Schedule workshop to map Spring service utilities to Databricks libraries
