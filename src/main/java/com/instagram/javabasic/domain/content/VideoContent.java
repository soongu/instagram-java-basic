package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/VideoContent.java
// 영상 콘텐츠 — 콘텐츠의 또 다른 종류예요. Content 를 물려받아 공통 정보는 그대로 쓰고,
// 영상만의 정보(영상 주소·재생 시간)를 더한 뒤 부모의 빈칸 두 개를 영상에 맞게 채워요.
public class VideoContent extends Content {

    // 영상만 추가로 갖는 정보
    private String videoUrl;        // 영상 주소
    private int durationSeconds;    // 재생 시간(초)

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
}
