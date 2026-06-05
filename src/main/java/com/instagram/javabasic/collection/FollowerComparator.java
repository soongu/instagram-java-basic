package com.instagram.javabasic.collection;

import java.util.Comparator;

// com/instagram/javabasic/collection/FollowerComparator.java
// Comparable 은 클래스가 "타고난 기본 정렬 기준" 한 가지를 정해요.
// 그런데 같은 명단을 "다른 기준" 으로도 정렬하고 싶을 때가 있죠. 그때 Comparator 를 따로 만들어요.
// 이 비교기는 팔로워 수가 "많은 사람부터(내림차순)" 줄을 세워요.
// (Comparable 의 기본 기준은 오름차순이었으니, 반대 방향을 골라잡는 셈이에요.)
public class FollowerComparator implements Comparator<SortableMember> {

    // compare(a, b) — a 가 b 보다 앞이면 음수, 뒤면 양수.
    // 많은 사람이 앞에 오도록(내림차순) 상대 값에서 내 값을 빼요(순서를 뒤집음).
    @Override
    public int compare(SortableMember a, SortableMember b) {
        return b.getFollowers() - a.getFollowers();
    }
}
