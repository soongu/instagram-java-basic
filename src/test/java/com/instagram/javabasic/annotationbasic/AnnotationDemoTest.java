package com.instagram.javabasic.annotationbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnnotationDemoTest {

    private final AnnotationDemo demo = new AnnotationDemo();

    @Test
    @DisplayName("@Deprecated 가 붙어도 메서드는 그대로 동작한다")
    void deprecatedMethod_stillWorks() {
        assertEquals("예전 방식으로 공유했어요", demo.oldShare());
    }

    @Test
    @DisplayName("권장 메서드도 정상 동작한다")
    void newMethod_works() {
        assertEquals("새 방식으로 공유했어요", demo.newShare());
    }

    @Test
    @DisplayName("@SuppressWarnings 가 붙은 메서드도 정상 동작한다")
    void suppressWarnings_stillWorks() {
        assertEquals("경고 없이 처리했어요", demo.quietMethod());
    }
}
