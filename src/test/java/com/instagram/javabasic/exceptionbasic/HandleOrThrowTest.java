package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandleOrThrowTest {

    @Test
    @DisplayName("던지는 쪽: 정상 숫자 글자는 int 로 변환된다")
    void parseLimit_정상() {
        HandleOrThrow parser = new HandleOrThrow();
        assertEquals(50, parser.parseLimit("50"));
    }

    @Test
    @DisplayName("던지는 쪽: 숫자가 아니면 NumberFormatException 이 그대로 전파된다")
    void parseLimit_잘못된값() {
        HandleOrThrow parser = new HandleOrThrow();
        assertThrows(NumberFormatException.class, () -> parser.parseLimit("열개"));
    }

    @Test
    @DisplayName("잡는 쪽: 정상 숫자면 그 값을 그대로 돌려준다")
    void parseLimitOrDefault_정상() {
        HandleOrThrow parser = new HandleOrThrow();
        assertEquals(50, parser.parseLimitOrDefault("50"));
    }

    @Test
    @DisplayName("잡는 쪽: 잘못된 값이면 기본값 30 으로 복구한다")
    void parseLimitOrDefault_복구() {
        HandleOrThrow parser = new HandleOrThrow();
        assertEquals(30, parser.parseLimitOrDefault("열개"));
    }
}
