# STUDIO9_IMPLEMENTATION_CANON_v1

> 구현 정본 문서  
> 시나리오명: **스튜디오 9의 마지막 컷**  
> 기준 Working Brief: `CLUEROOM_SCENARIO2_WORKING_BRIEF_v15`  
> 목적: Working Brief의 히스토리/패치 로그를 제거하고, 백엔드 DB/YAML/Android/AI 프롬프트/채점 로직에서 사용할 **구현 기준 데이터**를 고정한다.  
> 상태: `CANON_DRAFT_FOR_IMPLEMENTATION`  
> 핵심 원칙: 이 문서는 시나리오를 더 창작하기 위한 문서가 아니라, 구현팀이 데이터 구조로 옮기기 위한 정본이다.

---

## 0. 구현 기준 선언

### 0-1. 이 문서가 우선한다

```text
구현 기준:
STUDIO9_IMPLEMENTATION_CANON_v1.md

참고용:
CLUEROOM_SCENARIO2_WORKING_BRIEF_v1~v15
```

Working Brief와 Canon이 충돌하면 **Canon을 우선**한다.

### 0-2. 폐기된 구조

아래 구조는 더 이상 정본이 아니다.

```text
EVIDENCE_RAW_TIMECODE_DELTA_TABLE
→ 최종 범인 특정용 마스터키로 사용 금지
```

타임코드/장비 로그류는 보조 배경으로만 허용한다.

```text
허용:
- 촬영 현장 기록 보조
- B-roll / 현장 사진의 촬영 시점 보조
- 공식본과 원본 자료가 다르다는 분위기 보조

금지:
- “0.5초 먼저 움직인 장비 채널 = 범인” 식의 정답 구조
- 서월채의 바이탈 로그와 동일한 로그 해독형 마스터키 구조
```

### 0-3. 스튜디오 9의 정체성

```text
서월채:
바이탈 로그 / 생물학적 시간표 / 섭취 경로 추리

스튜디오 9:
현장 물증 / 물리적 선후관계 / 시각 자료 판독 추리
```

스튜디오 9의 플레이 감각은 **로그 해독**이 아니라 **현장 감식**이다.

---

## 1. 시나리오 메타데이터

```yaml
scenario:
  code: SCENARIO_STUDIO9
  title: "스튜디오 9의 마지막 컷"
  genre: "촬영장 미스터리 / 산업재해 위장극 / 미디어 현장 감식 추리"
  difficulty: NORMAL_PLUS
  estimatedPlayMinutes: 30
  culpritMode: RANDOM_VARIANT
  suspectCount: 7
  culpritEligibleCount: 4
  permanentRedHerringCount: 2
  neutralWitnessCount: 1
  evidenceCount: 35
  deductionMode: PHYSICAL_EVIDENCE_DEDUCTION
```

### 한 줄 소개

```text
모두가 보고 있었지만 아무도 보지 못한 3.4초의 과노출 속에서,
천재 쇼러너 백도윤은 자신이 만든 마지막 컷에 삼켜졌다.
```

### 사건 요약

OTT 드라마 `<스튜디오 9>`의 마지막 컷 테크 리허설 도중, 총괄 감독 백도윤이 루프탑 세트에서 추락사한다.  
표면상으로는 조명 과노출과 세트 안전 문제로 인한 촬영장 사고처럼 보이지만, 현장에는 누군가 사전에 조작한 물리적 흔적들이 남아 있다.

### 핵심 사건 장치

```text
Master Trigger:
백도윤이 직접 “Roll. Cue 9.”라고 말한다.

Cue 9:
마지막 컷 조명/카메라/프롬프터/안전 확인 루틴을 동시에 시작하는 현장 큐.

3.4초 과노출:
Cue 9 직후 강한 백라이트로 인해 메인 화면과 현장 시야가 하얗게 날아간 구간.
```

중요:

```text
이 시나리오는 “누가 그 시간에 움직였나”가 아니라,
“어떤 물리적 층위가 실제 치명 원인이었나”를 맞히는 사건이다.
```

---

## 2. Canonical Enum

### 2-1. VariantCode

```yaml
VariantCode:
  - VARIANT_ACTOR
  - VARIANT_WRITER
  - VARIANT_LIGHTING_DP
  - VARIANT_STUNT
```

### 2-2. CharacterType

```yaml
CharacterType:
  - CULPRIT_ELIGIBLE
  - PERMANENT_RED_HERRING
  - NEUTRAL_WITNESS
```

### 2-3. EvidenceCategory

```yaml
EvidenceCategory:
  - SCENE
  - PHYSICAL
  - DOCUMENT
  - AUDIO
  - VISUAL
  - MAP
```

### 2-4. UnlockPhase

```yaml
UnlockPhase:
  PHASE_0_OPENING: "초기 현장 자료"
  PHASE_1_BASIC_SCENE: "현장 기본 단서"
  PHASE_2_PHYSICAL_TRACE: "물리 흔적"
  PHASE_3_CONTRADICTION_AND_MOTIVE: "모순/동기 자료"
  PHASE_4_DECISIVE_PHYSICAL_PROOF: "결정적 물리 증거"
```

UI에는 내부 enum명을 그대로 노출하지 않는다.

### 2-5. Internal Evidence Role

```yaml
EvidenceRole:
  - COMMON
  - FATAL_LAYER_KEY
  - FATAL_LAYER_SUPPORT
  - FAKE_REFUTATION_KEY
  - MOTIVE_KEY
  - DIRTY_FAKE
  - UNLOCK_KEY
  - CONTEXT
```

### 2-6. ProofDimension

```yaml
ProofDimension:
  CULPRIT:
    description: "백도윤을 죽인 진짜 조작자는 누구인가?"

  FATAL_LAYER:
    description: "추락의 직접 원인이 된 물리적 조작은 무엇인가?"

  FAKE_REFUTATION:
    description: "유력한 가짜 흉기가 왜 직접 치명 경로가 아닌가?"

  MOTIVE:
    description: "진범이 백도윤을 죽일 동기는 무엇인가?"
```

---

## 3. 장소 정본

```yaml
locations:
  - code: LOC_ROOFTOP_SET
    name: "루프탑 세트"
    description: "마지막 컷 리허설이 진행된 주 무대. MARK 9, 난간, 안전장치, 조명 직격축이 얽힌다."

  - code: LOC_MONITOR_ZONE
    name: "모니터 존"
    description: "배우와 제작진이 리허설 화면을 확인하던 구역."

  - code: LOC_PROMPTER_STATION
    name: "프롬프터 / 인이어 큐 스테이션"
    description: "프롬프터 문구와 인이어 큐를 관리하는 구역."

  - code: LOC_LIGHTING_CONSOLE
    name: "조명 콘솔 / 촬영 모니터 구역"
    description: "Cue 9 백라이트와 카메라 노출을 조정하는 구역."

  - code: LOC_RIGGING_ZONE
    name: "안전 리깅 구역"
    description: "하네스, 난간 보조 잠금장치, 안전 결착 확인이 이루어지는 구역."

  - code: LOC_PRODUCTION_DESK
    name: "제작 데스크 / 외주 정산 구역"
    description: "장비 발주서, 예산 메일, 보험 문서가 남아 있는 구역."

  - code: LOC_DRESSING_ROOM_ACTOR
    name: "주연배우 대기실"
    description: "서이라의 개인 대기실. 검은 마킹 테이프 조각이 발견된다."

  - code: LOC_DRESSING_ROOM_SUPPORTING
    name: "조연배우 대기실"
    description: "민루아의 대기 공간. 신발 밑창 겔 흔적과 캐스팅 거래 메시지가 연결된다."

  - code: LOC_SCRIPT_SUPERVISOR_DESK
    name: "스크립터 기록 데스크"
    description: "고은별이 연속성 노트와 B-roll 백업 드라이브를 관리한 장소."

  - code: LOC_EQUIPMENT_STORAGE
    name: "장비 보관실"
    description: "확산 필터, 안전 클립, 예비 장비 등이 보관되는 장소."
```

---

## 4. 인물 정본

### 4-1. 피해자

```yaml
victim:
  code: VICTIM_BAEK_DOYOON
  name: "백도윤"
  roleLabel: "OTT 드라마 <스튜디오 9> 총괄 감독 / 쇼러너"
  publicImage: "천재 감독, 스타 제조기, 흥행 보증수표"
  hiddenProfile: "배우와 스태프의 약점을 작품과 권력 유지에 이용하던 통제형 창작자"
  deathSurface: "루프탑 세트 테크 리허설 중 추락사"
```

### 4-2. 심문 가능 인물 7명

| Code | Name | Role | Type | Culprit Eligible | Function |
|---|---|---|---|---|---|
| `SUSPECT_ACTOR` | 서이라 | 주연배우 | CULPRIT_ELIGIBLE | true | MARK 9 마킹 조작 Variant |
| `SUSPECT_WRITER` | 윤채린 | 메인 작가 | CULPRIT_ELIGIBLE | true | 프롬프터/인이어 지시 조작 Variant |
| `SUSPECT_LIGHTING_DP` | 류현규 | 조명감독·촬영감독 | CULPRIT_ELIGIBLE | true | 비규격 필터 / 과노출 Variant |
| `SUSPECT_STUNT_COORDINATOR` | 남기준 | 액션감독·안전 코디네이터 | CULPRIT_ELIGIBLE | true | 안전 잠금부 사전 훼손 Variant |
| `RED_HERRING_PRODUCER` | 강태오 | 제작총괄 | PERMANENT_RED_HERRING | false | 불량 클립 / 하단 안전망 Dirty Fake |
| `RED_HERRING_SUPPORTING_ACTOR` | 민루아 | 조연배우 | PERMANENT_RED_HERRING | false | 무광 겔 Dirty Fake / 첫 심문 타겟 |
| `WITNESS_SCRIPT_SUPERVISOR` | 고은별 | 스크립터·타임코드/연속성 데이터 매니저 | NEUTRAL_WITNESS | false | B-roll / 연속성 노트 해금 담당 |

### 4-3. 각 인물의 숨김 정보

```yaml
SUSPECT_ACTOR:
  publicRelation: "백도윤이 만든 톱스타"
  hiddenSecret: "백도윤이 과거 사적 관계와 해외 계약을 빌미로 서이라를 통제함"
  physicalAccess: "대기실과 리허설 동선상 MARK 9 위치 확인 가능"

SUSPECT_WRITER:
  publicRelation: "작품의 메인 작가"
  hiddenSecret: "백도윤이 과거 자신의 가족 원고/크레딧을 훔쳤다고 믿음"
  physicalAccess: "프롬프터/인이어 지시 문구와 최종 블로킹 수정에 관여 가능"

SUSPECT_LIGHTING_DP:
  publicRelation: "백도윤의 영상미를 만들어온 기술 파트너"
  hiddenSecret: "과거 조명 사고 책임을 백도윤에게 떠넘겨질 위기"
  physicalAccess: "Cue 9 조명 세팅, 확산 필터, 조명 메모에 접근 가능"

SUSPECT_STUNT_COORDINATOR:
  publicRelation: "루프탑 세트의 안전 책임자"
  hiddenSecret: "과거 촬영 사고 은폐 공범이며, 백도윤이 책임 전가를 준비 중"
  physicalAccess: "하네스, 난간 보조 잠금장치, 안전 점검표 관리 가능"

RED_HERRING_PRODUCER:
  publicRelation: "제작비와 외주 장비 계약 총괄"
  hiddenSecret: "저가 부품 교체와 장비 예산 차액 은폐"
  physicalAccess: "하단 보조 안전망 관련 부품 발주 가능"
  notCulpritReason: "하단 보조 안전망 문제는 최초 균형 붕괴의 직접 원인이 아님"

RED_HERRING_SUPPORTING_ACTOR:
  publicRelation: "백도윤이 새로 밀어주던 조연배우"
  hiddenSecret: "서이라를 방해하려고 무광 겔을 남김"
  physicalAccess: "주연배우 대기 동선 근처 접근 가능"
  notCulpritReason: "겔은 선행 페이크이며, 이후 누군가 MARK 9 테이프를 옮겨 붙인 물리 흔적이 남음"

WITNESS_SCRIPT_SUPERVISOR:
  publicRelation: "촬영 연속성, B-roll 동기화, 기록 백업 담당"
  hiddenSecret: "공식본에 없는 B-roll/연속성 자료를 개인 백업 드라이브에 숨김"
  physicalAccess: "치명 장치 조작 권한 없음"
  notCulpritReason: "범행을 만든 사람이 아니라 범행을 해석할 수 있는 자료를 숨긴 사람"
```

---

## 5. 증거 정본 테이블

### 5-1. 증거 수 정책

```yaml
evidenceCountPolicy:
  minimum: 30
  target: 35
  maximum: 38
```

### 5-2. Canonical Evidence Table

| No | Canonical Code | Title | Category | Phase | Location | Base Role |
|---:|---|---|---|---|---|---|
| 1 | `EVIDENCE_ROOFTOP_SET_SCENE_PHOTO` | 루프탑 세트 현장 사진 | SCENE | 0 | `LOC_ROOFTOP_SET` | CONTEXT |
| 2 | `EVIDENCE_MARK9_CURRENT_POSITION_PHOTO` | 사건 당시 MARK 9 위치 사진 | PHYSICAL | 0 | `LOC_ROOFTOP_SET` | CONTEXT |
| 3 | `EVIDENCE_STAGE_BLOCKING_OVERVIEW` | 루프탑 블로킹 배치도 | MAP | 0 | `LOC_ROOFTOP_SET` | CONTEXT |
| 4 | `EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST` | 백도윤의 Actor POV 체크리스트 | DOCUMENT | 0 | `LOC_ROOFTOP_SET` | FATAL_LAYER_SUPPORT |
| 5 | `EVIDENCE_MASTER_TRIGGER_AUDIO` | “Roll. Cue 9.” 인터컴 음성 | AUDIO | 1 | `LOC_PROMPTER_STATION` | COMMON |
| 6 | `EVIDENCE_OVEREXPOSED_FRAME_SEQUENCE` | 과노출 3.4초 프레임 시퀀스 | VISUAL | 1 | `LOC_MONITOR_ZONE` | COMMON |
| 7 | `EVIDENCE_SOUND_MIXER_AMBIENT_AUDIO_FRAGMENT` | 현장 앰비언트 오디오 파편 | AUDIO | 1 | `LOC_PROMPTER_STATION` | FATAL_LAYER_SUPPORT |
| 8 | `EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE` | 기존 MARK 9 접착 잔여선 | PHYSICAL | 2 | `LOC_ROOFTOP_SET` | FATAL_LAYER_SUPPORT |
| 9 | `EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE` | 검은 마스킹 라인의 무광 겔 자국 | PHYSICAL | 2 | `LOC_ROOFTOP_SET` | DIRTY_FAKE |
| 10 | `EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE` | 조연배우 신발 밑창 겔 흔적 | PHYSICAL | 2 | `LOC_DRESSING_ROOM_SUPPORTING` | DIRTY_FAKE |
| 11 | `EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT` | 저가형 안전 클립 파편 | PHYSICAL | 2 | `LOC_ROOFTOP_SET` | DIRTY_FAKE |
| 12 | `EVIDENCE_UNAPPROVED_EQUIPMENT_ORDER` | 비승인 장비 교체 발주서 | DOCUMENT | 2 | `LOC_PRODUCTION_DESK` | DIRTY_FAKE |
| 13 | `EVIDENCE_HARNESS_LOCK_CHECK_PHOTO` | 하네스 결착 확인 사진 | PHYSICAL | 2 | `LOC_RIGGING_ZONE` | FATAL_LAYER_SUPPORT |
| 14 | `EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL` | 프롬프터 반사 B-roll 스틸컷 | VISUAL | 3 | `LOC_SCRIPT_SUPERVISOR_DESK` | FATAL_LAYER_SUPPORT |
| 15 | `EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH` | 최종 블로킹 대본 불일치 | DOCUMENT | 3 | `LOC_PROMPTER_STATION` | FATAL_LAYER_SUPPORT |
| 16 | `EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO` | MAX-BLIND 손글씨 조명 메모 | DOCUMENT | 3 | `LOC_LIGHTING_CONSOLE` | FATAL_LAYER_SUPPORT |
| 17 | `EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME` | 비규격 확산 필터 프레임 | PHYSICAL | 3 | `LOC_EQUIPMENT_STORAGE` | FATAL_LAYER_SUPPORT |
| 18 | `EVIDENCE_SAFETY_LATCH_WEAR_PATTERN` | 안전 잠금부 비정상 마모 흔적 | PHYSICAL | 3 | `LOC_RIGGING_ZONE` | FATAL_LAYER_SUPPORT |
| 19 | `EVIDENCE_SAFETY_CHECKLIST_OVERWRITE` | 안전 점검표 덮어쓰기 흔적 | DOCUMENT | 3 | `LOC_RIGGING_ZONE` | FATAL_LAYER_SUPPORT |
| 20 | `EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE` | 주연배우 대기실 검은 마킹 테이프 조각 | PHYSICAL | 3 | `LOC_DRESSING_ROOM_ACTOR` | FATAL_LAYER_SUPPORT |
| 21 | `EVIDENCE_SUPPORTING_ACTOR_CASTING_DEAL_MESSAGE` | 조연배우 차기작 거래 메시지 | DOCUMENT | 3 | `LOC_DRESSING_ROOM_SUPPORTING` | MOTIVE_KEY |
| 22 | `EVIDENCE_INSURANCE_BUDGET_ADJUSTMENT_MAIL` | 보험·예산 조정 메일 | DOCUMENT | 3 | `LOC_PRODUCTION_DESK` | MOTIVE_KEY |
| 23 | `EVIDENCE_ACTOR_CONTRACT_BLACKMAIL_MESSAGE` | 주연배우 해외 계약 협박 메시지 | DOCUMENT | 3 | `LOC_DRESSING_ROOM_ACTOR` | MOTIVE_KEY |
| 24 | `EVIDENCE_OLD_MANUSCRIPT_CREDIT_FILE` | 오래된 원고와 크레딧 파일 | DOCUMENT | 3 | `LOC_PROMPTER_STATION` | MOTIVE_KEY |
| 25 | `EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT` | 조명 사고 책임 보고서 초안 | DOCUMENT | 3 | `LOC_LIGHTING_CONSOLE` | MOTIVE_KEY |
| 26 | `EVIDENCE_OLD_STUNT_ACCIDENT_FILE` | 과거 촬영 사고 은폐 파일 | DOCUMENT | 3 | `LOC_RIGGING_ZONE` | MOTIVE_KEY |
| 27 | `EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE` | 스크립터 백업 드라이브 | PHYSICAL | 3 | `LOC_SCRIPT_SUPERVISOR_DESK` | UNLOCK_KEY |
| 28 | `EVIDENCE_EDITED_MAKING_REEL_EXPORT` | 편집된 메이킹 릴 추출본 | VISUAL | 3 | `LOC_SCRIPT_SUPERVISOR_DESK` | UNLOCK_KEY |
| 29 | `EVIDENCE_CONTINUITY_SKETCH_NOTE` | 연속성 스케치 노트 | DOCUMENT | 4 | `LOC_SCRIPT_SUPERVISOR_DESK` | FATAL_LAYER_SUPPORT |
| 30 | `EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL` | 들뜬 MARK 9 테이프 끝 | PHYSICAL | 4 | `LOC_ROOFTOP_SET` | FATAL_LAYER_KEY |
| 31 | `EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL` | 확대된 프롬프터 반사 스틸 | VISUAL | 4 | `LOC_SCRIPT_SUPERVISOR_DESK` | FATAL_LAYER_KEY |
| 32 | `EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP` | 변형된 확산 필터 클로즈업 | PHYSICAL | 4 | `LOC_EQUIPMENT_STORAGE` | FATAL_LAYER_KEY |
| 33 | `EVIDENCE_SAFETY_LATCH_CLOSEUP` | 안전 잠금부 클로즈업 | PHYSICAL | 4 | `LOC_RIGGING_ZONE` | FATAL_LAYER_KEY |
| 34 | `EVIDENCE_STAGE_FLOOR_DUST_SCUFF_PATTERN` | 무대 바닥 먼지와 마찰 패턴 | PHYSICAL | 4 | `LOC_ROOFTOP_SET` | FATAL_LAYER_SUPPORT |
| 35 | `EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE` | 루프탑 드라이 런 안전 수칙 | DOCUMENT | 0 | `LOC_RIGGING_ZONE` | FATAL_LAYER_SUPPORT |

### 5-3. AssetKey 규칙

YAML/DB에는 로컬 Windows 경로를 넣지 않는다.

```yaml
assetKeyPattern: "official/studio9/v1/{category}/{canonicalCode}.png"
```

예:

```yaml
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL:
  assetKey: "official/studio9/v1/evidence/EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL.png"
```

---

## 6. EvidenceVariantState 정책

### 6-1. 카드 스폰 메타 방지

모든 핵심 증거 카드는 어떤 Variant든 무조건 등장한다.

```text
카드 존재 여부로 정답 추측 금지.
카드를 열었을 때의 상세 상태와 다른 증거와의 조합으로만 추리.
```

### 6-2. Default Evidence

대부분의 증거는 모든 Variant에서 아래가 동일하다.

```text
title
image
baseDetail
locationCode
phase
category
```

다만 activeVariant에 따라 내부 역할과 채점 매핑은 달라질 수 있다.

### 6-3. Variant State Evidence

아래 증거들은 activeVariant에 따라 `visibleDetailModifier`, `internalRole`, `proofMapping`이 달라질 수 있다.

```yaml
variantStateEvidence:
  - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
  - EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE
  - EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
  - EVIDENCE_SOUND_MIXER_AMBIENT_AUDIO_FRAGMENT
  - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
  - EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
  - EVIDENCE_SAFETY_LATCH_CLOSEUP
  - EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
```

### 6-4. 예시: `EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL`

```yaml
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL:
  VARIANT_ACTOR:
    role: FATAL_LAYER_KEY
    visibleDetailModifier: >
      테이프 한쪽 끝이 무광 겔 때문에 들떠 있으며,
      기존 접착 잔여선과 현재 MARK 9 위치가 난간 방향으로 어긋나 있다.
      이는 겔 이후 누군가 MARK 9를 옮겨 붙였다는 물리적 선후관계를 만든다.

  VARIANT_WRITER:
    role: DIRTY_FAKE
    visibleDetailModifier: >
      테이프 끝은 수상하지만, 기존 접착 잔여선과 치명 위치 이동이 완전히 닫히지 않는다.
      이 판의 결정적 물리층은 프롬프터/인이어 지시 쪽에 있다.

  VARIANT_LIGHTING_DP:
    role: DIRTY_FAKE
    visibleDetailModifier: >
      테이프 상태는 수상하지만, 백도윤의 공간 인지를 무너뜨린 직접 물리층은 조명 장치 쪽이다.

  VARIANT_STUNT:
    role: DIRTY_FAKE
    visibleDetailModifier: >
      테이프와 겔은 위험한 선행 흔적이지만, 정상이라면 안전장비가 추락을 막았어야 한다.
```

---

## 7. Variant Truth Table

### 7-1. `VARIANT_ACTOR`

```yaml
variantCode: VARIANT_ACTOR
culpritCode: SUSPECT_ACTOR
culpritName: "서이라"
fatalLayerSummary: "MARK 9 테이프 위치 조작"
directCause: "백도윤은 안전한 MARK 9라고 믿고 섰지만 실제 위치는 난간 가까이 이동되어 있었다."
motive: "해외 계약을 앞둔 서이라를 백도윤이 과거 사적 자료와 계약 폭로로 통제하려 했다."
```

#### 필수/강력 증거

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
    - EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE
  secondary:
    - EVIDENCE_MARK9_CURRENT_POSITION_PHOTO
    - EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE
    - EVIDENCE_CONTINUITY_SKETCH_NOTE

motiveEvidence:
  primary:
    - EVIDENCE_ACTOR_CONTRACT_BLACKMAIL_MESSAGE
```

#### Fake Refutation

```text
민루아의 무광 겔은 실제로 존재하지만, 새 MARK 9 테이프가 그 겔 자국 위에서 들떠 있다.
따라서 겔이 먼저 남았고, 이후 누군가 MARK 9 위치를 옮겼다는 선후관계가 생긴다.
강태오의 불량 클립은 하단 보조 안전망 문제로, 백도윤의 최초 균형 붕괴를 설명하지 못한다.
```

#### 최종 해설 5문장

```text
서이라는 백도윤이 자신을 계속 통제하려 하자 MARK 9 위치를 조작했다.
민루아의 겔은 실제로 위험했지만, 그 겔 위로 들뜬 MARK 9 테이프가 남아 있어 겔 이후의 조작이 있었음을 보여준다.
백도윤은 안전한 위치라고 믿고 섰지만, 실제 MARK 9는 난간 쪽으로 이동되어 있었다.
주연배우 대기실의 검은 마킹 테이프와 기존 접착 잔여선이 이 위치 조작을 뒷받침한다.
따라서 치명적 물리층은 미끄럼이 아니라 MARK 9 마킹 조작이다.
```

---

### 7-2. `VARIANT_WRITER`

```yaml
variantCode: VARIANT_WRITER
culpritCode: SUSPECT_WRITER
culpritName: "윤채린"
fatalLayerSummary: "프롬프터 반사 문구 + 인이어 경고음 + Actor POV 체크를 이용한 반사적 후퇴 유도"
directCause: "백도윤은 Actor POV를 확인하던 중 공식 대본에 없는 지시와 청각적 공황 자극을 받고 뒤로 물러섰다."
motive: "백도윤이 윤채린 가족의 원고/크레딧을 훔쳤고, 마지막 회에서도 윤채린의 이름을 지우려 했다."
```

#### 필수/강력 증거

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
    - EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST
    - EVIDENCE_SOUND_MIXER_AMBIENT_AUDIO_FRAGMENT
  secondary:
    - EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
    - EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH

motiveEvidence:
  primary:
    - EVIDENCE_OLD_MANUSCRIPT_CREDIT_FILE
```

#### Fake Refutation

```text
MARK 9와 무광 겔은 수상하지만, 이 판에서는 백도윤을 직접 뒤로 움직이게 만든 원인이 아니다.
B-roll과 Actor POV 체크리스트, 인이어 음향 파편이 결합되면서 프롬프터/인이어 지시가 치명 물리층으로 닫힌다.
강태오의 클립은 하단 안전망 문제라 백도윤의 반사적 후퇴를 설명하지 못한다.
```

#### 최종 해설 5문장

```text
윤채린은 백도윤이 자신의 원고와 이름을 빼앗았다고 믿고 있었다.
백도윤은 Actor POV를 확인하기 위해 배우 시야에서 프롬프터와 조명을 직접 확인하던 중이었다.
B-roll 스틸컷에는 공식 대본에 없는 후퇴 지시가 프롬프터 유리에 흐릿하게 반사되어 있다.
동시에 인이어/현장 음향 파편에는 백도윤이 반사적으로 움직일 수밖에 없는 청각 자극이 남아 있다.
따라서 치명적 물리층은 대본 그 자체가 아니라, 프롬프터와 인이어를 통한 시청각적 공황 유도다.
```

---

### 7-3. `VARIANT_LIGHTING_DP`

```yaml
variantCode: VARIANT_LIGHTING_DP
culpritCode: SUSPECT_LIGHTING_DP
culpritName: "류현규"
fatalLayerSummary: "비규격 확산 필터와 MAX-BLIND 세팅을 이용한 플래시성 과노출"
directCause: "Cue 9 백라이트가 정상 연출을 넘어 백도윤의 시야와 공간 인지를 무너뜨렸다."
motive: "백도윤은 과거 조명 사고 책임을 류현규에게 전가하려 했다."
```

#### 필수/강력 증거

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
    - EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
    - EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO
  secondary:
    - EVIDENCE_OVEREXPOSED_FRAME_SEQUENCE
    - EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT

motiveEvidence:
  primary:
    - EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT
```

#### Fake Refutation

```text
MARK 9, 프롬프터, 안전 잠금부 모두 수상하지만 이 판에서 결정적 상태로 닫히지 않는다.
비규격 필터와 MAX-BLIND 메모, 변형된 확산 필터가 결합되어 조명이 치명 물리층이 된다.
민루아의 겔과 강태오의 클립은 각각 동선 방해와 하단 안전망 문제일 뿐, 3.4초 과노출 자체를 만들지 못한다.
```

#### 최종 해설 5문장

```text
류현규는 백도윤에게 조명 사고 책임을 뒤집어쓸 위기에 있었다.
사건 당일 Cue 9에는 표준 필터 대신 겉보기에는 유사한 비규격 확산 필터가 끼워져 있었다.
MAX-BLIND 메모와 변형된 필터 클로즈업은 이 과노출이 우연한 장비 고장이 아니라 의도된 세팅이었음을 보여준다.
백도윤은 3.4초 동안 시야와 공간 인지를 잃었고, 그 틈에 균형을 무너뜨렸다.
따라서 치명적 물리층은 조명의 플래시성 과노출이다.
```

---

### 7-4. `VARIANT_STUNT`

```yaml
variantCode: VARIANT_STUNT
culpritCode: SUSPECT_STUNT_COORDINATOR
culpritName: "남기준"
fatalLayerSummary: "겉으로 정상 결착된 안전장비의 내부 하중 지지부 사전 손상"
directCause: "백도윤이 균형을 잃었을 때 안전장비가 잡아주어야 했지만, 내부 손상 때문에 버티지 못했다."
motive: "과거 촬영 사고 은폐 책임을 백도윤이 남기준에게 다시 전가하려 했다."
```

#### 필수/강력 증거

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_SAFETY_LATCH_CLOSEUP
    - EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
    - EVIDENCE_HARNESS_LOCK_CHECK_PHOTO
  secondary:
    - EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE
    - EVIDENCE_SAFETY_CHECKLIST_OVERWRITE
    - EVIDENCE_OLD_STUNT_ACCIDENT_FILE

motiveEvidence:
  primary:
    - EVIDENCE_OLD_STUNT_ACCIDENT_FILE
```

#### Fake Refutation

```text
강태오의 저가 클립은 하단 보조 안전망 부품이며, 백도윤이 직접 결착한 주 안전 잠금부와 다른 계층이다.
민루아의 겔, 조명 과노출, 프롬프터 지시는 모두 위험한 조건을 만들 수 있지만, 정상적인 안전장비라면 추락을 막았어야 한다.
이 판의 결정타는 안전장비 내부 지지부가 사건 전 이미 비정상적으로 손상되어 있었다는 점이다.
```

#### 최종 해설 5문장

```text
남기준은 과거 촬영 사고 은폐의 책임을 다시 뒤집어쓸 위기에 놓여 있었다.
루프탑 드라이 런 안전 수칙상 백도윤은 짧은 앵글 확인 중에도 안전 결착을 해야 했다.
하네스 결착 사진은 겉보기에는 정상처럼 보이지만, 잠금부 클로즈업과 마모 흔적은 내부 지지부의 사전 손상을 가리킨다.
강태오의 불량 클립은 하단 보조 안전망 문제일 뿐, 백도윤의 주 안전장비가 실패한 이유를 설명하지 못한다.
따라서 치명적 물리층은 안전 잠금부의 사전 훼손이다.
```

---

## 8. 고정 비범인 / 중립 오답 처리

### 8-1. `RED_HERRING_SUPPORTING_ACTOR` 민루아

```yaml
maxScoreCapIfSelectedAsCulprit: 40
```

오답 피드백:

```text
민루아는 무광 겔을 남긴 위험한 인물이 맞습니다.
하지만 겔은 백도윤의 최초 균형 붕괴 지점과 완전히 일치하지 않았고,
오히려 그 겔 위로 MARK 9 테이프 끝이 들뜬 흔적이 남아 있었습니다.
이는 민루아가 겔을 남긴 이후, 다른 누군가가 MARK 9 위치를 다시 조작했다는 뜻입니다.
그녀는 악의적인 방해자였지만, 백도윤을 그 위치에 세운 진범은 아닙니다.
```

### 8-2. `RED_HERRING_PRODUCER` 강태오

```yaml
maxScoreCapIfSelectedAsCulprit: 40
```

오답 피드백:

```text
강태오가 저가형 안전 클립으로 하단 보조 안전망을 위험하게 만든 것은 사실입니다.
하지만 하단 안전망은 백도윤이 처음 균형을 잃는 순간을 만든 장치가 아닙니다.
이번 사건의 직접 치명층은 위치 표시, 프롬프터/인이어, 조명 세팅, 또는 백도윤이 몸에 결착한 주 안전장치 중 하나였습니다.
강태오는 범죄에 가까운 은폐와 횡령을 했지만, 백도윤의 마지막 움직임을 설계한 사람은 아닙니다.
```

### 8-3. `WITNESS_SCRIPT_SUPERVISOR` 고은별

```yaml
maxScoreCapIfSelectedAsCulprit: 35
```

오답 피드백:

```text
고은별은 사건의 원본 자료를 숨긴 수상한 인물입니다.
하지만 그녀가 MARK 9 테이프, 프롬프터 지시, 조명 필터, 안전 잠금부 중 어느 하나를 직접 조작했다는 증거는 없습니다.
그녀가 숨긴 것은 범행 그 자체가 아니라, 범행을 해석할 수 있는 원본 자료였습니다.
```

---

## 9. 최종 추리 / 채점 정책

### 9-1. 추천 제출 UI

```yaml
finalDeductionSubmission:
  culpritCode: required
  selectedEvidenceCodes:
    recommendedCount: 4
    fallbackCount: 3
  fatalLayerExplanation: required
  fakeRefutationExplanation: required
  motiveExplanation: required
```

### 9-2. 4증거 슬롯 추천 이유

```text
1. 치명 수법 증거
2. 치명 수법 보조 증거
3. 페이크 기각 증거
4. 동기 증거
```

### 9-3. 3증거 슬롯 fallback

UI가 3개 증거 선택으로 고정되어 있으면:

```yaml
fallbackIfEvidenceSlotIsThree:
  selectedEvidenceCount: 3
  required:
    - fatalLayerEvidenceAtLeastOne
    - motiveEvidenceAtLeastOne
    - fakeRefutationEvidenceOrTextRequired
```

즉, 3개 슬롯일 경우 Fake Refutation은 서술 점수로 보완한다.

### 9-4. 점수 배분

```yaml
scoring:
  total: 100

  culpritSelection:
    points: 30

  fatalLayerProof:
    points: 30

  fakeRefutationProof:
    points: 20

  motiveProof:
    points: 15

  reasoningConsistency:
    points: 5
```

### 9-5. 점수 상한 정책

```yaml
scoreCaps:
  ifCulpritWrong:
    maxScore: 45

  ifCulpritWrongButFatalLayerCorrect:
    maxScore: 55

  ifCulpritCorrectButFatalLayerWrong:
    maxScore: 70

  ifCulpritCorrectAndFatalLayerCorrectButNoFakeRefutation:
    maxScore: 80

  ifPermanentRedHerringSelected:
    maxScore: 40

  ifNeutralWitnessSelected:
    maxScore: 35
```

### 9-6. Fake Refutation 부분점수

진범을 틀려도 Fake Refutation은 일부 인정한다.

```yaml
ifCulpritWrong:
  fakeRefutationProofMax: 12
```

단, 진범을 틀렸으면 고득점 랭크는 제한된다.

### 9-7. 랭크 정책

```yaml
rankPolicy:
  S:
    minScore: 90
    required:
      - culpritCorrect
      - fatalLayerCorrect
      - fakeRefutationAtLeastPartial
      - motiveAtLeastPartial

  A:
    minScore: 80
    required:
      - culpritCorrect
      - fatalLayerCorrect

  B:
    minScore: 65
    required:
      - culpritCorrect

  C:
    minScore: 45
    required:
      - atLeastOneCoreDimensionCorrect

  D:
    maxScore: 44
```

---

## 10. AI NPC 지식 경계

### 10-1. AI에게 절대 주면 안 되는 정보

```yaml
forbiddenAiPromptData:
  - activeVariant
  - culpritCode
  - variantSolution
  - fullTruthTable
  - evidenceRoleByVariant 전체
  - finalScoringAnswer
  - finalExplanation
  - 다른 NPC의 hiddenAction 전체
```

### 10-2. AI에게 줄 수 있는 정보

```yaml
allowedAiPromptData:
  - characterProfile
  - publicAlibi
  - hiddenSelfAction
  - directKnowledge
  - inferredKnowledge
  - forbiddenKnowledge
  - currentPresentedEvidence
  - evidenceReactionPolicy for current evidence only
  - stageResponsePolicy
```

### 10-3. DIRECT / INFERRED 규칙

```yaml
DIRECT:
  description: "직접 본 것, 직접 들은 것, 직접 한 것"
  allowedSpeech: "확실히 말할 수 있음"

INFERRED:
  description: "정황상 추정하지만 직접 확인하지 못한 것"
  requiredSpeechMarkers:
    - "확실하진 않지만"
    - "제가 본 건 아닙니다"
    - "소리만 들었습니다"
    - "그렇게 느꼈을 뿐입니다"
    - "단정할 수는 없습니다"
```

### 10-4. 과노출 순간 공통 답변 정책

모든 NPC는 3.4초 과노출 순간에 대해 아래 원칙을 지킨다.

```text
그 순간 화면과 시야가 하얗게 날아갔다.
정확히 눈으로 본 것은 없다.
본 것이 아니라 소리, 장비 반응, 이전/이후 장면을 근거로 추정할 수 있을 뿐이다.
```

금지:

```text
“누가 밀었습니다.”
“그 사람이 장치를 조작하는 걸 봤습니다.”
“제가 범인을 봤습니다.”
```

---

## 11. AI 심문 트리거 정본

| Target NPC | Trigger Evidence | Unlock / Reaction |
|---|---|---|
| 민루아 | `EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE` | 겔 자국을 일반 현장 오염으로 돌림 |
| 민루아 | `EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE` | 루프탑 동선 접근 인정 |
| 민루아 | `EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL` + 겔 증거 | 겔을 남긴 사실 인정, 테이프 조작자는 자신이 아니라고 주장 |
| 서이라 | `EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL` | MARK 9 위치에 민감했던 사실 축소 |
| 서이라 | `EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE` | 검은 마킹 테이프 소지 이유를 리허설 준비물로 둘러댐 |
| 윤채린 | `EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL` | 공식 대본과 다른 지시 문구를 처음엔 부정 |
| 윤채린 | `EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST` + 반사 스틸컷 | Actor POV Check를 백도윤이 직접 확인했다는 사실 인정 |
| 류현규 | `EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME` | 필터 교체를 장비팀 관행으로 돌림 |
| 류현규 | `EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP` + `EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO` | Cue 9 세팅이 평소보다 위험했다는 사실 일부 인정 |
| 남기준 | `EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE` | 드라이 런이라도 안전 결착이 의무였음을 인정 |
| 남기준 | `EVIDENCE_SAFETY_LATCH_CLOSEUP` + `EVIDENCE_SAFETY_LATCH_WEAR_PATTERN` | 잠금부 상태가 정상 사고 파손과 다르다는 점에 흔들림 |
| 강태오 | `EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT` | 저가 클립 사용을 부정하거나 외주 탓으로 돌림 |
| 강태오 | `EVIDENCE_UNAPPROVED_EQUIPMENT_ORDER` | 비승인 발주 인정, 살인 의도 부정 |
| 고은별 | `EVIDENCE_EDITED_MAKING_REEL_EXPORT` | 공식본과 원본 자료 차이를 알고 있었음을 회피 |
| 고은별 | `EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE` + 압박 | B-roll 원본 / 연속성 노트 일부 해금 |

### 민루아 STAGE_3 핵심 반응

```text
“...좋아요. 제가 그 겔을 남긴 건 맞아요.

근데 그건 감독님 때문이 아니었어요.
서이라 선배가 리허설 때 그쪽 동선으로 들어오기로 되어 있었거든요.
넘어지게 하려던 것도 아니고, 그냥 발이 미끄러워서 한 번만 흔들리면 된다고 생각했어요.
그럼 촬영이 밀리고, 제 장면이 살아날 수도 있으니까요.

하지만 저 테이프는 제가 붙인 게 아니에요.

보세요. 제 겔 위로 테이프 끝이 들떠 있잖아요.
제가 겔을 남긴 다음에 누군가가 MARK 9 테이프를 옮겨 붙였다는 뜻 아닌가요?

저는 바닥을 더럽혔어요.
그건 인정해요.
근데 감독님을 그 위치에 세운 사람은 따로 있어요.”
```

금지:

```text
“서이라가 범인입니다.”라고 단정 금지
“제가 서이라가 테이프 옮기는 걸 봤습니다.” 같은 허위 목격 금지
```

---

## 12. 스크립터 해금 구조

### 12-1. 해금 대상

```yaml
scriptSupervisorUnlocks:
  - EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
  - EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
  - EVIDENCE_CONTINUITY_SKETCH_NOTE
```

### 12-2. 해금 조건

```yaml
unlockCondition:
  requiredEvidence:
    - EVIDENCE_EDITED_MAKING_REEL_EXPORT
    - EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE
  requiredNpc:
    code: WITNESS_SCRIPT_SUPERVISOR
    minimumStage: STAGE_3
```

### 12-3. 해금 대사

```text
“공식본에는 빠진 컷들이 있습니다.

메이킹 팀 자료를 동기화하면서 봤어요.
그냥 현장 기록이라고 생각했는데, 몇 장은 공식본하고 말이 안 맞았습니다.

저는 그걸 숨기려고 한 게 아니라...
제가 그 자료를 갖고 있었다는 사실이 알려지면,
편집본 누락 책임이 제게 올 것 같았습니다.

이 드라이브 안에 원본 B-roll 스틸컷이랑 제 연속성 노트가 있습니다.
다만 이걸 봐도 누가 죽였는지까지 바로 나오진 않을 겁니다.
사진 속에서 무엇이 어긋났는지 직접 보셔야 합니다.”
```

---

## 13. UI / UX 구현 기준

### 13-1. Evidence List

증거가 35개이므로 필터가 필수다.

```yaml
evidenceFilters:
  category:
    - 전체
    - 현장
    - 물리
    - 문서
    - 오디오
    - 비주얼
    - 맵

  location:
    - 전체
    - 루프탑 세트
    - 모니터 존
    - 프롬프터 스테이션
    - 조명 콘솔
    - 리깅 구역
    - 제작 데스크
    - 대기실
    - 스크립터 데스크
```

기본 정렬:

```text
최근 해금순
```

### 13-2. 카드 스포일러 방지

```text
이미지 자체가 정답을 직접 말하면 안 된다.
핵심 단서는 카드 상세 텍스트 / 확대 보기 / 비교 설명에서 드러난다.
```

### 13-3. MVP 시각 탐색

MVP에서 가능한 수준:

```text
- 카드 이미지 보기
- 상세 텍스트 보기
- 관련 증거 태그 표시
- “비교해볼 증거” 추천 정도
```

고도화 후보:

```text
- 두 사진 겹쳐보기
- 특정 부위 확대
- 레이어 비교 UI
- 현장 스케치와 사진 오버레이
```

---

## 14. YAML / DB 구현 메모

### 14-1. YAML 원본, DB 런타임

```text
YAML = 시나리오 원본 파일
DB = 런타임 조회/세션/채점 저장소
Importer = YAML을 검증하고 DB 엔티티로 변환하는 계층
```

### 14-2. 권장 파일명

```text
studio9.v1.yaml
```

### 14-3. 권장 구조

```yaml
metadata:
scenario:
victim:
locations:
characters:
evidences:
evidenceVariantStates:
variants:
unlockRules:
npcPolicies:
scoring:
assets:
```

### 14-4. 반드시 검증할 것

```text
- evidence.code 중복 금지
- character.code 중복 금지
- location.code 중복 금지
- variant.culpritCode는 culpritEligible=true인 character만 가능
- evidence.locationCode가 locations에 존재해야 함
- solution의 evidenceCode가 evidences에 존재해야 함
- unlockRule.requiredEvidence가 evidences에 존재해야 함
- NPC prompt에 activeVariant / culpritCode / variantSolution / finalExplanation을 넣지 않을 것
```

---

## 15. 개발팀 전달용 최종 요약

```text
스튜디오 9는 서월채처럼 로그 하나로 범인을 특정하는 사건이 아니다.

이 시나리오는 모든 증거 카드가 항상 존재하고,
activeVariant에 따라 일부 증거의 상세 상태와 내부 역할이 달라진다.

플레이어는 카드의 존재 여부가 아니라,
들뜬 테이프, 반사된 프롬프터 글씨, 비규격 필터, 안전 잠금부 마모 같은
현장 물증을 비교해 치명 물리층을 특정해야 한다.

최종 채점은 범인, 치명 물리층, 페이크 기각, 동기를 기준으로 한다.

AI NPC에게는 activeVariant나 정답표를 절대 주지 않는다.
AI는 자기 행동, 직접 본 것, 직접 들은 것, 제시받은 증거에 대한 반응만 안다.
```

---

## 16. Canon 상태

```yaml
canonStatus:
  document: STUDIO9_IMPLEMENTATION_CANON_v1
  basedOn: CLUEROOM_SCENARIO2_WORKING_BRIEF_v15
  majorDesignStatus: APPROVED
  implementationReadiness: READY_FOR_YAML_AND_DB_SPEC
  nextRecommendedArtifacts:
    - STUDIO9_SCENARIO_YAML_SPEC_v1.md
    - studio9.v1.yaml
    - STUDIO9_NPC_PROMPT_SPEC_v1.md
    - STUDIO9_SCORING_CRITERIA_v1.json
```
