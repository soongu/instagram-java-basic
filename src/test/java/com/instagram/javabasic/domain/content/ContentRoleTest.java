package com.instagram.javabasic.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentRoleTest {

    // ===== 다중 구현: 한 클래스가 여러 역할(인터페이스)을 동시에 받는다 =====

    @Test
    @DisplayName("다중 구현: 이미지는 Shareable 로도, Commentable 로도 받을 수 있다")
    void imageContent_implementsBothRoles() {
        ImageContent image = new ImageContent("minji", 10, "a.jpg");

        // 같은 객체를 역할 타입으로도 가리킬 수 있어요 (다형성)
        Shareable shareable = image;
        Commentable commentable = image;

        assertEquals("instagram.com/p/img-minji", shareable.getShareUrl());
        assertEquals(0, commentable.getCommentCount());
    }

    // ===== default 메서드: 인터페이스가 물려준 기본 동작 =====

    @Test
    @DisplayName("default share: 이미지의 공유 문구는 default 메서드로 자동 조립된다")
    void share_imageUsesDefaultMethod() {
        ImageContent image = new ImageContent("minji", 10, "a.jpg");
        assertEquals("공유 링크가 생성됐어요: instagram.com/p/img-minji", image.share());
    }

    @Test
    @DisplayName("default share: 영상의 공유 문구도 default 메서드로 자동 조립된다")
    void share_videoUsesDefaultMethod() {
        VideoContent video = new VideoContent("jaehoon", 10, "b.mp4", 30);
        assertEquals("공유 링크가 생성됐어요: instagram.com/p/vid-jaehoon", video.share());
    }

    // ===== addComment + getCommentCount =====

    @Test
    @DisplayName("addComment: 댓글 3개를 달면 댓글 수가 3이 된다")
    void addComment_increasesCount() {
        ImageContent image = new ImageContent("minji", 10, "a.jpg");

        image.addComment("첫 댓글");
        image.addComment("둘째 댓글");
        image.addComment("셋째 댓글");

        assertEquals(3, image.getCommentCount());
    }

    // ===== 인터페이스 상수 =====

    @Test
    @DisplayName("인터페이스 상수: MAX_COMMENTS 는 100 이다")
    void interfaceConstant_maxComments() {
        assertEquals(100, Commentable.MAX_COMMENTS);
    }

    // ===== static 메서드 =====

    @Test
    @DisplayName("static 메서드: isFull 은 99에서 false, 100에서 true 를 돌려준다")
    void staticMethod_isFull() {
        assertFalse(Commentable.isFull(99));
        assertTrue(Commentable.isFull(100));
    }

    // ===== 한도: MAX_COMMENTS 에서 멈춘다 =====

    @Test
    @DisplayName("한도: 105번 댓글을 달아도 댓글 수는 MAX_COMMENTS(100)에서 멈춘다")
    void addComment_stopsAtMax() {
        VideoContent video = new VideoContent("jaehoon", 10, "b.mp4", 30);

        for (int i = 0; i < 105; i++) {
            video.addComment("댓글 " + i);
        }

        assertEquals(Commentable.MAX_COMMENTS, video.getCommentCount());
    }

    // ===== 역할 분화: TextContent 는 Commentable 이지만 Shareable 이 아니다 =====

    @Test
    @DisplayName("역할 분화: 텍스트는 댓글은 달 수 있지만 공유는 못 한다")
    void textContent_isCommentableButNotShareable() {
        TextContent text = new TextContent("seungwoo", 5, "안녕하세요");

        assertTrue(text instanceof Commentable);
        assertFalse(text instanceof Shareable);
    }

    // ===== lastComment: 마지막으로 단 댓글이 기록된다 =====

    @Test
    @DisplayName("lastComment: addComment 후 마지막 댓글 내용이 기록된다")
    void lastComment_recordsLatest() {
        TextContent text = new TextContent("seungwoo", 5, "안녕하세요");

        text.addComment("좋아요");

        assertEquals("좋아요", text.getLastComment());
    }
}
