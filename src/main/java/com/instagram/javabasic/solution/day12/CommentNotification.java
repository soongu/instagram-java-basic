package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/CommentNotification.java
// 과제 2 — 댓글 알림이에요. Notification 을 물려받아 공통 정보는 그대로 쓰고,
// "누가, 무슨 댓글을 달았는지" 를 더한 뒤 부모의 빈칸 message() 를 댓글에 맞게 채워요.
// 댓글 알림은 행위자 외에 댓글 내용까지 보여주는 게 자연스러워, 고유 필드를 두 개 가져요.
public class CommentNotification extends Notification {

    // 댓글 알림만 추가로 갖는 정보 — 댓글 단 사람과 댓글 내용
    private String actorName;
    private String commentText;

    // 생성자 — super(...) 로 공통 필드를 먼저 채우고, 행위자·댓글 내용을 채워요.
    public CommentNotification(String receiverName, String createdAgo, String actorName, String commentText) {
        super(receiverName, createdAgo);
        this.actorName = actorName;
        this.commentText = commentText;
    }

    // 부모의 빈칸을 채워요 — 댓글 알림 문구 (행위자 + 댓글 내용)
    @Override
    public String message() {
        return actorName + "님이 댓글을 남겼습니다: " + commentText;
    }
}
