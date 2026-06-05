package com.instagram.javabasic.solution.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day17SolutionTest {

    @Test
    @DisplayName("과제1: 문자열 수치를 파싱해 프로필 한 줄을 조립한다")
    void profileCardRendersOneLine() {
        String line = ProfileCard.render("jaehoon_dev", "42", "1240");
        assertEquals("@jaehoon_dev · 게시물 42 · 팔로워 1240", line);
    }

    @Test
    @DisplayName("과제2: 멘션 한 줄과 인원 통계를 조립한다")
    void mentionReceiptBuildsMentionsAndCount() {
        String receipt = MentionReceipt.build(new String[] {"minji", "seungwoo", "jaehoon"});
        assertEquals("@minji @seungwoo @jaehoon\n총 3명에게 멘션", receipt);
    }

    @Test
    @DisplayName("과제2: 한 명만 있으면 공백 없이 멘션 한 개")
    void mentionReceiptSingle() {
        String receipt = MentionReceipt.build(new String[] {"minji"});
        assertEquals("@minji\n총 1명에게 멘션", receipt);
    }

    @Test
    @DisplayName("과제3: 리터럴은 == true, new String 은 == false")
    void equalityStringReferences() {
        assertTrue(EqualityReport.literalReference());
        assertFalse(EqualityReport.newStringReference());
    }

    @Test
    @DisplayName("과제3: Integer 127 은 캐시되어 == true, 200 은 == false")
    void equalityIntegerCache() {
        assertTrue(EqualityReport.integerReference(127));
        assertFalse(EqualityReport.integerReference(200));
    }
}
