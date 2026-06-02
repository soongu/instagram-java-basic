package com.instagram.javabasic.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.instagram.javabasic.domain.comment.Comment;
import com.instagram.javabasic.domain.follow.Follow;
import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DomainModelDemoTest {

    @Test
    @DisplayName("통합 시연: 회원 → 글 → 댓글 → 팔로우 참조 체인이 모두 연결된다")
    void referenceChain_isFullyConnected() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);

        // 글을 쓰면서 작성자 객체를 직접 참조
        Post post = new Post("오늘 점심 맛집 추천!", jaehoon, 12);
        jaehoon.addWrittenPost(post);

        // 글이 작성자를 거꾸로도 가리킨다
        assertSame(jaehoon, post.getAuthor());
        assertSame(post, jaehoon.getWrittenPost(0));

        // 댓글 달기
        post.addComment(new Comment("minji_cafe", "어디예요?", 0));
        post.addComment("저도 갈래요");
        assertEquals(2, post.getCommentCount());

        // 팔로우 관계
        jaehoon.follow(minji);
        assertTrue(jaehoon.isFollowing(minji));

        Follow follow = new Follow(jaehoon, minji);
        assertSame(jaehoon, follow.getFollower());
        assertSame(minji, follow.getFollowee());
    }

    @Test
    @DisplayName("main 메서드는 예외 없이 끝까지 실행된다")
    void main_runsWithoutException() {
        assertDoesNotThrow(() -> DomainModelDemo.main(new String[] {}));
    }
}
