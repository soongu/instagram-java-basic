package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringFormattingTest {

    @Test
    @DisplayName("profileLine: %s 와 %d 로 프로필 한 줄을 만든다")
    void profileLine_formats() {
        assertEquals("@jaehoon_dev · 팔로워 1240명",
                StringFormatting.profileLine("jaehoon_dev", 1240));
    }

    @Test
    @DisplayName("engagementRate: %.2f 로 소수점 둘째 자리까지 반올림한다")
    void engagementRate_roundsToTwo() {
        assertEquals("참여율 8.57%", StringFormatting.engagementRate(8.567));
    }

    @Test
    @DisplayName("toHex: %x 로 16진수 문자열을 만든다")
    void toHex_formats() {
        assertEquals("ff", StringFormatting.toHex(255));
    }

    @Test
    @DisplayName("formatted: format 과 같은 결과를 만든다")
    void formatted_sameAsFormat() {
        assertEquals("@minji.kim · 팔로워 8500명",
                StringFormatting.profileLineFormatted("minji.kim", 8500));
    }
}
