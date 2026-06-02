package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/TextContent.java
// 텍스트 콘텐츠 — 글만 있는 콘텐츠예요. Content 를 물려받아 공통 정보는 그대로 쓰고,
// 본문 글을 더한 뒤 부모의 빈칸 두 개를 텍스트에 맞게 채워요.
public class TextContent extends Content {

    // 텍스트만 추가로 갖는 정보 — 게시글 본문
    private String body;

    // 생성자 — super(...) 로 공통 필드를 먼저 채우고, 본문을 채워요.
    public TextContent(String authorName, int likeCount, String body) {
        super(authorName, likeCount);
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    // 부모의 빈칸을 채워요 — 텍스트의 종류 이름
    @Override
    public String getType() {
        return "텍스트";
    }

    // 부모의 빈칸을 채워요 — 본문이 길면 앞 10글자만 잘라 "..." 를 붙여 미리보기로 보여줘요.
    @Override
    public String preview() {
        if (body.length() > 10) {
            return body.substring(0, 10) + "...";
        }
        return body;
    }
}
