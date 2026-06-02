package com.instagram.javabasic.domain.post;

// com/instagram/javabasic/domain/post/Post.java
// 인스타 게시물 한 개를 표현하는 클래스예요.
// 지난 시간에 만든 형태(내용·작성자·좋아요 수)를 그대로 가져오고,
// 이번 시간엔 toString 과 equals 를 새로 정의(오버라이딩)해서
// "사람 친화적인 출력" 과 "값으로 같은지 비교" 를 더해요.
public class Post {

    // 게시물을 이루는 정보 — private 으로 숨겨요
    private String content;       // 게시물 내용
    private String authorName;    // 작성자 이름
    private int likeCount;        // 좋아요 수
    private PostStatus status;    // 게시물 상태(공개/비공개/보관됨) — enum 으로 표현

    // 기본 생성자 — 빈 게시물을 만들어요. 상태는 일단 공개로 시작해요.
    public Post() {
        this.status = PostStatus.PUBLIC;
    }

    // 매개변수 생성자 — 세 가지 정보를 받아요. 상태는 기본값(공개)으로 둬요.
    public Post(String content, String authorName, int likeCount) {
        this.content = content;
        this.authorName = authorName;
        this.likeCount = likeCount;
        this.status = PostStatus.PUBLIC;
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

    public PostStatus getStatus() {
        return status;
    }

    // 상태를 바꿔요(예: 공개 → 보관됨). enum 타입이라 정해진 값만 들어올 수 있어요.
    public void setStatus(PostStatus status) {
        this.status = status;
    }

    // 이 게시물을 지금 공유할 수 있는지 — 판단 규칙은 상태(enum)에게 맡겨요.
    // Post 는 "공유 가능?" 만 묻고, 진짜 규칙은 PostStatus 안에 모여 있어요.
    public boolean canBeShared() {
        return status.canShare();
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
