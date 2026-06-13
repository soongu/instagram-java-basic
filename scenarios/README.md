# Private Scenario Source Index

This directory stores spoiler-bearing scenario source documents and future
answer-bearing YAML files. It is intentionally ignored by Git.

## Global Rules

- Do not commit culprit, variant solution, proof-dimension mapping, or hidden NPC truth to the public repository.
- Canon documents are the source of truth for implementation.
- Working briefs are history/supporting context only.
- If a canon and a working brief conflict, the canon wins.
- YAML files in this directory are source data. Runtime APIs must use DB data after import.
- Image files are local source assets. YAML should reference `assetKey` or `s3ObjectKey`, not local Windows paths.

## Scenario Folders

| Folder | Scenario Code | Canon | Working Brief | Notes |
|---|---|---|---|---|
| `seowolchae/` | `SCENARIO_SEOWOLCHAE_LAST_PRESCRIPTION` | `SEOWOLCHAE_IMPLEMENTATION_CANON_v1.1.md` | `CLUEROOM_SCENARIO_WORKING_BRIEF_v20.md` | Uses `CODEX_YAML_IMPORT_HANDOFF_v1.md` as importer handoff. |
| `studio9/` | `SCENARIO_STUDIO9` | `STUDIO9_IMPLEMENTATION_CANON_v1.md` | `CLUEROOM_SCENARIO2_WORKING_BRIEF_v15.md` | Follows the same importer strategy as Seowolchae. |

## Future Private Outputs

Expected private output files:

```text
.private/scenarios/seowolchae/seowolchae.v1.yaml
.private/scenarios/seowolchae/asset-map.csv
.private/scenarios/studio9/studio9.v1.yaml
.private/scenarios/studio9/asset-map.csv
```

Do not place these files under `docs/` or `src/main/resources/` unless the repository is intentionally private and the team explicitly accepts spoiler exposure.
