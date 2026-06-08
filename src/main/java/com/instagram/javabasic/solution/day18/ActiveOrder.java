// com/instagram/javabasic/solution/day18/ActiveOrder.java
package com.instagram.javabasic.solution.day18;

import java.util.Comparator;

/**
 * 과제 3: activeDays 가 작은 사람(=최근 활동) 이 앞으로 오도록 줄 세우는 정렬 기준.
 */
class ActiveOrder implements Comparator<FollowUser> {

    @Override
    public int compare(FollowUser a, FollowUser b) {
        return a.getActiveDays() - b.getActiveDays();
    }
}
