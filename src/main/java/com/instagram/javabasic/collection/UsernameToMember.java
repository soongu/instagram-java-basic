package com.instagram.javabasic.collection;

import java.util.HashMap;
import java.util.Map;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/collection/UsernameToMember.java
// Map 은 "이름표(키) → 물건(값)" 으로 짝지어 보관하는 사물함이에요.
// 여기서는 username(키)으로 Member 객체(값)를 짝지어 두고,
// 이름만 알면 그 사람의 정보를 단번에 꺼낼 수 있게 만들어요(빠른 조회).
//   Map<String, Member> 의 <String, Member> 는 "이름표는 String, 안에 넣는 건 Member" 라는 약속이에요.
public class UsernameToMember {

    // username 을 이름표로, Member 객체를 내용물로 보관하는 사물함이에요.
    private final Map<String, Member> members = new HashMap<>();

    // 회원을 등록해요. member 의 username 을 키로 삼아 짝지어 넣어요.
    public void register(Member member) {
        members.put(member.getUsername(), member);
    }

    // username 으로 회원을 찾아요. 없으면 null 이 나와요.
    public Member find(String username) {
        return members.get(username);
    }

    // 그 이름이 등록돼 있는지만 물어봐요.
    public boolean exists(String username) {
        return members.containsKey(username);
    }

    // 없을 때 대신 돌려줄 값을 미리 정해 두고 꺼내요(getOrDefault).
    // 없는 키를 찾아도 null 대신 우리가 정한 "기본 회원" 이 나와요.
    public Member findOrDefault(String username, Member fallback) {
        return members.getOrDefault(username, fallback);
    }

    public int size() {
        return members.size();
    }

    public static void main(String[] args) {
        UsernameToMember repo = new UsernameToMember();
        repo.register(new Member("minji", 8500, 150, 12, 400));
        repo.register(new Member("jaehoon", 1240, 42, 3, 120));

        System.out.println("등록 수: " + repo.size()); // 2
        Member found = repo.find("minji");
        System.out.println("찾은 사람: " + found);
        System.out.println("없는 이름 find: " + repo.find("unknown")); // null
        System.out.println("minji 있나요? " + repo.exists("minji"));   // true
    }
}
