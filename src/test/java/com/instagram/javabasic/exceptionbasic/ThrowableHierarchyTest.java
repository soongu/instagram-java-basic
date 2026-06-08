package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThrowableHierarchyTest {

    @Test
    @DisplayName("RuntimeException 계열은 unchecked 로 분류한다")
    void classify_unchecked() {
        ThrowableHierarchy hierarchy = new ThrowableHierarchy();
        assertEquals("unchecked (RuntimeException 계열)", hierarchy.classify(new ClassCastException()));
        assertEquals("unchecked (RuntimeException 계열)", hierarchy.classify(new IllegalArgumentException()));
        assertEquals("unchecked (RuntimeException 계열)", hierarchy.classify(new IllegalStateException()));
    }

    @Test
    @DisplayName("RuntimeException 이 아닌 Exception 은 checked 로 분류한다")
    void classify_checked() {
        ThrowableHierarchy hierarchy = new ThrowableHierarchy();
        assertEquals("checked (Exception 계열)", hierarchy.classify(new Exception()));
    }

    @Test
    @DisplayName("Error 는 복구 대상 아님으로 분류한다")
    void classify_error() {
        ThrowableHierarchy hierarchy = new ThrowableHierarchy();
        assertEquals("Error (복구 대상 아님)", hierarchy.classify(new OutOfMemoryError()));
    }

    @Test
    @DisplayName("isUnchecked 는 RuntimeException 계열에만 true 를 돌려준다")
    void isUnchecked_분류() {
        ThrowableHierarchy hierarchy = new ThrowableHierarchy();
        assertTrue(hierarchy.isUnchecked(new IllegalArgumentException()));
        assertFalse(hierarchy.isUnchecked(new Exception()));
        assertFalse(hierarchy.isUnchecked(new OutOfMemoryError()));
    }

    @Test
    @DisplayName("RuntimeException 은 Exception 이자 Throwable 이다 (instanceof 계층)")
    void 계층관계_확인() {
        RuntimeException e = new IllegalArgumentException();
        assertTrue(e instanceof RuntimeException);
        assertTrue(e instanceof Exception);
        assertTrue(e instanceof Throwable);
    }
}
