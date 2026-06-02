package com.instagram.javabasic.solution.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.instagram.javabasic.domain.content.Commentable;
import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.Shareable;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.content.VideoContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day13SolutionTest {

    // ===== 과제 1: 새 역할 Playable + AudioContent =====

    @Test
    @DisplayName("과제1: Playable 의 default play() 가 재생 시간을 담아 자동으로 동작한다")
    void audioContent_playUsesDefaultMethod() {
        AudioContent audio = new AudioContent("minji", 50, 30);

        assertEquals("▶ 재생을 시작해요 (30초)", audio.play());
    }

    @Test
    @DisplayName("과제1: 음성은 Commentable·Playable 이지만 Shareable 은 아니다 (역할 분화)")
    void audioContent_hasCommentableAndPlayableButNotShareable() {
        AudioContent audio = new AudioContent("minji", 50, 30);

        assertTrue(audio instanceof Commentable);
        assertTrue(audio instanceof Playable);
        assertFalse(audio instanceof Shareable);
    }

    @Test
    @DisplayName("과제1: AudioContent 에 댓글 3번 달면 댓글 수가 3 이 된다")
    void audioContent_addCommentCounts() {
        AudioContent audio = new AudioContent("minji", 50, 30);

        audio.addComment("좋은 목소리예요");
        audio.addComment("잘 들었어요");
        audio.addComment("또 올려주세요");

        assertEquals(3, audio.getCommentCount());
        assertEquals("또 올려주세요", audio.getLastComment());
    }

    @Test
    @DisplayName("과제1: AudioContent 도 Content 라서 render() 한 줄에 합류한다")
    void audioContent_rendersAsContent() {
        Content audio = new AudioContent("minji", 50, 30);

        assertEquals("음성", audio.getType());
        assertEquals("[음성] minji 님의 음성 (30초) (♥ 50)", audio.render());
    }

    // ===== 과제 2: 공유 가능한 콘텐츠만 세기 =====

    @Test
    @DisplayName("과제2: image+video+text+audio 섞인 배열에서 공유 가능한 것은 2개(이미지·영상)")
    void countShareable_countsOnlyShareableContents() {
        Content[] feed = {
                new ImageContent("minji", 120, "beach.jpg"),
                new VideoContent("jaehoon", 340, "trip.mp4", 45),
                new TextContent("seungwoo", 12, "오늘 점심 맛있었다"),
                new AudioContent("yujin", 8, 30)
        };

        assertEquals(2, Day13SolutionMain.countShareable(feed));
    }

    // ===== 과제 3: 역할 보고 도우미 =====

    @Test
    @DisplayName("과제3: 이미지는 공유·댓글 두 역할을 가진다")
    void describeRoles_imageHasShareAndComment() {
        Content image = new ImageContent("minji", 120, "beach.jpg");

        assertEquals("공유 가능, 댓글 가능", Day13SolutionMain.describeRoles(image));
    }

    @Test
    @DisplayName("과제3: 텍스트는 댓글 역할만 가진다")
    void describeRoles_textHasCommentOnly() {
        Content text = new TextContent("seungwoo", 12, "오늘 점심 맛있었다");

        assertEquals("댓글 가능", Day13SolutionMain.describeRoles(text));
    }

    @Test
    @DisplayName("과제3: 음성은 댓글·재생 두 역할을 가진다 (공유는 빠진다)")
    void describeRoles_audioHasCommentAndPlay() {
        Content audio = new AudioContent("yujin", 8, 30);

        assertEquals("댓글 가능, 재생 가능", Day13SolutionMain.describeRoles(audio));
    }
}
