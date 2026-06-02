package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/Commentable.java
// "댓글을 달 수 있다" 는 역할을 약속하는 인터페이스예요. Shareable 과 마찬가지로
// Content 부모와는 따로 노는 역할이라, 콘텐츠 종류와 상관없이 이 역할만 받아갈 수 있어요.
public interface Commentable {

    // 인터페이스 안의 변수는 자동으로 public static final 이에요(상수).
    // 한 콘텐츠에 달 수 있는 댓글의 최대 개수예요.
    int MAX_COMMENTS = 100;

    // 약속 — 댓글을 단다 / 현재 댓글 수를 돌려준다. 본문은 구현 클래스가 채워요.
    void addComment(String text);
    int getCommentCount();

    // static 메서드 — 객체 없이 인터페이스 이름으로 바로 부를 수 있어요(Commentable.isFull(...)).
    // 지금 댓글 수가 한도에 찼는지 검사하는 공통 도우미예요.
    static boolean isFull(int currentCount) {
        return currentCount >= MAX_COMMENTS;
    }
}
