package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// com/instagram/javabasic/collection/FollowDateTreeMap.java
// TreeMap 은 "이름표(키)를 자동으로 정렬해 주는 사물함" 이에요.
// 여기서는 팔로우한 날짜 문자열("2026-01-15") 을 키로, 팔로우한 username 을 값으로 둬요.
// 날짜를 뒤죽박죽 넣어도 안에서는 항상 시간순(이른 날짜 → 늦은 날짜)으로 줄을 서요.
//   날짜는 아직 배우지 않은 타입 대신 "2026-01-15" 같은 ISO 문자열로 표현해요.
//   문자열도 사전순으로 비교하면 그대로 시간순이 돼서 정렬에 딱 맞아요.
public class FollowDateTreeMap {

    // 날짜(키) → username(값) 을 보관해요. TreeMap 이라 키가 자동 정렬돼요.
    private final TreeMap<String, String> follows = new TreeMap<>();

    // 팔로우 한 건을 기록해요. 어떤 순서로 넣든 안에서는 날짜순으로 정리돼요.
    public void recordFollow(String date, String username) {
        follows.put(date, username);
    }

    // 가장 이른 팔로우 날짜 — TreeMap 은 firstKey() 로 제일 작은 키를 바로 알려줘요.
    public String earliestDate() {
        return follows.firstKey();
    }

    // 가장 늦은 팔로우 날짜 — lastKey() 로 제일 큰 키를 알려줘요.
    public String latestDate() {
        return follows.lastKey();
    }

    // 날짜순으로 정렬된 username 목록을 돌려줘요.
    // TreeMap 을 순회하면 키가 작은 것부터(이른 날짜부터) 차례로 나와요.
    public List<String> followsInOrder() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : follows.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        FollowDateTreeMap log = new FollowDateTreeMap();
        // 일부러 뒤섞어 넣어요
        log.recordFollow("2026-03-20", "seungwoo");
        log.recordFollow("2026-01-15", "minji");
        log.recordFollow("2026-02-10", "jaehoon");

        System.out.println("가장 이른 팔로우 날짜: " + log.earliestDate()); // 2026-01-15
        System.out.println("가장 늦은 팔로우 날짜: " + log.latestDate());   // 2026-03-20
        System.out.println("시간순 팔로우 순서: " + log.followsInOrder());  // [minji, jaehoon, seungwoo]
    }
}
