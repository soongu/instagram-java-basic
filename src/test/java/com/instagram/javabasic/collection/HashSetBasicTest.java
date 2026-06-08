package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HashSetBasicTest {

    @Test
    @DisplayName("같은 username 을 두 번 넣어도 size 는 그대로 2 다(중복 자동 제거)")
    void removesDuplicate() {
        HashSetBasic demo = new HashSetBasic();
        Set<String> liked = demo.likedUsernames();

        assertEquals(2, liked.size());
        assertTrue(liked.contains("minji"));
        assertTrue(liked.contains("jaehoon"));
    }

    @Test
    @DisplayName("이미 있는 값을 add 하면 false 를 돌려준다")
    void addReturnsFalseOnDuplicate() {
        HashSetBasic demo = new HashSetBasic();

        assertFalse(demo.addReturnsFalseOnDuplicate());
    }

    @Test
    @DisplayName("contains 로 명단 포함 여부를, remove 로 제거를 확인한다")
    void containsAndRemove() {
        HashSetBasic demo = new HashSetBasic();
        Set<String> liked = demo.likedUsernames();

        assertTrue(demo.hasLiked(liked, "minji"));
        assertFalse(demo.hasLiked(liked, "unknown"));

        demo.cancelLike(liked, "minji");
        assertEquals(1, liked.size());
        assertFalse(demo.hasLiked(liked, "minji"));
    }
}
