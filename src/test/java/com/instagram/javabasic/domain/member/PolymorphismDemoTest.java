package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PolymorphismDemoTest {

    PolymorphismDemo demo = new PolymorphismDemo();

    // ===== 1. 업캐스팅 =====

    @Test
    @DisplayName("업캐스팅: 부모 타입 변수로 자식 객체를 담아도 username 을 읽을 수 있다")
    void upcasting_canReadParentMethod() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1000, 0, 0, 0, "콘텐츠 관리자");
        assertEquals("jaehoon_dev", demo.upcastAndGetUsername(admin));
    }

    @Test
    @DisplayName("업캐스팅: 부모 타입 변수로 호출해도 calculateRecommendScore 는 자식 버전(+50)이 불린다")
    void upcasting_callsOverriddenScore() {
        AdminMember admin = new AdminMember("jaehoon_dev", 1000, 0, 0, 0, "콘텐츠 관리자");
        // 부모 점수 10 + 관리자 보너스 50 = 60
        assertEquals(60, demo.upcastAndGetScore(admin));
    }

    // ===== 2. 동적 디스패치 =====

    @Test
    @DisplayName("동적 디스패치: 같은 기본 점수라도 실제 타입의 오버라이딩 버전이 각각 실행된다")
    void dynamicDispatch_eachTypeRunsOwnVersion() {
        // 세 객체 모두 followers 1000 -> 기본 점수 10 으로 동일
        Member normal = new Member("normal", 1000, 0, 0, 0);
        Member admin = new AdminMember("admin", 1000, 0, 0, 0, "콘텐츠 관리자");
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);

        int[] scores = demo.collectScores(new Member[] {normal, admin, premium});

        assertEquals(10, scores[0]);  // 부모 버전
        assertEquals(60, scores[1]);  // +50 버전
        assertEquals(40, scores[2]);  // +30 버전
    }

    @Test
    @DisplayName("동적 디스패치: 배열 점수의 합도 각 타입 버전이 더해진 결과다")
    void dynamicDispatch_sumUsesOverriddenVersions() {
        Member normal = new Member("normal", 1000, 0, 0, 0);
        Member admin = new AdminMember("admin", 1000, 0, 0, 0, "콘텐츠 관리자");
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);

        // 10 + 60 + 40 = 110
        assertEquals(110, demo.totalScore(new Member[] {normal, admin, premium}));
    }

    // ===== 3. instanceof 타입 판별 =====

    @Test
    @DisplayName("instanceof: 부모 타입으로 받은 객체의 실제 타입 이름을 돌려준다")
    void describeType_returnsActualTypeName() {
        Member normal = new Member("normal", 1000, 0, 0, 0);
        Member admin = new AdminMember("admin", 1000, 0, 0, 0, "콘텐츠 관리자");
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);

        assertEquals("일반 회원", demo.describeType(normal));
        assertEquals("관리자", demo.describeType(admin));
        assertEquals("프리미엄 회원", demo.describeType(premium));
    }

    @Test
    @DisplayName("instanceof: 관리자 여부를 boolean 으로 판별한다")
    void isAdmin_detectsAdminMember() {
        Member admin = new AdminMember("admin", 1000, 0, 0, 0, "콘텐츠 관리자");
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);

        assertTrue(demo.isAdmin(admin));
        assertFalse(demo.isAdmin(premium));
    }

    // ===== 4. 다운캐스팅 + 향상된 instanceof 패턴 변수 =====

    @Test
    @DisplayName("다운캐스팅: 관리자면 패턴 변수로 안전하게 내려받아 deletePost 를 부른다")
    void safeDeletePost_runsWhenAdmin() {
        Member admin = new AdminMember("jaehoon_dev", 1000, 0, 0, 0, "콘텐츠 관리자");
        assertEquals(
                "[관리자] jaehoon_dev 가 게시물 post_77 을(를) 삭제했어요.",
                demo.safeDeletePost(admin, "post_77"));
    }

    @Test
    @DisplayName("다운캐스팅: 관리자가 아니면 캐스팅을 시도하지 않고 안내 문자열을 돌려준다")
    void safeDeletePost_skipsWhenNotAdmin() {
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);
        assertEquals("관리자만 게시물을 삭제할 수 있어요.", demo.safeDeletePost(premium, "post_77"));
    }

    @Test
    @DisplayName("다운캐스팅: 프리미엄이면 패턴 변수로 내려받아 광고 차단 여부를 읽는다")
    void checkAdProtected_readsPremiumField() {
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);
        Member normal = new Member("normal", 1000, 0, 0, 0);

        assertTrue(demo.checkAdProtected(premium));
        assertFalse(demo.checkAdProtected(normal));
    }

    // ===== 5. ClassCastException 안전성 대비 =====

    @Test
    @DisplayName("위험한 다운캐스팅: instanceof 없이 (AdminMember) 강제 캐스팅하면 ClassCastException 이 터진다")
    void unsafeCast_throwsClassCastException() {
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);
        assertThrows(ClassCastException.class, () -> demo.unsafeDeletePost(premium, "post_77"));
    }

    @Test
    @DisplayName("위험한 다운캐스팅: 순수 부모 Member 도 (AdminMember) 캐스팅하면 ClassCastException 이 터진다")
    void unsafeCast_onPlainMember_throws() {
        Member normal = new Member("normal", 1000, 0, 0, 0);
        assertThrows(ClassCastException.class, () -> demo.unsafeDeletePost(normal, "post_77"));
    }

    @Test
    @DisplayName("안전 버전: 같은 상황에서 instanceof 를 먼저 보면 예외 없이 안내 문자열로 처리된다")
    void safeVersion_doesNotThrow() {
        Member premium = new PremiumMember("premium", 1000, 0, 0, 0, true);
        // 예외가 나지 않고 안내 문자열을 받는다
        assertEquals("관리자만 게시물을 삭제할 수 있어요.", demo.safeDeletePost(premium, "post_77"));
    }
}
