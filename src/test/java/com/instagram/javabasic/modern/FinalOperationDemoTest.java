package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class FinalOperationDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 글", minji, 300);
        secret.setStatus(PostStatus.PRIVATE);
        return List.of(
                new Post("카페 다녀옴", minji, 250),
                secret,
                new Post("평범한 하루", minji, 40));
    }

    @Test
    @DisplayName("collect(toList())로 공개 글 내용만 모은다")
    void publicContents() {
        List<String> contents = FinalOperationDemo.publicContents(sample());
        assertEquals(List.of("카페 다녀옴", "평범한 하루"), contents);
    }

    @Test
    @DisplayName("count로 인기 글(좋아요 100+) 개수를 센다")
    void countPopular() {
        assertEquals(2L, FinalOperationDemo.countPopular(sample()));
    }

    @Test
    @DisplayName("forEach로 공개 글을 한 줄씩 만든다")
    void feedLines() {
        List<String> lines = FinalOperationDemo.feedLines(sample());
        assertEquals(List.of("minji: 카페 다녀옴", "minji: 평범한 하루"), lines);
    }
}
