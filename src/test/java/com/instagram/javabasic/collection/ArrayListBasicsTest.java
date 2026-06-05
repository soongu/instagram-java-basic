package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayListBasicsTest {

    @Test
    @DisplayName("add 한 만큼 size 가 자동으로 세고 get 으로 꺼낸다")
    void addGetSize() {
        ArrayListBasics demo = new ArrayListBasics();
        List<String> tags = demo.buildTags();

        assertEquals(3, tags.size());
        assertEquals("#sunset", tags.get(0));
        assertEquals("#travel", tags.get(2));
    }

    @Test
    @DisplayName("크기를 정하지 않아도 다섯 개 이상 담긴다(동적 크기)")
    void growsDynamically() {
        ArrayListBasics demo = new ArrayListBasics();
        List<String> many = demo.addMany();

        assertEquals(5, many.size());
        assertEquals("#seoul", many.get(4));
    }
}
