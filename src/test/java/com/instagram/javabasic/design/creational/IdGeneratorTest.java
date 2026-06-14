package com.instagram.javabasic.design.creational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IdGeneratorTest {

    @Test
    @DisplayName("어디서 불러도 같은 발급기 하나만 존재한다 (Singleton)")
    void singleInstance() {
        IdGenerator a = IdGenerator.INSTANCE;
        IdGenerator b = IdGenerator.INSTANCE;

        assertSame(a, b);
    }

    @Test
    @DisplayName("nextId: 부를 때마다 1씩 커지는 새 번호를 준다")
    void nextId_increasing() {
        long first = IdGenerator.INSTANCE.nextId();
        long second = IdGenerator.INSTANCE.nextId();

        assertEquals(first + 1, second);
        assertTrue(second > first);
    }

    @Test
    @DisplayName("nextId: 여러 번 발급해도 번호가 절대 겹치지 않는다")
    void nextId_unique() {
        Set<Long> seen = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            long id = IdGenerator.INSTANCE.nextId();
            assertTrue(seen.add(id), "번호 " + id + " 가 중복 발급되었어요");
        }
    }
}
