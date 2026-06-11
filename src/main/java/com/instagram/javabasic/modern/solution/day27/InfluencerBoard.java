package com.instagram.javabasic.modern.solution.day27;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/solution/day27/InfluencerBoard.java
// Day 27 과제 2 풀이 — 지난 시간 중간 연산(sorted·limit·map)에 오늘 joining 을 이어 붙여
// "팔로워 많은 순 상위 N명 명단" 을 한 문장으로 만들어요.
public class InfluencerBoard {

    // 팔로워 내림차순으로 줄 세워 앞에서 limit 명만, 이름을 " > " 로 이어 한 줄 문자열로.
    public static String topFollowersLine(List<Member> members, int limit) {
        return members.stream()
                .sorted(Comparator.comparingInt(Member::getFollowers).reversed())
                .limit(limit)
                .map(Member::getUsername)
                .collect(Collectors.joining(" > "));
    }
}
