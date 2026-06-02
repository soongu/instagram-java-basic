package com.instagram.javabasic.solution.day14;

// com/instagram/javabasic/solution/day14/PostCard.java
// 과제 1 — 게시물을 '화면에 보여주는' 책임만 맡는 클래스예요.
// 데이터를 저장·관리하는 일(PostData)과 떼어 놓아서, 표시 방식이 바뀌어도
// 데이터 쪽 코드는 건드릴 필요가 없어요. 책임이 하나라서 고치기 쉬워요.
public class PostCard {

    private String writer;
    private String content;
    private int likeCount;

    public PostCard(String writer, String content, int likeCount) {
        this.writer = writer;
        this.content = content;
        this.likeCount = likeCount;
    }

    // 게시물을 한 줄 카드 문구로 꾸며서 돌려줘요. 화면 표시가 이 클래스의 유일한 일이에요.
    public String formatCard() {
        return "@" + writer + ": " + content + " (좋아요 " + likeCount + ")";
    }
}
