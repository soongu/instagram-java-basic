package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayListCrudTest {

    @Test
    @DisplayName("set 으로 자리 값을 바꾸고 향상된 for 로 전체를 잇는다")
    void setAndIterate() {
        ArrayListCrud demo = new ArrayListCrud();
        assertEquals("#first", demo.replaceFirst("#first").get(0));
        assertEquals("#sunset #jeju #travel", demo.joinAll());
    }

    @Test
    @DisplayName("contains/indexOf 로 포함 여부와 위치를 찾는다")
    void containsAndIndexOf() {
        ArrayListCrud demo = new ArrayListCrud();
        assertTrue(demo.containsTag("#jeju"));
        assertFalse(demo.containsTag("#none"));
        assertEquals(2, demo.indexOfTag("#travel"));
        assertEquals(-1, demo.indexOfTag("#none"));
    }

    @Test
    @DisplayName("remove(int) 는 위치로, remove(Integer 값) 은 값으로 동작한다")
    void removeIntVsObjectTrap() {
        ArrayListCrud demo = new ArrayListCrud();

        // [10,20,30] 에서 remove(2) → 위치 2 인 30 제거 → [10,20]
        List<Integer> byPos = demo.removeByPosition();
        assertEquals(2, byPos.size());
        assertEquals(Integer.valueOf(10), byPos.get(0));
        assertEquals(Integer.valueOf(20), byPos.get(1));

        // [10,20,30] 에서 remove(값 20) → [10,30]
        List<Integer> byVal = demo.removeByValue();
        assertEquals(2, byVal.size());
        assertEquals(Integer.valueOf(10), byVal.get(0));
        assertEquals(Integer.valueOf(30), byVal.get(1));
    }

    @Test
    @DisplayName("removeByIndex 와 clear/isEmpty 가 동작한다")
    void removeAndClear() {
        ArrayListCrud demo = new ArrayListCrud();
        assertEquals("#jeju", demo.removeByIndex(0).get(0));
        assertTrue(demo.clearAndCheck());
    }
}
