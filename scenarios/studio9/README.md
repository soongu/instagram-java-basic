# Studio9 Private Scenario Notes

This folder stores private source documents for `SCENARIO_STUDIO9`.

## Source Priority

1. `STUDIO9_IMPLEMENTATION_CANON_v1.md`
   - Implementation canon.
   - Use this as the source of truth for canonical codes, variants, evidence, AI boundaries, and scoring.

2. `CLUEROOM_SCENARIO2_WORKING_BRIEF_v15.md`
   - Working history and design rationale.
   - Use only as supporting context for card copy, image intent, NPC tone, and rejected/adjusted ideas.

If the canon and working brief conflict, the canon wins.

## Future YAML Targets

When the backend is ready for private scenario import, create:

```text
.private/scenarios/studio9/studio9.v1.yaml
.private/scenarios/studio9/asset-map.csv
```

Do not commit answer-bearing YAML, variant solutions, proof-dimension mappings, or hidden NPC truth to the public repository.

## Import Strategy

Use the same import strategy documented in:

```text
.private/scenarios/seowolchae/CODEX_YAML_IMPORT_HANDOFF_v1.md
```

Studio9 should follow the same ten-step flow:

```text
1. Source document cleanup: done
2. Canonical code freeze: done
   See CANONICAL_CODES.md
3. YAML schema design: done
   See docs/scenarios/SCENARIO_YAML_SCHEMA.md
4. Image asset mapping: done
   See asset-map.csv
5. YAML draft: in progress
   See studio9.v1.yaml
   - Base scenario, victim, locations, characters, evidence list: done
   - Evidence baseDetail: done for v1 draft
   - Variant truth, scoring, npcPolicies: pending
6. YAML DTO / Loader / Validator
7. DB structure expansion
8. ImportService / Runner
9. Runtime connection
10. Verification
```
