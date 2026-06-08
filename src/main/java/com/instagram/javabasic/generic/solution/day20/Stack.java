package com.instagram.javabasic.generic.solution.day20;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/generic/solution/day20/Stack.java
// 제네릭으로 만든 "쌓아 올리는 상자" Stack<T> 예요.
// Stack(스택) 은 접시를 쌓듯 위로만 넣고(push) 위에서만 꺼내는(pop) 자료구조예요.
//   가장 마지막에 넣은 것이 가장 먼저 나와요 — 이걸 LIFO(Last In, First Out, 나중에 들어온 게 먼저 나감)라고 불러요.
// 클래스 옆 <T> 덕분에 한 번만 만들어 두면, 문자열 스택·회원 스택을 따로 짤 필요가 없어요.
//   new Stack<String>() 면 문자열 스택, new Stack<Member>() 면 회원 스택이 돼요.
// 안쪽은 List<T> 하나로 보관하고, "맨 위" 는 리스트의 마지막 칸(items.size()-1)으로 정해요.
public class Stack<T> {

    // 쌓아 둔 항목들을 보관하는 리스트예요. 마지막 칸이 "맨 위" 가 돼요.
    private final List<T> items = new ArrayList<>();

    // push — 맨 위에 하나 쌓아요. 리스트의 끝에 더하면 그게 새 "맨 위" 가 돼요.
    public void push(T value) {
        items.add(value);
    }

    // pop — 맨 위 항목을 꺼내면서 동시에 제거해요.
    // 비어 있으면 꺼낼 게 없으니 IllegalStateException 을 던져 "지금은 비었어요" 를 분명히 알려요.
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("스택이 비어 있어 꺼낼 수 없어요.");
        }
        int topIndex = items.size() - 1;
        T top = items.get(topIndex);
        items.remove(topIndex);
        return top;
    }

    // peek — 맨 위 항목을 살짝 들여다봐요. pop 과 달리 제거하지 않아요.
    // 비어 있으면 볼 게 없으니 똑같이 IllegalStateException 을 던져요.
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("스택이 비어 있어 들여다볼 수 없어요.");
        }
        return items.get(items.size() - 1);
    }

    // 비었는지 — 쌓인 게 하나도 없으면 true 예요.
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // 개수 — 지금 몇 개가 쌓여 있는지 알려줘요.
    public int size() {
        return items.size();
    }

    public static void main(String[] args) {
        // 문자열 스택 — 나중에 넣은 게 먼저 나와요(LIFO)
        Stack<String> words = new Stack<>();
        words.push("첫째");
        words.push("둘째");
        words.push("셋째");

        System.out.println("쌓인 개수: " + words.size()); // 3
        System.out.println("맨 위 엿보기: " + words.peek()); // 셋째 (제거 안 함)
        System.out.println("꺼내기: " + words.pop());       // 셋째
        System.out.println("꺼내기: " + words.pop());       // 둘째
        System.out.println("남은 개수: " + words.size());   // 1

        // 회원 스택 — 같은 코드가 Member 에도 그대로 통해요
        Stack<Member> members = new Stack<>();
        members.push(new Member("minji", 8500, 150, 12, 400));
        members.push(new Member("jaehoon", 1240, 42, 3, 120));

        System.out.println("맨 위 회원: " + members.pop().getUsername()); // jaehoon
    }
}
