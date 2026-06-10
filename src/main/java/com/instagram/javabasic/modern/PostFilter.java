package com.instagram.javabasic.modern;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/PostFilter.java
// 지난 시간(Day 24)의 "사물함을 훑으며 조건 맞는 것만 새 리스트에 담는" for 반복을,
// 조건(Predicate) 을 인자로 받는 메서드 하나로 묶었어요.
// 안쪽 for 반복은 똑같지만, "무슨 조건으로 거를지" 는 부르는 쪽이 람다로 정해요.
// 같은 filter 하나로 좋아요 많은 글, 특정 작성자 글, 공개 글… 무엇이든 걸러낼 수 있어요.
public class PostFilter {

    // 조건에 맞는 게시물만 골라 새 리스트로 돌려줘요. 원본 리스트는 건드리지 않아요.
    public static List<Post> filter(List<Post> posts, Predicate<Post> condition) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (condition.test(post)) {
                result.add(post);
            }
        }
        return result;
    }
}
