# Seowolchae Private Scenario Notes

This folder stores private source documents for `SCENARIO_SEOWOLCHAE_LAST_PRESCRIPTION`.

## Source Priority

1. `SEOWOLCHAE_IMPLEMENTATION_CANON_v1.1.md`
   - Implementation canon.
   - Use this as the source of truth for canonical codes, variants, evidence, AI boundaries, and scoring.

2. `CLUEROOM_SCENARIO_WORKING_BRIEF_v20.md`
   - Working history and design rationale.
   - Use only as supporting context for card copy, image intent, NPC tone, and rejected/adjusted ideas.

3. `CODEX_YAML_IMPORT_HANDOFF_v1.md`
   - Backend/importer implementation handoff.
   - Use this for the ten-step YAML import workflow and runtime safety constraints.

If the canon and working brief conflict, the canon wins.

## Future YAML Targets

When preparing private scenario import data, create:

```text
.private/scenarios/seowolchae/seowolchae.v1.yaml
.private/scenarios/seowolchae/asset-map.csv
```

Do not commit answer-bearing YAML, variant solutions, proof-dimension mappings, or hidden NPC truth to the public repository.

## Import Strategy

Follow the ten-step flow documented in:

```text
.private/scenarios/seowolchae/CODEX_YAML_IMPORT_HANDOFF_v1.md
```

Current source cleanup status:

```text
1. Source document cleanup: done
2. Canonical code freeze: done
   See CANONICAL_CODES.md
3. YAML schema design: done
   See docs/scenarios/SCENARIO_YAML_SCHEMA.md
4. Image asset mapping: done
   See asset-map.csv
5. YAML draft: in progress
   See seowolchae.v1.yaml
   - Base scenario, victim, locations, characters, evidence list: done
   - Evidence baseDetail: done for v1 draft
   - Variant truth, scoring, npcPolicies: pending
```
