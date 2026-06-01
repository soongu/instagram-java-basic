package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdminMemberTest {

    @Test
    @DisplayName("생성자 + super: 부모 필드도 함께 채워진다")
    void constructor_superFillsParentFields() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1240, 42, 8, 120, "콘텐츠 관리자");

        assertEquals("jaehoon_dev", admin.getUsername());
        assertEquals(1240, admin.getFollowers());
        assertEquals(42, admin.getPosts());
        assertEquals(8, admin.getMutualFriends());
        assertEquals(120, admin.getDaysActive());
    }

    @Test
    @DisplayName("getAdminRole: 관리자 역할이 그대로 읽힌다")
    void getAdminRole_returnsRole() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1240, 42, 8, 120, "콘텐츠 관리자");
        assertEquals("콘텐츠 관리자", admin.getAdminRole());
    }

    @Test
    @DisplayName("deletePost: 삭제 안내 문자열을 돌려준다")
    void deletePost_returnsMessage() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1240, 42, 8, 120, "콘텐츠 관리자");
        assertEquals("[관리자] jaehoon_dev 가 게시물 post_77 을(를) 삭제했어요.", admin.deletePost("post_77"));
    }

    @Test
    @DisplayName("calculateRecommendScore 오버라이딩: 부모 점수 + 50 (104 -> 154)")
    void calculateRecommendScore_addsAdminBonus() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1240, 42, 8, 120, "콘텐츠 관리자");
        assertEquals(154, admin.calculateRecommendScore());
    }

    @Test
    @DisplayName("toString 오버라이딩: 부모 형식 뒤에 역할이 붙는다")
    void toString_appendsRole() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1240, 42, 8, 120, "콘텐츠 관리자");
        // 점수는 오버라이딩된 154점이 들어가요 (super.toString 안의 calculateRecommendScore 도 오버라이딩된 것이 불림)
        assertEquals("@jaehoon_dev (팔로워 1240, 점수 154점) [관리자: 콘텐츠 관리자]", admin.toString());
    }

    @Test
    @DisplayName("상속: grade() 도 그대로 쓸 수 있고, 오버라이딩된 점수 기준으로 판정된다")
    void grade_usesOverriddenScore() {
        // followers 7000 -> 부모 점수 70, +50 보너스 = 120점 -> 70~149 구간이라 보통
        AdminMember admin = new AdminMember("c", 7000, 0, 0, 0, "콘텐츠 관리자");
        assertEquals(120, admin.calculateRecommendScore());
        assertEquals("보통", admin.grade());

        // followers 6900 -> 부모 점수 69, +50 = 119 -> 여전히 보통 (부모만이면 관심 낮음이었을 값)
        AdminMember admin2 = new AdminMember("d", 6900, 0, 0, 0, "콘텐츠 관리자");
        assertEquals("보통", admin2.grade());
    }

    @Test
    @DisplayName("상속: AdminMember 는 Member 의 한 종류다")
    void adminMember_isAMember() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1240, 42, 8, 120, "콘텐츠 관리자");
        assertTrue(admin instanceof Member);
    }
}
