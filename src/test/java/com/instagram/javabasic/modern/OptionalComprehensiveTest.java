package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class OptionalComprehensiveTest {

    private final Member jaehoon = new Member("jaehoon", "jaehoon@example.com");
    private final Member minji = new Member("minji", 8500, 150, 5, 365); // 이메일 null
    private final Member weird = new Member("noat", "골뱅이없는주소");     // @ 없음

    @Test
    @DisplayName("종합: 이메일이 정상이면 도메인까지 뽑은 프로필 한 줄")
    void profileLineNormal() {
        assertEquals("@jaehoon · example.com", OptionalComprehensive.profileLine(jaehoon));
    }

    @Test
    @DisplayName("종합: 이메일이 없으면 도메인 미상")
    void profileLineNoEmail() {
        assertEquals("@minji · 도메인 미상", OptionalComprehensive.profileLine(minji));
    }

    @Test
    @DisplayName("종합: @ 가 없는 이메일도 filter 로 걸러 도메인 미상")
    void profileLineNoAt() {
        assertEquals("@noat · 도메인 미상", OptionalComprehensive.profileLine(weird));
    }

    @Test
    @DisplayName("종합: 이메일이 등록된 회원 수 (빈 상자는 저절로 빠짐)")
    void registeredEmailCount() {
        assertEquals(2, OptionalComprehensive.registeredEmailCount(List.of(jaehoon, minji, weird)));
    }
}
