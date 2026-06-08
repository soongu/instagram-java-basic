package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SafeRemovalIteratorTest {

    @Test
    @DisplayName("Iterator.remove 로 비활성 회원을 안전하게 지우고 남은 회원을 돌려준다")
    void removeInactiveSafely() {
        SafeRemovalIterator demo = new SafeRemovalIterator();
        List<String> all = new ArrayList<>(List.of("minji", "jaehoon", "seungwoo", "yuna"));
        List<String> inactive = List.of("jaehoon", "yuna");

        List<String> survivors = demo.removeInactive(all, inactive);

        assertEquals(List.of("minji", "seungwoo"), survivors);
    }

    @Test
    @DisplayName("for-each 순회 중 list.remove 를 부르면 ConcurrentModificationException 이 터진다")
    void unsafeRemoveThrows() {
        SafeRemovalIterator demo = new SafeRemovalIterator();
        List<String> all = new ArrayList<>(List.of("minji", "jaehoon", "seungwoo", "yuna"));
        List<String> inactive = List.of("jaehoon", "yuna");

        assertThrows(ConcurrentModificationException.class,
                () -> demo.unsafeRemove(all, inactive));
    }
}
