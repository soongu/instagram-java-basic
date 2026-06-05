package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringAndWrapperSummaryTest {

    @Test
    @DisplayName("parseFollowers: 문자열 팔로워 수를 정수로 바꾼다")
    void parseFollowers_toInt() {
        assertEquals(1240, StringAndWrapperSummary.parseFollowers("1240"));
    }

    @Test
    @DisplayName("buildTagLine: StringBuilder 로 해시태그 줄을 조립한다")
    void buildTagLine_assembles() {
        String[] tags = {"daily", "coding", "instagram"};
        assertEquals("#daily #coding #instagram",
                StringAndWrapperSummary.buildTagLine(tags));
    }

    @Test
    @DisplayName("summarize: 세 도구를 묶어 프로필 한 줄 요약을 만든다")
    void summarize_combinesAllTools() {
        String[] tags = {"daily", "coding", "instagram"};
        assertEquals("@jaehoon_dev · 팔로워 1240명 · #daily #coding #instagram",
                StringAndWrapperSummary.summarize("jaehoon_dev", "1240", tags));
    }
}
