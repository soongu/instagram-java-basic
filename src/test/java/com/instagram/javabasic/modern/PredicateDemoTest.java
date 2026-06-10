package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class PredicateDemoTest {

    private final Member minji = new Member("minji", 8500, 150, 5, 365);
    private final Member newbie = new Member("newbie", 50, 2, 10, 5);

    @Test
    @DisplayName("Predicate.test — 팔로워 1000 이상이면 인기")
    void popularTest() {
        assertTrue(PredicateDemo.POPULAR.test(minji));
        assertFalse(PredicateDemo.POPULAR.test(newbie));
    }

    @Test
    @DisplayName("and — 인기이면서 활동적일 때만 true")
    void andCombines() {
        assertTrue(PredicateDemo.POPULAR.and(PredicateDemo.ACTIVE).test(minji));
        assertFalse(PredicateDemo.POPULAR.and(PredicateDemo.ACTIVE).test(newbie));
    }

    @Test
    @DisplayName("negate — 조건을 뒤집는다")
    void negateFlips() {
        assertTrue(PredicateDemo.POPULAR.negate().test(newbie));
        assertFalse(PredicateDemo.POPULAR.negate().test(minji));
    }
}
