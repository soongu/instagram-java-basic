package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class LambdaIntroTest {

    private List<Member> sample() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("jaehoon", 1240, 42, 3, 200));
        members.add(new Member("minji", 8500, 150, 5, 365));
        members.add(new Member("seungwoo", 320, 12, 1, 90));
        return members;
    }

    @Test
    @DisplayName("익명 클래스 정렬 — 팔로워 많은 순(내림차순)")
    void anonymousSortsDescending() {
        List<Member> sorted = LambdaIntro.sortByFollowersAnonymous(sample());
        assertEquals("minji", sorted.get(0).getUsername());
        assertEquals("jaehoon", sorted.get(1).getUsername());
        assertEquals("seungwoo", sorted.get(2).getUsername());
    }

    @Test
    @DisplayName("람다 정렬 — 익명 클래스와 결과가 똑같다")
    void lambdaSortsSameAsAnonymous() {
        List<Member> sorted = LambdaIntro.sortByFollowersLambda(sample());
        assertEquals("minji", sorted.get(0).getUsername());
        assertEquals("jaehoon", sorted.get(1).getUsername());
        assertEquals("seungwoo", sorted.get(2).getUsername());
    }

    @Test
    @DisplayName("정렬은 새 리스트로 — 원본 순서는 그대로")
    void doesNotMutateOriginal() {
        List<Member> original = sample();
        LambdaIntro.sortByFollowersLambda(original);
        assertEquals("jaehoon", original.get(0).getUsername());
    }
}
