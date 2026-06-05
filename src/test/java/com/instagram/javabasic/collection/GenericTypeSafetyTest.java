package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GenericTypeSafetyTest {

    @Test
    @DisplayName("오토박싱된 Integer 들의 합을 언박싱으로 더한다")
    void sumLikesWithAutoboxing() {
        GenericTypeSafety demo = new GenericTypeSafety();
        assertEquals(505, demo.sumLikes());
    }

    @Test
    @DisplayName("get 으로 꺼낸 Integer 가 int 로 언박싱된다")
    void firstLikeUnboxes() {
        GenericTypeSafety demo = new GenericTypeSafety();
        assertEquals(7, demo.firstLike());
    }
}
