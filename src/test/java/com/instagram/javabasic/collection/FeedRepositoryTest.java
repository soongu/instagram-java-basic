package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.post.Post;

class FeedRepositoryTest {

    @Test
    @DisplayName("회원별로 작성한 글이 묶여서 조회된다")
    void postsGroupedByMember() {
        FeedRepository repo = new FeedRepository();
        repo.addPost(1L, new Post("제주 노을", "minji", 0));
        repo.addPost(1L, new Post("카페 투어", "minji", 0));
        repo.addPost(2L, new Post("운동 기록", "jaehoon", 0));

        assertEquals(2, repo.getPostsOf(1L).size());
        assertEquals(1, repo.getPostsOf(2L).size());
    }

    @Test
    @DisplayName("글을 한 번도 안 쓴 회원은 빈 목록을 돌려준다")
    void emptyForUnknownMember() {
        FeedRepository repo = new FeedRepository();

        List<Post> posts = repo.getPostsOf(999L);
        assertTrue(posts.isEmpty());
    }

    @Test
    @DisplayName("같은 회원이 같은 글에 두 번 좋아요 해도 likeCount 는 1 이다(Set 중복 제거)")
    void duplicateLikeCountedOnce() {
        FeedRepository repo = new FeedRepository();
        Long postId = 100L;

        repo.like(postId, 2L);
        repo.like(postId, 2L); // 같은 사람 중복
        repo.like(postId, 3L);

        assertEquals(2, repo.likeCount(postId));
    }
}
