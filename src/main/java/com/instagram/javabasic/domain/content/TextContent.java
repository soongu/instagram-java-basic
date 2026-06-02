package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/TextContent.java
// 텍스트 콘텐츠 — 글만 있는 콘텐츠예요. Content 를 물려받아 공통 정보는 그대로 쓰고,
// 본문 글을 더한 뒤 부모의 빈칸 두 개를 텍스트에 맞게 채워요.
// 여기가 역할 분화의 핵심이에요 — 텍스트는 댓글은 받지만(Commentable) 공유는 안 해요(Shareable 없음).
// 그래서 implements 에 Commentable 만 적어요.
public class TextContent extends Content implements Commentable {

    // 텍스트만 추가로 갖는 정보 — 게시글 본문
    private String body;

    // 댓글 역할(Commentable)을 위해 필요한 정보 — 댓글 수와 마지막 댓글 내용
    private int commentCount;
    private String lastComment;

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

    // Commentable 의 약속을 채워요 — 댓글 달기.
    // 한도에 찼으면(Commentable.isFull) 더 받지 않고 그냥 돌아가요.
    // 텍스트는 Shareable 을 받지 않았으니 getShareUrl 은 만들지 않아요.
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
