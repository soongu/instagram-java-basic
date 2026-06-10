package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class MemberFilterTest {

    @Test
    @DisplayName("@FunctionalInterface 에 람다를 바로 담아 isMatch 로 부른다")
    void lambdaImplementsCustomFunctionalInterface() {
        MemberFilter popular = m -> m.getFollowers() >= 1000;

        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member newbie = new Member("newbie", 50, 2, 10, 5);

        assertTrue(popular.isMatch(minji));
        assertFalse(popular.isMatch(newbie));
    }

    @Test
    @DisplayName("같은 인터페이스에 다른 람다를 담으면 다른 규칙이 된다")
    void differentLambdaDifferentRule() {
        MemberFilter active = m -> m.getDaysActive() >= 100;

        Member veteran = new Member("veteran", 300, 80, 2, 365);
        Member newbie = new Member("newbie", 50, 2, 10, 5);

        assertTrue(active.isMatch(veteran));
        assertFalse(active.isMatch(newbie));
    }
}
