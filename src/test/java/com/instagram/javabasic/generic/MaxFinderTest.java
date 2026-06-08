package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.collection.SortableMember;

class MaxFinderTest {

    @Test
    @DisplayName("Integer 리스트에서 가장 큰 숫자를 찾는다")
    void maxOfIntegers() {
        List<Integer> likeCounts = new ArrayList<>();
        likeCounts.add(120);
        likeCounts.add(8500);
        likeCounts.add(42);

        assertEquals(8500, MaxFinder.max(likeCounts));
    }

    @Test
    @DisplayName("String 리스트에서 사전순으로 가장 뒤인 이름을 찾는다")
    void maxOfStrings() {
        List<String> names = new ArrayList<>();
        names.add("minji");
        names.add("jaehoon");
        names.add("seungwoo");

        assertEquals("seungwoo", MaxFinder.max(names));
    }

    @Test
    @DisplayName("Comparable 을 구현한 SortableMember 도 최댓값을 찾는다")
    void maxOfSortableMembers() {
        List<SortableMember> members = new ArrayList<>();
        members.add(new SortableMember("minji", 8500));
        members.add(new SortableMember("jaehoon", 1240));

        assertEquals("minji", MaxFinder.max(members).getUsername());
    }

    @Test
    @DisplayName("빈 리스트에 max 를 부르면 IllegalArgumentException 이 터진다")
    void maxOnEmptyListThrows() {
        List<Integer> empty = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> MaxFinder.max(empty));
    }
}
