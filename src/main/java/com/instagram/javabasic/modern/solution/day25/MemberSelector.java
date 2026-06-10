package com.instagram.javabasic.modern.solution.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.instagram.javabasic.domain.member.Member;

// [기초] 과제 1 예시답안 — PostFilter 를 회원용으로 옮긴 거예요.
// 조건(Predicate<Member>) 을 인자로 받으니, select 하나로 무엇이든 거를 수 있어요.
public class MemberSelector {

    // 조건에 맞는 회원만 골라 새 리스트로 돌려줘요. 원본은 건드리지 않아요.
    public static List<Member> select(List<Member> members, Predicate<Member> condition) {
        List<Member> result = new ArrayList<>();
        for (Member m : members) {
            if (condition.test(m)) {
                result.add(m);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 5, 365));
        members.add(new Member("jaehoon", 1240, 42, 3, 200));
        members.add(new Member("newbie", 50, 2, 10, 5));
        members.add(new Member("seungwoo", 320, 12, 1, 90));

        // 같은 select 에 람다만 갈아 끼워 세 가지 조건으로 걸러요
        System.out.println("인기 회원: " + select(members, m -> m.getFollowers() >= 1000).size());
        System.out.println("신규 회원: " + select(members, m -> m.getDaysActive() < 100).size());
        System.out.println("점수 50+: " + select(members, m -> m.calculateRecommendScore() >= 50).size());
    }
}
