package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/VideoItem.java
// 과제 3 — 영상 미디어예요. MediaItem 의 빈칸 셋을 모두 채워요.
// 영상은 재생할 수 있으니 isPlayable() 은 true 예요 — 셋 중 유일하게 true 인 종류죠.
public class VideoItem extends MediaItem {

    private String videoUrl;
    private int durationSeconds;

    public VideoItem(String authorName, String videoUrl, int durationSeconds) {
        super(authorName);
        this.videoUrl = videoUrl;
        this.durationSeconds = durationSeconds;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    @Override
    public String getType() {
        return "영상";
    }

    @Override
    public String preview() {
        return getAuthorName() + " 님의 영상 (" + durationSeconds + "초)";
    }

    // 영상은 재생할 수 있어요 — true
    @Override
    public boolean isPlayable() {
        return true;
    }
}
