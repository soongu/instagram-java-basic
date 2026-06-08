package com.instagram.javabasic.collection;

import java.util.HashSet;
import java.util.Set;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/collection/HashCodeContract.java
// HashSet 에 "직접 만든 클래스(Member)" 를 넣을 때는 equals 만으로는 부족해요.
// HashSet 은 먼저 hashCode 로 칸을 정하고, 그 칸에서 equals 로 같은지 확인하거든요.
// 그래서 Member 에 equals(username 기준) 와 hashCode(username 기준) 를 함께 맞춰 두면,
// username 이 같은 두 Member 객체를 "같은 사람" 으로 보고 중복으로 걸러내요.
public class HashCodeContract {

    // username 이 같은 Member 두 개를 HashSet 에 넣어요.
    // equals/hashCode 가 username 기준으로 맞춰져 있어서 결과 명단 크기는 1 이에요.
    public Set<Member> uniqueMembers() {
        Set<Member> members = new HashSet<>();
        members.add(new Member("minji", 8500, 150, 12, 400));
        members.add(new Member("minji", 9999, 200, 20, 500)); // 같은 username — 중복으로 걸러져요
        members.add(new Member("jaehoon", 1240, 42, 3, 120));
        return members;
    }

    public static void main(String[] args) {
        HashCodeContract demo = new HashCodeContract();
        Set<Member> members = demo.uniqueMembers();

        System.out.println("중복 제거 후 인원: " + members.size()); // 2 (minji 둘은 하나로)

        Member a = new Member("minji", 8500, 150, 12, 400);
        Member b = new Member("minji", 9999, 200, 20, 500);
        System.out.println("두 minji equals? " + a.equals(b));         // true
        System.out.println("두 minji hashCode 같나? " + (a.hashCode() == b.hashCode())); // true
    }
}
