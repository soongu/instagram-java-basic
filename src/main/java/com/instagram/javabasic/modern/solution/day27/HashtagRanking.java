package com.instagram.javabasic.modern.solution.day27;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/solution/day27/HashtagRanking.java
// Day 27 과제 3 풀이 — 지난 시간에 모아둔 "중복 포함 해시태그 목록" 을 드디어 순위로!
// flatMap 으로 펼치고 → groupingBy + counting 으로 빈도를 세고 → 가장 많이 나온 태그를 뽑아요.
public class HashtagRanking {

    // 해시태그별 등장 횟수 — 같은 태그가 몇 번 나왔는지 Map<태그, 횟수> 로.
    public static Map<String, Long> frequency(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> Arrays.stream(post.getContent().split(" ")))
                .filter(word -> word.startsWith("#"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // 가장 많이 나온 해시태그 한 개. max 가 돌려준 "없을 수도 있는 상자" 는 .orElse(null) 로 꺼낸 뒤
    // null 인지 직접 확인해요. (이 null 확인을 더 깔끔하게 다루는 법은 다음 시간 몫이에요.)
    public static String mostPopularTag(List<Post> posts) {
        Map.Entry<String, Long> top = frequency(posts).entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .orElse(null);
        return top == null ? "(해시태그 없음)" : top.getKey();
    }
}
