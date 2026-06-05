package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringComparisonTest {

    @Test
    @DisplayName("new String 두 개는 == 로 비교하면 false (다른 객체)")
    void newStrings_referenceNotEqual() {
        String a = new String("instagram");
        String b = new String("instagram");
        assertFalse(StringComparison.compareByReference(a, b));
    }

    @Test
    @DisplayName("new String 두 개는 equals 로 비교하면 true (내용 동일)")
    void newStrings_contentEqual() {
        String a = new String("instagram");
        String b = new String("instagram");
        assertTrue(StringComparison.compareByContent(a, b));
    }

    @Test
    @DisplayName("문자열 리터럴은 풀을 공유해 == 도 true")
    void literals_sharePool() {
        assertTrue(StringComparison.literalsSharePool());
    }
}
