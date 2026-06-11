package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class CollectorsDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Post secret = new Post("비밀 #일상", minji, 90);
        secret.setStatus(PostStatus.PRIVATE);
        return List.of(
                new Post("카페 #카페 #일상", minji, 250),
                new Post("코딩 #개발 #일상", jaehoon, 120),
                secret);
    }

    @Test
    @DisplayName("groupingBy: 작성자별로 글을 묶는다")
    void postsByAuthor() {
        Map<String, List<Post>> byAuthor = CollectorsDemo.postsByAuthor(sample());
        assertEquals(2, byAuthor.get("minji").size());
        assertEquals(1, byAuthor.get("jaehoon").size());
    }

    @Test
    @DisplayName("groupingBy + counting: 해시태그 빈도를 센다 (#일상 3회)")
    void hashtagFrequency() {
        Map<String, Long> freq = CollectorsDemo.hashtagFrequency(sample());
        assertEquals(3L, freq.get("#일상"));
        assertEquals(1L, freq.get("#카페"));
        assertEquals(1L, freq.get("#개발"));
    }

    @Test
    @DisplayName("toSet: 중복 없는 작성자 집합")
    void distinctAuthors() {
        Set<String> authors = CollectorsDemo.distinctAuthors(sample());
        assertEquals(Set.of("minji", "jaehoon"), authors);
    }

    @Test
    @DisplayName("toMap: 이름->팔로워 맵")
    void followerMap() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Map<String, Integer> map = CollectorsDemo.followerMap(List.of(minji, jaehoon));
        assertEquals(8500, map.get("minji"));
        assertEquals(1240, map.get("jaehoon"));
    }

    @Test
    @DisplayName("joining: 닉네임을 쉼표로 연결")
    void usernamesJoined() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        assertEquals("minji, jaehoon", CollectorsDemo.usernamesJoined(List.of(minji, jaehoon)));
    }

    @Test
    @DisplayName("partitioningBy: 공개(true) 2개 / 비공개(false) 1개")
    void partitionByPublic() {
        Map<Boolean, List<Post>> parts = CollectorsDemo.partitionByPublic(sample());
        assertEquals(2, parts.get(true).size());
        assertEquals(1, parts.get(false).size());
        assertTrue(parts.containsKey(true) && parts.containsKey(false));
    }
}
