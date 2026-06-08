// com/instagram/javabasic/solution/day18/RankBoard.java
package com.instagram.javabasic.solution.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 과제 2: 회원들을 담아 두고 팔로워 순위를 뽑아주는 보드.
 * RankMember 가 Comparable 이라서 Collections.sort 한 줄로 정렬된다.
 */
class RankBoard {

    private final List<RankMember> members = new ArrayList<>();

    void add(RankMember member) {
        members.add(member);
    }

    /** 팔로워 많은 순으로 정렬한 뒤, 위에서 n 명을 화면에 출력한다. */
    void printTop(int n) {
        Collections.sort(members);
        int limit = Math.min(n, members.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + "위 " + members.get(i));
        }
    }

    /** 팔로워 많은 순으로 정렬한 뒤, 위에서 n 명을 리스트로 돌려준다. */
    List<RankMember> top(int n) {
        Collections.sort(members);
        int limit = Math.min(n, members.size());
        List<RankMember> result = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            result.add(members.get(i));
        }
        return result;
    }
}
