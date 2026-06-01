package com.instagram.javabasic.domain.post;

// com/instagram/javabasic/domain/post/Post.java
// 인스타 게시물 한 개를 표현하는 클래스예요.
// 지난 시간에 만든 형태(내용·작성자·좋아요 수)를 그대로 가져오고,
// 이번 시간엔 toString 과 equals 를 새로 정의(오버라이딩)해서
// "사람 친화적인 출력" 과 "값으로 같은지 비교" 를 더해요.
public class Post {

    // 게시물을 이루는 세 가지 정보 — private 으로 숨겨요
    private String content;       // 게시물 내용
    private String authorName;    // 작성자 이름
    private int likeCount;        // 좋아요 수

    // 기본 생성자 — 빈 게시물을 만들어요
    public Post() {
    }

    // 매개변수 생성자 — 세 가지 정보를 한 번에 받아 게시물을 완성해요
    public Post(String content, String authorName, int likeCount) {
        this.content = content;
        this.authorName = authorName;
        this.likeCount = likeCount;
    }

    // 좋아요 한 번 — 좋아요 수를 1 올려요
    public void addLike() {
        this.likeCount++;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    // toString — 게시물을 그대로 출력하면 주소가 찍혀요.
    // 이렇게 새로 정의하면 우리가 원하는 글로 바뀌어요.
    @Override
    public String toString() {
        return "[게시물] 내용: " + content + ", 좋아요: " + likeCount;
    }

    // equals — 작성자와 내용이 모두 같으면 "같은 게시물" 로 봐요.
    // 기본 동작은 메모리 주소 비교라서, 값으로 같은지 보려면 이렇게 새로 정의해요.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Post other = (Post) obj;
        return authorName != null && authorName.equals(other.authorName)
                && content != null && content.equals(other.content);
    }
}
