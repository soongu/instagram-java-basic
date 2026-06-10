package com.instagram.javabasic.modern;

import java.util.function.Predicate;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/PredicateDemo.java
// Predicate<T> — "조건을 담는 그릇" 이에요. test(T) 가 true/false 를 돌려줘요.
// 우리가 직접 만든 MemberFilter 와 똑같은 모양인데, 자바가 미리 만들어둔 표준판이에요.
// and / or / negate 로 조건을 합치거나 뒤집을 수도 있어요.
public class PredicateDemo {

    // 인기 회원인가? — 팔로워 1000 이상이면 true
    public static final Predicate<Member> POPULAR = m -> m.getFollowers() >= 1000;

    // 활동적인가? — 활동 일수 100 이상이면 true
    public static final Predicate<Member> ACTIVE = m -> m.getDaysActive() >= 100;

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member newbie = new Member("newbie", 50, 2, 10, 5);

        System.out.println("minji 인기? " + POPULAR.test(minji));    // true
        System.out.println("newbie 인기? " + POPULAR.test(newbie));  // false

        // and — 둘 다 true 일 때만 true
        Predicate<Member> popularAndActive = POPULAR.and(ACTIVE);
        System.out.println("minji 인기+활동? " + popularAndActive.test(minji));  // true

        // negate — 결과를 뒤집어요
        System.out.println("newbie 인기 아님? " + POPULAR.negate().test(newbie));  // true
    }
}
