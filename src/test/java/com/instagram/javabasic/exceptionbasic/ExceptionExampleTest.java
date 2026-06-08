package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExceptionExampleTest {

    @Test
    @DisplayName("triggerClassCast 는 ClassCastException 을 던진다")
    void triggerClassCast_던짐() {
        ExceptionExample example = new ExceptionExample();
        assertThrows(ClassCastException.class, () -> example.triggerClassCast());
    }

    @Test
    @DisplayName("triggerIllegalArgument 는 IllegalArgumentException 을 던진다")
    void triggerIllegalArgument_던짐() {
        ExceptionExample example = new ExceptionExample();
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> example.triggerIllegalArgument());
        assertEquals("id 999 에 해당하는 항목이 없어요.", thrown.getMessage());
    }

    @Test
    @DisplayName("triggerIllegalState 는 IllegalStateException 을 던진다")
    void triggerIllegalState_던짐() {
        ExceptionExample example = new ExceptionExample();
        assertThrows(IllegalStateException.class, () -> example.triggerIllegalState());
    }
}
