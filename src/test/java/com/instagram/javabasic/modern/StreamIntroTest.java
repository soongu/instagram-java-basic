package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class StreamIntroTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("공개 글 1", minji, 250));
        Post secret = new Post("비밀 글", minji, 5);
        secret.setStatus(PostStatus.PRIVATE);
        posts.add(secret);
        posts.add(new Post("공개 글 2", minji, 80));
        return posts;
    }

    @Test
    @DisplayName("for 방식과 stream 방식의 결과가 완전히 같다")
    void forAndStreamSameResult() {
        List<Post> posts = sample();
        assertEquals(StreamIntro.publicPostsByFor(posts), StreamIntro.publicPostsByStream(posts));
    }

    @Test
    @DisplayName("공개 게시물만 2개 걸러진다")
    void filtersOnlyPublic() {
        assertEquals(2, StreamIntro.publicPostsByStream(sample()).size());
    }

    @Test
    @DisplayName("원본 리스트는 그대로 유지된다")
    void doesNotMutateInput() {
        List<Post> posts = sample();
        StreamIntro.publicPostsByStream(posts);
        assertEquals(3, posts.size());
    }
}
