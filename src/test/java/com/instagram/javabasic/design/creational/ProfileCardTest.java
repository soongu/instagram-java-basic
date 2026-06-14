package com.instagram.javabasic.design.creational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfileCardTest {

    @Test
    @DisplayName("필수 username 만으로도 카드를 만들 수 있고, 선택 값은 기본값이 채워진다")
    void build_withRequiredOnly() {
        ProfileCard card = ProfileCard.builder("jaehoon").build();

        assertEquals("jaehoon", card.getUsername());
        assertEquals("", card.getBio());
        assertEquals("", card.getWebsite());
        assertFalse(card.isVerified());
        assertEquals(0, card.getFollowerCount());
    }

    @Test
    @DisplayName("원하는 선택 값만 골라 이름표 붙여 넣을 수 있다 (순서는 자유)")
    void build_withSomeOptionals() {
        ProfileCard card = ProfileCard.builder("minji")
                .followerCount(8500)
                .verified(true)
                .build();

        assertEquals("minji", card.getUsername());
        assertTrue(card.isVerified());
        assertEquals(8500, card.getFollowerCount());
        assertEquals("", card.getBio());   // 안 넣은 건 기본값 그대로
    }

    @Test
    @DisplayName("모든 값을 채워 완성한 카드는 넣은 그대로 담긴다")
    void build_withAll() {
        ProfileCard card = ProfileCard.builder("seungwoo")
                .bio("사진 찍는 걸 좋아해요 📷")
                .website("instagram.com/seungwoo")
                .verified(true)
                .followerCount(320)
                .build();

        assertEquals("seungwoo", card.getUsername());
        assertEquals("사진 찍는 걸 좋아해요 📷", card.getBio());
        assertEquals("instagram.com/seungwoo", card.getWebsite());
        assertTrue(card.isVerified());
        assertEquals(320, card.getFollowerCount());
    }
}
