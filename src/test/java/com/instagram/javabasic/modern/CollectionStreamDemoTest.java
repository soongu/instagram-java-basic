package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class CollectionStreamDemoTest {

    @Test
    @DisplayName("List 흐름은 같은 원소들을 그대로 돌려준다")
    void fromListRoundTrip() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = List.of(new Post("a", minji, 1), new Post("b", minji, 2));
        assertEquals(posts, CollectionStreamDemo.fromList(posts));
    }

    @Test
    @DisplayName("Set 흐름은 넣은 순서대로 리스트가 된다")
    void fromSetKeepsOrder() {
        Set<String> tags = new LinkedHashSet<>();
        tags.add("#일상");
        tags.add("#개발");
        assertEquals(List.of("#일상", "#개발"), CollectionStreamDemo.fromSet(tags));
    }
}
