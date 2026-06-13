# CODEX_YAML_IMPORT_HANDOFF_v1

> 대상: 프로젝트 내부 Codex / 백엔드 담당자  
> 목적: 서월채 시나리오를 Java seed 하드코딩이 아니라 **YAML 원본 파일 → Importer 검증 → DB 저장 → 런타임 DB 조회** 구조로 구현하기 위한 개발 인수인계  
> 기준 문서: `SEOWOLCHAE_IMPLEMENTATION_CANON_v1.1.md`  
> 공통 스키마: `docs/scenarios/SCENARIO_YAML_SCHEMA.md`  
> 중요: 이 문서는 **시나리오 내용을 새로 창작하는 문서가 아니라 구현 작업 지시서**다.

> 2026-05-28 이후 기준: 정답/Variant가 포함된 공식 YAML은 public repo의
> `src/main/resources`에 커밋하지 않는다. 실제 YAML은 `.private/scenarios/...`
> 또는 운영 서버의 private import path에서 읽고, public repo에는 importer 코드와
> spoiler-free schema만 둔다. 오래된 위치 예시는 18장을 우선한다.

---

## 0. 결론

서월채 시나리오는 단순 `Scenario 1 : Solution 1` 구조가 아니다.

```text
Scenario 1
 ├─ ScenarioVariant N
 │   └─ VariantSolution 1
 ├─ Evidence N
 ├─ EvidenceVariantState N
 ├─ Location N
 ├─ ScenarioCharacter N
 ├─ NpcKnowledgeProfile N
 └─ UnlockRule N
```

공식 시나리오는 YAML 파일을 원본으로 관리하고, 서버가 이를 파싱해서 DB 엔티티로 저장한다.

```text
src/main/resources/scenarios/seowolchae.v1.yaml
        ↓
ScenarioYamlImporter
        ↓
Validation
        ↓
DB 저장
        ↓
PlaySession 시작 시 activeVariant 선택
        ↓
게임 진행 / 증거 조회 / AI 심문 / 최종 추리 채점
```

런타임에서 매 요청마다 YAML을 직접 읽으면 안 된다. YAML은 **콘텐츠 원본/시드 소스**이고, 실제 플레이는 DB 기준으로 동작해야 한다.

---

## 1. 절대 변경하면 안 되는 기획 제약

### 1-1. Variant 구조

서월채는 아래 4개 Variant를 지원한다.

```text
VARIANT_SECURITY   → 범인: 특수보안팀장
VARIANT_SECRETARY  → 범인: 비서실장
VARIANT_SPOUSE     → 범인: 배우자
VARIANT_DOCTOR     → 범인: 예비병원장
```

정식 목표는 `RANDOM_REQUIRED`다.

다만 MVP 일정상 어려우면 다음 fallback이 허용된다.

```text
DB/엔티티 구조는 Variant-ready로 만든다.
하지만 데모/시연에서는 enabled=true인 Variant를 VARIANT_SECRETARY 1개만 두거나,
서버에서 activeVariant를 VARIANT_SECRETARY로 고정할 수 있다.
```

이 fallback은 일정 방어용이며, 구조 자체를 `Scenario 1 : Solution 1`로 되돌리면 안 된다.

---

### 1-2. AI NPC 정보 경계

AI 심문 프롬프트에 아래 정보는 넣으면 안 된다.

```text
activeVariant
culpritCode
variantSolution
fullTruthTimeline
evidenceRoleByVariant 전체
정답 해설
채점 정답
다른 NPC의 hiddenAction 전체
```

AI에게 줄 수 있는 정보는 아래 범위다.

```text
characterProfile
publicAlibi
hiddenSelfAction
directKnowledge
inferredKnowledge
forbiddenKnowledge
stageResponsePolicy
evidenceReactionPolicy
```

증거 반응 정책은 RAG식으로 동적 주입한다.

```text
평소:
NPC 기본 성격 + 본인 지식 경계만 프롬프트에 넣는다.

유저가 특정 evidenceCode를 제시했을 때:
그 evidenceCode에 연결된 evidenceReactionPolicy만 추가로 넣는다.
```

---

### 1-3. MVP 맵 정책

서월채 MVP에서는 맵 탐색이 아니다.

```text
맵 = 참고용 평면도
증거 = 증거 탭에서 Phase 기반 공개
locationCode = 증거 출처/필터 기준
```

좌표/hotspot은 지금 구현하지 않아도 된다.

```text
MVP:
evidence.locationCode

고도화:
evidence.mapHotspot = { floor, x, y, object }
```

---

## 2. Codex 작업 목표

### 이번 작업에서 만들어야 할 것

```text
1. YAML 파싱 의존성 추가
2. YAML DTO/Record 정의
3. Scenario Importer 작성
4. Validation 작성
5. 공식 시나리오 YAML 파일 위치/로드 구조 작성
6. DB 엔티티 또는 기존 엔티티 확장
7. activeVariant 저장 구조 준비
8. 증거 조회 시 activeVariant에 따라 EvidenceVariantState 적용
9. 최종 추리 채점이 VariantSolution + ProofDimension 기준으로 동작할 수 있게 연결
10. AI 심문용 데이터 조회는 NPC Knowledge Boundary 기준으로 분리
```

### 이번 작업에서 하지 말 것

```text
1. 시나리오 내용 임의 수정 금지
2. 증거 코드 임의 변경 금지
3. 범인/정답을 AI 프롬프트에 넣기 금지
4. YAML만 읽고 DB 없이 플레이 진행 금지
5. 맵 hotspot/좌표 기능까지 무리하게 구현 금지
6. 커스텀 시나리오 업로드 UI까지 이번에 구현하지 말 것
```

---

## 3. 권장 파일 위치

### YAML 원본

```text
src/main/resources/scenarios/seowolchae.v1.yaml
```

### 구현 정본 문서

```text
docs/scenario/SEOWOLCHAE_IMPLEMENTATION_CANON_v1.1.md
docs/scenario/CODEX_YAML_IMPORT_HANDOFF_v1.md
```

### Importer 패키지 후보

기존 프로젝트 패키지 구조를 기준으로 아래 중 하나를 추천한다.

```text
com.startup.domain.scenario.importer
com.startup.domain.scenario.importer.yaml
com.startup.domain.scenario.importer.validation
```

예시:

```text
src/main/java/com/startup/domain/scenario/importer/
 ├─ ScenarioImportRunner.java
 ├─ ScenarioImportService.java
 ├─ ScenarioYamlLoader.java
 ├─ ScenarioYamlValidator.java
 ├─ ScenarioImportException.java
 └─ yaml/
     ├─ ScenarioYaml.java
     ├─ LocationYaml.java
     ├─ CharacterYaml.java
     ├─ EvidenceYaml.java
     ├─ VariantYaml.java
     ├─ VariantSolutionYaml.java
     ├─ EvidenceVariantStateYaml.java
     ├─ UnlockRuleYaml.java
     ├─ NpcKnowledgeProfileYaml.java
     └─ NpcEvidenceReactionPolicyYaml.java
```

---

## 4. build.gradle 의존성

현재 프로젝트에는 Jackson YAML 의존성이 명시되어 있지 않다. 아래 의존성 추가를 검토한다.

```gradle
implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-yaml'
```

YAML DTO는 `record` 또는 Lombok DTO로 구성하면 된다. Java 21이므로 record 사용 가능하다.

---

## 5. 엔티티 설계 방향

현재 프로젝트에 `Scenario` 엔티티는 존재하지만, Variant/Evidence/Location/Character 엔티티는 정본 구조에 맞춰 추가 또는 확장이 필요하다.

### 5-1. Scenario 확장

기존 `Scenario`에는 code/version/contentHash가 없다. 공식 YAML import를 안정적으로 하려면 추가를 권장한다.

```text
Scenario
- id
- code                 // 예: SCENARIO_SEOWOLCHAE_LAST_PRESCRIPTION
- version              // 예: 1.0.0
- title
- description
- synopsis
- difficulty
- scenarioType
- visibility
- status
- estimatedPlayTimeMinutes
- contentHash          // YAML 내용 hash
```

권장 unique:

```text
unique(code, version)
```

이미 운영 중인 컬럼 변경 부담이 크면 별도 `ScenarioContentMeta`를 둘 수도 있다. 하지만 MVP에서는 `Scenario`에 직접 추가하는 것이 단순하다.

---

### 5-2. 추가 권장 엔티티

```text
ScenarioVariant
- id
- scenarioId
- code
- culpritCharacterCode
- enabled
- weight
- sortOrder

VariantSolution
- id
- variantId
- culpritCode
- motiveSummary
- methodSummary
- coverUpSummary
- solutionText
- proofDimensionJson
- finalFeedbackJson

ScenarioCharacter
- id
- scenarioId
- code
- name
- roleLabel
- characterType       // SUSPECT / WITNESS
- culpritEligible
- publicProfile
- profileJson

ScenarioLocation
- id
- scenarioId
- code
- name
- floor
- description
- sortOrder

Evidence
- id
- scenarioId
- code
- title
- category            // SCENE / PHYSICAL / DOCUMENT / DIGITAL_LOG / TESTIMONY / MAP
- unlockPhase
- locationCode
- oneLine
- baseDetail
- imageAssetKey
- sortOrder

EvidenceVariantState
- id
- evidenceId
- variantId
- internalRole
- visibleDetailModifier
- proofTagsJson

EvidenceUnlockRule
- id
- evidenceId
- unlockType          // PHASE / ACTIVE_INVESTIGATION / HINT_FALLBACK
- unlockConditionJson

NpcKnowledgeProfile
- id
- characterId
- publicAlibi
- hiddenSelfAction
- directKnowledgeJson
- inferredKnowledgeJson
- forbiddenKnowledgeJson
- stageResponsePolicyJson

NpcEvidenceReactionPolicy
- id
- characterId
- evidenceId
- minStage
- policyText
- allowedFactsJson
- forbiddenFactsJson
- tone
- priority
```

복잡한 정책은 초기에 JSON 컬럼으로 두는 것이 빠르다.

---

## 6. PlaySession에 필요한 핵심 필드

현재 프로젝트에는 아직 명확한 play domain 엔티티가 없고 AI 쪽에 `MockPlaySessionReader` 계열이 존재한다. Variant 기반 게임을 하려면 결국 PlaySession에는 아래 정보가 필요하다.

```text
PlaySession
- id
- userId
- scenarioId
- activeVariantId
- status
- currentPhase
- hintUsed
- startedAt
- completedAt
```

핵심은 `activeVariantId`다.

증거 조회, AI 심문, 최종 추리 채점은 반드시 `playSession.activeVariantId`를 기준으로 해야 한다.

---

## 7. YAML 전체 구조 초안

`seowolchae.v1.yaml`은 아래 구조를 따른다.

```yaml
scenario:
  code: SCENARIO_SEOWOLCHAE_LAST_PRESCRIPTION
  version: "1.0.0"
  title: "서월채의 마지막 처방"
  description: "서광의료재단 VIP 별장동에서 벌어진 독살 미스터리"
  synopsis: "차민혁 이사장이 비공식 조정 회의와 만찬 이후 2층 침실에서 쓰러진다."
  genre: "폐쇄 저택 독살 미스터리"
  difficulty: NORMAL
  estimatedPlayTimeMinutes: 30
  culpritMode: RANDOM_REQUIRED
  mapModeMvp: REFERENCE_ONLY
  evidenceModeMvp: PHASE_BASED_EVIDENCE_TAB
  status: PUBLISHED
  visibility: PUBLIC
  scenarioType: OFFICIAL

locations:
  - code: LOC_DIRECTOR_SUITE
    name: "2F 이사장 침실"
    floor: "2F"
    description: "차민혁이 쓰러진 장소"
    sortOrder: 1

characters:
  - code: SUSPECT_SECRETARY
    name: "한지오"
    roleLabel: "비서실장"
    characterType: SUSPECT
    culpritEligible: true
    publicProfile: "서월채 운영 실무와 이사장 일정을 관리한 인물"

evidences:
  - code: EVIDENCE_BEDROOM_WATER_BOTTLE_AND_CUP
    title: "침실 협탁의 물병과 컵"
    category: PHYSICAL
    unlockPhase: PHASE_1_BASIC_OBJECTS
    locationCode: LOC_DIRECTOR_SUITE
    oneLine: "이사장 침실 협탁 위에 놓인 물병과 컵."
    baseDetail: "물병 뚜껑의 나사선이 미세하게 어긋나 있고, 컵 안쪽에는 둥글게 닦인 흔적이 남아 있다."
    imageAssetKey: "seowolchae/evidence/bedroom-water-bottle-cup.png"
    sortOrder: 10

variants:
  - code: VARIANT_SECRETARY
    culpritCode: SUSPECT_SECRETARY
    enabled: true
    weight: 1
    solution:
      motiveSummary: "비밀 장부와 내부 감사 책임을 뒤집어쓸 위기"
      methodSummary: "침실 물병/컵 경로"
      coverUpSummary: "침실 정리 업무와 물병 서비스 체크리스트로 위장"
      solutionText: "비서실장은 침실 물병과 컵을 이용해 차민혁의 야간 루틴을 노렸다."
      proofDimensions:
        TIME_PROOF:
          primary:
            - EVIDENCE_WEARABLE_VITAL_RAW_LOG
        METHOD_PROOF:
          primary:
            - EVIDENCE_WATER_SERVICE_CHECKLIST
            - EVIDENCE_BEDROOM_WATER_BOTTLE_AND_CUP
        ACCESS_PROOF:
          primaryCombination:
            - EVIDENCE_CARE_CALL_PANEL_LOG
            - EVIDENCE_SECOND_FLOOR_CCTV_STILL
        MOTIVE_PROOF:
          primary:
            - EVIDENCE_SECRET_LEDGER_AUDIT_NOTICE
        COVERUP_PROOF:
          secondary:
            - EVIDENCE_WATER_SERVICE_CHECKLIST

evidenceVariantStates:
  - evidenceCode: EVIDENCE_WEARABLE_VITAL_RAW_LOG
    variantCode: VARIANT_SECRETARY
    internalRole: TIME_KEY
    visibleDetailModifier: "21:18 이후 호흡수와 체온 변화가 두드러진다."
    proofTags:
      - TIME_PROOF

unlockRules:
  - evidenceCode: EVIDENCE_WEARABLE_VITAL_RAW_LOG
    unlockType: ACTIVE_INVESTIGATION
    unlockCondition:
      requiredPhase: PHASE_4_KILLING_BLOW
      requiredEvidenceCodes:
        - EVIDENCE_CARE_STATION_ACCESS_LOG
        - EVIDENCE_CARE_MANAGER_WHISTLEBLOWER_DRAFT
      requiredNpcCode: WITNESS_CARE_MANAGER
      hintFallbackAllowed: true

npcKnowledgeProfiles:
  - characterCode: SUSPECT_SECRETARY
    publicAlibi: "만찬 이후 침실 정리와 회의 자료 정리를 했다고 주장한다."
    hiddenSelfAction: "침실 물병과 컵, 체크리스트를 만진 사실을 축소한다."
    directKnowledge:
      - "본인이 21:07 전후 2층 침실 근처에 올라간 사실"
      - "진료실 쪽에서 금속 서랍 닫히는 소리를 들은 사실"
    inferredKnowledge:
      - "누군가 다른 사람도 2층에 있었을 수 있다고 추정한다."
    forbiddenKnowledge:
      - "본인이 현재 Variant의 범인인지 여부"
      - "다른 인물이 실제로 무엇을 조작했는지"
```

---

## 8. Enum 기준

### EvidenceCategory

```text
SCENE
PHYSICAL
DOCUMENT
DIGITAL_LOG
TESTIMONY
MAP
```

### UnlockPhase

```text
PHASE_0_OPENING
PHASE_1_BASIC_OBJECTS
PHASE_2_SYSTEM_LOGS
PHASE_3_MOTIVE_AND_CONTRADICTION
PHASE_4_KILLING_BLOW
```

UI에는 내부 enum을 그대로 노출하지 않는다.

```text
PHASE_0_OPENING → 초기 현장 자료
PHASE_1_BASIC_OBJECTS → 현장 물건 증거
PHASE_2_SYSTEM_LOGS → 시스템 기록
PHASE_3_MOTIVE_AND_CONTRADICTION → 관계/동기 문서
PHASE_4_KILLING_BLOW → 결정적 기록
```

### EvidenceRole

```text
COMMON
KEY
TIME_KEY
METHOD_KEY
ACCESS_KEY
MOTIVE_KEY
COVERUP_KEY
SUPPORT_KEY
SUPPORT
DIRTY_FAKE
EXCLUSION
```

### ProofDimension

```text
TIME_PROOF
METHOD_PROOF
ACCESS_PROOF
MOTIVE_PROOF
COVERUP_PROOF
```

### CharacterType

```text
SUSPECT
WITNESS
```

### CulpritMode

```text
FIXED
RANDOM_REQUIRED
```

---

## 9. Importer 동작 방식

### 9-1. 서버 시작 시 import

`ScenarioImportRunner`를 만든다.

```text
ApplicationReadyEvent 이후
classpath:/scenarios/*.yaml 탐색
각 파일을 import
```

개발 중에는 profile로 제어할 수 있다.

```yaml
scenario:
  import:
    enabled: true
    fail-on-error: true
```

---

### 9-2. Idempotent import

같은 시나리오가 매번 중복 저장되면 안 된다.

권장 방식:

```text
scenario.code + scenario.version 조회
없으면 신규 import
있고 contentHash 같으면 skip
있고 contentHash 다르면 fail
```

개발 중 force 옵션을 둘 수 있다.

```yaml
scenario:
  import:
    force-update: false
```

MVP에서는 `same code + version + different hash`이면 fail 시키는 것이 안전하다. 수정이 필요하면 version을 올린다.

---

### 9-3. Import 순서

```text
1. Scenario 저장
2. Locations 저장
3. Characters 저장
4. Evidences 저장
5. Variants 저장
6. VariantSolutions 저장
7. EvidenceVariantStates 저장
8. UnlockRules 저장
9. NpcKnowledgeProfiles 저장
10. NpcEvidenceReactionPolicies 저장
```

서로 참조하는 code를 먼저 map으로 구성한다.

```text
Map<String, Location>
Map<String, ScenarioCharacter>
Map<String, Evidence>
Map<String, ScenarioVariant>
```

---

## 10. Validation 규칙

Importer는 YAML을 저장하기 전에 반드시 검증한다.

필수 검증:

```text
1. scenario.code 필수
2. scenario.version 필수
3. scenario.code + version 중복 정책 확인
4. location.code 중복 금지
5. character.code 중복 금지
6. evidence.code 중복 금지
7. variant.code 중복 금지
8. evidence.locationCode가 locations에 존재해야 함
9. variant.culpritCode가 characters에 존재해야 함
10. variant.culpritCode는 culpritEligible=true여야 함
11. neutral witness는 culprit로 지정되면 안 됨
12. solution.proofDimensions의 evidenceCode가 evidences에 존재해야 함
13. unlockRule.requiredEvidenceCodes가 evidences에 존재해야 함
14. evidenceVariantState.evidenceCode가 evidences에 존재해야 함
15. evidenceVariantState.variantCode가 variants에 존재해야 함
16. npcKnowledgeProfile.characterCode가 characters에 존재해야 함
17. npcEvidenceReactionPolicy.evidenceCode가 evidences에 존재해야 함
18. active fallback Variant가 존재해야 함
19. VARIANT_SECRETARY가 fallback으로 존재해야 함
20. EVIDENCE_WEARABLE_VITAL_RAW_LOG는 unlockRule이 반드시 있어야 함
```

Validation 실패 시 서버 시작을 fail 시키는 것이 좋다. 공식 시나리오 YAML 오류는 조용히 무시하면 안 된다.

---

## 11. 증거 조회 로직

API가 증거를 반환할 때는 아래 과정을 거친다.

```text
1. playSession 조회
2. scenarioId 조회
3. activeVariantId 조회
4. Evidence 목록 조회
5. 현재 phase / unlock 상태 기준으로 공개 가능 증거 필터링
6. EvidenceVariantState가 있으면 activeVariant 기준 modifier 적용
7. EvidenceResponse 반환
```

응답 예시:

```json
{
  "code": "EVIDENCE_WEARABLE_VITAL_RAW_LOG",
  "title": "웨어러블 바이탈 원시 데이터",
  "category": "DIGITAL_LOG",
  "locationCode": "LOC_CARE_STATION",
  "phase": "PHASE_4_KILLING_BLOW",
  "oneLine": "이사장의 착용형 기기에서 추출된 원시 생체 로그.",
  "detail": "20:58, 21:18, 21:29 지점에서 생체 신호 변화가 확인된다. 21:18 이후 호흡수와 체온 변화가 두드러진다.",
  "imageAssetKey": "seowolchae/evidence/wearable-vital-raw-log.png",
  "isUnlocked": true
}
```

주의:

```text
EvidenceVariantState의 internalRole은 클라이언트에 노출하지 않는다.
```

---

## 12. AI 심문 로직

AI 심문은 정답 DB를 직접 넘기면 안 된다.

### 12-1. 기본 프롬프트 데이터

```text
Scenario의 기본 분위기
NPC characterProfile
NPC publicAlibi
NPC hiddenSelfAction
NPC directKnowledge
NPC inferredKnowledge
NPC forbiddenKnowledge
Stage response rule
최근 대화 기록
```

### 12-2. 증거 제시 시 추가 데이터

유저가 evidenceCode를 제시했을 때만 아래를 추가한다.

```text
NpcEvidenceReactionPolicy(characterCode, evidenceCode)
```

절대 추가 금지:

```text
VariantSolution
culpritCode
proofDimensionJson
evidenceRoleByVariant 전체
```

---

## 13. 최종 추리 채점

최종 제출 UI는 아래 입력을 받는다.

```text
selectedCulpritCode
selectedEvidenceCodes[3]
motiveText
methodText
coverUpText
```

채점은 `playSession.activeVariantId`의 `VariantSolution` 기준으로 한다.

### 13-1. 점수 구조

```text
범인 선택: 30
핵심 증거 선택: 20
범행 방법 설명: 20
동기 설명: 15
은폐/알리바이 설명: 10
논리 일관성: 5
```

### 13-2. 증거 채점은 proof dimension 기반

단순히 `selectedEvidenceCodes`가 `keyEvidenceIds`와 일치하는지 보지 않는다.

```text
TIME_PROOF
METHOD_PROOF
ACCESS_PROOF
MOTIVE_PROOF
COVERUP_PROOF
```

각 selected evidence가 어떤 proof dimension을 채우는지 본다.

`primaryCombination`이 있으면 조합형 채점한다.

예:

```yaml
ACCESS_PROOF:
  primaryCombination:
    - EVIDENCE_CARE_CALL_PANEL_LOG
    - EVIDENCE_SECOND_FLOOR_CCTV_STILL
```

둘 다 있으면 access 만점, 하나만 있으면 부분점수.

---

## 14. MVP 범위 정리

### 해야 함

```text
1. YAML 파일 import
2. Scenario / Evidence / Variant / Solution 저장
3. PlaySession activeVariant 저장
4. Phase 기반 증거 공개
5. EvidenceVariantState modifier 적용
6. AI 프롬프트용 NPC Knowledge 조회
7. 최종 추리 VariantSolution 기반 채점
```

### 이번 MVP에서 안 해도 됨

```text
1. 커스텀 시나리오 업로드 UI
2. 맵 hotspot 좌표
3. 맵 클릭으로 증거 해금
4. 이미지 자동 생성/관리
5. YAML schema editor
6. 관리자 CMS
```

---

## 15. Codex 구현 순서 추천

### Step 1. 의존성 추가

```gradle
implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-yaml'
```

### Step 2. YAML DTO 작성

```text
ScenarioYaml
LocationYaml
CharacterYaml
EvidenceYaml
VariantYaml
VariantSolutionYaml
EvidenceVariantStateYaml
UnlockRuleYaml
NpcKnowledgeProfileYaml
NpcEvidenceReactionPolicyYaml
```

### Step 3. 엔티티/Repository 작성

```text
ScenarioVariant
VariantSolution
ScenarioCharacter
ScenarioLocation
Evidence
EvidenceVariantState
EvidenceUnlockRule
NpcKnowledgeProfile
NpcEvidenceReactionPolicy
```

기존 `Scenario`에는 code/version/contentHash 추가를 검토한다.

### Step 4. Importer 작성

```text
ScenarioYamlLoader
ScenarioYamlValidator
ScenarioImportService
ScenarioImportRunner
```

### Step 5. 테스트 작성

최소 테스트:

```text
1. seowolchae.v1.yaml 파싱 성공
2. 중복 code 검증 실패
3. 없는 locationCode 참조 시 실패
4. culpritEligible=false 캐릭터가 culprit이면 실패
5. 같은 code/version/hash는 skip
6. 같은 code/version/different hash는 fail
7. activeVariant 선택 가능
8. EvidenceVariantState modifier 적용 가능
9. ProofDimension 기반 채점 가능
```

### Step 6. 기존 Mock Reader 대체 계획

현재 AI 도메인에는 `MockEvidenceReader`, `MockSolutionReader`, `MockSuspectReader`, `MockScenarioDataReader`, `MockPlaySessionReader` 계열이 존재한다.

Importer 구현 후에는 아래 방향으로 교체한다.

```text
MockEvidenceReader
→ DbEvidenceReader

MockSolutionReader
→ DbVariantSolutionReader

MockSuspectReader
→ DbScenarioCharacterReader

MockScenarioDataReader
→ DbScenarioDataReader

MockPlaySessionReader
→ DbPlaySessionReader
```

단, 한 번에 다 바꾸기 어렵다면 Mock은 유지하고, DB Reader를 새로 만든 뒤 Profile 또는 Bean 우선순위로 교체한다.

---

## 16. 인수인계 핵심 요약

Codex는 아래 원칙으로 구현하면 된다.

```text
1. YAML은 콘텐츠 원본이다.
2. 런타임은 DB 기준이다.
3. Scenario는 Variant N개를 가진다.
4. PlaySession은 activeVariant를 가진다.
5. AI NPC는 activeVariant를 모른다.
6. Evidence는 기본적으로 공통이고, 결정타 5개만 activeVariant별 modifier가 가능하다.
7. 최종 채점은 evidence ID 단순 일치가 아니라 proof dimension 기반이다.
8. MVP에서 맵은 참고용이고, locationCode만 저장하면 된다.
9. 커스텀 시나리오 업로드는 후속 고도화다.
10. 공식 시나리오 추가는 YAML 파일 추가로 가능해야 한다.
```

---

## 17. 필수 참고 문서

Codex가 구현 전에 반드시 봐야 할 문서:

```text
.private/scenarios/seowolchae/SEOWOLCHAE_IMPLEMENTATION_CANON_v1.1.md
```

이 문서에는 canonical code, Variant Truth Table, EvidenceRoleMatrix, ProofDimension, AI 지식 경계가 고정되어 있다.

Working Brief 계열 문서는 히스토리 참고용이다.

```text
CLUEROOM_SCENARIO_WORKING_BRIEF_v20.md
```

정본은 아니다.

---

## 18. 2026-05-28 추가 결정사항

### 18-1. 현재 구현 보류 기준

현재 단계에서는 실제 YAML import 구현을 바로 진행하지 않는다.

이유:

```text
1. 기존 백엔드에는 ScenarioVariant, VariantSolution, EvidenceVariantState, PlaySession.activeVariant 구조가 아직 없다.
2. 서월채 YAML에는 culpritCode, VariantSolution, ProofDimension, NPC hidden truth가 포함되므로 public repo에 커밋하면 안 된다.
3. 현재 DB 구조에 억지로 맞추면 Variant / ProofDimension / NPC 지식 경계를 나중에 다시 갈아엎을 가능성이 크다.
```

따라서 지금은 설계 기준만 고정하고, 실제 import 구현은 백엔드 엔티티 구조가 준비된 뒤 재개한다.

### 18-2. 데이터 관리 원칙

```text
YAML = 시나리오 원본 데이터
DB = 실제 런타임 저장소
Importer = YAML을 검증해서 DB 엔티티로 변환하는 계층
```

게임 플레이 중 매 요청마다 YAML 파일을 직접 읽지 않는다.

올바른 흐름:

```text
private YAML
  ↓
Importer validation
  ↓
DB 저장
  ↓
런타임 API는 DB 기준으로 조회
```

정답/Variant가 들어간 YAML은 public repository에 올리지 않는다.

public repository에 둘 수 있는 것:

```text
- importer 코드
- YAML DTO / Loader / Validator
- spoiler-free sample YAML
- schema 문서
- AI prompt boundary 문서
```

public repository에 두면 안 되는 것:

```text
- seowolchae.v1.yaml 실제 정답본
- culpritCode
- VariantSolution
- ProofDimension 정답 매핑
- fullTruthTimeline
- NPC hidden truth
- evidenceRoleByVariant 전체
```

### 18-3. private 데이터 위치

로컬 개발 기준:

```text
.private/scenarios/seowolchae/
```

현재 보관 대상:

```text
SEOWOLCHAE_IMPLEMENTATION_CANON_v1.1.md
CLUEROOM_SCENARIO_WORKING_BRIEF_v20.md
CODEX_YAML_IMPORT_HANDOFF_v1.md
images/
```

추후 실제 YAML과 asset map도 아래에 둔다.

```text
.private/scenarios/seowolchae/seowolchae.v1.yaml
.private/scenarios/seowolchae/asset-map.csv
```

운영 서버에서는 Git repo가 아니라 별도 private path로 전달한다.

예:

```text
CLUEROOM_SCENARIO_IMPORT_PATH=/opt/clueroom/private-scenarios
```

서버 배포 흐름:

```text
git pull
→ 코드 / importer 최신화

private YAML 별도 업로드
→ importer 실행
→ DB 저장
```

즉, 정답 포함 시나리오 데이터는 `.env`, Firebase service account, secret env 파일처럼 secret에 준해 관리한다.

### 18-4. 구현 재개 조건

아래 구조가 준비되면 YAML import 작업을 재개한다.

```text
Scenario.code / version / contentHash
ScenarioVariant
VariantSolution
EvidenceVariantState
EvidenceUnlockRule
NpcKnowledgeProfile 또는 prompt-safe policy 저장 구조
PlaySession.activeVariantId 또는 activeVariantCode
Evidence image asset key / S3 object key 저장 정책
```

현재 있는 `Scenario`, `Suspect`, `Evidence`, `ScenarioLocation`, `Victim`, `PlaySession`은 최대한 재사용한다.

다만 서월채의 Variant 구조를 위해 아래 신규/확장은 필요하다.

```text
ScenarioVariant
VariantSolution
EvidenceVariantState
PlaySession.activeVariant
ProofDimension 기반 scoring 저장 구조
NPC prompt-safe knowledge / reaction policy 저장 구조
```

### 18-5. 구현 재개 시 작업 순서

추후 Codex는 아래 순서로 재개한다.

```text
1. 소스 문서 정리
   - Canon v1.1을 구현 기준으로 확정
   - Working Brief는 히스토리/이미지 보조로만 사용
   - Handoff는 Importer 구현 지시서로 사용

2. Canonical code freeze
   - scenario / variant / character / location / evidence / assetKey 코드 확정
   - 이후 코드명 변경 금지

3. YAML 스키마 설계
   - metadata, scenario, victim, locations, characters, evidences, evidenceVariantStates, variants, unlockRules, npcPolicies, scoring, assets 구획 확정
   - 파싱 가능한 데이터와 설명 문장 분리
   - 공통 schema 기준은 `docs/scenarios/SCENARIO_YAML_SCHEMA.md`

4. 이미지 asset mapping 작성
   - 로컬 한글 파일명과 S3 object key 매핑
   - YAML에는 Windows path가 아니라 assetKey 또는 s3ObjectKey만 저장

5. seowolchae.v1.yaml 초안 작성
   - 1차: scenario / victim / locations / characters / evidences / assets
   - 2차: variants / solutions / proofDimensions
   - 3차: unlockRules / npcPolicies / scoring

6. YAML DTO / Loader / Validator 작성
   - DB 저장 전 파싱/검증부터 구현
   - code 중복, 참조 오류, culpritEligible 검증

7. DB 구조 확장
   - 기존 엔티티 재사용 우선
   - Variant / Solution / EvidenceVariantState / activeVariant 구조 추가

8. ImportService / Runner 작성
   - YAML → Entity 변환
   - contentHash 기반 idempotent import
   - 같은 version 다른 hash면 실패

9. 런타임 연결
   - PlaySession 시작 시 activeVariant 선택
   - Evidence 조회 시 activeVariant modifier 적용
   - AI 심문은 prompt-safe 데이터만 조회
   - FinalDeduction은 VariantSolution + ProofDimension 기준 채점

10. 검증
   - YAML parsing test
   - validation test
   - importer idempotent test
   - evidence response test
   - AI prompt safety test
   - final deduction scoring test
```

### 18-6. AI 프롬프트 안전 원칙

AI NPC 프롬프트에는 아래 정보를 넣지 않는다.

```text
activeVariant
culpritCode
solutionText
variantSolution
fullTruthTimeline
evidenceRoleByVariant 전체
ProofDimension 정답 매핑
```

AI에게 전달 가능한 정보는 prompt-safe 데이터로 제한한다.

```text
character public profile
public alibi
본인의 숨김 행동 중 현재 stage에서 허용된 응답 정책
directKnowledge
inferredKnowledge
forbiddenKnowledge
사용자가 제시한 evidenceCode에 연결된 reaction policy
ResponsePolicyResolver가 선택한 policyText / allowedFacts / forbiddenFacts / tone
```

YAML에는 truth layer가 들어갈 수 있지만, Prompt Builder는 반드시 prompt-safe layer만 사용해야 한다.
