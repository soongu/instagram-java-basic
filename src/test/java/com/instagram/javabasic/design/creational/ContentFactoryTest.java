package com.instagram.javabasic.design.creational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.design.creational.ContentFactory.ContentType;
import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.content.VideoContent;

class ContentFactoryTest {

    @Test
    @DisplayName("IMAGE 종류를 주면 ImageContent 가 만들어진다")
    void create_image() {
        Content content = ContentFactory.create(ContentType.IMAGE, "jaehoon", "photo.jpg");

        assertEquals("이미지", content.getType());
        assertInstanceOf(ImageContent.class, content);
    }

    @Test
    @DisplayName("VIDEO 종류를 주면 VideoContent 가 만들어진다")
    void create_video() {
        Content content = ContentFactory.create(ContentType.VIDEO, "minji", "reel.mp4");

        assertEquals("영상", content.getType());
        assertInstanceOf(VideoContent.class, content);
    }

    @Test
    @DisplayName("TEXT 종류를 주면 TextContent 가 만들어진다")
    void create_text() {
        Content content = ContentFactory.create(ContentType.TEXT, "seungwoo", "오늘 날씨 좋네요");

        assertEquals("텍스트", content.getType());
        assertInstanceOf(TextContent.class, content);
    }

    @Test
    @DisplayName("갓 만든 콘텐츠는 좋아요 0 에서 시작하고, render() 로 한 줄 미리보기가 나온다")
    void create_startsWithZeroLikes() {
        Content content = ContentFactory.create(ContentType.IMAGE, "jaehoon", "photo.jpg");

        assertEquals(0, content.getLikeCount());
        assertTrue(content.render().contains("이미지"));
    }
}
