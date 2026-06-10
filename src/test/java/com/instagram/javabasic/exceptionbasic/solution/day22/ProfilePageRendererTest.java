package com.instagram.javabasic.exceptionbasic.solution.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfilePageRendererTest {

    @Test
    @DisplayName("정상 자기소개면 3단 사슬을 통과해 그대로 돌려준다")
    void rendersValidBio() {
        ProfilePageRenderer renderer = new ProfilePageRenderer();

        String page = renderer.renderProfilePage("자바 좋아요");

        assertEquals("자바 좋아요", page);
    }

    @Test
    @DisplayName("readBio 는 빈 자기소개에 IllegalStateException 을 던진다")
    void readBioThrowsForEmptyBio() {
        ProfilePageRenderer renderer = new ProfilePageRenderer();

        assertThrows(IllegalStateException.class,
                () -> renderer.readBio(""));
    }

    @Test
    @DisplayName("renderProfilePage(null) 의 예외가 맨 위까지 전파돼 IllegalStateException 으로 잡힌다")
    void propagatesExceptionFromDeepestCall() {
        ProfilePageRenderer renderer = new ProfilePageRenderer();

        assertThrows(IllegalStateException.class,
                () -> renderer.renderProfilePage(null));
    }
}
