package com.instagram.javabasic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;
import com.instagram.javabasic.exceptionbasic.InvalidCaptionException;
import com.instagram.javabasic.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private final Member jaehoon = new Member("jaehoon", "jaehoon@insta.com");

    private PostService newService() {
        return new PostService(new PostRepository());
    }

    @Test
    @DisplayName("createPost 는 새 id 를 돌려주고, readPost 로 그 글을 다시 읽을 수 있다")
    void create_read_왕복() {
        PostService service = newService();
        Long id = service.createPost(jaehoon, "첫 게시물!");
        assertEquals("첫 게시물!", service.readPost(id).getContent());
    }

    @Test
    @DisplayName("빈 캡션이나 100자를 넘는 캡션은 InvalidCaptionException 으로 막힌다")
    void create_캡션검증() {
        PostService service = newService();
        assertThrows(InvalidCaptionException.class, () -> service.createPost(jaehoon, "   "));
        assertThrows(InvalidCaptionException.class, () -> service.createPost(jaehoon, "가".repeat(101)));
    }

    @Test
    @DisplayName("딱 100자 캡션은 통과한다 (경계값)")
    void create_경계값100자() {
        PostService service = newService();
        Long id = service.createPost(jaehoon, "가".repeat(100));
        assertEquals(100, service.readPost(id).getContent().length());
    }

    @Test
    @DisplayName("updateCaption 은 내용을 새 글로 바꾼다")
    void update_정상() {
        PostService service = newService();
        Long id = service.createPost(jaehoon, "수정 전");
        service.updateCaption(id, "수정 후");
        assertEquals("수정 후", service.readPost(id).getContent());
    }

    @Test
    @DisplayName("보관(ARCHIVED)된 글은 수정할 수 없어 IllegalStateException 을 던진다")
    void update_보관글거부() {
        PostRepository repo = new PostRepository();
        PostService service = new PostService(repo);
        Post post = new Post("보관될 글", jaehoon, 0);
        Long id = repo.save(post);
        post.setStatus(PostStatus.ARCHIVED);
        assertThrows(IllegalStateException.class, () -> service.updateCaption(id, "고쳐볼게"));
    }

    @Test
    @DisplayName("수정할 새 캡션도 규칙 검사를 다시 받는다")
    void update_새캡션검증() {
        PostService service = newService();
        Long id = service.createPost(jaehoon, "처음 글");
        assertThrows(InvalidCaptionException.class, () -> service.updateCaption(id, ""));
    }

    @Test
    @DisplayName("deletePost 로 지운 글은 더 이상 읽히지 않는다")
    void delete_정상() {
        PostService service = newService();
        Long id = service.createPost(jaehoon, "지울 글");
        service.deletePost(id);
        assertThrows(IllegalArgumentException.class, () -> service.readPost(id));
    }

    @Test
    @DisplayName("postsOf 는 그 작성자의 글만 모은다")
    void postsOf() {
        PostService service = newService();
        Member minji = new Member("minji", "minji@insta.com");
        service.createPost(jaehoon, "재훈 글1");
        service.createPost(jaehoon, "재훈 글2");
        service.createPost(minji, "민지 글1");
        assertEquals(2, service.postsOf(jaehoon).size());
    }
}
