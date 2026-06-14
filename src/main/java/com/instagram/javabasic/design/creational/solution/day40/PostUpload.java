package com.instagram.javabasic.design.creational.solution.day40;

// com/instagram/javabasic/design/creational/solution/day40/PostUpload.java
// 과제 2 — 게시물 업로드 옵션. caption 은 필수, 나머지는 선택이라 Builder 로 조립해요.
// 필드가 전부 final 이라 한 번 만들면 바뀌지 않아요(불변).
public class PostUpload {

    private final String caption;           // 필수 — 캡션
    private final String location;          // 선택 — 위치 태그
    private final boolean commentsEnabled;  // 선택 — 댓글 허용 여부
    private final boolean hideLikeCount;    // 선택 — 좋아요 수 숨김 여부

    private PostUpload(Builder builder) {
        this.caption = builder.caption;
        this.location = builder.location;
        this.commentsEnabled = builder.commentsEnabled;
        this.hideLikeCount = builder.hideLikeCount;
    }

    public static Builder builder(String caption) {
        return new Builder(caption);
    }

    public String getCaption() {
        return caption;
    }

    public String getLocation() {
        return location;
    }

    public boolean isCommentsEnabled() {
        return commentsEnabled;
    }

    public boolean isHideLikeCount() {
        return hideLikeCount;
    }

    public static class Builder {

        private final String caption;             // 필수 — 입구에서 받아요
        private String location = "";              // 선택 — 기본: 위치 없음
        private boolean commentsEnabled = true;    // 선택 — 기본: 댓글 허용
        private boolean hideLikeCount = false;     // 선택 — 기본: 좋아요 수 보임

        private Builder(String caption) {
            this.caption = caption;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder commentsEnabled(boolean commentsEnabled) {
            this.commentsEnabled = commentsEnabled;
            return this;
        }

        public Builder hideLikeCount(boolean hideLikeCount) {
            this.hideLikeCount = hideLikeCount;
            return this;
        }

        public PostUpload build() {
            return new PostUpload(this);
        }
    }
}
