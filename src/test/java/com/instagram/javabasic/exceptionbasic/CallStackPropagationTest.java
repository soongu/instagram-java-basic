package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CallStackPropagationTest {

    @Test
    @DisplayName("정상 주소면 가장 깊은 메서드의 결과가 맨 위까지 그대로 돌아온다")
    void showPage_정상() {
        CallStackPropagation page = new CallStackPropagation();
        assertEquals("profile.jpg", page.showPage("profile.jpg"));
    }

    @Test
    @DisplayName("null 주소면 맨 깊은 메서드의 예외가 showPage 까지 전파된다")
    void showPage_null_전파() {
        CallStackPropagation page = new CallStackPropagation();
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> page.showPage(null));
        assertEquals("프로필 이미지 주소가 없어요.", e.getMessage());
    }

    @Test
    @DisplayName("빈 주소면 중간 메서드(renderProfile)에서도 같은 예외가 전파된다")
    void renderProfile_빈값_전파() {
        CallStackPropagation page = new CallStackPropagation();
        assertThrows(IllegalStateException.class, () -> page.renderProfile(""));
    }

    @Test
    @DisplayName("가장 깊은 loadProfileImage 가 예외의 출발점이다")
    void loadProfileImage_출발점() {
        CallStackPropagation page = new CallStackPropagation();
        assertThrows(IllegalStateException.class, () -> page.loadProfileImage(null));
        assertEquals("photo.png", page.loadProfileImage("photo.png"));
    }
}
