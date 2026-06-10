package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThrowsDeclarationTest {

    @Test
    @DisplayName("서로 다른 사람이면 예외 없이 팔로우가 끝난다")
    void follow_정상() {
        ThrowsDeclaration service = new ThrowsDeclaration();
        assertDoesNotThrow(() -> service.follow("minji", "jaehoon"));
    }

    @Test
    @DisplayName("자기 자신을 팔로우하면 예외가 호출자에게 전파된다")
    void follow_자기팔로우() {
        ThrowsDeclaration service = new ThrowsDeclaration();
        Exception e = assertThrows(Exception.class, () -> service.follow("minji", "minji"));
        assertEquals("자기 자신은 팔로우할 수 없어요.", e.getMessage());
    }
}
