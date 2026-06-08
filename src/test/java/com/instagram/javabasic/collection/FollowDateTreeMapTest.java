package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowDateTreeMapTest {

    private FollowDateTreeMap shuffled() {
        FollowDateTreeMap log = new FollowDateTreeMap();
        log.recordFollow("2026-03-20", "seungwoo");
        log.recordFollow("2026-01-15", "minji");
        log.recordFollow("2026-02-10", "jaehoon");
        return log;
    }

    @Test
    @DisplayName("뒤섞어 넣어도 firstKey 는 가장 이른 날짜, lastKey 는 가장 늦은 날짜다")
    void firstAndLastKey() {
        FollowDateTreeMap log = shuffled();

        assertEquals("2026-01-15", log.earliestDate());
        assertEquals("2026-03-20", log.latestDate());
    }

    @Test
    @DisplayName("순회 순서는 날짜순으로 정렬되어 있다")
    void iterationIsOrdered() {
        FollowDateTreeMap log = shuffled();

        assertEquals(List.of("minji", "jaehoon", "seungwoo"), log.followsInOrder());
    }
}
