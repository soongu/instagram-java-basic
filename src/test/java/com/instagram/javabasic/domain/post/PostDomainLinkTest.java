package com.instagram.javabasic.domain.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.instagram.javabasic.domain.comment.Comment;
import com.instagram.javabasic.domain.content.Commentable;
import com.instagram.javabasic.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostDomainLinkTest {

    @Test
    @DisplayName("author 참조 생성자: 작성자 Member 와 authorName 문자열이 함께 채워진다")
    void authorConstructor_syncsAuthorAndAuthorName() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);

        Post post = new Post("오늘 점심 맛집 추천!", jaehoon, 12);

        assertSame(jaehoon, post.getAuthor());
        assertEquals("jaehoon_dev", post.getAuthorName());
        assertEquals("오늘 점심 맛집 추천!", post.getContent());
        assertEquals(12, post.getLikeCount());
        assertEquals(PostStatus.PUBLIC, post.getStatus());
    }

    @Test
    @DisplayName("addComment(Comment): 댓글 객체를 배열에 추가하고 개수가 늘어난다")
    void addComment_byObject_storesAndCounts() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Post post = new Post("오늘 점심 맛집 추천!", jaehoon, 12);

        Comment c1 = new Comment("minji", "어디예요?", 0);
        Comment c2 = new Comment("seungwoo", "저도 가고싶어요", 0);
        post.addComment(c1);
        post.addComment(c2);

        assertEquals(2, post.getCommentCount());
        assertSame(c1, post.getComment(0));
        assertSame(c2, post.getComment(1));
    }

    @Test
    @DisplayName("addComment(String): 텍스트만 받으면 작성자 이름으로 댓글을 만들어 추가한다 (Commentable 약속)")
    void addComment_byText_buildsCommentWithAuthorName() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Post post = new Post("오늘 점심 맛집 추천!", jaehoon, 12);

        post.addComment("좋아요!");

        assertEquals(1, post.getCommentCount());
        Comment stored = post.getComment(0);
        assertEquals("jaehoon_dev", stored.getAuthor());
        assertEquals("좋아요!", stored.getText());
        assertEquals(0, stored.getLikeCount());
    }

    @Test
    @DisplayName("Post 는 Commentable 역할(인터페이스)을 수행한다")
    void post_isCommentable() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Commentable commentable = new Post("내용", jaehoon, 0);

        commentable.addComment("첫 댓글");

        assertEquals(1, commentable.getCommentCount());
    }

    @Test
    @DisplayName("MAX_COMMENTS 한도를 넘는 댓글은 무시되고 개수가 한도에서 멈춘다")
    void addComment_ignoredWhenFull() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Post post = new Post("내용", jaehoon, 0);

        for (int i = 0; i < Commentable.MAX_COMMENTS + 5; i++) {
            post.addComment("댓글 " + i);
        }

        assertEquals(Commentable.MAX_COMMENTS, post.getCommentCount());
    }

    @Test
    @DisplayName("기존 생성자(문자열 authorName)는 그대로 동작한다 (가법 진화)")
    void legacyConstructor_stillWorks() {
        Post post = new Post("내용", "jaehoon_dev", 12);
        assertEquals("jaehoon_dev", post.getAuthorName());
        assertEquals(0, post.getCommentCount());
    }
}
