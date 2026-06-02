package com.instagram.javabasic.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentDemoTest {

    // ===== 다형성 배열: 같은 Content[] 인데 render() 결과가 종류별로 갈린다 =====

    @Test
    @DisplayName("다형성 배열: 부모 타입 배열을 같은 render() 로 순회해도 종류마다 다른 줄이 나온다")
    void polymorphicArray_rendersDifferentlyPerType() {
        Content[] feed = {
                new ImageContent("minji", 120, "beach.jpg"),
                new VideoContent("jaehoon", 340, "trip.mp4", 45),
                new TextContent("seungwoo", 12, "오늘 날씨가 정말 좋네요 산책 가요")
        };

        String[] rendered = new String[feed.length];
        for (int i = 0; i < feed.length; i++) {
            rendered[i] = feed[i].render();
        }

        assertEquals("[이미지] minji 님의 사진: beach.jpg (♥ 120)", rendered[0]);
        assertEquals("[영상] jaehoon 님의 영상 (45초) (♥ 340)", rendered[1]);
        assertEquals("[텍스트] 오늘 날씨가 정말 ... (♥ 12)", rendered[2]);
    }

    @Test
    @DisplayName("다형성 배열: 각 줄은 자기 종류의 머리표([이미지]/[영상]/[텍스트])로 시작한다")
    void polymorphicArray_eachLineStartsWithOwnType() {
        Content[] feed = {
                new ImageContent("minji", 1, "a.jpg"),
                new VideoContent("jaehoon", 1, "b.mp4", 10),
                new TextContent("seungwoo", 1, "hi")
        };

        assertTrue(feed[0].render().startsWith("[이미지]"));
        assertTrue(feed[1].render().startsWith("[영상]"));
        assertTrue(feed[2].render().startsWith("[텍스트]"));
    }
}
