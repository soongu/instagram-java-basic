package com.instagram.javabasic.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentTest {

    // ===== abstract 메서드 구현: 자식마다 다른 종류/미리보기 =====

    @Test
    @DisplayName("getType: 자식마다 콘텐츠 종류 글자가 다르게 채워진다")
    void getType_differsPerChild() {
        Content image = new ImageContent("minji", 10, "a.jpg");
        Content video = new VideoContent("jaehoon", 10, "b.mp4", 30);
        Content text = new TextContent("seungwoo", 10, "안녕하세요");

        assertEquals("이미지", image.getType());
        assertEquals("영상", video.getType());
        assertEquals("텍스트", text.getType());
    }

    @Test
    @DisplayName("preview: 이미지는 작성자와 사진 주소를 보여준다")
    void preview_imageShowsUrl() {
        Content image = new ImageContent("minji", 10, "beach.jpg");
        assertEquals("minji 님의 사진: beach.jpg", image.preview());
    }

    @Test
    @DisplayName("preview: 영상은 재생 시간을 함께 보여준다")
    void preview_videoShowsDuration() {
        Content video = new VideoContent("jaehoon", 10, "trip.mp4", 45);
        assertEquals("jaehoon 님의 영상 (45초)", video.preview());
    }

    @Test
    @DisplayName("preview: 텍스트가 10글자를 넘으면 앞 10글자만 잘라 ... 를 붙인다")
    void preview_textTruncatesWhenLong() {
        Content text = new TextContent("seungwoo", 10, "오늘 날씨가 정말 좋네요 산책 가요");
        // 본문 앞 10글자(공백 포함)만 잘리고 "..." 가 붙어요
        assertEquals("오늘 날씨가 정말 ...", text.preview());
    }

    @Test
    @DisplayName("preview: 텍스트가 10글자 이하면 그대로 보여준다")
    void preview_textKeepsWhenShort() {
        Content text = new TextContent("seungwoo", 10, "안녕하세요");
        assertEquals("안녕하세요", text.preview());
    }

    // ===== 템플릿 메서드: 부모의 render() 틀 + 자식이 채운 빈칸 =====

    @Test
    @DisplayName("render: 부모의 틀 하나로 종류마다 다른 완성 문자열이 조립된다")
    void render_assemblesPerType() {
        Content image = new ImageContent("minji", 120, "beach.jpg");
        Content video = new VideoContent("jaehoon", 340, "trip.mp4", 45);

        assertEquals("[이미지] minji 님의 사진: beach.jpg (♥ 120)", image.render());
        assertEquals("[영상] jaehoon 님의 영상 (45초) (♥ 340)", video.render());
    }

    // ===== 부모가 물려준 concrete 동작: 모든 자식이 공통으로 쓴다 =====

    @Test
    @DisplayName("addLike: 부모가 물려준 좋아요 동작은 모든 자식이 공통으로 쓴다")
    void addLike_inheritedByAllChildren() {
        Content image = new ImageContent("minji", 0, "a.jpg");
        image.addLike();
        image.addLike();

        assertEquals(2, image.getLikeCount());
    }

    @Test
    @DisplayName("부모 타입 변수로도 공통 getter(작성자)를 그대로 읽을 수 있다")
    void parentType_readsCommonField() {
        Content text = new TextContent("seungwoo", 5, "hi");
        assertEquals("seungwoo", text.getAuthorName());
    }
}
