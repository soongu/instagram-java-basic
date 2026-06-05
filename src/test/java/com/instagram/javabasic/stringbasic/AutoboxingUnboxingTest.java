package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoboxingUnboxingTest {

    @Test
    @DisplayName("오토박싱: int 가 Integer 로 자동 포장된다")
    void autobox_intToInteger() {
        assertEquals(Integer.valueOf(10), AutoboxingUnboxing.box(10));
    }

    @Test
    @DisplayName("언박싱: Integer 가 int 로 자동 변환된다")
    void unbox_integerToInt() {
        assertEquals(10, AutoboxingUnboxing.unbox(Integer.valueOf(10)));
    }

    @Test
    @DisplayName("null 언박싱은 NullPointerException 을 던진다")
    void unboxNull_throwsNpe() {
        assertThrows(NullPointerException.class, () -> AutoboxingUnboxing.unboxNull());
    }

    @Test
    @DisplayName("캐시 범위(127) 안의 Integer 는 == 도 true")
    void cached_withinRange() {
        assertTrue(AutoboxingUnboxing.sameCachedObject(127));
    }

    @Test
    @DisplayName("캐시 범위(200) 밖의 Integer 는 == 가 false")
    void cached_outsideRange() {
        assertFalse(AutoboxingUnboxing.sameCachedObject(200));
    }
}
