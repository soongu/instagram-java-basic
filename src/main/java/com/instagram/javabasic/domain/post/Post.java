package com.instagram.javabasic.domain.post;

import com.instagram.javabasic.domain.comment.Comment;
import com.instagram.javabasic.domain.content.Commentable;
import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/domain/post/Post.java
// 인스타 게시물 한 개를 표현하는 클래스예요.
// 지난 시간에 만든 형태(내용·작성자·좋아요 수)를 그대로 가져오고,
// 이번 시간엔 게시물을 도메인의 중심에 놓아요.
// 작성자를 이름 문자열 대신 Member 객체로 직접 가리키고("참조"),
// 댓글 여러 개를 배열로 모아 "한 게시물 : 여러 댓글" 의 1:N 연결을 표현해요.
// 댓글을 달 수 있는 역할(Commentable)도 이 게시물이 직접 맡아요.
public class Post implements Commentable {

    // 게시물을 이루는 정보 — private 으로 숨겨요
    private String content;       // 게시물 내용
    private String authorName;    // 작성자 이름
    private int likeCount;        // 좋아요 수
    private PostStatus status;    // 게시물 상태(공개/비공개/보관됨) — enum 으로 표현

    // 작성자를 Member 객체로 직접 가리켜요(참조). authorName 은 그 이름을 베껴둔 값이에요.
    // 두 가지가 함께 있는 이유는 아래 생성자 설명에서 풀어요.
    private Member author;

    // 이 게시물에 달린 댓글들 — 배열 하나에 모아요(1:N). 몇 개 찼는지는 commentCount 로 세요.
    private Comment[] comments = new Comment[Commentable.MAX_COMMENTS];
    private int commentCount = 0;

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

    // 작성자를 Member 객체로 직접 받는 생성자 — 도메인 연결의 핵심이에요.
    // 객체를 받는 순간, 그 사람의 이름(getUsername())을 authorName 에도 똑같이 베껴둬요.
    // 그래서 "객체로 따라가기(author)" 와 "이름만 보기(authorName)" 두 가지가 늘 같은 사람을 가리켜요.
    public Post(String content, Member author, int likeCount) {
        this.content = content;
        this.author = author;
        this.authorName = author.getUsername();
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

    // 작성자 객체를 그대로 돌려줘요 — 이걸로 작성자의 점수·등급까지 따라갈 수 있어요.
    public Member getAuthor() {
        return author;
    }

    // ===== 댓글(1:N): Commentable 역할을 채워요 =====

    // 댓글 객체를 직접 받아 배열에 담아요. 한도(MAX_COMMENTS)에 차면 더 담지 않고 넘어가요.
    public void addComment(Comment c) {
        if (Commentable.isFull(commentCount)) {
            System.out.println("댓글이 한도(" + Commentable.MAX_COMMENTS + "개)에 찼어요. 더 담지 않아요.");
            return;
        }
        comments[commentCount] = c;
        commentCount++;
    }

    // Commentable 약속 — 텍스트만 받으면 작성자 이름으로 댓글 객체를 만들어 담아요.
    // commenter 정보가 따로 없으니, 글 작성자 이름을 쓰고(없으면 "익명") 좋아요 0 으로 시작해요.
    @Override
    public void addComment(String text) {
        String commenter = (authorName != null) ? authorName : "익명";
        addComment(new Comment(commenter, text, 0));
    }

    // Commentable 약속 — 지금까지 달린 댓글 수를 돌려줘요.
    @Override
    public int getCommentCount() {
        return commentCount;
    }

    // 인덱스로 댓글 하나를 꺼내요.
    public Comment getComment(int index) {
        return comments[index];
    }

    // 상태를 바꿔요(예: 공개 → 보관됨). enum 타입이라 정해진 값만 들어올 수 있어요.
    public void setStatus(PostStatus status) {
        this.status = status;
    }

    // 게시물 내용을 새 글로 바꿔요. 지금까진 한 번 쓰면 못 고쳤는데, 수정 기능을 위해 통로를 하나 열어요.
    // "고쳐도 되는 상태인가" 같은 규칙은 여기서 따지지 않아요 — 그 판단은 게시물을 다루는 서비스가 맡아요.
    public void editContent(String newContent) {
        this.content = newContent;
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
