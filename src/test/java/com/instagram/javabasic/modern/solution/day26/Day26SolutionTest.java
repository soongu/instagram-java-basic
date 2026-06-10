package com.instagram.javabasic.modern.solution.day26;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class Day26SolutionTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        return List.of(
                new Post("카페 #카페 #일상", minji, 250),     // 인기(>=200), minji
                new Post("운동화 #패션", minji, 300),          // 인기, minji (중복 작성자)
                new Post("코딩 #개발 #일상", jaehoon, 220),    // 인기, jaehoon
                new Post("산책 #댕스타", jaehoon, 80));        // 비인기
    }

    @Test
    @DisplayName("과제 1 — 좋아요 200+ 작성자 이름, 중복 포함")
    void names() {
        assertEquals(List.of("minji", "minji", "jaehoon"), PopularAuthors.names(sample()));
    }

    @Test
    @DisplayName("과제 2 — 중복 없이 가나다순")
    void distinctSortedNames() {
        assertEquals(List.of("jaehoon", "minji"), PopularAuthors.distinctSortedNames(sample()));
    }

    @Test
    @DisplayName("과제 3 — 모든 해시태그 한 줄기로 (중복 포함)")
    void allTags() {
        assertEquals(List.of("#카페", "#일상", "#패션", "#개발", "#일상", "#댕스타"),
                HashtagCollector.allTags(sample()));
    }
}
