# CLUEROOM_SCENARIO2_WORKING_BRIEF_v15
## 스튜디오 9 — 물리적 인과율 기반 최종 추리 / 채점 기준 재설계본

> 목적: v7~v10의 `원본 타임코드 Delta 표` 중심 추리 구조를 폐기하고, 스튜디오 9만의 핵심 플레이 감각을 **현장 감식 / 물리적 선후관계 / 시각 자료 판독**으로 재정렬한다.  
> 핵심 원칙: 서월채처럼 “마지막에 절대 로그 하나 보고 시간표로 범인 특정”하는 방식은 2번 시나리오에서 반복하지 않는다.  
> 상태: v11 방향 승인 후, 레드팀이 지적한 물리/직업/은폐 개연성 오류 3개를 v12에서 보정한다.

---

## 0. v12 최종 방향

### 0-1. 유지하는 것

```text
- 스튜디오 9의 무대: 마지막 컷 테크 리허설 / 루프탑 세트 / MARK 9
- Master Trigger: 백도윤이 직접 “Roll. Cue 9.”를 말함
- 3.4초 과노출: 모두가 보고 있었지만 아무도 보지 못한 순간
- 7명 AI NPC 구조
  - 범인 가능 4명
  - 고정 비범인 Dirty Fake 2명
  - 중립 참고인 1명
- Dirty Fake Evidence 구조
- 첫 심문 타겟: 조연배우 민루아
- AI NPC 지식 경계
```

### 0-2. 폐기 / 강등하는 것

```text
EVIDENCE_RAW_TIMECODE_DELTA_TABLE
```

이 증거를 **최종 범인 특정용 마스터키**로 쓰지 않는다.

타임코드/장비 로그류는 완전히 사라지는 것이 아니라, 다음 역할로 강등한다.

```text
허용:
- 사건 순간의 배경 기록
- 장비 작동을 보조적으로 설명
- B-roll / 현장 사진의 촬영 시점 보조
- AI 심문에서 “공식 기록과 현장 흔적이 안 맞는다”는 보조 근거

금지:
- 특정 채널이 0.5초 먼저 움직였으므로 곧바로 범인 특정
- 원본 로그 하나로 4개 Variant 중 정답을 확정
```

### 0-3. 새 핵심 추리 방식

```text
로그 추리 ❌
현장 감식형 물리 추리 ⭕
```

플레이어가 해야 할 일은 다음이다.

```text
- 무엇이 무엇 위에 덮였는가?
- 어떤 흔적이 나중에 생겼는가?
- 어떤 장비 파손이 자연 파손이 아니라 사전 조작인가?
- 어떤 시각 자료에 공식본과 다른 장면이 비쳤는가?
- 어떤 페이크 조작은 위험하지만 직접 사망 원인은 아닌가?
```

---

## 1. 핵심 레드팀 피드백 반영

### 1-1. 주연배우 Variant 물리 오류 보정 — “겔 위에 테이프가 붙나?”

#### 기존 문제

```text
조연배우 민루아가 무광 겔을 뿌림
→ 주연배우 서이라가 그 위에 MARK 9 테이프를 옮겨 붙임
```

이 설명은 물리적으로 약하다.

```text
겔/오일 위에 마스킹 테이프가 팽팽하게 잘 붙어 있으면 이상함.
```

#### v12 보정

정본은 다음과 같이 수정한다.

```text
MARK 9 테이프는 겔 위에 “깔끔하게 붙어 있었던 것”이 아니다.

새로 옮겨 붙인 MARK 9 테이프의 한쪽 끝이
무광 겔 성분 때문에 접착에 실패해 들떠 있었고,
접착면에는 투명한 끈적임과 바닥 먼지가 함께 묻어 있었다.
```

즉 결정타는 “겔 위에 잘 붙은 테이프”가 아니라:

```text
겔 때문에 제대로 붙지 못한 테이프
```

다.

#### 이게 증명하는 것

```text
1. 민루아가 먼저 겔을 남겼다.
2. 그 후 누군가 MARK 9 테이프를 그 겔이 있는 쪽으로 옮겼다.
3. 그 사람은 겔의 존재를 몰랐거나 급하게 작업했다.
4. 따라서 백도윤의 위험 위치 이동은 단순 미끄럼이 아니라, MARK 9 자체의 위치 조작과 연결된다.
```

#### 관련 증거

```yaml
code: EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
title: "들뜬 MARK 9 테이프 끝"
playerText: >
  MARK 9 테이프의 한쪽 끝이 바닥에서 살짝 들떠 있다.
  들뜬 접착면 안쪽에는 투명한 겔 성분과 검은 바닥 먼지가 함께 말려 들어가 있다.
  기존 마크 위치로 보이는 옅은 접착 잔여선은 난간에서 더 떨어진 안전한 위치에 남아 있다.
```

---

### 1-2. 메인 작가 Variant 직업 개연성 보정 — “감독이 왜 프롬프터를 보나?”

#### 기존 문제

프롬프터는 보통 배우가 대사/지시를 보기 위한 장치다.

```text
백도윤은 배우가 아니라 감독.
그가 왜 배우용 프롬프터를 보고 움직이는가?
```

#### v12 보정

사건판에 다음 전제를 추가한다.

```text
백도윤은 마지막 컷에서 주연배우의 눈동선, 프롬프터 반사, 카메라 앵글, MARK 9 위치가 동시에 맞는지 확인하려고 했다.
이를 현장에서는 “Actor POV Check”라고 불렀다.

즉, 백도윤은 감독이지만,
마지막 드라이 런에서는 배우가 서는 MARK 9 위치에 직접 서서
주연배우가 실제로 보게 될 시야를 확인하고 있었다.
```

이 전제를 Phase 0 공통 증거에 넣는다.

```yaml
code: EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST
title: "백도윤의 Actor POV 체크리스트"
playerText: >
  백도윤의 손글씨가 남은 최종 컷 점검표.
  ‘MARK 9에서 배우 시야 확인’, ‘프롬프터 반사 여부 확인’, ‘백라이트 진입 시 눈동선 확인’ 항목에 동그라미가 쳐져 있다.
  단순 카메라 앵글 확인이 아니라, 백도윤이 배우 위치에 직접 서서 시야를 점검하려 했음을 보여준다.
```

#### 이게 증명하는 것

```text
백도윤이 프롬프터/반사 화면을 볼 이유가 생긴다.
따라서 메인 작가가 프롬프터/인이어 지시를 조작해 피해자의 움직임을 유도한 수법이 성립한다.
```

---

### 1-3. 조명감독 Variant 은폐 오류 보정 — “필터가 녹았다면 왜 바로 몰랐나?”

#### 기존 문제

```text
조명 필터가 녹거나 타면 냄새/연기 때문에 현장에서 바로 알 수 있음.
```

그러면 조명감독의 은폐가 약해지고, “그냥 장비 고장”처럼 보일 수 있다.

#### v12 보정

정본은 다음과 같이 수정한다.

```text
결정타는 “단순히 녹은 필터”가 아니다.

원래 장착되어야 할 내열 확산 필터 대신,
겉보기에는 같은 색과 크기처럼 보이지만 열에 약한 비규격 확산 필터가
표준 프레임 안쪽에 끼워져 있었다.

과노출 이후 필터 가장자리에 미세 변형이 생겼지만,
외부 하우징과 세트 연무 때문에 현장에서는 단순한 먼지 탄 냄새나 고출력 테스트 흔적으로 넘겨졌다.
```

즉 조명감독 Variant의 핵심은:

```text
밝기 수치 조작 ❌
단순 필터 용융 ❌
비규격 필터 교체 + MAX-BLIND 메모 + 과노출 프레임 조합 ⭕
```

#### 관련 증거

```yaml
code: EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
title: "비규격 확산 필터 프레임"
playerText: >
  Cue 9 백라이트 하우징 안쪽에서 회수된 확산 필터 프레임.
  겉면 라벨은 표준 필터와 비슷하지만, 모서리 절단면과 고정 홈의 깊이가 기존 예비 필터와 미세하게 다르다.
  필터 가장자리에는 과도한 열을 받은 듯한 얕은 변형이 남아 있다.
```

```yaml
code: EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
title: "변형된 확산 필터 클로즈업"
playerText: >
  필터 표면 전체가 탄 것은 아니다.
  그러나 프레임 가장자리 한쪽만 비정상적으로 휘어 있고,
  해당 방향은 사건 당시 MARK 9를 향하던 백라이트 조사 방향과 일치한다.
```

---

## 2. 스튜디오 9의 정답 구조 재정의

### 2-1. 기존 정답 구조

```text
원본 타임코드 Delta 표
→ 어느 장비 채널이 이상했는지 확인
→ 해당 장비 담당자가 범인
```

이 구조는 서월채의 바이탈 로그와 체감이 겹친다.

### 2-2. v12 정답 구조

```text
B-roll / 현장 사진 / 훼손 장비 / 겹친 흔적
→ 물리적 선후관계 또는 물리적 파손 방식 확인
→ 어떤 “수법”이 진짜 치명 경로인지 특정
→ 그 수법을 사전에 만들 수 있었던 인물과 동기 증거를 결합
```

즉 2번 시나리오의 정답 구조는 다음이다.

```text
범인 특정 = 사람 위치 추적이 아니라, 흉기 레이어 판독
```

---

## 3. Variant별 결정타 재설계

### 3-1. VARIANT_ACTOR — 주연배우 / MARK 9 위치 조작

#### 수법

```text
서이라는 MARK 9 테이프를 원래보다 난간 쪽으로 옮겨 붙였다.
백도윤은 Actor POV Check를 위해 그 위치에 직접 섰고,
조작된 마크 때문에 자신이 안전 범위에 있다고 착각했다.
```

#### 결정타

```text
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE
EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE
```

#### 물리 인과율

```text
민루아의 겔 자국이 먼저 존재
→ 서이라가 그 위쪽으로 MARK 9를 옮겨 붙임
→ 테이프 접착면이 겔 때문에 들뜸
→ 기존 안전한 MARK 9 위치의 접착 잔여선과 현재 위치가 어긋남
```

#### Dirty Fake와의 관계

```text
민루아의 겔은 진짜 위험한 방해 행위다.
하지만 겔 자체가 직접 사망 원인은 아니다.
오히려 겔 위에 뒤늦게 올라간 테이프가 “그 이후의 진짜 조작”을 드러낸다.
```

---

### 3-2. VARIANT_WRITER — 메인 작가 / 프롬프터 반사 지시 조작

#### 수법

```text
윤채린은 최종 드라이 런용 프롬프터/인이어 지시 문구를 조작했다.
백도윤은 Actor POV Check 중이었기 때문에 주연배우 시야 기준의 프롬프터 반사를 확인하고 있었다.
과노출 직전, 공식 대본에는 없는 짧은 후퇴 지시가 시야에 들어왔다.
```

#### 결정타

```text
EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST
EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH
EVIDENCE_WRITER_REVISION_NOTE
```

#### 물리/시각 인과율

```text
공식 최종 대본에는 후퇴 지시가 없음
→ B-roll 스틸컷 속 유리/모니터 반사에는 후퇴 지시 문구가 흐릿하게 비침
→ 백도윤이 배우 시야를 직접 확인하던 상황이라 그 문구를 봤을 가능성이 생김
→ 프롬프터 지시가 피해자의 움직임을 유도한 물리적 원인으로 성립
```

#### 유저가 깨달아야 할 점

```text
작가가 종이 대본을 바꿔서 사람을 죽인 게 아니다.
현장에서 피해자가 실제로 보고 따를 수 있는 지시 시스템을 바꾼 것이다.
```

---

### 3-3. VARIANT_LIGHTING_DP — 조명감독 / 비규격 필터 + 플래시성 백라이트

#### 수법

```text
류현규는 Cue 9 백라이트를 단순히 밝게 한 것이 아니라,
비규격 확산 필터와 MAX-BLIND 지시를 조합해
MARK 9 위치의 시야와 공간 인지를 순간적으로 무너뜨리는 상태를 만들었다.
```

#### 결정타

```text
EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO
EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
EVIDENCE_OVEREXPOSED_FRAME_SEQUENCE
```

#### 물리 인과율

```text
정상 Cue 9은 윤곽 백라이트
→ 사건 당시 Cue 9 관련 메모에는 MAX-BLIND가 추가
→ 실제 조명 하우징에는 표준 필터가 아닌 비규격 필터가 끼워져 있음
→ 필터 변형 방향과 과노출 프레임 방향이 MARK 9를 향함
→ 단순 은폐용 조명이 아니라 백도윤의 공간 인지를 무너뜨리는 장치로 작동
```

#### 안전 표현 제한

실제 위해 방법처럼 구체적인 전력량, 조명 수치, 장치 개조 방법은 작성하지 않는다.

```text
허용:
- 비규격 필터
- MAX-BLIND 메모
- 변형 방향
- 과노출 프레임

금지:
- 실제 조명 개조 절차
- 구체적 출력 수치
- 실제로 재현 가능한 위해 방식
```

---

### 3-4. VARIANT_STUNT — 액션감독 / 안전 잠금부 사전 훼손

#### 수법

```text
남기준은 드라이 런 안전 수칙상 반드시 연결되어야 하는 허리 안전벨트와 난간 보조 잠금장치를 정상 결착처럼 보이게 만들었다.
그러나 잠금부는 이미 사전 훼손되어, 하중이 걸릴 때 피해자를 잡아주지 못했다.
```

#### 결정타

```text
EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE
EVIDENCE_HARNESS_LOCK_CHECK_PHOTO
EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
EVIDENCE_SAFETY_CHECKLIST_OVERWRITE
```

#### 물리 인과율

```text
루프탑 MARK 라인 내부 진입 시 안전벨트 연결은 의무
→ 백도윤은 실제로 안전장치를 착용/연결한 것으로 보임
→ 하지만 잠금부의 마모 방향과 결착 흔적이 정상 사용 흔적과 다름
→ 정상 장치라면 추락 전 최초 균형 붕괴를 잡아야 했으나 실패
```

#### 제작총괄 페이크와의 분리

```text
제작총괄 강태오:
하단 보조 안전망 / 저가형 클립 / 예산 횡령

액션감독 남기준:
상단 허리 안전벨트 / 난간 보조 잠금장치 / 직접 결착 장비

두 장비 계층은 다르다.
```

---

## 4. 고정 비범인 Dirty Fake 재정의

### 4-1. 제작총괄 강태오

#### 한 짓

```text
루프탑 하단 보조 안전망의 일부 클립을 저가형 부품으로 교체했다.
```

#### 왜 범인처럼 보이는가

```text
추락사 사건에서 안전망 부품 조작은 매우 치명적으로 보인다.
비승인 발주서와 예산 조정 메일도 있다.
```

#### 왜 범인은 아닌가

```text
문제의 클립은 하단 보조 안전망 영역에 속한다.
백도윤의 최초 균형 붕괴 / 추락 시작점은 상단 MARK 9와 루프탑 난간 주변에서 발생했다.
강태오의 조작은 위험하고 범죄에 가깝지만, 이번 사망의 최초 치명 경로는 아니다.
```

#### 관련 증거

```text
EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT
EVIDENCE_UNAPPROVED_EQUIPMENT_ORDER
EVIDENCE_INSURANCE_BUDGET_ADJUSTMENT_MAIL
```

---

### 4-2. 조연배우 민루아

#### 한 짓

```text
주연배우 서이라의 리허설 동선을 방해하려고,
검은 마스킹 테이프 라인 주변에 무광 투명 겔을 남겼다.
```

#### 왜 범인처럼 보이는가

```text
오일/겔 자국
신발 밑창의 같은 성분
차기작 캐스팅 거래 메시지
```

이 세 가지가 결합되면 매우 수상하다.

#### 왜 범인은 아닌가

```text
겔 자국은 백도윤의 최초 치명 위치와 완전히 일치하지 않는다.
겔의 궤적은 주연배우의 대기 동선 쪽으로 길게 이어진다.
또한 MARK 9 테이프 일부가 겔 위에서 접착 실패한 흔적은,
민루아의 겔 이후 다른 사람이 위치 마킹을 건드렸다는 신호가 된다.
```

#### 관련 증거

```text
EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE
EVIDENCE_SUPPORTING_ACTOR_CASTING_DEAL_MESSAGE
```

---

## 5. 중립 참고인 스크립터의 역할 재설정

### 기존

```text
원본 타임코드 Delta 표를 숨김
```

### v12 수정

```text
B-roll 메모리 카드
연속성 스케치 노트
메이킹 카메라 반사 프레임
컷별 MARK 9 위치 스케치
```

을 숨김.

### 스크립터가 범인이 아닌 이유

```text
고은별은 사건을 기록하고 은닉했지만, 치명 장치 자체를 조작한 증거는 없다.
그녀의 죄는 기록 보존 / 제출 지연 / 편집본과 원본 자료 차이를 숨긴 것에 있다.
```

### 해금 구조

```text
EVIDENCE_EDITED_MAKING_REEL_EXPORT
+ EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE
+ 고은별 STAGE_3 압박
→ B-roll / 연속성 스케치 노트 해금
```

단, 이 자료도 정답을 직접 말하지 않는다.

```text
해금 자료가 제공하는 것:
- 테이프가 들뜬 클로즈업
- 프롬프터 반사 프레임
- 필터 하우징 상태
- 잠금부 일부가 찍힌 메이킹 컷
- MARK 9 위치 스케치

해금 자료가 제공하지 않는 것:
- “범인은 누구다”
- “이 증거가 정답이다”
- “현재 Variant는 이것이다”
```

---

## 6. 증거 목록 재설계 v12

### 6-1. 증거 수 정책

```yaml
evidenceCountPolicy:
  minimum: 30
  target: 34
  maximum: 38
```

스튜디오 9는 다음 이유로 서월채보다 증거 수가 조금 많아도 된다.

```text
- 심문 가능 인물 7명
- 물리 Dirty Fake 2명
- 로그가 아닌 현장 감식 중심
- 각 Variant마다 독립적인 물리 결정타 필요
```

중요한 것은 수량이 아니라 역할이다.

```text
의미 없는 증거 추가 금지
각 증거는 다음 중 하나를 반드시 담당해야 함:
- 사건판 이해
- 범행 경로 증명
- Dirty Fake 유도
- 동기 증명
- AI 심문 트리거
- 최종 추리 proof dimension 보조
```

---

### 6-2. v12 증거 목록 35개

| No | Code | Title | Category | Phase | Main Role |
|---:|---|---|---|---|---|
| 1 | `EVIDENCE_ROOFTOP_SET_SCENE_PHOTO` | 루프탑 세트 현장 사진 | SCENE | 0 | 사건판 |
| 2 | `EVIDENCE_MARK9_CURRENT_POSITION_PHOTO` | 사건 당시 MARK 9 위치 사진 | PHYSICAL | 0 | 공통 현장 |
| 3 | `EVIDENCE_STAGE_BLOCKING_OVERVIEW` | 루프탑 블로킹 배치도 | MAP | 0 | 공간 이해 |
| 4 | `EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST` | 백도윤의 Actor POV 체크리스트 | DOCUMENT | 0 | 작가 루트 전제 |
| 5 | `EVIDENCE_MASTER_TRIGGER_AUDIO` | “Roll. Cue 9.” 인터컴 음성 | AUDIO | 1 | Master Trigger |
| 6 | `EVIDENCE_OVEREXPOSED_FRAME_SEQUENCE` | 과노출 3.4초 프레임 시퀀스 | VISUAL | 1 | 공통 사건 |
| 7 | `EVIDENCE_SOUND_MIXER_AMBIENT_AUDIO_FRAGMENT` | 현장 앰비언트 오디오 파편 | AUDIO | 1 | 청각 증언 보조 |
| 8 | `EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE` | 기존 MARK 9 접착 잔여선 | PHYSICAL | 2 | 배우 루트 보조 |
| 9 | `EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE` | 검은 마스킹 라인의 무광 겔 자국 | PHYSICAL | 2 | 민루아 Dirty Fake |
| 10 | `EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE` | 조연배우 신발 밑창 겔 흔적 | PHYSICAL | 2 | 민루아 Dirty Fake |
| 11 | `EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT` | 저가형 안전 클립 파편 | PHYSICAL | 2 | 강태오 Dirty Fake |
| 12 | `EVIDENCE_UNAPPROVED_EQUIPMENT_ORDER` | 비승인 장비 교체 발주서 | DOCUMENT | 2 | 강태오 Dirty Fake |
| 13 | `EVIDENCE_HARNESS_LOCK_CHECK_PHOTO` | 하네스 결착 확인 사진 | PHYSICAL | 2 | 액션감독 루트 보조 |
| 14 | `EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL` | 프롬프터 반사 B-roll 스틸컷 | VISUAL | 3 | 작가 루트 KEY 후보 |
| 15 | `EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH` | 최종 블로킹 대본 불일치 | DOCUMENT | 3 | 작가 루트 보조 |
| 16 | `EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO` | MAX-BLIND 손글씨 조명 메모 | DOCUMENT | 3 | 조명 루트 보조 |
| 17 | `EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME` | 비규격 확산 필터 프레임 | PHYSICAL | 3 | 조명 루트 KEY 후보 |
| 18 | `EVIDENCE_SAFETY_LATCH_WEAR_PATTERN` | 안전 잠금부 비정상 마모 흔적 | PHYSICAL | 3 | 액션감독 루트 KEY 후보 |
| 19 | `EVIDENCE_SAFETY_CHECKLIST_OVERWRITE` | 안전 점검표 덮어쓰기 흔적 | DOCUMENT | 3 | 액션감독 은폐 |
| 20 | `EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE` | 주연배우 대기실 검은 마킹 테이프 조각 | PHYSICAL | 3 | 배우 루트 보조 |
| 21 | `EVIDENCE_SUPPORTING_ACTOR_CASTING_DEAL_MESSAGE` | 조연배우 차기작 거래 메시지 | DOCUMENT | 3 | 민루아 동기 |
| 22 | `EVIDENCE_INSURANCE_BUDGET_ADJUSTMENT_MAIL` | 보험·예산 조정 메일 | DOCUMENT | 3 | 강태오 동기 |
| 23 | `EVIDENCE_ACTOR_CONTRACT_BLACKMAIL_MESSAGE` | 주연배우 해외 계약 협박 메시지 | DOCUMENT | 3 | 배우 동기 |
| 24 | `EVIDENCE_OLD_MANUSCRIPT_CREDIT_FILE` | 오래된 원고와 크레딧 파일 | DOCUMENT | 3 | 작가 동기 |
| 25 | `EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT` | 조명 사고 책임 보고서 초안 | DOCUMENT | 3 | 조명감독 동기 |
| 26 | `EVIDENCE_OLD_STUNT_ACCIDENT_FILE` | 과거 촬영 사고 은폐 파일 | DOCUMENT | 3 | 액션감독 동기 |
| 27 | `EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE` | 스크립터 백업 드라이브 | PHYSICAL | 3 | 중립 해금 |
| 28 | `EVIDENCE_EDITED_MAKING_REEL_EXPORT` | 편집된 메이킹 릴 추출본 | VISUAL | 3 | 중립 해금 전조 |
| 29 | `EVIDENCE_CONTINUITY_SKETCH_NOTE` | 연속성 스케치 노트 | DOCUMENT | 4 | 스크립터 결정 자료 |
| 30 | `EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL` | 들뜬 MARK 9 테이프 끝 | PHYSICAL | 4 | 배우 결정타 |
| 31 | `EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL` | 확대된 프롬프터 반사 스틸 | VISUAL | 4 | 작가 결정타 |
| 32 | `EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP` | 변형된 확산 필터 클로즈업 | PHYSICAL | 4 | 조명 결정타 |
| 33 | `EVIDENCE_SAFETY_LATCH_CLOSEUP` | 안전 잠금부 클로즈업 | PHYSICAL | 4 | 액션 결정타 |
| 34 | `EVIDENCE_STAGE_FLOOR_DUST_SCUFF_PATTERN` | 무대 바닥 먼지와 마찰 패턴 | PHYSICAL | 4 | 물리 경로 보조 |
| 35 | `EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE` | 루프탑 드라이 런 안전 수칙 | DOCUMENT | 0 | 액션감독 루트 전제 |

---

## 7. Variant별 KEY / DIRTY_FAKE / SUPPORT 재매핑

### 7-1. VARIANT_ACTOR

```yaml
variantCode: VARIANT_ACTOR
culpritCode: SUSPECT_ACTOR
method: "MARK 9 위치 조작"
keyEvidence:
  - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
  - EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE
  - EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE
  - EVIDENCE_ACTOR_CONTRACT_BLACKMAIL_MESSAGE
supportEvidence:
  - EVIDENCE_CONTINUITY_SKETCH_NOTE
  - EVIDENCE_MARK9_CURRENT_POSITION_PHOTO
dirtyFake:
  - EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
  - EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE
  - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
  - EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
```

### 7-2. VARIANT_WRITER

```yaml
variantCode: VARIANT_WRITER
culpritCode: SUSPECT_WRITER
method: "프롬프터/현장 지시 조작"
keyEvidence:
  - EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST
  - EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
  - EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH
  - EVIDENCE_OLD_MANUSCRIPT_CREDIT_FILE
supportEvidence:
  - EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
  - EVIDENCE_CONTINUITY_SKETCH_NOTE
dirtyFake:
  - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
  - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
  - EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
  - EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
```

### 7-3. VARIANT_LIGHTING_DP

```yaml
variantCode: VARIANT_LIGHTING_DP
culpritCode: SUSPECT_LIGHTING_DP
method: "비규격 필터와 MAX-BLIND 조명 큐를 이용한 공간 인지 붕괴"
keyEvidence:
  - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
  - EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
  - EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO
  - EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT
supportEvidence:
  - EVIDENCE_OVEREXPOSED_FRAME_SEQUENCE
  - EVIDENCE_SOUND_MIXER_AMBIENT_AUDIO_FRAGMENT
dirtyFake:
  - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
  - EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
  - EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
  - EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT
```

### 7-4. VARIANT_STUNT

```yaml
variantCode: VARIANT_STUNT
culpritCode: SUSPECT_STUNT_COORDINATOR
method: "허리 안전벨트 / 난간 보조 잠금부 사전 훼손"
keyEvidence:
  - EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE
  - EVIDENCE_HARNESS_LOCK_CHECK_PHOTO
  - EVIDENCE_SAFETY_LATCH_CLOSEUP
  - EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
  - EVIDENCE_OLD_STUNT_ACCIDENT_FILE
supportEvidence:
  - EVIDENCE_SAFETY_CHECKLIST_OVERWRITE
dirtyFake:
  - EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT
  - EVIDENCE_UNAPPROVED_EQUIPMENT_ORDER
  - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
  - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
```

> 주의: v12에서는 `EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE`를 독립 증거로 포함한다.  
> 증거 수는 35개지만, 앱 UI에서 Phase / 장소 / 인물 필터를 제공하면 소화 가능한 범위로 본다.

---

## 8. EvidenceVariantState 정책

스튜디오 9는 서월채와 달리 결정타가 “로그 하나”가 아니라 물리 증거별로 분산된다.

따라서 다음 카드들은 `Variant Evidence`로 본다.

```text
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
EVIDENCE_SAFETY_LATCH_CLOSEUP
EVIDENCE_STAGE_FLOOR_DUST_SCUFF_PATTERN
```

### 8-1. 원칙

```text
activeVariant의 결정타 증거:
- 물리적 선후관계 또는 파손 원인이 명확히 닫힘

non-activeVariant의 동일 계열 증거:
- 수상함은 남지만, 치명 경로와는 한 끗 어긋남
- 또는 사후 은폐 / 자기 비밀 은폐 / 다른 인물 방해로 설명됨
```

### 8-2. 예시

```yaml
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL:
  VARIANT_ACTOR:
    role: METHOD_KEY
    visibleDetailModifier: "테이프 들뜬 방향과 기존 접착 잔여선의 위치 차이가 MARK 9 재배치를 직접 가리킨다."
  VARIANT_LIGHTING_DP:
    role: DIRTY_FAKE
    visibleDetailModifier: "테이프 끝이 들떠 있지만, 기존 접착 잔여선과 추락 경로가 완전히 닫히지는 않는다."
```

---

## 9. 모바일 UI / 시각적 탐색 요소

현재 MVP가 맵 탐험형은 아니더라도, 증거 카드 내부에서는 최소한의 시각적 판독 경험을 줄 수 있다.

### 9-1. MVP에서 가능한 방식

```text
- 증거 이미지 1장 + 짧은 상세 텍스트
- 카드 확대 이미지
- 비교 이미지 2장 나란히 배치
- “관찰 포인트” 1~2개
```

### 9-2. 고도화 후보

```text
- 두 사진 겹쳐보기
- 확대/돋보기
- B-roll 스틸컷 특정 부위 탭
- 마킹 위치 전후 슬라이더
- 장비 클로즈업 전환
```

### 9-3. 카드별 추천

```text
MARK 9 테이프:
이전 위치 사진 vs 현재 위치 사진 나란히 배치

프롬프터 반사:
B-roll 스틸컷 전체 이미지 + 확대 이미지

확산 필터:
정상 예비 필터 vs 사건 필터 비교 이미지

안전 잠금부:
정상 잠금부 예시 사진 vs 사건 잠금부 클로즈업

무광 겔:
일반 조명에서는 거의 안 보이는 바닥 사진 + 측면광에서 드러나는 자국 사진
```

---

## 10. AI 심문 영향

### 10-1. 로그 제시 중심에서 물증 제시 중심으로 수정

기존 트리거:

```text
원본 타임코드 Delta 표 제시
```

수정:

```text
B-roll 스틸컷
들뜬 MARK 9 테이프
프롬프터 반사 이미지
비규격 필터
안전 잠금부 클로즈업
무광 겔 자국
```

### 10-2. NPC가 말할 수 있는 것

```text
직접 본 물체
직접 만진 장비
직접 들은 소리
자기가 숨긴 조작
증거를 제시받고 인정할 수 있는 범위
```

### 10-3. NPC가 말하면 안 되는 것

```text
“이 증거가 이번 Variant의 KEY다”
“이 사람이 범인이다”
“실제로는 이 수법이 정답이다”
“다른 사람의 hiddenAction 전체”
```

---

## 11. 다음 작업

v13에서 해야 할 일은 다음이다.

```text
1. v12 증거 목록 기준으로 공통 알리바이 / NPC 반응 트리거 재작성
2. 민루아 첫 심문 시나리오를 물리 증거 중심으로 재작성
3. 스크립터 B-roll / 연속성 노트 해금 조건 확정
4. 최종 추리 / Proof Dimension을 물리 증거 기준으로 재정렬
5. 구현 정본화 전 code freeze
```

---

## 12. v12 판정

```yaml
scenario2V12Status:
  oldMasterKey: "EVIDENCE_RAW_TIMECODE_DELTA_TABLE"
  oldMethod: "디지털 로그 기반 시간표 추리"
  newMethod: "현장 감식 / 물리적 인과율 / 시각 자료 판독"
  result: "PHYSICAL_EVIDENCE_REWORK_DRAFT_COMPLETE"
  overallStatus: "CONDITIONAL_PASS"
```

v12 기준으로 스튜디오 9는 더 이상 서월채의 바이탈 로그 구조를 반복하지 않는다.

```text
서월채:
생물학적 시간표 추리

스튜디오 9:
현장 흔적의 물리적 선후관계 추리
```

이 차이가 2번 시나리오의 정체성이다.


---

# v13 추가 보정 — 물리 추리 마이크로 맹점 보강 + AI 심문 트리거 재작성

> 목적: v12에서 로그 마스터키를 폐기하고 물리적 인과율 중심으로 전환한 뒤 새로 발생한 **광학 / 촉각 / 자료 소유권**의 미세한 개연성 문제를 보정한다.  
> 핵심: 스튜디오 9는 더 이상 “타임코드 로그를 읽어 범인을 찾는 사건”이 아니다. 플레이어는 **사진, 반사, 접착 실패, 장비 외관과 내부 상태 차이, 연속성 기록**을 통해 현장을 감식한다.  
> 상태: v13 기준, `원본 타임코드 Delta 표`는 정답 마스터키로 사용하지 않는다.

---

## 13. v13 보정 1 — B-roll 프롬프터 반사 스틸컷의 광학 개연성

### 13-1. 문제

기존 v12의 메인 작가 Variant는 다음 증거를 결정타로 사용한다.

```text
EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
```

그런데 사건 순간에는 3.4초 과노출이 발생한다.

```text
문제:
메인 카메라와 현장 시야가 하얗게 날아갈 정도의 과노출이라면,
B-roll 카메라는 어떻게 프롬프터 반사 문구를 찍었는가?
```

### 13-2. v13 정본

B-roll 스틸컷은 과노출이 한창 진행 중인 프레임이 아니다.

```text
정본:
B-roll 스틸컷은 Cue 9 과노출이 완전히 터지기 직전,
메이킹 카메라가 조명 직격축을 벗어난 측면 앵글에서 포착한 짧은 프레임이다.

이후 프레임들은 메인 카메라처럼 하얗게 날아갔다.
따라서 반사 문구가 남은 것은 “과노출 중에도 멀쩡히 찍힌 장면”이 아니라,
과노출 직전의 마지막으로 판독 가능한 프레임이다.
```

### 13-3. 증거 카드 문구 보정

```yaml
evidenceCode: EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
title: "B-roll 스틸컷 속 프롬프터 반사"
playerText: >
  메이킹 카메라가 조명 직격축을 벗어난 측면 앵글에서 잡은 짧은 프레임.
  다음 프레임부터 화면은 하얗게 날아가지만, 직전 프레임의 프롬프터 유리 가장자리에
  공식 대본과 다른 짧은 지시 문구가 흐릿하게 반사되어 있다.
suspiciousPoint:
  - "과노출 중의 기록이 아니라, 과노출 직전 마지막 판독 가능 프레임"
  - "공식 대본에는 없는 지시 문구가 반사됨"
  - "백도윤이 Actor POV Check 중이었다는 맥락과 결합해야 의미가 생김"
internalUse:
  - "VARIANT_WRITER의 METHOD_KEY 후보"
  - "단독으로 범인을 확정하지 않음"
  - "EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST, EVIDENCE_WRITER_REVISION_NOTE와 결합 필요"
```

---

## 14. v13 보정 2 — 안전 잠금부 훼손의 촉각 개연성

### 14-1. 문제

액션감독 Variant의 결정타는 안전 잠금부의 비정상 훼손이다.

```text
문제:
백도윤이 직접 안전벨트를 착용했다면,
잠금장치가 헐겁거나 이상하면 손으로 느끼지 않았을까?
```

### 14-2. v13 정본

잠금부는 겉으로 헐거운 장비가 아니다.

```text
정본:
사건 당시 잠금부는 외관상 정상적으로 닫혔고,
현장 사진에서도 결착된 것처럼 보이며,
짧은 ‘철칵’ 확인음도 남아 있다.

문제는 외부 손잡이나 걸쇠가 아니라,
하중이 실릴 때 버텨야 하는 내부 코어 쪽의 비정상 마모 / 미세 손상이다.

이 손상은 착용자가 손으로 채울 때는 거의 알기 어렵고,
충격이나 하중이 걸렸을 때만 문제가 드러나는 형태로 묘사한다.
```

> 안전 주의: 실제 장비 훼손 절차, 구체적인 도구, 실제 재현 가능한 방법은 문서와 증거 카드에 쓰지 않는다.  
> 표현은 “내부 코어 쪽 비정상 마모”, “정상 사용 방향과 다른 마모 흔적”, “하중 지지부의 미세 손상” 수준으로 제한한다.

### 14-3. 증거 카드 문구 보정

```yaml
evidenceCode: EVIDENCE_SAFETY_LATCH_CLOSEUP
title: "안전 잠금부 클로즈업"
playerText: >
  현장 사진상 잠금부는 닫혀 있으며, 외관만 보면 정상 결착으로 보인다.
  하지만 회수 후 촬영된 클로즈업에서는 하중을 받는 내부 지지부 쪽에
  정상 사용 방향과 다른 미세한 마모 흔적이 집중되어 있다.
suspiciousPoint:
  - "겉으로는 정상 결착처럼 보임"
  - "착용 당시 손으로 느끼기 어려운 내부 손상"
  - "정상 사고 파손이 아니라 사전 손상 가능성을 남김"
internalUse:
  - "VARIANT_STUNT의 METHOD_KEY 후보"
  - "EVIDENCE_ROOFTOP_DRY_RUN_SAFETY_RULE과 결합해야 의미가 명확해짐"
```

```yaml
evidenceCode: EVIDENCE_SAFETY_LATCH_WEAR_PATTERN
title: "잠금부 마모 방향 비교 사진"
playerText: >
  정상적으로 충격을 받은 잠금부 샘플과 사건 현장의 잠금부를 나란히 비교한 사진.
  사건 잠금부의 마모는 충격 이후 생긴 파손보다,
  이전부터 약해져 있던 지지부가 하중을 버티지 못한 형태에 가깝다.
suspiciousPoint:
  - "사고 후 파손과 사전 손상의 차이"
  - "액션팀의 안전 점검표 덮어쓰기와 함께 봐야 함"
internalUse:
  - "VARIANT_STUNT의 METHOD_KEY / COVERUP_KEY 후보"
```

---

## 15. v13 보정 3 — 스크립터가 B-roll 원본을 가진 이유

### 15-1. 문제

v12에서 스크립터 고은별은 B-roll 메모리 카드 / 메이킹 스틸컷 / 연속성 스케치 노트를 숨긴다.

```text
문제:
스크립터는 연속성 기록 담당자이지,
메이킹 카메라 SD 카드를 마음대로 빼돌리는 사람이 아니다.
왜 B-roll 원본 자료가 고은별에게 있었는가?
```

### 15-2. v13 정본

고은별의 역할은 단순 스크립터가 아니라 **스크립터 겸 타임코드/연속성 데이터 매니저**다.

```text
정본:
사건 직후 현장이 통제되면서, 메이킹 팀의 B-roll 원본과 현장 스틸컷은
동기화/정리/백업을 위해 타임코드 데이터 매니저 역할을 겸하던 고은별에게 일괄 전달되었다.

고은별은 자료를 정리하던 중,
공식 제출본에는 빠져 있는 측면 B-roll 프레임과 연속성 스케치의 불일치를 발견했다.

그녀는 자료를 즉시 제출하지 않고 개인 백업 드라이브에 사본을 보관했다.
이 행동은 범행이 아니라 기록 은닉 / 제출 지연 / 자기 책임 회피에 가깝다.
```

### 15-3. 증거 카드 문구 보정

```yaml
evidenceCode: EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE
title: "스크립터의 백업 드라이브"
playerText: >
  고은별이 개인적으로 보관하던 백업 드라이브.
  사건 직후 메이킹 팀의 B-roll 원본과 현장 스틸컷을 동기화하던 과정에서 복사한 파일들이 들어 있다.
  일부 파일명은 공식 제출본의 컷 번호와 맞지 않고, 몇 장의 스틸컷에는 별도의 연속성 메모가 붙어 있다.
suspiciousPoint:
  - "고은별이 원본 자료의 일부를 숨김"
  - "공식본과 다른 앵글의 컷이 포함됨"
  - "범인을 단정하는 자료가 아니라, 현장 감식 증거를 열어주는 자료"
internalUse:
  - "중립 참고인 해금 증거"
  - "B-roll / 연속성 노트 / 반사 스틸컷 해금 조건"
```

```yaml
evidenceCode: EVIDENCE_CONTINUITY_SKETCH_NOTE
title: "연속성 스케치 노트"
playerText: >
  드라이 런 직전 고은별이 남긴 손그림 기록.
  MARK 9의 위치, 배우 시야 방향, 프롬프터 반사 위치, 조명탑 그림자 영역이 간단한 선으로 표시되어 있다.
  사건 이후 공식 현장 사진과 비교하면 몇몇 위치가 미묘하게 맞지 않는다.
suspiciousPoint:
  - "기계 로그가 아니라 현장 사람이 남긴 수기 기록"
  - "MARK 9, 프롬프터 반사, 조명 그림자 영역을 연결하는 보조 지도"
internalUse:
  - "현장 감식형 추리의 중심 보조 증거"
```

---

## 16. v13 AI 심문 트리거 재작성 방향

### 16-1. 폐기하는 기존 트리거

```text
원본 타임코드 Delta 표 제시
장비 로그 채널 제시
“몇 초 먼저 움직였지?” 식 압박
```

### 16-2. v13 기준 트리거

```text
물리 증거를 들이밀고 압박한다.

예:
- 들뜬 MARK 9 테이프 끝
- 무광 겔 자국
- 조연배우 신발 밑창 겔 흔적
- B-roll 프롬프터 반사 스틸컷
- 비규격 확산 필터
- 안전 잠금부 클로즈업
- 연속성 스케치 노트
```

AI NPC는 장비 로그 해석자가 아니라, **자기가 만진 물건 / 본 장면 / 들은 소리 / 숨긴 행동**을 기준으로 반응한다.

---

## 17. 첫 심문 타겟 — 민루아 STAGE 반응 재설계

### 17-1. 민루아의 역할

```yaml
characterCode: RED_HERRING_SUPPORTING_ACTOR
name: "민루아"
roleLabel: "조연배우 / 백도윤의 새 뮤즈 후보"
culpritEligible: false
function:
  - "첫 번째로 무너지는 Dirty Fake 용의자"
  - "오일/겔 자국을 통해 플레이어가 물리 증거 추리를 배우게 만드는 튜토리얼형 미끼"
  - "주연배우 서이라 / MARK 9 / 대기 동선으로 수사를 확장시키는 징검다리"
```

민루아는 범인은 아니지만, 무고하지도 않다.

```text
민루아는 주연배우 서이라를 방해하려고 무광 겔을 남겼다.
하지만 MARK 9 테이프를 옮겨 붙인 사람은 아니다.
백도윤이 직접 MARK 9에 설 것까지는 예상하지 못했다.
```

---

### 17-2. STAGE_0 — 증거 제시 전

```text
“저요? 저는 그날 제 대기 위치에 있었어요.
감독님이 MARK 9에 올라가는 걸 보고 놀라긴 했지만,
그건 다들 마찬가지였잖아요.”
```

특징:

```text
- 불안하지만 강하게 부정
- 주연배우와의 경쟁심은 숨김
- 루프탑 바닥에 뭔가 남긴 사실은 절대 먼저 말하지 않음
```

---

### 17-3. STAGE_1 — 무광 겔 자국 제시

제시 증거:

```text
EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
```

반응:

```text
“세트장 바닥에 그런 자국이 있었다고 해서 그게 다 제 건가요?
소품팀도 쓰고, 분장팀도 쓰고, 액션팀도 별별 걸 다 쓰잖아요.

그리고 그 자국, 감독님이 떨어진 바로 그 자리라고 확정된 것도 아니잖아요.”
```

기능:

```text
- 자국 자체를 일반 현장 오염으로 돌림
- 자신의 소유/행동은 인정하지 않음
- 오일/겔이 치명 원인이라는 프레임을 약하게 유지
```

---

### 17-4. STAGE_2 — 신발 밑창 겔 흔적 제시

제시 증거:

```text
EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE
```

반응:

```text
“...그건 제가 밟은 겁니다. 일부러 묻힌 게 아니라요.

그날 루프탑은 정신없었어요.
마킹 테이프도 있고, 소품도 있고, 스태프들이 계속 왔다 갔다 했고요.

제가 거기 근처에 간 건 맞지만,
그게 감독님을 떨어뜨렸다는 뜻은 아니잖아요.”
```

기능:

```text
- 루프탑 동선 근처 접근 인정
- 여전히 의도적 조작은 부정
- 백도윤의 사망과 직접 연결되는 것은 거부
```

---

### 17-5. STAGE_3 — 들뜬 MARK 9 테이프 + 겔 자국 조합 제시

제시 증거 조합:

```text
EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
```

반응:

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

물귀신 발언:

```text
“그 마크에 제일 집착하던 건 제가 아니었어요.
서이라 선배가 카메라 위치랑 자기 동선을 계속 확인하고 있었죠.

그 사람 대기실에서 검은 마킹 테이프가 나왔다는 얘기,
확인해보셨어요?”
```

기능:

```text
- 자신의 죄 인정
- 살인 의도는 부정
- 물리적 선후관계 설명
- 다음 타겟: 주연배우 서이라 / MARK 9 테이프 조작
```

금지:

```text
“서이라가 범인이다”라고 단정 금지
“제가 본인이 테이프 옮기는 걸 봤다”는 식의 허위 목격 금지
```

---

## 18. v13 증거 트리거 매트릭스 초안

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

---

## 19. 스크립터 B-roll / 연속성 노트 해금 조건

### 19-1. 기본 정책

```text
B-roll / 연속성 노트는 자동 공개하지 않는다.
고은별을 압박해 얻는 후반 해금 증거다.
```

### 19-2. 해금 조건 초안

```yaml
unlockTarget:
  - EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
  - EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
  - EVIDENCE_CONTINUITY_SKETCH_NOTE

requiredEvidence:
  - EVIDENCE_EDITED_MAKING_REEL_EXPORT
  - EVIDENCE_SCRIPT_SUPERVISOR_BACKUP_DRIVE

requiredNpc:
  characterCode: WITNESS_SCRIPT_SUPERVISOR
  minimumStage: STAGE_2_EVIDENCE_COMBINATION

fallback:
  hintUnlockAllowed: true
  fallbackText: "공식 제출본 말고, 현장 기록을 따로 갖고 있던 사람이 있었을지 확인해보자."
```

### 19-3. 고은별 해금 대사

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

## 20. v13 판정

```yaml
scenario2V13Status:
  target: "AI 심문 트리거 재작성 + v12 물리 맹점 보정"
  oldMasterKeyStatus: "폐기 유지"
  mainDeductionMode: "현장 감식 / 물리적 선후관계 / 시각 자료 판독"
  firstInterrogationTarget: "RED_HERRING_SUPPORTING_ACTOR"
  result: "PHYSICAL_AI_TRIGGER_REWORK_DRAFT_COMPLETE"
  overallStatus: "CONDITIONAL_PASS"
```

v13 기준으로 스튜디오 9는 다음 상태다.

```text
- 로그 마스터키 재탕 문제 해결
- 물리 증거의 미세 개연성 보강
- 민루아 첫 심문 루트 정리
- 스크립터 B-roll 해금 구조 정리
- AI 트리거가 로그 중심에서 물증 중심으로 전환됨
```

다음 단계는 `최종 추리 / 채점 기준 / Proof Dimension`을 물리 증거 기준으로 다시 정렬하는 것이다.


---

## 21. v14 진입 판단 — 최종 추리 / 채점 기준 재설계

### 21-1. v12/v13 피드백 반영 요약

v12~v13을 거치며 스튜디오 9의 핵심 추리 방식은 완전히 바뀌었다.

```text
이전:
원본 타임코드 Delta 표를 열고,
어느 장비 채널이 먼저 움직였는지 확인한다.

현재:
현장의 물리 흔적, B-roll 스틸컷, 반사 이미지, 장비 파손 상태,
증거끼리의 겹침/불일치를 직접 비교한다.
```

따라서 최종 추리 채점도 서월채와 같은 방식이면 안 된다.

```text
서월채:
시간 / 섭취 / 접근 / 동기 중심

스튜디오 9:
물리적 치명층 / 페이크 기각 / 현장 모순 / 동기 중심
```

### 21-2. v14의 핵심 목적

```text
1. 범인만 맞히는 구조를 피한다.
2. 로그 기반 시간표 채점으로 회귀하지 않는다.
3. 4개 Variant의 결정타 증거가 “스폰 여부”로 정답을 드러내지 않게 한다.
4. 유저가 최종 추리에서 진짜 흉기뿐 아니라, 강력한 페이크를 왜 배제했는지도 설명하게 한다.
5. 최종 해설이 “정답 발표”가 아니라 “현장 감식 결과 보고서”처럼 느껴지게 한다.
```

---

## 22. 증거 스폰 메타 방지 정책

### 22-1. 원칙

```text
모든 핵심 증거 카드는 어떤 Variant에서도 존재한다.
카드가 나오고 안 나오고로 범인을 찍을 수 있으면 실패다.
```

즉, 아래 증거들은 activeVariant와 무관하게 항상 등장한다.

```text
- MARK 9 테이프 관련 카드
- 무광 겔 관련 카드
- 프롬프터 반사 B-roll 카드
- 조명 필터 관련 카드
- 안전 잠금부 관련 카드
- 불량 안전 클립 관련 카드
- B-roll / 연속성 노트 관련 카드
```

### 22-2. 바뀌는 것

Variant에 따라 바뀌는 것은 **카드의 존재 여부가 아니라 상세 상태**다.

```yaml
EvidenceVariantState:
  changes:
    - visibleDetailModifier
    - internalRole
    - scoringRole
    - fakeRefutationHint
  doesNotChange:
    - evidenceCode
    - title
    - category
    - baseImage
    - locationCode
```

예시:

```text
EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL

VARIANT_ACTOR:
- 테이프 끝이 무광 겔 성분 때문에 들떠 있다.
- 기존 접착 잔여선과 현재 MARK 9가 어긋난다.
- 역할: FATAL_LAYER_KEY

VARIANT_WRITER:
- 테이프 끝 들뜸은 있으나 기존 접착 잔여선과 현재 위치가 거의 일치한다.
- 겔 자국은 주연배우 대기 동선 쪽으로 이어진다.
- 역할: DIRTY_FAKE / FAKE_REFUTATION_SUPPORT

VARIANT_LIGHTING_DP:
- 테이프 상태는 수상하지만 추락 직접 원인을 설명하지 못한다.
- 과노출 프레임과 비규격 필터 쪽 물증이 더 강하다.
- 역할: DIRTY_FAKE

VARIANT_STUNT:
- 테이프 흔적은 있지만 안전장비 내부 손상과는 직접 연결되지 않는다.
- 역할: DIRTY_FAKE
```

### 22-3. 금지

```text
이번 판에만 특정 결정타 증거 카드가 새로 등장 ❌
이번 판에만 특정 용의자 관련 증거가 통째로 사라짐 ❌
카드 제목만 보고 정답을 알 수 있음 ❌
```

---

## 23. 최종 추리 제출 구조

### 23-1. 추천 UI

스튜디오 9는 서월채보다 인물과 물증이 많기 때문에, 가능하면 증거 선택 슬롯을 4개로 둔다.

```yaml
finalDeduction:
  culpritSelection: true
  selectedEvidenceCountRecommended: 4
  requiredTexts:
    - fatalLayerExplanation
    - fakeRefutationExplanation
    - motiveExplanation
```

추천 UI 순서:

```text
01. 범인 선택
02. 치명적 물리 수법 선택/서술
03. 핵심 증거 4개 선택
04. 페이크 증거가 왜 정답이 아닌지 설명
05. 동기 설명
06. 최종 제출
```

### 23-2. UI가 3개 증거 슬롯으로 고정된 경우

MVP 프론트가 3개 증거 선택만 지원한다면 아래 정책을 사용한다.

```yaml
fallbackIfEvidenceSlotIsThree:
  selectedEvidenceCount: 3
  required:
    - fatalLayerEvidenceAtLeastOne
    - motiveEvidenceAtLeastOne
    - fakeRefutationEvidenceOrTextRequired
  note: "Fake Refutation은 증거 선택 또는 서술 평가로 보완한다."
```

즉, 3개 슬롯이라면 다음이 가능하다.

```text
증거 1: 치명 수법 증거
증거 2: 동기 증거
증거 3: 페이크 기각 증거

또는

증거 1: 치명 수법 증거
증거 2: 치명 수법 보조 증거
증거 3: 동기 증거
+ 페이크 기각은 서술로 채점
```

---

## 24. 스튜디오 9 전용 Proof Dimension

### 24-1. 4축 구조

스튜디오 9는 아래 4축으로 채점한다.

```yaml
ProofDimension:
  CULPRIT:
    description: "백도윤을 죽인 진짜 조작자는 누구인가?"

  FATAL_LAYER:
    description: "추락의 직접 원인이 된 물리적 조작은 무엇인가?"

  FAKE_REFUTATION:
    description: "유력한 가짜 흉기가 왜 진짜 치명 경로가 아닌가?"

  MOTIVE:
    description: "진범이 백도윤을 죽일 동기는 무엇인가?"
```

### 24-2. 왜 Fake Refutation을 별도 축으로 두는가

스튜디오 9는 2명의 고정 비범인 페이크가 실제 위험한 물리 조작을 한다.

```text
제작총괄:
저가형 안전 클립 / 비승인 발주 / 하단 보조 안전망 문제

조연배우:
무광 겔 / 신발 밑창 흔적 / 주연배우 방해 의도
```

이 둘은 단순히 “동기만 있는 용의자”가 아니다.

```text
진짜 범죄에 가까운 조작을 했지만,
이번 사망의 최초/직접 치명 경로는 아니다.
```

따라서 플레이어는 단순히 “진범은 A다”만 맞히면 부족하다.

```text
“민루아의 겔이 왜 정답이 아닌가”
“강태오의 불량 클립이 왜 정답이 아닌가”
```

까지 설명할 수 있어야 고득점이다.

### 24-3. 점수 배분 초안

```yaml
scoring:
  total: 100

  culpritSelection:
    points: 30

  fatalLayerProof:
    points: 30
    description: "치명적 물리 수법과 핵심 물증을 정확히 설명"

  fakeRefutationProof:
    points: 20
    description: "가짜 흉기를 물리적으로 기각"

  motiveProof:
    points: 15

  reasoningConsistency:
    points: 5
```

서월채와 다른 점:

```text
서월채:
시간 / 섭취 / 접근 / 은폐 채점이 중요

스튜디오 9:
치명 물리층 / 페이크 기각 / 현장 감식 논리가 중요
```

---

## 25. Variant별 정답표 — v14 기준

### 25-1. VARIANT_ACTOR — 주연배우 / MARK 9 마킹 조작

```yaml
variantCode: VARIANT_ACTOR
culprit: "주연배우 / 서이라"

fatalLayer:
  summary: "MARK 9 테이프가 원래 기준보다 난간 쪽으로 옮겨 붙어 있었다."
  directCause: "백도윤은 안전한 MARK 9 위치라고 믿고 섰지만, 실제 위치는 난간에 가까워져 있었다."
```

핵심 증거:

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
    - EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE
  secondary:
    - EVIDENCE_MARK9_CURRENT_POSITION_PHOTO
    - EVIDENCE_ACTOR_DRESSING_ROOM_BLACK_TAPE
    - EVIDENCE_CONTINUITY_SKETCH_NOTE
```

페이크 기각:

```yaml
fakeRefutation:
  targetFake:
    - "민루아의 무광 겔"
    - "강태오의 불량 안전 클립"

  explanation:
    - "무광 겔은 실제로 존재하지만, 새 MARK 9 테이프가 그 겔 자국 위에서 들떠 있다."
    - "즉 겔이 먼저 남았고, 그 이후 누군가 MARK 9 테이프를 옮겨 붙였다."
    - "민루아의 겔은 주연배우 동선 방해용 페이크이고, 치명적인 위치 조작은 테이프 이동이다."
    - "불량 안전 클립은 하단 보조 안전망 문제로, 백도윤의 최초 균형 붕괴를 설명하지 못한다."
```

동기:

```yaml
motiveEvidence:
  primary:
    - EVIDENCE_ACTOR_CONTRACT_BLACKMAIL_MESSAGE
  explanation: "백도윤은 서이라의 해외 계약을 막고 과거 사적 자료를 이용해 계속 통제하려 했다."
```

다른 사람이 아닌 이유:

```text
- 메인 작가의 프롬프터 반사 증거는 결정적 후퇴 지시로 닫히지 않는다.
- 조명 필터는 비규격 교체의 결정적 상태가 아니다.
- 안전 잠금부는 하중 지지부 사전 손상으로 닫히지 않는다.
- 민루아의 겔은 테이프 밑/주변 선후관계 때문에 선행 페이크로 분리된다.
```

---

### 25-2. VARIANT_WRITER — 메인 작가 / 프롬프터·인이어 공황 유도

```yaml
variantCode: VARIANT_WRITER
culprit: "메인 작가 / 윤채린"

fatalLayer:
  summary: "프롬프터 반사 문구와 인이어 경고음이 결합되어 백도윤의 반사적 후퇴를 유도했다."
  directCause: "백도윤은 Actor POV 체크 중, 과노출 직전/동시에 비공식 지시와 급박한 경고음을 받아 뒤로 물러섰다."
```

중요 보정:

```text
프롬프터 텍스트 하나만으로 감독이 떨어지는 구조는 금지한다.
반드시 아래 3요소가 결합되어야 한다.

1. Actor POV 체크 전제
2. 공식 대본에 없는 프롬프터 반사 문구
3. 인이어에서 누설된 다급한 경고음 또는 찢어지는 소리
```

핵심 증거:

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
    - EVIDENCE_DIRECTOR_ACTOR_POV_CHECKLIST
    - EVIDENCE_SOUND_MIXER_AMBIENT_AUDIO_FRAGMENT
  secondary:
    - EVIDENCE_PROMPTER_REFLECTION_ENHANCED_STILL
    - EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH
    - EVIDENCE_WRITER_REVISION_NOTE
```

페이크 기각:

```yaml
fakeRefutation:
  targetFake:
    - "주연배우 MARK 9 테이프"
    - "민루아 무광 겔"
    - "강태오 불량 안전 클립"

  explanation:
    - "MARK 9 자체는 사건판에서 수상하지만, 기존 접착 잔여선과 현재 위치가 치명적 이동으로 닫히지 않는다."
    - "무광 겔은 주연배우 대기 동선 쪽에 길게 이어져 있으며, 백도윤의 실제 반사적 후퇴 원인은 프롬프터/인이어 쪽이다."
    - "불량 클립은 하단 안전망 문제이고, 백도윤이 뒤로 물러서게 만든 직접 원인이 아니다."
```

동기:

```yaml
motiveEvidence:
  primary:
    - EVIDENCE_OLD_MANUSCRIPT_CREDIT_FILE
  secondary:
    - EVIDENCE_WRITER_REVISION_NOTE
  explanation: "윤채린은 백도윤이 자신의 가족의 원고와 크레딧을 훔쳤다고 믿고 있었고, 마지막 회에서도 자신의 이름이 지워질 위기에 있었다."
```

다른 사람이 아닌 이유:

```text
- 조명은 시야를 무너뜨렸지만, 이 Variant에서는 백도윤을 뒤로 움직이게 만든 직접 지시는 프롬프터/인이어 조작이다.
- 액션 장비 훼손은 결정적 상태로 닫히지 않는다.
- 민루아의 겔과 강태오의 클립은 실제 위험하지만 백도윤의 반사적 후퇴를 설명하지 못한다.
```

---

### 25-3. VARIANT_LIGHTING_DP — 조명감독 / 비규격 필터와 과노출

```yaml
variantCode: VARIANT_LIGHTING_DP
culprit: "조명감독·촬영감독 / 류현규"

fatalLayer:
  summary: "Cue 9 백라이트가 정상 연출을 넘어선 플래시성 과노출로 변질되어 백도윤의 공간 인지를 무너뜨렸다."
  directCause: "표준 필터가 아닌 비규격 확산 필터와 MAX-BLIND 지시가 결합되어, 백도윤은 찰나에 시야와 방향 감각을 잃었다."
```

핵심 증거:

```yaml
fatalLayerEvidence:
  primary:
    - EVIDENCE_NONSTANDARD_DIFFUSION_FILTER_FRAME
    - EVIDENCE_WARPED_DIFFUSION_FILTER_CLOSEUP
    - EVIDENCE_MAX_BLIND_HANDWRITTEN_LIGHTING_MEMO
  secondary:
    - EVIDENCE_OVEREXPOSED_FRAME_SEQUENCE
    - EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT
```

페이크 기각:

```yaml
fakeRefutation:
  targetFake:
    - "프롬프터 반사 문구"
    - "MARK 9 테이프"
    - "안전 잠금부"
    - "불량 클립 / 무광 겔"

  explanation:
    - "프롬프터 반사와 MARK 9 흔적은 수상하지만, 백도윤을 직접 무너뜨린 물리층은 조명의 시각/공간 인지 붕괴다."
    - "안전장비는 정상처럼 보였으나 이 Variant에서는 내부 손상 결정타가 나오지 않는다."
    - "강태오의 클립과 민루아의 겔은 각각 하단 안전망/주연배우 동선 문제로, 3.4초 과노출의 직접 원인이 아니다."
```

동기:

```yaml
motiveEvidence:
  primary:
    - EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT
  explanation: "백도윤은 과거 조명 사고 책임을 류현규에게 넘기려 했고, 류현규는 자신이 백도윤의 영상미를 떠받쳐왔음에도 희생양이 될 위기에 있었다."
```

다른 사람이 아닌 이유:

```text
- 테이프 이동은 결정적 선후관계로 닫히지 않는다.
- 프롬프터 반사는 치명 후퇴 지시로 충분히 닫히지 않는다.
- 안전 잠금부는 외관상 수상하지만 내부 손상 결정타가 부족하다.
- 과노출 프레임과 비규격 필터, MAX-BLIND 메모가 서로 맞물리는 사람은 조명감독뿐이다.
```

---

### 25-4. VARIANT_STUNT — 액션감독 / 안전 잠금부 사전 훼손

```yaml
variantCode: VARIANT_STUNT
culprit: "액션감독·안전 코디네이터 / 남기준"

fatalLayer:
  summary: "겉으로 정상 결착된 안전장비의 내부 하중 지지부가 사전에 손상되어 있었다."
  directCause: "백도윤이 균형을 잃었을 때 장비가 잡아주어야 했지만, 내부 지지부가 버티지 못했다."
```

핵심 증거:

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
```

페이크 기각:

```yaml
fakeRefutation:
  targetFake:
    - "강태오의 저가 안전 클립"
    - "민루아의 무광 겔"
    - "조명 과노출"
    - "프롬프터 반사"

  explanation:
    - "강태오의 클립은 하단 보조 안전망 부품이고, 백도윤이 몸에 결착한 주 안전 잠금부와는 별도 계층이다."
    - "민루아의 겔은 실제로 위험하지만 최초 균형 붕괴 지점과 일치하지 않는다."
    - "조명과 프롬프터는 혼란을 만들었을 수 있으나, 정상이라면 추락을 막았어야 할 안전장치가 사전 손상되어 있었다."
```

동기:

```yaml
motiveEvidence:
  primary:
    - EVIDENCE_OLD_STUNT_ACCIDENT_FILE
  secondary:
    - EVIDENCE_SAFETY_CHECKLIST_OVERWRITE
  explanation: "남기준은 과거 촬영 사고 은폐의 공범이었고, 백도윤이 그 책임을 다시 자신에게 넘기려는 것을 알고 있었다."
```

다른 사람이 아닌 이유:

```text
- MARK 9 테이프나 프롬프터, 조명은 백도윤을 위험하게 만들 수 있지만, 안전장비가 정상이라면 사망까지 이어지지 않았을 가능성이 높다.
- 결정적 물리층은 '백도윤을 떨어뜨린 장치'가 아니라 '추락을 막아야 했는데 막지 못하게 만든 장치'다.
```

---

## 26. EvidenceVariantState 적용 대상 — v14 기준

v14 기준으로, 아래 증거들은 activeVariant에 따라 세부 텍스트가 달라질 수 있다.

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

나머지 증거는 기본 카드 텍스트를 유지한다.

```text
Default Evidence:
- title / image / baseDetail 동일
- activeVariant에 따라 internalRole과 scoring mapping만 달라질 수 있음
```

---

## 27. B-roll / 연속성 노트의 역할 재정의

### 27-1. 금지

```text
B-roll 카드 하나로 정답을 보여주면 안 된다.
B-roll은 완성된 정답지가 아니다.
```

### 27-2. 허용

```text
B-roll은 다른 물증을 해석하게 해주는 돋보기다.
```

예시:

```text
B-roll 스틸컷 단독:
“프롬프터 유리에 무언가 반사되어 있다.”

공식 대본과 비교:
“공식 대본에는 저 지시가 없다.”

Actor POV 체크리스트와 비교:
“백도윤은 실제로 저 시야를 확인하려고 Actor POV 위치에 섰다.”

인이어/현장 음향 파편과 비교:
“그 순간 백도윤이 반사적으로 물러설 조건이 갖춰져 있었다.”
```

즉:

```text
B-roll = 힌트
다른 증거와 결합 = 증명
```

---

## 28. 최종 추리 채점 예시

### 28-1. 고득점 예시 — 주연배우 Variant

```text
범인:
서이라

선택 증거:
- EVIDENCE_MARK9_TAPE_LIFTED_EDGE_GEL
- EVIDENCE_MARK9_OLD_ADHESIVE_RESIDUE
- EVIDENCE_ACTOR_CONTRACT_BLACKMAIL_MESSAGE
- EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE

서술:
민루아의 겔은 실제로 있었지만, MARK 9 테이프 끝이 그 겔 위에서 들떠 있었다.
즉 겔이 먼저였고, 그 이후 누군가 MARK 9를 옮겼다.
서이라는 백도윤에게 해외 계약을 막히고 있었고, 자신의 대기실에서 검은 테이프 조각도 나온다.
백도윤은 잘못 옮겨진 MARK 9를 안전한 위치라고 믿고 섰다가 추락했다.
```

채점:

```yaml
culpritSelection: full
fatalLayerProof: full
fakeRefutationProof: full
motiveProof: full
reasoningConsistency: full
```

### 28-2. 부분 점수 예시 — 조연배우 오답

```text
범인:
민루아

선택 증거:
- EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
- EVIDENCE_SUPPORTING_ACTOR_SHOE_GEL_TRACE
- EVIDENCE_SUPPORTING_ACTOR_CASTING_DEAL_MESSAGE

서술:
민루아가 겔을 남겨 백도윤을 미끄러지게 했다.
```

채점:

```yaml
culpritSelection: 0
fatalLayerProof: partial
fakeRefutationProof: 0
motiveProof: partial
reasoningConsistency: partial
```

피드백:

```text
민루아는 실제로 위험한 겔을 남겼지만, 테이프 끝이 그 겔 위에서 들떠 있다는 점이 중요합니다.
그 흔적은 겔 이후에 누군가 MARK 9 테이프를 옮겼음을 보여줍니다.
민루아는 나쁜 짓을 했지만, 백도윤을 치명 위치에 세운 사람은 아닙니다.
```

### 28-3. 부분 점수 예시 — 조명감독 Variant에서 B-roll만 보고 작가를 찍은 경우

```text
범인:
윤채린

선택 증거:
- EVIDENCE_PROMPTER_REFLECTION_BROLL_STILL
- EVIDENCE_FINAL_BLOCKING_SCRIPT_MISMATCH
- EVIDENCE_OLD_MANUSCRIPT_CREDIT_FILE
```

채점 기준:

```text
작가의 동기와 프롬프터 모순은 잘 봤지만,
해당 Variant의 결정적 물리층이 비규격 필터 / MAX-BLIND 조명 세팅이라면 고득점 불가.
```

피드백:

```text
프롬프터 반사는 수상하지만,
이번 판에서 백도윤을 직접 무너뜨린 물리층은 조명의 비정상 세팅입니다.
B-roll은 정답지가 아니라 현장 자료 중 하나입니다.
```

---

## 29. 힌트 구조 — 스튜디오 9 전용

### 29-1. 힌트 1

```text
누가 백도윤을 미워했는지만 보지 말고,
현장에 남은 물리적 흔적이 어떤 순서로 생겼는지 보자.
```

### 29-2. 힌트 2

```text
가장 수상한 물건이 반드시 진짜 흉기는 아니다.
오일 자국, 불량 클립, 테이프, 필터, 잠금부 중
“추락의 최초 원인”과 “사망을 막지 못한 원인”을 구분해야 한다.
```

### 29-3. 힌트 3

```text
B-roll과 연속성 노트는 정답을 말해주지 않는다.
현장 사진과 비교해서, 어떤 흔적이 다른 흔적 위에 생겼는지 확인하자.
```

---

## 30. v14 판정

```yaml
scenario2V14Status:
  target: "최종 추리 / 채점 기준 / Proof Dimension 재설계"
  mainDeductionMode: "물리 증거 기반 현장 감식"
  proofDimensions:
    - CULPRIT
    - FATAL_LAYER
    - FAKE_REFUTATION
    - MOTIVE
  evidenceSlotRecommended: 4
  fallbackIfEvidenceSlotIsThree: true
  spawnSnipePrevention: "all key cards exist in every Variant; detail state changes only"
  result: "FINAL_DEDUCTION_SCORING_DRAFT_COMPLETE"
  overallStatus: "CONDITIONAL_PASS"
```

v14 기준으로 스튜디오 9는 이제 다음 상태다.

```text
- 로그 마스터키 구조 폐기 유지
- 물리 증거 중심 최종 채점 구조 확정
- Fake Refutation을 별도 채점 축으로 도입
- 카드 스폰 메타 방지 정책 추가
- B-roll을 정답지가 아니라 비교용 돋보기로 제한
- 작가 Variant의 “감독 바보 문제”를 Actor POV + 프롬프터 + 인이어 공황 조건으로 보정
```

다음 단계는 `증거 코드 정본화 / Variant Role Matrix / 구현용 Canon 압축`이다.


---

## 31. v15 — 오답 시 채점 종속성 / Scoring Dependency 확정

> 목적: v14에서 확정한 `CULPRIT / FATAL_LAYER / FAKE_REFUTATION / MOTIVE` 4축 채점 구조에 대해, **진범 선택이 틀렸을 때 나머지 점수를 어떻게 처리할지** 결정한다.  
> 핵심 원칙: 스튜디오 9는 “범인만 맞히기” 게임이 아니지만, 동시에 “범인을 틀렸는데 S급에 가까운 점수”가 나오면 안 된다.

---

### 31-1. 최종 정책

```text
진범을 틀렸다고 전체 0점 처리하지 않는다.
하지만 진범을 틀린 상태에서는 고득점 상한을 강하게 건다.
```

이유:

```text
- 플레이어가 민루아의 겔이나 강태오의 불량 클립을 정확히 기각했다면, 그 추리 행위는 인정할 가치가 있다.
- 하지만 최종적으로 백도윤을 죽인 조작자를 틀렸다면 사건 재구성은 실패다.
- 따라서 일부 관찰 점수는 주되, 사건 해결 점수는 제한한다.
```

즉, `Fake Refutation`은 **반독립 점수**다.

```text
Culprit이 틀려도 일부 인정 가능
하지만 Culprit이 틀리면 Fake Refutation 만점은 불가
```

---

### 31-2. 점수 종속성 요약

```yaml
scoringDependencyPolicy:
  total: 100

  ifCulpritCorrect:
    maxScore: 100
    fatalLayerProof: "full"
    fakeRefutationProof: "full"
    motiveProof: "full"
    reasoningConsistency: "full"

  ifCulpritWrong:
    maxScore: 45
    culpritSelection: 0
    fatalLayerProof: "max 10 only if the actual fatal layer is identified"
    fakeRefutationProof: "max 12"
    motiveProof: "max 5 only if related to actual culprit or correctly observed hidden motive"
    reasoningConsistency: "max 3"

  ifCulpritWrongButFatalLayerCorrect:
    maxScore: 55
    description: "흉기는 맞췄지만 조작자를 틀린 경우"

  ifCulpritCorrectButFatalLayerWrong:
    maxScore: 70
    description: "범인은 맞췄지만 이번 사건의 직접 물리 수법을 틀린 경우"

  ifCulpritCorrectAndFatalLayerCorrectButNoFakeRefutation:
    maxScore: 80
    description: "범인과 수법은 맞췄지만 스튜디오 9의 핵심인 페이크 기각을 못 한 경우"

  ifNeutralWitnessSelected:
    maxScore: 35
    description: "고은별 선택. 자료 은닉은 맞지만 치명 장치 조작 없음"

  ifPermanentRedHerringSelected:
    maxScore: 40
    description: "민루아/강태오 선택. 위험한 조작은 맞지만 직접 치명 경로는 아님"
```

---

### 31-3. 왜 전체 0점 처리는 하지 않는가?

전체 0점 처리는 직관적으로는 깔끔하지만, 스튜디오 9에는 맞지 않는다.

스튜디오 9의 핵심 플레이는:

```text
1. 진짜 치명 물리층 찾기
2. 페이크 물리층 기각하기
3. 그 물리층을 조작할 수 있는 인물 연결하기
```

이다.

따라서 플레이어가 다음을 맞혔다면 일부 보상해야 한다.

```text
- 민루아의 겔은 진짜 사인이 아니다
- 강태오의 불량 클립은 직접 원인이 아니다
- B-roll은 정답지가 아니라 비교용 자료다
- 들뜬 테이프 / 비규격 필터 / 내부 잠금부 / 프롬프터 반사 중 하나의 의미를 일부 파악했다
```

하지만 최종 범인을 틀렸다면 다음은 실패한 것이다.

```text
- 누가 백도윤의 마지막 컷을 설계했는가
- 어떤 물리층이 직접 사망 원인인가
- 동기와 물증이 같은 사람에게 닫히는가
```

따라서:

```text
전체 0점 ❌
하지만 고득점 불가 ⭕
```

로 처리한다.

---

### 31-4. 구체 사례 — 실제 정답은 액션감독인데 조명감독을 고른 경우

상황:

```yaml
activeVariant: VARIANT_STUNT
actualCulprit: SUSPECT_STUNT_DIRECTOR
playerSelectedCulprit: SUSPECT_LIGHTING_DP
```

플레이어 제출:

```text
범인:
조명감독 류현규

선택 증거:
- EVIDENCE_NONSTANDARD_DIFFUSION_FILTER
- EVIDENCE_MATTE_GEL_SMEAR_ON_TAPE_LINE
- EVIDENCE_CHEAP_SAFETY_CLIP_FRAGMENT
- EVIDENCE_LIGHTING_ACCIDENT_BLAME_DRAFT

서술:
조명 세팅이 수상하고, 민루아의 겔과 강태오의 불량 클립은 직접 사인은 아니라고 판단.
```

채점:

```yaml
culpritSelection: 0 / 30
fatalLayerProof: 0~5 / 30
  reason: "비규격 필터는 수상하지만 activeVariant의 실제 fatal layer는 내부 잠금부 손상"

fakeRefutationProof: 8~12 / 20
  reason: "민루아 겔과 강태오 클립을 가짜 흉기로 기각한 추리는 일부 정확함"

motiveProof: 0~5 / 15
  reason: "조명감독 동기를 썼다면 이번 판의 실제 동기와 다름"

reasoningConsistency: 1~3 / 5
  reason: "페이크 기각은 일관되나 최종 범인/수법 연결이 틀림"

maxScoreCap: 45
```

피드백 문구:

```text
민루아의 겔과 강태오의 불량 클립이 직접 사인이 아니라는 판단은 날카로웠습니다.
하지만 이번 사건에서 백도윤을 실제로 추락에서 지켜주지 못하게 만든 치명 물리층은 조명 필터가 아니라 안전 잠금부였습니다.
조명 증거는 수상한 흔적이지만, 실제 추락을 막아야 할 장치의 내부 손상과 연결되지 않았습니다.
따라서 페이크 기각 일부는 인정되지만, 최종 범인과 치명 수법은 오답입니다.
```

---

### 31-5. 구체 사례 — 진범은 맞혔지만 페이크 기각을 못 한 경우

상황:

```yaml
activeVariant: VARIANT_ACTOR
actualCulprit: SUSPECT_LEAD_ACTOR
playerSelectedCulprit: SUSPECT_LEAD_ACTOR
```

플레이어가 맞힌 것:

```text
- 범인: 주연배우
- 치명 수법: MARK 9 테이프 조작
```

하지만 빠뜨린 것:

```text
- 민루아의 겔이 왜 직접 사인이 아닌지 설명하지 않음
- 강태오의 불량 클립이 왜 직접 사인이 아닌지 설명하지 않음
```

채점:

```yaml
culpritSelection: 30 / 30
fatalLayerProof: 25~30 / 30
fakeRefutationProof: 0~8 / 20
motiveProof: 10~15 / 15
reasoningConsistency: 3~5 / 5
maxScoreCap: 80
```

피드백 문구:

```text
주연배우가 MARK 9 위치를 조작했다는 핵심 추리는 맞았습니다.
다만 스튜디오 9에서는 진짜 흉기를 찾는 것만큼, 강한 페이크 흉기를 기각하는 과정도 중요합니다.
민루아의 겔과 강태오의 불량 클립이 왜 직접 사인이 아닌지 설명하지 못했기 때문에, 최고 등급에는 도달하지 못했습니다.
```

---

### 31-6. 구체 사례 — 범인은 틀렸지만 실제 Fatal Layer는 맞힌 경우

드문 케이스지만 가능하다.

예:

```text
실제 정답:
액션감독

플레이어 선택:
제작총괄

플레이어 서술:
백도윤은 추락을 막아야 할 안전 계층이 작동하지 않아 사망했다.
하단 안전망보다 상단 결착 장치 쪽 손상이 더 중요하다.
```

이 경우:

```text
범인은 틀림
하지만 fatal layer의 방향은 일부 맞음
```

채점:

```yaml
culpritSelection: 0 / 30
fatalLayerProof: 5~10 / 30
fakeRefutationProof: 8~12 / 20
motiveProof: 0~5 / 15
reasoningConsistency: 1~3 / 5
maxScoreCap: 55
```

피드백:

```text
안전 계층이 핵심이라는 판단은 맞았습니다.
하지만 하단 안전망을 바꾼 강태오와, 상단 결착 장치를 직접 관리한 남기준은 다른 책임 계층에 있습니다.
이번 사건의 직접 치명층은 불량 클립이 아니라 백도윤의 몸을 실제로 잡아줘야 했던 결착 장치였습니다.
```

---

### 31-7. 고정 비범인 선택 시 처리

#### 민루아를 범인으로 선택한 경우

```yaml
selectedCulprit: RED_HERRING_SUPPORTING_ACTOR
maxScoreCap: 40
```

인정 가능한 부분:

```text
- 겔 자국을 찾음
- 신발 밑창 흔적을 연결함
- 주연배우 방해 의도를 이해함
```

불인정:

```text
- 백도윤을 직접 MARK 9 위험 위치에 세운 행위
- 실제 치명 물리층 조작
```

오답 피드백:

```text
민루아는 무광 겔을 남긴 위험한 인물이 맞습니다.
하지만 겔은 백도윤의 최초 균형 붕괴 지점과 완전히 일치하지 않았고,
오히려 그 겔 위로 MARK 9 테이프 끝이 들뜬 흔적이 남아 있었습니다.
이는 민루아가 겔을 남긴 이후, 다른 누군가가 MARK 9 위치를 다시 조작했다는 뜻입니다.
그녀는 악의적인 방해자였지만, 백도윤을 그 위치에 세운 진범은 아닙니다.
```

#### 강태오를 범인으로 선택한 경우

```yaml
selectedCulprit: RED_HERRING_PRODUCER
maxScoreCap: 40
```

인정 가능한 부분:

```text
- 불량 클립과 비승인 발주서를 찾음
- 하단 보조 안전망의 위험성을 이해함
- 제작비 차액/보험 문제 동기를 파악함
```

불인정:

```text
- 최초 균형 붕괴 원인
- 백도윤의 몸을 직접 지지해야 했던 장치의 훼손
```

오답 피드백:

```text
강태오가 저가형 안전 클립으로 하단 보조 안전망을 위험하게 만든 것은 사실입니다.
하지만 하단 안전망은 백도윤이 처음 균형을 잃는 순간을 만든 장치가 아닙니다.
이번 사건의 직접 치명층은 추락을 예방해야 할 상단 장치, 위치 표시, 조명 세팅, 또는 시청각 지시 중 하나였습니다.
강태오는 범죄에 가까운 은폐와 횡령을 했지만, 백도윤의 마지막 움직임을 설계한 사람은 아닙니다.
```

#### 고은별을 범인으로 선택한 경우

```yaml
selectedCulprit: WITNESS_SCRIPT_SUPERVISOR
maxScoreCap: 35
```

인정 가능한 부분:

```text
- B-roll / 연속성 노트 은닉
- 공식본과 원본 자료의 차이
- 자료 제출 지연
```

불인정:

```text
- 현장 물리층 조작
- MARK 9 / 프롬프터 / 조명 / 안전장비 직접 조작
```

오답 피드백:

```text
고은별은 사건의 원본 자료를 숨긴 수상한 인물입니다.
하지만 그녀가 MARK 9 테이프, 프롬프터 지시, 조명 필터, 안전 잠금부 중 어느 하나를 직접 조작했다는 증거는 없습니다.
그녀가 숨긴 것은 범행 그 자체가 아니라, 범행을 해석할 수 있는 원본 자료였습니다.
```

---

### 31-8. Reasoning Consistency 종속 규칙

`ReasoningConsistency`는 독립 보너스가 아니다.

```text
범인과 치명 수법이 모두 틀린 경우:
max 1~2점

범인은 틀렸지만 페이크 기각이 논리적인 경우:
max 3점

범인은 맞고 치명 수법도 맞지만 일부 설명이 빠진 경우:
3~5점

범인/수법/페이크/동기가 모두 맞는 경우:
5점
```

즉:

```text
그럴듯한 글을 썼다고 일관성 5점 ❌
정답 구조와 모순 없이 연결될 때 일관성 5점 ⭕
```

---

### 31-9. 랭크 산정 기준

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

주의:

```text
culpritCorrect=false인 경우 S/A/B 불가.
fatalLayerCorrect=false인 경우 S/A 불가.
fakeRefutation이 0이면 S 불가.
```

---

### 31-10. v15 판정

```yaml
scenario2V15Status:
  target: "오답 시 채점 종속성 / scoring dependency"
  scoringMode: "physical evidence based deduction"
  hardZeroOnWrongCulprit: false
  culpritWrongMaxScore: 45
  culpritWrongButFatalLayerCorrectMaxScore: 55
  culpritCorrectFatalLayerWrongMaxScore: 70
  culpritCorrectFatalLayerCorrectNoFakeRefutationMaxScore: 80
  permanentRedHerringSelectedMaxScore: 40
  neutralWitnessSelectedMaxScore: 35
  result: "SCORING_DEPENDENCY_POLICY_COMPLETE"
  overallStatus: "PASS"
```

이제 스튜디오 9는 다음 상태다.

```text
- 최종 추리 4축 확정
- Fake Refutation 특수 채점 축 확정
- 오답 시 부분점수 / 점수 상한 정책 확정
- 고정 비범인 / 중립 참고인 오답 피드백 확정
- S/A/B/C/D 랭크 종속 조건 확정
```

다음 단계는 정말로 `증거 코드 정본화 / Variant Role Matrix / 구현용 Canon 압축`이다.
