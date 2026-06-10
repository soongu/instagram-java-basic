package com.instagram.javabasic.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostRepositoryTest {

    @Test
    @DisplayName("save 는 1부터 자동으로 id 를 붙여 돌려준다")
    void save_자동id_증가() {
        PostRepository repo = new PostRepository();
        Member jaehoon = new Member("jaehoon", "jaehoon@insta.com");
        assertEquals(1L, repo.save(new Post("첫 글", jaehoon, 0)));
        assertEquals(2L, repo.save(new Post("둘째 글", jaehoon, 0)));
    }

    @Test
    @DisplayName("저장한 게시물을 받은 id 로 다시 꺼낼 수 있다")
    void save_findById_왕복() {
        PostRepository repo = new PostRepository();
        Long id = repo.save(new Post("첫 글", new Member("jaehoon", "jaehoon@insta.com"), 0));
        assertEquals("첫 글", repo.findById(id).getContent());
    }

    @Test
    @DisplayName("없는 id 를 찾으면 IllegalArgumentException 을 던진다 (게시물은 표준 예외)")
    void findById_없는id() {
        PostRepository repo = new PostRepository();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> repo.findById(99L));
        assertEquals("id 99 에 해당하는 게시물이 없어요.", e.getMessage());
    }

    @Test
    @DisplayName("findByAuthor 는 그 작성자가 쓴 글만 골라 모은다")
    void findByAuthor() {
        PostRepository repo = new PostRepository();
        Member jaehoon = new Member("jaehoon", "jaehoon@insta.com");
        Member minji = new Member("minji", "minji@insta.com");
        repo.save(new Post("재훈 글1", jaehoon, 0));
        repo.save(new Post("재훈 글2", jaehoon, 0));
        repo.save(new Post("민지 글1", minji, 0));
        assertEquals(2, repo.findByAuthor(jaehoon).size());
        assertEquals(1, repo.findByAuthor(minji).size());
    }

    @Test
    @DisplayName("deleteById 로 지운 게시물은 더 이상 조회되지 않고, 없는 id 삭제는 예외")
    void deleteById() {
        PostRepository repo = new PostRepository();
        Long id = repo.save(new Post("지울 글", new Member("jaehoon", "jaehoon@insta.com"), 0));
        repo.deleteById(id);
        assertEquals(0, repo.count());
        assertThrows(IllegalArgumentException.class, () -> repo.deleteById(id));
    }
}
