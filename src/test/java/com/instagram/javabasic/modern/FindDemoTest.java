package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class FindDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 일기", minji, 30);
        secret.setStatus(PostStatus.PRIVATE);
        return List.of(
                new Post("카페 다녀옴", minji, 250),
                secret,
                new Post("산책", minji, 40));
    }

    @Test
    @DisplayName("findFirst: 첫 비공개 글의 내용")
    void firstPrivateContent() {
        assertEquals("비밀 일기", FindDemo.firstPrivateContent(sample()));
    }

    @Test
    @DisplayName("findFirst: 비공개 글이 없으면 orElse 기본값")
    void firstPrivateContentEmpty() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> allPublic = List.of(new Post("공개1", minji, 10));
        assertEquals("(비공개 글 없음)", FindDemo.firstPrivateContent(allPublic));
    }

    @Test
    @DisplayName("findAny: 인기 글 하나(좋아요 100+)")
    void anyPopularContent() {
        assertEquals("카페 다녀옴", FindDemo.anyPopularContent(sample()));
    }

    @Test
    @DisplayName("max + orElse: 가장 좋아요 많은 글")
    void topLikedContent() {
        assertEquals("카페 다녀옴", FindDemo.topLikedContent(sample()));
    }

    @Test
    @DisplayName("max + orElse: 빈 목록이면 기본값")
    void topLikedContentEmpty() {
        assertEquals("(글 없음)", FindDemo.topLikedContent(List.of()));
    }
}
