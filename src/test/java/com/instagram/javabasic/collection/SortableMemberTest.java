package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SortableMemberTest {

    @Test
    @DisplayName("compareTo 는 팔로워 수 차이로 앞뒤를 정한다")
    void compareToByFollowers() {
        SortableMember a = new SortableMember("a", 100);
        SortableMember b = new SortableMember("b", 300);

        assertTrue(a.compareTo(b) < 0); // a 가 앞(팔로워 적음)
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new SortableMember("c", 100)));
    }

    @Test
    @DisplayName("Collections.sort 가 compareTo 기준(팔로워 오름차순)으로 정렬한다")
    void collectionsSortUsesCompareTo() {
        List<SortableMember> members = new ArrayList<>();
        members.add(new SortableMember("minji", 8500));
        members.add(new SortableMember("jaehoon", 1240));
        members.add(new SortableMember("seungwoo", 320));

        Collections.sort(members);

        assertEquals("seungwoo", members.get(0).getUsername());
        assertEquals("jaehoon", members.get(1).getUsername());
        assertEquals("minji", members.get(2).getUsername());
    }
}
