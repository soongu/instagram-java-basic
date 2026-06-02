package com.instagram.javabasic.design.srp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SrpDesignTest {

    // ===== 나쁜 예: 한 클래스가 너무 많은 일을 한다 =====

    @Test
    @DisplayName("나쁜 예: BigMember 하나가 인증·프로필·점수·표시까지 전부 한다")
    void bigMember_doesEverything() {
        BigMember member = new BigMember("jaehoon", "pw1234", "안녕하세요", 1200);

        // 인증
        assertTrue(member.checkPassword("pw1234"));
        assertFalse(member.checkPassword("wrong"));

        // 점수 계산
        assertEquals(12, member.calculateScore());

        // 표시 문자열 포맷
        assertEquals("@jaehoon | 안녕하세요 | 팔로워 1200", member.formatProfileCard());
    }

    // ===== 좋은 예: 책임을 둘로 나눠도 기능은 그대로다 =====

    @Test
    @DisplayName("좋은 예: Account 는 인증만 책임진다")
    void account_checksPassword() {
        Account account = new Account("jaehoon", "pw1234");

        assertTrue(account.checkPassword("pw1234"));
        assertFalse(account.checkPassword("wrong"));
    }

    @Test
    @DisplayName("좋은 예: Profile 은 프로필 표시만 책임진다")
    void profile_formatsProfileCard() {
        Profile profile = new Profile("jaehoon", "안녕하세요", 1200);

        assertEquals("@jaehoon | 안녕하세요 | 팔로워 1200", profile.formatProfileCard());
    }

    @Test
    @DisplayName("책임을 나눠도 기능(인증 결과·표시 문자열)은 나쁜 예와 똑같다")
    void splittingResponsibilityKeepsBehavior() {
        BigMember big = new BigMember("jaehoon", "pw1234", "안녕하세요", 1200);

        Account account = new Account("jaehoon", "pw1234");
        Profile profile = new Profile("jaehoon", "안녕하세요", 1200);

        // 인증 결과가 동일
        assertEquals(big.checkPassword("pw1234"), account.checkPassword("pw1234"));
        // 표시 문자열이 동일
        assertEquals(big.formatProfileCard(), profile.formatProfileCard());
    }
}
