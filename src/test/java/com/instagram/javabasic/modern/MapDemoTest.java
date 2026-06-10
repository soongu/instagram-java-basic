package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class MapDemoTest {

    private List<Post> posts() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        return List.of(
                new Post("카페 다녀옴", minji, 250),
                new Post("운동 시작", minji, 40));
    }

    @Test
    @DisplayName("게시물 흐름을 내용(String) 흐름으로 바꾼다")
    void contents() {
        assertEquals(List.of("카페 다녀옴", "운동 시작"), MapDemo.contents(posts()));
    }

    @Test
    @DisplayName("게시물 흐름을 좋아요 수(Integer) 흐름으로 바꾼다")
    void likeCounts() {
        assertEquals(List.of(250, 40), MapDemo.likeCounts(posts()));
    }

    @Test
    @DisplayName("회원 흐름을 이름 흐름으로 바꾼다")
    void usernames() {
        Member a = new Member("minji", 8500, 150, 5, 365);
        Member b = new Member("jaehoon", 1240, 42, 3, 200);
        assertEquals(List.of("minji", "jaehoon"), MapDemo.usernames(List.of(a, b)));
    }

    @Test
    @DisplayName("filter 뒤 map 으로 인기 글 내용만 뽑는다")
    void popularContents() {
        assertEquals(List.of("카페 다녀옴"), MapDemo.popularContents(posts()));
    }
}
