package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayStreamDemoTest {

    @Test
    @DisplayName("배열에서 시작한 흐름은 같은 순서의 리스트가 된다")
    void fromArray() {
        String[] names = {"minji", "jaehoon", "seungwoo"};
        assertEquals(List.of("minji", "jaehoon", "seungwoo"), ArrayStreamDemo.fromArray(names));
    }

    @Test
    @DisplayName("range(0, n) 은 0부터 n-1 까지 (끝 제외)")
    void indexesExcludeEnd() {
        assertEquals(List.of(0, 1, 2, 3, 4), ArrayStreamDemo.indexes(5));
    }

    @Test
    @DisplayName("rangeClosed(1, n) 은 1부터 n 까지 (끝 포함)")
    void rankLabelsIncludeEnd() {
        assertEquals(List.of("1위", "2위", "3위"), ArrayStreamDemo.rankLabels(3));
    }
}
