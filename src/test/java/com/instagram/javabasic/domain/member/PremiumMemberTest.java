package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PremiumMemberTest {

    @Test
    @DisplayName("생성자 + super: 부모 다섯 필드가 함께 채워진다")
    void constructor_superFillsParentFields() {
        PremiumMember premium = new PremiumMember("jaehoon_dev", 1240, 42, 8, 120, true);

        assertEquals("jaehoon_dev", premium.getUsername());
        assertEquals(1240, premium.getFollowers());
        assertEquals(42, premium.getPosts());
        assertEquals(8, premium.getMutualFriends());
        assertEquals(120, premium.getDaysActive());
    }

    @Test
    @DisplayName("isAdProtected: 프리미엄만의 광고 차단 여부가 그대로 읽힌다")
    void isAdProtected_returnsValue() {
        PremiumMember premium = new PremiumMember("jaehoon_dev", 1240, 42, 8, 120, true);
        assertTrue(premium.isAdProtected());
    }

    @Test
    @DisplayName("calculateRecommendScore 오버라이딩: 부모 점수 + 30 (104 -> 134)")
    void calculateRecommendScore_addsPremiumBonus() {
        // followers 1240 -> 12, posts 42 -> 8, mutualFriends 8 -> 80, daysActive 120 -> 4 = 부모 104점
        PremiumMember premium = new PremiumMember("jaehoon_dev", 1240, 42, 8, 120, true);
        assertEquals(134, premium.calculateRecommendScore());
    }

    @Test
    @DisplayName("toString 오버라이딩: 부모 형식 뒤에 [프리미엄] 이 붙는다")
    void toString_appendsPremiumTag() {
        PremiumMember premium = new PremiumMember("jaehoon_dev", 1240, 42, 8, 120, true);
        // 점수는 오버라이딩된 134점 (super.toString 안의 calculateRecommendScore 도 오버라이딩된 것이 불림)
        assertEquals("@jaehoon_dev (팔로워 1240, 점수 134점) [프리미엄]", premium.toString());
    }

    @Test
    @DisplayName("상속: grade() 도 그대로 쓸 수 있고, 오버라이딩된 점수 기준으로 판정된다")
    void grade_usesOverriddenScore() {
        // followers 14000 -> 부모 점수 140, +30 보너스 = 170점 -> 150~299 구간이라 추천
        PremiumMember premium = new PremiumMember("c", 14000, 0, 0, 0, false);
        assertEquals(170, premium.calculateRecommendScore());
        assertEquals("추천", premium.grade());
    }

    @Test
    @DisplayName("상속: PremiumMember 는 Member 의 한 종류다")
    void premiumMember_isAMember() {
        PremiumMember premium = new PremiumMember("jaehoon_dev", 1240, 42, 8, 120, true);
        assertTrue(premium instanceof Member);
    }

    @Test
    @DisplayName("static totalMembers: PremiumMember 를 만들면 전체 회원 수가 1 늘어난다")
    void totalMembers_increasesWhenCreated() {
        int before = Member.getTotalMembers();
        new PremiumMember("newbie", 100, 5, 1, 30, false);
        assertEquals(before + 1, Member.getTotalMembers());
    }
}
