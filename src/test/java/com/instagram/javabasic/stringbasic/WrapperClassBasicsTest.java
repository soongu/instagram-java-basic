package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WrapperClassBasicsTest {

    @Test
    @DisplayName("parseInt: 문자열을 int 로 바꾼다")
    void parse_textToInt() {
        assertEquals(1240, WrapperClassBasics.parseToInt("1240"));
    }

    @Test
    @DisplayName("valueOf: Integer 객체를 만든다")
    void valueOf_returnsBoxed() {
        assertEquals(Integer.valueOf(8500), WrapperClassBasics.toIntegerObject("8500"));
    }

    @Test
    @DisplayName("intValue: Integer 에서 int 값을 꺼낸다")
    void intValue_extracts() {
        assertEquals(320, WrapperClassBasics.extractInt(Integer.valueOf(320)));
    }

    @Test
    @DisplayName("toString: int 를 문자열로 바꾼다")
    void toString_intToText() {
        assertEquals("42", WrapperClassBasics.intToText(42));
    }

    @Test
    @DisplayName("MAX_VALUE: int 의 최댓값 상수")
    void maxValue_constant() {
        assertEquals(2147483647, WrapperClassBasics.maxIntValue());
    }

    @Test
    @DisplayName("Double/Boolean 포장 클래스도 같은 방식으로 변환한다")
    void doubleAndBoolean_parse() {
        assertEquals(3.14, WrapperClassBasics.parseToDouble("3.14"), 0.0001);
        assertEquals(true, WrapperClassBasics.parseToBoolean("true"));
    }
}
