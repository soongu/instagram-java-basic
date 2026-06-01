package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("기본 생성자로 만든 객체는 필드가 기본값(null/0)이다")
    void defaultConstructor_initializesFieldsToDefaults() {
        Member member = new Member();

        assertNull(member.username);
        assertEquals(0, member.followers);
        assertEquals(0, member.posts);
        assertEquals(0, member.mutualFriends);
        assertEquals(0, member.daysActive);
    }

    @Test
    @DisplayName("매개변수 생성자로 만든 객체는 전달한 값이 필드에 그대로 담긴다")
    void parameterizedConstructor_assignsAllFields() {
        Member member = new Member("jaehoon_dev", 1240, 42, 8, 120);

        assertEquals("jaehoon_dev", member.username);
        assertEquals(1240, member.followers);
        assertEquals(42, member.posts);
        assertEquals(8, member.mutualFriends);
        assertEquals(120, member.daysActive);
    }

    @Test
    @DisplayName("calculateRecommendScore: jaehoon_dev 는 12+8+80+4 = 104점 (this 필드 사용)")
    void calculateRecommendScore_jaehoon() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        assertEquals(104, jaehoon.calculateRecommendScore());
    }

    @Test
    @DisplayName("calculateRecommendScore: 여러 회원의 점수가 손계산과 일치한다")
    void calculateRecommendScore_manyCases() {
        assertEquals(357, new Member("minji_cafe", 8500, 150, 23, 365).calculateRecommendScore());
        assertEquals(26, new Member("seungwoo", 320, 12, 2, 30).calculateRecommendScore());
        assertEquals(215, new Member("soyeon_art", 4100, 88, 15, 210).calculateRecommendScore());
        assertEquals(638, new Member("wooseok99", 15800, 320, 40, 500).calculateRecommendScore());
        assertEquals(149, new Member("hayoung_food", 2300, 67, 11, 95).calculateRecommendScore());
    }

    @Test
    @DisplayName("calculateRecommendScore: 정수 나눗셈이라 나머지는 버려진다")
    void calculateRecommendScore_integerDivision() {
        // 99/100=0, 4/5=0, 0*10=0, 29/30=0 => 0
        Member member = new Member("newbie", 99, 4, 0, 29);
        assertEquals(0, member.calculateRecommendScore());
    }

    @Test
    @DisplayName("grade: 300점 이상은 강력 추천")
    void grade_strongRecommend() {
        // 30000/100 = 300 정확히 경계
        assertEquals("강력 추천", new Member("a", 30000, 0, 0, 0).grade());
    }

    @Test
    @DisplayName("grade: 150~299점은 추천")
    void grade_recommend() {
        assertEquals("추천", new Member("b", 15000, 0, 0, 0).grade());   // 150
        assertEquals("추천", new Member("b2", 29900, 0, 0, 0).grade());  // 299
    }

    @Test
    @DisplayName("grade: 70~149점은 보통")
    void grade_normal() {
        assertEquals("보통", new Member("c", 7000, 0, 0, 0).grade());    // 70
        assertEquals("보통", new Member("c2", 14900, 0, 0, 0).grade());  // 149
    }

    @Test
    @DisplayName("grade: 70점 미만은 관심 낮음")
    void grade_low() {
        assertEquals("관심 낮음", new Member("d", 6900, 0, 0, 0).grade());  // 69
        assertEquals("관심 낮음", new Member("d2", 0, 0, 0, 0).grade());    // 0
    }
}
