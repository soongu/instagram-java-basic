package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/MediaItem.java
// 과제 3 — "추상 메서드를 하나 더 추가하면 어떤 일이 벌어지나" 를 체감하는 부모예요.
// 기존 Content 는 빈칸이 둘(getType·preview) 이었죠. 여기서는 거기에 isPlayable() 까지 더해
// 빈칸이 셋이 돼요. 그러면 이 부모를 물려받는 모든 자식은 셋을 전부 채워야 해요.
// 하나라도 빠뜨리면 "abstract method ... is not implemented" 컴파일 에러가 나요.
// 그게 추상 메서드 추가의 파급이에요 — 부모가 빈칸을 늘리면 모든 자식이 구현을 강제당해요.
public abstract class MediaItem {

    // 모든 미디어가 공통으로 갖는 정보 — 자식이 super(...) 로 채워요.
    private String authorName;

    public MediaItem(String authorName) {
        this.authorName = authorName;
    }

    // ===== abstract 메서드 셋 — 자식이 모두 채워야 하는 빈칸이에요 =====

    // 종류 이름 — "이미지" / "영상" / "텍스트"
    public abstract String getType();

    // 짧은 미리보기
    public abstract String preview();

    // 새로 추가한 빈칸 — 재생 가능한 미디어인가? (영상만 true)
    // 이 한 줄을 부모에 더하는 순간, 아래 세 자식 전부 isPlayable() 을 구현해야 해요.
    public abstract boolean isPlayable();

    public String getAuthorName() {
        return authorName;
    }
}
