package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/ImageContent.java
// 이미지 콘텐츠 — 콘텐츠의 한 종류예요. Content 를 물려받아(extends) 공통 정보(작성자·좋아요)는
// 그대로 쓰고, 부모가 남겨둔 빈칸(getType·preview) 두 개를 이미지에 맞게 채워요.
public class ImageContent extends Content {

    // 이미지만 추가로 갖는 정보 — 사진 주소
    private String imageUrl;

    // 생성자 — 첫 줄 super(...) 로 부모의 공통 필드(작성자·좋아요)를 먼저 채우고,
    // 그 다음 이미지만의 필드를 채워요.
    public ImageContent(String authorName, int likeCount, String imageUrl) {
        super(authorName, likeCount);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // 부모의 빈칸을 채워요 — 이미지의 종류 이름
    @Override
    public String getType() {
        return "이미지";
    }

    // 부모의 빈칸을 채워요 — 이미지는 작성자와 사진 주소를 미리보기로 보여줘요
    @Override
    public String preview() {
        return getAuthorName() + " 님의 사진: " + imageUrl;
    }
}
