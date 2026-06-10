package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CheckedVsUncheckedTest {

    @Test
    @DisplayName("unchecked: 음수 나이면 IllegalArgumentException 을 던진다")
    void uncheckedExample_음수() {
        CheckedVsUnchecked demo = new CheckedVsUnchecked();
        assertThrows(IllegalArgumentException.class, () -> demo.uncheckedExample(-1));
    }

    @Test
    @DisplayName("unchecked: 정상 나이면 예외 없이 통과한다")
    void uncheckedExample_정상() {
        CheckedVsUnchecked demo = new CheckedVsUnchecked();
        assertDoesNotThrow(() -> demo.uncheckedExample(25));
    }

    @Test
    @DisplayName("checked: fail 이 true 면 checked Exception 을 던진다")
    void checkedExample_실패() {
        CheckedVsUnchecked demo = new CheckedVsUnchecked();
        assertThrows(Exception.class, () -> demo.checkedExample(true));
    }

    @Test
    @DisplayName("checked: fail 이 false 면 예외 없이 통과한다")
    void checkedExample_성공() throws Exception {
        CheckedVsUnchecked demo = new CheckedVsUnchecked();
        assertDoesNotThrow(() -> demo.checkedExample(false));
    }
}
