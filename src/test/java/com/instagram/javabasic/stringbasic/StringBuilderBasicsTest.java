package com.instagram.javabasic.stringbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringBuilderBasicsTest {

    @Test
    @DisplayName("joinTags: append 로 해시태그를 한 줄로 누적한다")
    void joinTags_accumulates() {
        String[] tags = {"daily", "instagram", "java"};
        assertEquals("#daily #instagram #java", StringBuilderBasics.joinTags(tags));
    }

    @Test
    @DisplayName("joinTags: 태그가 하나면 구분 공백 없이 만든다")
    void joinTags_single() {
        String[] tags = {"solo"};
        assertEquals("#solo", StringBuilderBasics.joinTags(tags));
    }

    @Test
    @DisplayName("wrapWithBrackets: insert 와 append 로 대괄호를 감싼다")
    void wrap_insertsAndAppends() {
        assertEquals("[post]", StringBuilderBasics.wrapWithBrackets("post"));
    }

    @Test
    @DisplayName("deleteRange: 지정 구간을 잘라낸다")
    void delete_removesRange() {
        assertEquals("gram", StringBuilderBasics.deleteRange("instagram", 0, 5));
    }

    @Test
    @DisplayName("reverse: 글자 순서를 뒤집는다")
    void reverse_flipsOrder() {
        assertEquals("nimda", StringBuilderBasics.reverse("admin"));
    }
}
