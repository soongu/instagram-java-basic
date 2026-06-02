package com.instagram.javabasic.enumbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GradeTest {

    @Test
    @DisplayName("상수마다 한글 이름(displayName)을 갖는다")
    void displayName_perConstant() {
        assertEquals("일반", Grade.NORMAL.getDisplayName());
        assertEquals("프리미엄", Grade.PREMIUM.getDisplayName());
        assertEquals("관리자", Grade.ADMIN.getDisplayName());
    }

    @Test
    @DisplayName("상수마다 보너스 점수(bonusScore)를 갖는다")
    void bonusScore_perConstant() {
        assertEquals(0, Grade.NORMAL.getBonusScore());
        assertEquals(30, Grade.PREMIUM.getBonusScore());
        assertEquals(50, Grade.ADMIN.getBonusScore());
    }

    @Test
    @DisplayName("values(): 세 등급이 모두 들어 있다")
    void values_containsAllGrades() {
        assertEquals(3, Grade.values().length);
    }
}
