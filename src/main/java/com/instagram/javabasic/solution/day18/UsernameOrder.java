// com/instagram/javabasic/solution/day18/UsernameOrder.java
package com.instagram.javabasic.solution.day18;

import java.util.Comparator;

/**
 * 과제 3: 사용자 이름을 가나다(알파벳) 순으로 줄 세우는 정렬 기준.
 * 람다 대신 이름을 가진 클래스로 만들어 정렬 규칙을 분명히 드러낸다.
 */
class UsernameOrder implements Comparator<FollowUser> {

    @Override
    public int compare(FollowUser a, FollowUser b) {
        return a.getUsername().compareTo(b.getUsername());
    }
}
