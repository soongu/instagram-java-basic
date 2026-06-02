package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/ImageContent.java
// 이미지 콘텐츠 — 콘텐츠의 한 종류예요. Content 를 물려받아(extends) 공통 정보(작성자·좋아요)는
// 그대로 쓰고, 부모가 남겨둔 빈칸(getType·preview) 두 개를 이미지에 맞게 채워요.
// 여기에 더해 두 가지 역할(Shareable·Commentable)도 함께 받아요 — 이미지는 공유도 되고
// 댓글도 달 수 있으니까요. extends 는 하나뿐이지만 implements 는 여러 개를 한꺼번에 받을 수 있어요.
public class ImageContent extends Content implements Shareable, Commentable {

    // 이미지만 추가로 갖는 정보 — 사진 주소
    private String imageUrl;

    // 댓글 역할(Commentable)을 위해 필요한 정보 — 댓글 수와 마지막 댓글 내용
    private int commentCount;
    private String lastComment;

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

    // Shareable 의 약속을 채워요 — 이미지의 공유 링크 주소.
    // share() 는 default 라 따로 만들 필요 없이 자동으로 물려받아요.
    @Override
    public String getShareUrl() {
        return "instagram.com/p/img-" + getAuthorName();
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
