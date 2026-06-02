package com.instagram.javabasic.solution.day13;

// com/instagram/javabasic/solution/day13/AudioContent.java
// 음성 콘텐츠 — 콘텐츠의 또 다른 종류예요. Content 를 물려받아(extends) 공통 정보(작성자·좋아요)는
// 그대로 쓰고, 부모가 남겨둔 빈칸(getType·preview) 두 개를 음성에 맞게 채워요.
// 여기가 역할 분화의 핵심이에요 — 음성은 재생도 되고(Playable) 댓글도 달 수 있지만(Commentable),
// 공유는 안 해요(Shareable 없음). 그래서 implements 에 Playable·Commentable 만 적어요.
// 새 역할(Playable)을 새로 만들어 입혔는데도 기존 Content 부모는 한 줄도 고치지 않았어요.
import com.instagram.javabasic.domain.content.Commentable;
import com.instagram.javabasic.domain.content.Content;

public class AudioContent extends Content implements Playable, Commentable {

    // 음성만 추가로 갖는 정보 — 재생 길이(초)
    private int durationSeconds;

    // 댓글 역할(Commentable)을 위해 필요한 정보 — 댓글 수와 마지막 댓글 내용
    private int commentCount;
    private String lastComment;

    // 생성자 — 첫 줄 super(...) 로 부모의 공통 필드(작성자·좋아요)를 먼저 채우고,
    // 그 다음 음성만의 필드를 채워요.
    public AudioContent(String authorName, int likeCount, int durationSeconds) {
        super(authorName, likeCount);
        this.durationSeconds = durationSeconds;
    }

    // 부모의 빈칸을 채워요 — 음성의 종류 이름
    @Override
    public String getType() {
        return "음성";
    }

    // 부모의 빈칸을 채워요 — 음성은 작성자와 재생 시간을 미리보기로 보여줘요
    @Override
    public String preview() {
        return getAuthorName() + " 님의 음성 (" + durationSeconds + "초)";
    }

    // Playable 의 약속을 채워요 — 재생 길이(초).
    // play() 는 default 라 따로 만들 필요 없이 자동으로 물려받아요.
    @Override
    public int getDurationSeconds() {
        return durationSeconds;
    }

    // Commentable 의 약속을 채워요 — 댓글 달기.
    // 한도에 찼으면(Commentable.isFull) 더 받지 않고 그냥 돌아가요 (ImageContent 와 같은 방식).
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
