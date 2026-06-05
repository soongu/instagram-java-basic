package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringImmutabilityTest {

    @Test
    @DisplayName("append 는 새 문자열을 돌려준다")
    void append_returnsNewString() {
        assertEquals("jaehoon_dev", StringImmutability.append("jaehoon", "_dev"));
    }

    @Test
    @DisplayName("이어붙이기는 원본과 다른 객체를 만든다")
    void append_makesNewObject() {
        assertFalse(StringImmutability.makesNewObject("jaehoon", "_dev"));
    }

    @Test
    @DisplayName("원본은 이어붙인 뒤에도 그대로 남는다 (불변)")
    void original_unchanged() {
        String original = "jaehoon";
        StringImmutability.append(original, "_dev");
        assertEquals("jaehoon", original);
    }

    @Test
    @DisplayName("새 객체는 원본과 identityHashCode 가 다르다")
    void identity_differsAfterAppend() {
        String original = "jaehoon";
        String changed = original + "_dev";
        assertNotEquals(
                StringImmutability.identityOf(original),
                StringImmutability.identityOf(changed));
    }
}
