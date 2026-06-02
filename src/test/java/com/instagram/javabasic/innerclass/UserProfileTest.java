package com.instagram.javabasic.innerclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserProfileTest {

    @Test
    @DisplayName("정적 중첩 클래스는 바깥 객체 없이 UserProfile.Stats 로 만들 수 있다")
    void staticNested_createdWithoutOuter() {
        UserProfile.Stats stats = new UserProfile.Stats(42, 1500);
        assertEquals(42, stats.getPostCount());
        assertEquals(1500, stats.getFollowerCount());
    }

    @Test
    @DisplayName("Stats.summary: 한 줄 요약 문자열을 만든다")
    void summary_formatsStats() {
        UserProfile.Stats stats = new UserProfile.Stats(42, 1500);
        assertEquals("게시물 42 · 팔로워 1500", stats.summary());
    }

    @Test
    @DisplayName("UserProfile 은 이름과 Stats 를 함께 들고 있다")
    void profile_holdsNameAndStats() {
        UserProfile.Stats stats = new UserProfile.Stats(10, 200);
        UserProfile profile = new UserProfile("jaehoon_dev", stats);
        assertEquals("jaehoon_dev", profile.getUsername());
        assertEquals("게시물 10 · 팔로워 200", profile.getStats().summary());
    }
}
