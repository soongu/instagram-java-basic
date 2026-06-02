package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/ImageItem.java
// 과제 3 — 이미지 미디어예요. MediaItem 의 빈칸 셋(getType·preview·isPlayable)을 모두 채워요.
// 이미지는 재생하는 게 아니라 보는 거라 isPlayable() 은 false 예요.
public class ImageItem extends MediaItem {

    private String imageUrl;

    public ImageItem(String authorName, String imageUrl) {
        super(authorName);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getType() {
        return "이미지";
    }

    @Override
    public String preview() {
        return getAuthorName() + " 님의 사진: " + imageUrl;
    }

    // 이미지는 재생 대상이 아니에요 — false
    @Override
    public boolean isPlayable() {
        return false;
    }
}
