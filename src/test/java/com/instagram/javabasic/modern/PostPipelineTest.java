package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class PostPipelineTest {

    @Test
    @DisplayName("공개+인기 글을 좋아요 순으로 제목만 — 비공개/비인기는 빠진다")
    void popularPublicTitles() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 인기 글", minji, 500);
        secret.setStatus(PostStatus.PRIVATE);
        List<Post> posts = List.of(
                new Post("두 번째 인기 글", minji, 120),
                new Post("가장 인기 글", minji, 300),
                secret,
                new Post("평범한 글", minji, 40));

        assertEquals(List.of("가장 인기 글", "두 번째 인기 글"), PostPipeline.popularPublicTitles(posts));
    }
}
