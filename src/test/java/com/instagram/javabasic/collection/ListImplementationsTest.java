package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListImplementationsTest {

    @Test
    @DisplayName("ArrayList 와 LinkedList 가 같은 List 약속으로 동일하게 동작한다")
    void bothBehaveSameThroughListInterface() {
        ListImplementations demo = new ListImplementations();

        List<String> arr = demo.withArrayList();
        List<String> linked = demo.withLinkedList();

        assertEquals(2, arr.size());
        assertEquals(2, linked.size());
        assertEquals("minji", arr.get(0));
        assertEquals("minji", linked.get(0));
    }

    @Test
    @DisplayName("LinkedList 앞쪽 삽입은 기존 원소를 뒤로 민다")
    void addFrontShiftsRest() {
        ListImplementations demo = new ListImplementations();

        List<String> names = demo.addFront();

        assertEquals(3, names.size());
        assertEquals("jaehoon", names.get(0));
        assertEquals("minji", names.get(1));
        assertEquals("seungwoo", names.get(2));
    }
}
