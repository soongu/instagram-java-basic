package com.instagram.javabasic.solution.day14;

// com/instagram/javabasic/solution/day14/PostData.java
// 과제 1 — 게시물의 '데이터를 보관' 하는 책임만 맡는 클래스예요.
// 화면에 어떻게 보일지(표시)는 전혀 신경 쓰지 않아요. 그건 PostCard 의 몫이에요.
// "한 클래스는 한 가지 책임만" — 데이터 보관과 화면 표시를 갈라놓은 모습이에요.
public class PostData {

    private String writer;
    private String content;
    private int likeCount;

    public PostData(String writer, String content, int likeCount) {
        this.writer = writer;
        this.content = content;
        this.likeCount = likeCount;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
