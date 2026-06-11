package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class ParallelStreamDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        return List.of(
                new Post("a", minji, 10),
                new Post("b", minji, 20),
                new Post("c", minji, 30));
    }

    @Test
    @DisplayName("순차 합산은 60")
    void sequential() {
        assertEquals(60, ParallelStreamDemo.sequentialTotalLikes(sample()));
    }

    @Test
    @DisplayName("합산처럼 순서 무관한 일은 병렬도 같은 결과")
    void parallelSameAsSequential() {
        List<Post> posts = sample();
        assertEquals(ParallelStreamDemo.sequentialTotalLikes(posts),
                ParallelStreamDemo.parallelTotalLikes(posts));
    }
}
