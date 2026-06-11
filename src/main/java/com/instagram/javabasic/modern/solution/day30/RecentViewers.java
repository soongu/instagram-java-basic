package com.instagram.javabasic.modern.solution.day30;

import java.util.List;

// com/instagram/javabasic/modern/solution/day30/RecentViewers.java
// [과제 3] "최근 본 프로필" 목록을 순서 있는 컬렉션(List) 위에서 다뤄요.
// 먼저 본 사람이 앞, 방금 본 사람이 뒤에 줄 서 있어요.
// get(0)·get(size-1) 대신 getFirst()·getLast() 한 단어로 또렷하게 꺼내고, 뒤집기는 reversed() 하나로 끝나요.
public class RecentViewers {

    // 가장 먼저 본 사람(첫 번째)을 돌려줘요.
    public static String firstViewer(List<String> viewers) {
        return viewers.getFirst();
    }

    // 방금 본 사람(마지막)을 돌려줘요.
    public static String latestViewer(List<String> viewers) {
        return viewers.getLast();
    }

    // 최신순(방금 본 사람이 맨 앞)으로 뒤집은 목록을 돌려줘요. 원본은 그대로 둬요.
    // reversed() 는 원본을 건드리지 않고 뒤집힌 모습을 돌려주고, copyOf 로 독립된 목록으로 떠서 안전하게 넘겨요.
    public static List<String> recentOrder(List<String> viewers) {
        return List.copyOf(viewers.reversed());
    }
}
