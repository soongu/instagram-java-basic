package com.instagram.javabasic.solution.day16;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/solution/day16/MemberAwareComment.java
// [응용] 과제 1 예시답안 — 댓글에 작성자를 Member 객체로 직접 연결한 댓글이에요.
// 기존 Comment 는 작성자를 이름 문자열(author)로만 가지고 있었어요.
// 그래서 댓글만 보고는 "이 사람의 추천 점수가 몇 점인지" 같은 건 따라갈 수 없었죠.
// 여기서는 이름 문자열은 그대로 두고, 옆에 commenter(Member 객체)를 함께 둬요.
// Post 가 작성자를 Member 로 가리킨 패턴(Step 2)을 댓글에 똑같이 적용한 거예요.
public class MemberAwareComment {

    // 작성자 이름 — 예전 방식 그대로 글자 그 자체로 보관해요.
    private String author;
    // 댓글 내용
    private String text;
    // 좋아요 수
    private int likeCount;

    // 작성자를 Member 객체로 직접 가리켜요(참조). author 는 그 사람의 이름을 베껴둔 값이에요.
    private Member commenter;

    // 예전처럼 이름만 받는 생성자 — Member 연결 없이 이름만으로도 만들 수 있어요.
    public MemberAwareComment(String author, String text, int likeCount) {
        this.author = author;
        this.text = text;
        this.likeCount = likeCount;
    }

    // Member 객체를 받는 생성자 — 도메인 연결의 핵심이에요.
    // 객체를 받는 순간, 그 사람의 이름(getUsername())을 author 에도 똑같이 베껴둬요.
    // 그래서 "객체로 따라가기(commenter)" 와 "이름만 보기(author)" 가 늘 같은 사람을 가리켜요.
    public MemberAwareComment(Member commenter, String text, int likeCount) {
        this.commenter = commenter;
        this.author = commenter.getUsername();
        this.text = text;
        this.likeCount = likeCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public int getLikeCount() {
        return likeCount;
    }

    // 작성자 객체를 그대로 돌려줘요 — 이걸로 작성자의 점수·등급까지 따라갈 수 있어요.
    public Member getCommenter() {
        return commenter;
    }

    @Override
    public String toString() {
        return "@" + author + ": " + text + " (좋아요 " + likeCount + ")";
    }
}
