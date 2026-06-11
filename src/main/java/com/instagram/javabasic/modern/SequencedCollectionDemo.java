package com.instagram.javabasic.modern;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// com/instagram/javabasic/modern/SequencedCollectionDemo.java
// "순서가 있는 컬렉션" 들(List·LinkedList·Deque)이 공통으로 갖게 된 약속이 SequencedCollection 이에요.
// 지난 시간엔 첫 번째를 list.get(0), 마지막을 list.get(size - 1) 로 꺼냈는데,
// 이제 getFirst()·getLast() 한 단어로 또렷하게 꺼내요. 뒤집기도 reversed() 하나로 끝나요.
public class SequencedCollectionDemo {

    public static void main(String[] args) {
        // 최근 본 스토리 순서 — 먼저 본 사람이 앞, 방금 본 사람이 뒤에 있어요.
        List<String> recentStories = new ArrayList<>(List.of("jaehoon", "minji", "dana"));

        // 옛날 방식 vs 새 방식 — 결과는 같지만 새 방식이 훨씬 또렷해요.
        System.out.println("=== 첫·마지막 꺼내기 ===");
        System.out.println("옛날 첫 번째: " + recentStories.get(0)
                + " / 옛날 마지막: " + recentStories.get(recentStories.size() - 1));
        System.out.println("새 첫 번째: " + recentStories.getFirst()
                + " / 새 마지막: " + recentStories.getLast());

        // addFirst / addLast — 앞이나 뒤에 바로 끼워 넣어요.
        recentStories.addFirst("suho");   // 가장 앞(가장 오래전 본 사람)으로
        recentStories.addLast("yuna");    // 가장 뒤(방금 본 사람)로
        System.out.println("=== 앞·뒤에 추가 후 ===");
        System.out.println(recentStories);

        // reversed() — 순서를 뒤집은 모습을 돌려줘요(원본은 그대로).
        System.out.println("=== 최신순으로 뒤집기 ===");
        System.out.println("뒤집은 순서: " + recentStories.reversed());
        System.out.println("원본은 그대로: " + recentStories);

        // 같은 약속을 LinkedList 도 그대로 따라요 — 타입이 달라도 메서드 이름이 같아요.
        LinkedList<String> timeline = new LinkedList<>(List.of("아침 게시물", "점심 게시물", "저녁 게시물"));
        System.out.println("=== LinkedList 도 동일 ===");
        System.out.println("타임라인 첫 글: " + timeline.getFirst()
                + " / 마지막 글: " + timeline.getLast());

        // Deque(양쪽으로 넣고 빼는 큐)도 같은 약속을 따라요.
        Deque<String> feed = new ArrayDeque<>(List.of("게시물A", "게시물B"));
        feed.addFirst("새 게시물");
        System.out.println("=== Deque 도 동일 ===");
        System.out.println("피드 맨 앞: " + feed.getFirst());
    }
}
