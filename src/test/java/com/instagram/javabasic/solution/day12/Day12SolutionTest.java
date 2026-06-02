package com.instagram.javabasic.solution.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.content.VideoContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day12SolutionTest {

    // ===== 과제 1: LinkContent 가 기존 Content 계열에 끼어든다 =====

    @Test
    @DisplayName("과제1: LinkContent 는 [링크] 머리표와 공유 링크 미리보기로 render 된다")
    void linkContent_rendersWithLinkType() {
        Content link = new LinkContent("minji", 8, "https://spartacodingclub.kr");

        assertEquals("링크", link.getType());
        assertEquals("[링크] minji 님이 공유한 링크: https://spartacodingclub.kr (♥ 8)", link.render());
    }

    @Test
    @DisplayName("과제1: 기존 세 종류 + LinkContent 를 같은 Content[] 에 담아 순회해도 종류별로 갈린다")
    void linkContent_joinsExistingFeedArray() {
        Content[] feed = {
                new ImageContent("minji", 120, "beach.jpg"),
                new VideoContent("jaehoon", 340, "trip.mp4", 45),
                new TextContent("seungwoo", 12, "오늘 날씨가 정말 좋네요 산책 가요"),
                new LinkContent("minji", 8, "https://example.com")
        };

        assertTrue(feed[0].render().startsWith("[이미지]"));
        assertTrue(feed[1].render().startsWith("[영상]"));
        assertTrue(feed[2].render().startsWith("[텍스트]"));
        assertTrue(feed[3].render().startsWith("[링크]"));
    }

    // ===== 과제 2: Notification 추상 부모 + 자식 3종 =====

    @Test
    @DisplayName("과제2: 알림 종류마다 message() 가 다르고 render() 틀은 공통이다")
    void notification_eachTypeHasOwnMessage() {
        Notification follow = new FollowNotification("minji", "5분 전", "jaehoon");
        Notification like = new LikeNotification("minji", "12분 전", "seungwoo");
        Notification comment = new CommentNotification("minji", "1시간 전", "yujin", "사진 너무 예뻐요!");

        assertEquals("[minji] jaehoon님이 회원님을 팔로우했습니다 (5분 전)", follow.render());
        assertEquals("[minji] seungwoo님이 회원님의 게시물을 좋아합니다 (12분 전)", like.render());
        assertEquals("[minji] yujin님이 댓글을 남겼습니다: 사진 너무 예뻐요! (1시간 전)", comment.render());
    }

    @Test
    @DisplayName("과제2: Notification[] 배열을 같은 render() 로 순회해도 알림마다 다른 줄이 나온다")
    void notification_polymorphicArray() {
        Notification[] alerts = {
                new FollowNotification("minji", "5분 전", "jaehoon"),
                new LikeNotification("minji", "12분 전", "seungwoo"),
                new CommentNotification("minji", "1시간 전", "yujin", "예뻐요!")
        };

        assertTrue(alerts[0].render().contains("팔로우"));
        assertTrue(alerts[1].render().contains("좋아합니다"));
        assertTrue(alerts[2].render().contains("댓글"));
    }

    // ===== 과제 3: MediaItem 의 isPlayable() 추상 메서드 =====

    @Test
    @DisplayName("과제3: isPlayable() 은 영상만 true, 이미지/텍스트는 false")
    void mediaItem_isPlayableDiffersPerType() {
        MediaItem image = new ImageItem("minji", "beach.jpg");
        MediaItem video = new VideoItem("jaehoon", "trip.mp4", 45);
        MediaItem text = new TextItem("seungwoo", "안녕하세요 반갑습니다 오늘도 좋은 하루");

        assertFalse(image.isPlayable());
        assertTrue(video.isPlayable());
        assertFalse(text.isPlayable());
    }

    @Test
    @DisplayName("과제3: MediaItem[] 순회 시 isPlayable() 결과로 분기되고 종류 이름도 갈린다")
    void mediaItem_polymorphicBranching() {
        MediaItem[] items = {
                new ImageItem("minji", "beach.jpg"),
                new VideoItem("jaehoon", "trip.mp4", 45),
                new TextItem("seungwoo", "짧은 글")
        };

        int playable = 0;
        for (MediaItem item : items) {
            if (item.isPlayable()) {
                playable++;
            }
        }

        assertEquals(1, playable);
        assertEquals("이미지", items[0].getType());
        assertEquals("영상", items[1].getType());
        assertEquals("텍스트", items[2].getType());
    }
}
