package com.instagram.javabasic.service.solution.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentRepositoryTest {

    @Test
    @DisplayName("save 는 1부터 자동 id 를 붙이고, findById 로 다시 꺼낼 수 있다")
    void save_findById() {
        CommentRepository repo = new CommentRepository();
        Long id = repo.save(new Comment("jaehoon", "좋아요!", 0));
        assertEquals(1L, id);
        assertEquals("좋아요!", repo.findById(id).getText());
    }

    @Test
    @DisplayName("없는 id 를 찾으면 IllegalArgumentException 을 던진다")
    void findById_없는id() {
        CommentRepository repo = new CommentRepository();
        assertThrows(IllegalArgumentException.class, () -> repo.findById(99L));
    }

    @Test
    @DisplayName("deleteById 로 지운 댓글은 사라지고, 같은 id 를 다시 지우면 예외")
    void deleteById() {
        CommentRepository repo = new CommentRepository();
        Long id = repo.save(new Comment("minji", "굿", 0));
        repo.deleteById(id);
        assertEquals(0, repo.count());
        assertThrows(IllegalArgumentException.class, () -> repo.deleteById(id));
    }
}
