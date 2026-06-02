package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/VideoContent.java
// 영상 콘텐츠 — 콘텐츠의 또 다른 종류예요. Content 를 물려받아 공통 정보는 그대로 쓰고,
// 영상만의 정보(영상 주소·재생 시간)를 더한 뒤 부모의 빈칸 두 개를 영상에 맞게 채워요.
// 이미지와 마찬가지로 공유·댓글 두 역할(Shareable·Commentable)을 함께 받아요.
public class VideoContent extends Content implements Shareable, Commentable {

    // 영상만 추가로 갖는 정보
    private String videoUrl;        // 영상 주소
    private int durationSeconds;    // 재생 시간(초)

    // 댓글 역할(Commentable)을 위해 필요한 정보 — 댓글 수와 마지막 댓글 내용
    private int commentCount;
    private String lastComment;

    // 생성자 — super(...) 로 공통 필드를 먼저 채우고, 영상만의 필드를 채워요.
    public VideoContent(String authorName, int likeCount, String videoUrl, int durationSeconds) {
        super(authorName, likeCount);
        this.videoUrl = videoUrl;
        this.durationSeconds = durationSeconds;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    // 부모의 빈칸을 채워요 — 영상의 종류 이름
    @Override
    public String getType() {
        return "영상";
    }

    // 부모의 빈칸을 채워요 — 영상은 재생 시간을 미리보기에 함께 보여줘요
    @Override
    public String preview() {
        return getAuthorName() + " 님의 영상 (" + durationSeconds + "초)";
    }

    // Shareable 의 약속을 채워요 — 영상의 공유 링크 주소.
    // share() 는 default 라 따로 만들 필요 없이 자동으로 물려받아요.
    @Override
    public String getShareUrl() {
        return "instagram.com/p/vid-" + getAuthorName();
    }

    // Commentable 의 약속을 채워요 — 댓글 달기.
    // 한도에 찼으면(Commentable.isFull) 더 받지 않고 그냥 돌아가요.
    @Override
    public void addComment(String text) {
        if (Commentable.isFull(commentCount)) return;
        this.lastComment = text;
        this.commentCount++;
    }

    // Commentable 의 약속을 채워요 — 현재 댓글 수
    @Override
    public int getCommentCount() {
        return commentCount;
    }

    // 마지막으로 달린 댓글을 확인하는 일반 메서드 (인터페이스 약속은 아니에요)
    public String getLastComment() {
        return lastComment;
    }
}
