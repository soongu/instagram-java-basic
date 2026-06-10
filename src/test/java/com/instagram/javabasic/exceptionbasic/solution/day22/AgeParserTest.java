package com.instagram.javabasic.exceptionbasic.solution.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgeParserTest {

    @Test
    @DisplayName("parseAge 는 정상 문자열을 숫자로 변환한다")
    void parseAgeConvertsValidString() {
        AgeParser parser = new AgeParser();

        int age = parser.parseAge("20");

        assertEquals(20, age);
    }

    @Test
    @DisplayName("parseAge 는 숫자가 아닌 입력에 NumberFormatException 을 그대로 던진다")
    void parseAgeThrowsForInvalidString() {
        AgeParser parser = new AgeParser();

        assertThrows(NumberFormatException.class,
                () -> parser.parseAge("스무살"));
    }

    @Test
    @DisplayName("parseAgeOrDefault 는 정상 문자열을 숫자로 변환한다")
    void parseAgeOrDefaultConvertsValidString() {
        AgeParser parser = new AgeParser();

        int age = parser.parseAgeOrDefault("20");

        assertEquals(20, age);
    }

    @Test
    @DisplayName("parseAgeOrDefault 는 잘못된 입력을 잡아 기본값 0 을 돌려준다")
    void parseAgeOrDefaultReturnsDefaultForInvalidString() {
        AgeParser parser = new AgeParser();

        int age = parser.parseAgeOrDefault("스무살");

        assertEquals(0, age);
    }
}
