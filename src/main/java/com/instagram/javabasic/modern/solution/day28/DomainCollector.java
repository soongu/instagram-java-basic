package com.instagram.javabasic.modern.solution.day28;

import java.util.List;
import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/solution/day28/DomainCollector.java
// [과제 3] 흐름(Stream)과 상자(Optional)의 만남 — 이메일이 등록된 회원들의 도메인만 중복 없이 모아요.
// Optional.stream() 은 값이 있으면 원소 1개, 비면 0개짜리 흐름이라, flatMap 으로 빈 상자(이메일 없음)가 저절로 걸러져요.
public class DomainCollector {

    public static List<String> registeredDomains(List<Member> members) {
        return members.stream()
                .map(Member::getEmail)
                .flatMap(email -> Optional.ofNullable(email).stream())
                .filter(email -> email.contains("@"))
                .map(email -> email.substring(email.indexOf("@") + 1))
                .distinct()
                .toList();
    }
}
