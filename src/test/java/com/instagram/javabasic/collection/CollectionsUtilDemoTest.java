package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CollectionsUtilDemoTest {

    @Test
    @DisplayName("FollowerComparator 로 팔로워 많은 순(내림차순) 정렬한다")
    void sortByFollowersDesc() {
        CollectionsUtilDemo demo = new CollectionsUtilDemo();
        List<SortableMember> sorted = demo.sortByFollowersDesc();

        assertEquals("minji", sorted.get(0).getUsername());
        assertEquals("jaehoon", sorted.get(1).getUsername());
        assertEquals("seungwoo", sorted.get(2).getUsername());
    }

    @Test
    @DisplayName("UsernameComparator 로 이름 순(알파벳) 정렬한다")
    void sortByUsername() {
        CollectionsUtilDemo demo = new CollectionsUtilDemo();
        List<SortableMember> sorted = demo.sortByUsername();

        assertEquals("jaehoon", sorted.get(0).getUsername());
        assertEquals("minji", sorted.get(1).getUsername());
        assertEquals("seungwoo", sorted.get(2).getUsername());
    }

    @Test
    @DisplayName("기본 정렬 후 reverse 하면 내림차순이 된다")
    void sortThenReverse() {
        CollectionsUtilDemo demo = new CollectionsUtilDemo();
        List<SortableMember> result = demo.sortThenReverse();

        assertEquals("minji", result.get(0).getUsername());
        assertEquals("seungwoo", result.get(2).getUsername());
    }

    @Test
    @DisplayName("Collections.max/min 이 팔로워 최다/최소를 찾는다")
    void maxAndMin() {
        CollectionsUtilDemo demo = new CollectionsUtilDemo();
        assertEquals("minji", demo.mostFollowed().getUsername());
        assertEquals("seungwoo", demo.leastFollowed().getUsername());
    }

    @Test
    @DisplayName("같은 씨앗(42) shuffle 은 항상 같은 순서를 낸다(결정적)")
    void shuffleIsDeterministicWithSeed() {
        CollectionsUtilDemo demo = new CollectionsUtilDemo();
        List<SortableMember> first = demo.shuffleWithSeed();
        List<SortableMember> second = demo.shuffleWithSeed();

        // 같은 씨앗이므로 두 번 섞어도 순서가 동일하고, 원소 개수는 보존된다
        assertEquals(3, first.size());
        assertEquals(first.get(0).getUsername(), second.get(0).getUsername());
        assertEquals(first.get(1).getUsername(), second.get(1).getUsername());
        assertEquals(first.get(2).getUsername(), second.get(2).getUsername());
    }
}
