package com.instagram.javabasic.domain.comment;

// com/instagram/javabasic/domain/comment/Comment.java
// 댓글 한 개를 표현하는 "불변(immutable)" 클래스예요.
// 불변이란 — 한 번 만들어진 객체의 값이 절대 바뀌지 않는다는 뜻이에요.
// 그래서 모든 필드에 final 을 붙여 "다시 대입 금지" 로 잠그고,
// 값을 넣는 통로(생성자)는 하나만 두고, 값을 바꾸는 setter 는 아예 두지 않아요.
// 좋아요를 누르고 싶으면? 원본을 고치는 대신 좋아요가 1 늘어난 "새 댓글" 을 만들어요.
public class Comment {

    // 모든 필드가 final — 생성자에서 한 번 정해지면 다시는 못 바꿔요
    private final String author;
    private final String text;
    private final int likeCount;

    // 값을 넣는 유일한 통로 — 생성자 하나뿐이에요
    public Comment(String author, String text, int likeCount) {
        this.author = author;
        this.text = text;
        this.likeCount = likeCount;
    }

    // ===== 읽기 전용: getter 만 있고 setter 는 없어요 =====
    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public int getLikeCount() {
        return likeCount;
    }

    // 불변 객체에서 "값을 바꾸는" 방법 — 자기 자신은 그대로 두고,
    // 좋아요가 1 늘어난 새 Comment 를 만들어 돌려줘요.
    public Comment withLike() {
        return new Comment(author, text, likeCount + 1);
    }

    @Override
    public String toString() {
        return "@" + author + ": " + text + " (좋아요 " + likeCount + ")";
    }

    // equals — 작성자와 내용이 모두 같으면 "같은 댓글" 로 봐요 (좋아요 수는 비교 제외)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment other = (Comment) obj;
        return author != null && author.equals(other.author)
                && text != null && text.equals(other.text);
    }
}
