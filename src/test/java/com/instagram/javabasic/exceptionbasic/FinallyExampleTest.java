package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinallyExampleTest {

    @Test
    @DisplayName("정상 경로: try → finally 순서로 실행되고 finally 가 정리한다")
    void runWithCleanup_정상() {
        FinallyExample example = new FinallyExample();
        String log = example.runWithCleanup(false);
        assertEquals("try → finally(정리)", log);
    }

    @Test
    @DisplayName("예외 경로: try → catch → finally 순서로 실행되고 finally 가 정리한다")
    void runWithCleanup_예외() {
        FinallyExample example = new FinallyExample();
        String log = example.runWithCleanup(true);
        assertEquals("try → catch → finally(정리)", log);
    }

    @Test
    @DisplayName("정상이든 예외든 finally(정리) 는 무조건 실행된다")
    void runWithCleanup_finally무조건() {
        FinallyExample example = new FinallyExample();
        assertTrue(example.runWithCleanup(false).contains("finally(정리)"));
        assertTrue(example.runWithCleanup(true).contains("finally(정리)"));
    }
}
