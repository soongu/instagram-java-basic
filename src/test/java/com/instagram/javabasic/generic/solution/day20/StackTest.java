package com.instagram.javabasic.generic.solution.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class StackTest {

    @Test
    @DisplayName("Stack<String> 은 나중에 넣은 것을 먼저 꺼낸다(LIFO)")
    void stringStackIsLifo() {
        Stack<String> stack = new Stack<>();
        stack.push("첫째");
        stack.push("둘째");
        stack.push("셋째");

        assertEquals(3, stack.size());
        assertEquals("셋째", stack.pop());
        assertEquals("둘째", stack.pop());
        assertEquals("첫째", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("peek 은 맨 위를 보여주지만 제거하지는 않는다")
    void peekDoesNotRemove() {
        Stack<String> stack = new Stack<>();
        stack.push("아래");
        stack.push("위");

        assertEquals("위", stack.peek());
        assertEquals("위", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    @DisplayName("Stack<Member> 도 같은 코드로 push/pop 이 LIFO 로 동작한다")
    void memberStackIsLifo() {
        Stack<Member> stack = new Stack<>();
        Member minji = new Member("minji", 8500, 150, 12, 400);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 120);
        stack.push(minji);
        stack.push(jaehoon);

        assertEquals("jaehoon", stack.pop().getUsername());
        assertEquals("minji", stack.peek().getUsername());
        assertEquals(1, stack.size());
    }

    @Test
    @DisplayName("빈 스택에서 pop 을 부르면 IllegalStateException 이 터진다")
    void popOnEmptyThrows() {
        Stack<String> stack = new Stack<>();

        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("빈 스택에서 peek 을 부르면 IllegalStateException 이 터진다")
    void peekOnEmptyThrows() {
        Stack<Member> stack = new Stack<>();

        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    @DisplayName("새로 만든 스택은 비어 있고 size 가 0 이다")
    void newStackIsEmpty() {
        Stack<String> stack = new Stack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertFalse(stack.isEmpty() == false);
    }
}
