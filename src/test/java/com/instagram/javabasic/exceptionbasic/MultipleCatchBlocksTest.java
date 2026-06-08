package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultipleCatchBlocksTest {

    @Test
    @DisplayName("시나리오 1: IllegalArgumentException 은 구체적 catch 가 먼저 잡는다")
    void handle_illegalArgument() {
        MultipleCatchBlocks demo = new MultipleCatchBlocks();
        assertEquals("IllegalArgument 처리", demo.handle(1));
    }

    @Test
    @DisplayName("시나리오 2: 그 외 RuntimeException 은 일반 catch 가 잡는다")
    void handle_otherRuntime() {
        MultipleCatchBlocks demo = new MultipleCatchBlocks();
        assertEquals("그 외 런타임 처리", demo.handle(2));
    }

    @Test
    @DisplayName("시나리오 0: 예외가 없으면 정상을 돌려준다")
    void handle_정상() {
        MultipleCatchBlocks demo = new MultipleCatchBlocks();
        assertEquals("정상", demo.handle(0));
    }
}
