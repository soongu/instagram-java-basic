package com.instagram.javabasic.domain.post;

// com/instagram/javabasic/domain/post/PostStatus.java
// 게시물의 상태예요. 공개·비공개·보관됨 세 가지로 정해져 있어요.
// 등급(Grade)처럼 상수마다 데이터(한글 이름·공유 가능 여부)를 갖고,
// 거기에 더해 "행동(메서드)" 까지 enum 안에 직접 정의했어요.
// 상태에 관한 규칙을 한 곳에 모아두면, 쓰는 쪽 코드가 훨씬 깔끔해져요.
public enum PostStatus {
    PUBLIC("공개", true),
    PRIVATE("비공개", false),
    ARCHIVED("보관됨", false);

    private final String displayName;
    private final boolean shareable;

    PostStatus(String displayName, boolean shareable) {
        this.displayName = displayName;
        this.shareable = shareable;
    }

    public String getDisplayName() {
        return displayName;
    }

    // 공유할 수 있는 상태인지 — 생성자에서 정해둔 값을 그대로 알려줘요.
    public boolean canShare() {
        return shareable;
    }

    // 수정할 수 있는 상태인지 — 보관된 글만 수정 불가예요.
    // enum 메서드 안에서 this 는 "지금 이 상수" 를 가리켜요(예: PUBLIC).
    public boolean isEditable() {
        return this != ARCHIVED;
    }
}
