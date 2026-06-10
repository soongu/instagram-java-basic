package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThrowStatementTest {

    @Test
    @DisplayName("정상 캡션은 그대로 돌려준다")
    void validateCaption_정상() {
        ThrowStatement validator = new ThrowStatement();
        assertEquals("오늘 점심 인증", validator.validateCaption("오늘 점심 인증"));
    }

    @Test
    @DisplayName("null 캡션이면 IllegalArgumentException 을 던진다")
    void validateCaption_null() {
        ThrowStatement validator = new ThrowStatement();
        assertThrows(IllegalArgumentException.class, () -> validator.validateCaption(null));
    }

    @Test
    @DisplayName("빈 문자열 캡션이면 IllegalArgumentException 을 던진다")
    void validateCaption_빈문자열() {
        ThrowStatement validator = new ThrowStatement();
        assertThrows(IllegalArgumentException.class, () -> validator.validateCaption(""));
    }

    @Test
    @DisplayName("2200자를 넘으면 IllegalArgumentException 을 던지고 실제 길이를 메시지에 담는다")
    void validateCaption_길이초과() {
        ThrowStatement validator = new ThrowStatement();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ThrowStatement.MAX_CAPTION_LENGTH + 1; i++) {
            sb.append("가");
        }
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateCaption(sb.toString()));
        assertEquals("캡션은 2200자까지예요. 지금은 2201자.", e.getMessage());
    }

    @Test
    @DisplayName("정확히 2200자는 통과한다(경계값)")
    void validateCaption_경계값() {
        ThrowStatement validator = new ThrowStatement();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ThrowStatement.MAX_CAPTION_LENGTH; i++) {
            sb.append("가");
        }
        String caption = sb.toString();
        assertEquals(caption, validator.validateCaption(caption));
    }
}
