package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniqueTagSetTest {

    @Test
    @DisplayName("HashSet 은 중복을 제거하고 모든 태그를 담는다")
    void hashSetRemovesDuplicate() {
        UniqueTagSet demo = new UniqueTagSet();
        Set<String> tags = demo.hashSetTags();

        assertEquals(3, tags.size());
        assertTrue(tags.contains("#travel"));
        assertTrue(tags.contains("#jeju"));
        assertTrue(tags.contains("#sunset"));
    }

    @Test
    @DisplayName("TreeSet 의 첫 원소는 사전순 최소, 끝 원소는 사전순 최대다")
    void treeSetSortsFirstLast() {
        UniqueTagSet demo = new UniqueTagSet();
        TreeSet<String> tags = demo.treeSetTags();

        assertEquals("#cafe", tags.first());
        assertEquals("#travel", tags.last());
    }

    @Test
    @DisplayName("TreeSet 순회 결과는 사전순으로 정렬되어 있다")
    void sortedTagListIsOrdered() {
        UniqueTagSet demo = new UniqueTagSet();
        List<String> sorted = demo.sortedTagList();

        assertEquals(List.of("#cafe", "#jeju", "#sunset", "#travel"), sorted);
    }
}
