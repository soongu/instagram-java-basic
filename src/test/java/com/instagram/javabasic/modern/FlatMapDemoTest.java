package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class FlatMapDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        return List.of(
                new Post("오늘 카페 #카페 #일상", minji, 250),
                new Post("새 운동화 #패션 #일상", minji, 80));
    }

    @Test
    @DisplayName("모든 게시물의 해시태그를 한 줄기로 펼친다 (중복 포함)")
    void allTags() {
        assertEquals(List.of("#카페", "#일상", "#패션", "#일상"), FlatMapDemo.allTags(sample()));
    }

    @Test
    @DisplayName("distinct 를 더하면 중복 없는 해시태그가 된다")
    void distinctTags() {
        assertEquals(List.of("#카페", "#일상", "#패션"), FlatMapDemo.distinctTags(sample()));
    }
}
