package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class ReduceDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        return List.of(
                new Post("카페", minji, 250),
                new Post("코딩", jaehoon, 120),
                new Post("산책", minji, 40));
    }

    @Test
    @DisplayName("reduce(0, sum)로 좋아요를 모두 합산한다")
    void totalLikes() {
        assertEquals(410, ReduceDemo.totalLikes(sample()));
    }

    @Test
    @DisplayName("reduce로 팔로워 합계를 구한다")
    void totalFollowers() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        assertEquals(9740, ReduceDemo.totalFollowers(List.of(minji, jaehoon)));
    }

    @Test
    @DisplayName("reduce(0, max)로 최다 좋아요 값을 구한다")
    void mostLikes() {
        assertEquals(250, ReduceDemo.mostLikes(sample()));
    }

    @Test
    @DisplayName("빈 흐름이면 초기값 0이 그대로 나온다")
    void emptyStream() {
        assertEquals(0, ReduceDemo.totalLikes(List.of()));
        assertEquals(0, ReduceDemo.mostLikes(List.of()));
    }
}
