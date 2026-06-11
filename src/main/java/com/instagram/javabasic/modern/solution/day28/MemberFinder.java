package com.instagram.javabasic.modern.solution.day28;

import java.util.List;
import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

// com/instagram/javabasic/modern/solution/day28/MemberFinder.java
// [과제 1] 회원 명단에서 username 으로 한 명 찾기.
// 저장소는 "찾았다 / 못 찾았다" 만 Optional 로 보고하고, 없을 때 무엇을 할지는 호출자가 정해요.
public class MemberFinder {

    // 찾으면 값이 든 상자, 없으면 빈 상자를 돌려줘요.
    public static Optional<Member> findByUsername(List<Member> members, String username) {
        return members.stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst();
    }

    // 없으면 예외 — "그 회원이 꼭 있어야 진행 가능" 인 자리.
    public static Member getByUsernameOrThrow(List<Member> members, String username) {
        return findByUsername(members, username)
                .orElseThrow(() -> new MemberNotFoundException(username + " 회원을 찾을 수 없어요."));
    }
}
