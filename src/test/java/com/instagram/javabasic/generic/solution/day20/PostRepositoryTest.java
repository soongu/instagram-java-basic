package com.instagram.javabasic.generic.solution.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.post.Post;

class PostRepositoryTest {

    @Test
    @DisplayName("Repository<Post> 를 물려받아 save/findById 를 형변환 없이 쓴다")
    void inheritsSaveAndFindById() {
        PostRepository repo = new PostRepository();
        Post post = new Post("첫 게시물", "minji", 10);
        repo.save(1L, post);

        Post found = repo.findById(1L);

        assertSame(post, found);
        assertEquals(1, repo.count());
    }

    @Test
    @DisplayName("findByWriter 는 같은 작성자의 게시물만 모아 돌려준다")
    void findByWriterFiltersByAuthor() {
        PostRepository repo = new PostRepository();
        repo.save(1L, new Post("첫 게시물", "minji", 10));
        repo.save(2L, new Post("점심 인증", "jaehoon", 5));
        repo.save(3L, new Post("저녁 노을", "minji", 8));

        List<Post> minjiPosts = repo.findByWriter("minji");

        assertEquals(2, minjiPosts.size());
        for (Post p : minjiPosts) {
            assertEquals("minji", p.getAuthorName());
        }
    }

    @Test
    @DisplayName("작성자가 한 명인 경우 그 한 건만 돌려준다")
    void findByWriterSingleAuthor() {
        PostRepository repo = new PostRepository();
        repo.save(1L, new Post("첫 게시물", "minji", 10));
        repo.save(2L, new Post("점심 인증", "jaehoon", 5));

        assertEquals(1, repo.findByWriter("jaehoon").size());
    }

    @Test
    @DisplayName("없는 작성자로 findByWriter 를 부르면 빈 리스트를 돌려준다")
    void findByWriterMissingReturnsEmpty() {
        PostRepository repo = new PostRepository();
        repo.save(1L, new Post("첫 게시물", "minji", 10));

        List<Post> result = repo.findByWriter("seoyeon");

        assertTrue(result.isEmpty());
    }
}
