package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayLimitDemoTest {

    @Test
    @DisplayName("정한 크기(3)까지는 담기고, 가득 차면 더 못 담는다")
    void cannotAddWhenFull() {
        ArrayLimitDemo demo = new ArrayLimitDemo();
        assertTrue(demo.add("minji"));
        assertTrue(demo.add("seungwoo"));
        assertTrue(demo.add("jaehoon"));
        assertFalse(demo.add("yuna")); // 4번째는 거부
        assertEquals(3, demo.getCount());
    }

    @Test
    @DisplayName("중간 제거 시 뒷사람을 당겨와 빈칸을 메우고 인원이 줄어든다")
    void removeShiftsRemaining() {
        ArrayLimitDemo demo = new ArrayLimitDemo();
        demo.add("minji");
        demo.add("seungwoo");
        demo.add("jaehoon");

        demo.removeWithShift(0); // minji 제거

        assertEquals(2, demo.getCount());
        assertEquals("seungwoo", demo.get(0));
        assertEquals("jaehoon", demo.get(1));
        assertNull(demo.get(2)); // 마지막 자리는 비워짐
    }
}
