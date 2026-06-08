package com.instagram.javabasic.exceptionbasic.solution.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SafePostReaderTest {

    private List<String> sampleTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("첫 게시물");
        titles.add("점심 인증");
        titles.add("저녁 노을");
        return titles;
    }

    @Test
    @DisplayName("정상 index 면 그 자리의 제목을 그대로 돌려준다")
    void returnsTitleForValidIndex() {
        SafePostReader reader = new SafePostReader();

        String title = reader.getTitleSafely(sampleTitles(), 1);

        assertEquals("점심 인증", title);
    }

    @Test
    @DisplayName("범위 밖 index 면 예외가 새지 않고 안내 문구를 돌려준다")
    void returnsFallbackForOutOfRangeIndex() {
        SafePostReader reader = new SafePostReader();

        String title = reader.getTitleSafely(sampleTitles(), 100);

        assertEquals("(없는 게시물)", title);
    }

    @Test
    @DisplayName("범위 밖을 한 번 부른 뒤에도 정상 index 조회가 계속 동작한다")
    void continuesAfterRecovery() {
        SafePostReader reader = new SafePostReader();
        List<String> titles = sampleTitles();

        reader.getTitleSafely(titles, 100);
        String title = reader.getTitleSafely(titles, 0);

        assertEquals("첫 게시물", title);
    }
}
