package com.instagram.javabasic.modern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

// com/instagram/javabasic/modern/OptionalRepositoryDemo.java
// 실전 — 저장소가 Optional 을 돌려주면 무엇이 달라질까요?
// 핵심: 저장소는 "찾았다 / 못 찾았다" 만 솔직하게 보고하고,
// "없을 때 무엇을 할지(예외? 기본값?)" 는 호출하는 쪽이 정해요.
// 같은 findById 하나로, 어떤 곳은 예외를 던지고 어떤 곳은 게스트로 대신할 수 있어요.
public class OptionalRepositoryDemo {

    private final Map<Long, Member> store = new HashMap<>();

    public void save(Long id, Member member) {
        store.put(id, member);
    }

    // 조회는 Optional 을 돌려줘요 — 없으면 빈 상자. 여기서 예외를 던지지 않아요(정책은 호출자 몫).
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 호출자 A — 없으면 예외. orElseThrow 로 우리 예외를 던져요.
    public Member getOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new MemberNotFoundException("id " + id + " 회원이 없어요."));
    }

    // 호출자 B — 없으면 "게스트". 같은 findById 인데 정책이 달라요.
    public String usernameOrGuest(Long id) {
        return findById(id).map(Member::getUsername).orElse("게스트");
    }

    // Optional + Stream — 여러 id 를 찾아, 존재하는 회원만 모아요.
    // Optional.stream() 은 값이 있으면 원소 1개, 비면 0개짜리 흐름이라 flatMap 으로 빈 상자가 저절로 걸러져요.
    public List<Member> findExisting(List<Long> ids) {
        return ids.stream()
                .map(this::findById)
                .flatMap(Optional::stream)
                .toList();
    }
}
