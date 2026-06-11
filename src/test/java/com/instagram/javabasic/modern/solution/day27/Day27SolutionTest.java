package com.instagram.javabasic.modern.solution.day27;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class Day27SolutionTest {

    private List<Post> posts() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Post secret = new Post("비밀 #일상", minji, 90);
        secret.setStatus(PostStatus.PRIVATE);
        Post archived = new Post("작년 #추억", jaehoon, 70);
        archived.setStatus(PostStatus.ARCHIVED);
        return List.of(
                new Post("카페 #카페 #일상", minji, 250),
                new Post("코딩 #개발 #일상", jaehoon, 120),
                secret,
                archived);
    }

    @Test
    @DisplayName("과제 1 — groupingBy+counting: 상태별 글 개수 (공개2/비공개1/보관1)")
    void countByStatus() {
        Map<PostStatus, Long> report = StatusReport.countByStatus(posts());
        assertEquals(2L, report.get(PostStatus.PUBLIC));
        assertEquals(1L, report.get(PostStatus.PRIVATE));
        assertEquals(1L, report.get(PostStatus.ARCHIVED));
    }

    @Test
    @DisplayName("과제 2 — sorted+limit+map+joining: 팔로워 상위 2명 명단")
    void topFollowersLine() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);
        assertEquals("minji > jaehoon",
                InfluencerBoard.topFollowersLine(List.of(seungwoo, minji, jaehoon), 2));
    }

    @Test
    @DisplayName("과제 3 — flatMap+groupingBy+counting: 해시태그 빈도 (#일상 3회)")
    void hashtagFrequency() {
        Map<String, Long> freq = HashtagRanking.frequency(posts());
        assertEquals(3L, freq.get("#일상"));
        assertEquals(1L, freq.get("#카페"));
        assertEquals(1L, freq.get("#개발"));
        assertEquals(1L, freq.get("#추억"));
    }

    @Test
    @DisplayName("과제 3 — max+orElse: 가장 많이 나온 해시태그는 #일상")
    void mostPopularTag() {
        assertEquals("#일상", HashtagRanking.mostPopularTag(posts()));
    }

    @Test
    @DisplayName("과제 3 — 해시태그가 없으면 orElse 기본값")
    void mostPopularTagEmpty() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> noTags = List.of(new Post("그냥 일상 글", minji, 10));
        assertEquals("(해시태그 없음)", HashtagRanking.mostPopularTag(noTags));
    }
}
