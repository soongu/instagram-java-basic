package com.instagram.javabasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.Day32Review.FeedSummary;
import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;

class Day32ReviewTest {

    private final Member jaehoon = new Member("jaehoon", "jaehoon@example.com");
    private final Member minji = new Member("minji", "minji@example.com");

    private List<Post> samplePosts() {
        Post p1 = new Post("한강 러닝 5km", jaehoon, 320);
        Post p2 = new Post("오늘의 카페", jaehoon, 88);
        Post secret = new Post("비밀 메모", jaehoon, 999);
        secret.setStatus(PostStatus.PRIVATE);
        Post p3 = new Post("주말 산책", minji, 150);
        return List.of(p1, p2, secret, p3);
    }

    @Test
    @DisplayName("Stream+Enum+람다: 공개 게시물만 좋아요 내림차순으로 거른다")
    void publicPostsByLikes_filtersAndSorts() {
        List<Post> result = Day32Review.publicPostsByLikes(samplePosts());

        // 비공개(PRIVATE) 글은 빠지므로 4개 중 3개만 남아요.
        assertEquals(3, result.size());
        // 좋아요 내림차순: 320 → 150 → 88
        assertEquals(320, result.get(0).getLikeCount());
        assertEquals(150, result.get(1).getLikeCount());
        assertEquals(88, result.get(2).getLikeCount());
        // 비공개 글의 좋아요(999)는 어디에도 없어야 해요.
        assertTrue(result.stream().noneMatch(p -> p.getLikeCount() == 999));
    }

    @Test
    @DisplayName("Stream groupingBy + Map: 작성자 이름별로 게시물을 모은다")
    void groupByAuthor_buildsMap() {
        Map<String, List<Post>> byAuthor = Day32Review.groupByAuthor(samplePosts());

        assertEquals(2, byAuthor.size());
        assertEquals(3, byAuthor.get("jaehoon").size()); // 공개2 + 비공개1
        assertEquals(1, byAuthor.get("minji").size());
    }

    @Test
    @DisplayName("record + Stream: 작성자 요약(글 수·좋아요 합)을 FeedSummary 로 만든다")
    void summarize_buildsRecord() {
        Map<String, List<Post>> byAuthor = Day32Review.groupByAuthor(samplePosts());

        FeedSummary summary = Day32Review.summarize("jaehoon", byAuthor.get("jaehoon"));

        assertEquals("jaehoon", summary.username());
        assertEquals(3, summary.postCount());
        assertEquals(320 + 88 + 999, summary.totalLikes()); // 1407
    }

    @Test
    @DisplayName("Optional + 저장소: 저장된 회원은 id 로 찾아 돌려준다")
    void requireMember_returnsSavedMember() {
        MemberRepository repository = new MemberRepository();
        Long id = repository.save(jaehoon);

        Member found = Day32Review.requireMember(repository, id);

        assertEquals("jaehoon", found.getUsername());
    }

    @Test
    @DisplayName("커스텀 예외: 없는 회원을 찾으면 MemberNotFoundException 을 던진다")
    void requireMember_throwsWhenMissing() {
        MemberRepository repository = new MemberRepository();
        repository.save(jaehoon);

        assertThrows(MemberNotFoundException.class,
                () -> Day32Review.requireMember(repository, 999L));
    }
}
