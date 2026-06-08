package com.instagram.javabasic.exceptionbasic;

import com.instagram.javabasic.generic.ObjectBox;
import com.instagram.javabasic.generic.Repository;
import com.instagram.javabasic.generic.solution.day20.Stack;

// com/instagram/javabasic/exceptionbasic/ExceptionExample.java
// 지난 시간(Day 20)에 만든 세 클래스가 "문제 상황"에서 던졌던 신호들을 한자리에 모았어요.
// 그 신호의 정식 이름이 바로 예외(Exception, 프로그램 실행 중에 생긴 예상 밖의 사건)예요.
// 여기서는 일부러 예외를 잡지 않고 "터지는 모습" 그대로 보여줘요.
//   - 잘못된 형변환  → ClassCastException(형변환 실패)
//   - 없는 id 조회   → IllegalArgumentException(잘못된 인자)
//   - 빈 스택 꺼내기 → IllegalStateException(지금 할 수 없는 상태)
// 예외를 "잡아서" 프로그램이 멈추지 않게 하는 방법은 이번 시간에 차근차근 배워요.
public class ExceptionExample {

    // 만능 그릇 ObjectBox 에 String 을 넣고 Integer 로 꺼내면 형변환이 실패해요.
    // 컴파일은 멀쩡히 지나가고, 실행하는 순간 ClassCastException 이 터져요.
    public Integer triggerClassCast() {
        ObjectBox box = new ObjectBox();
        box.set("minji");
        return (Integer) box.get();
    }

    // 텅 빈 저장소에 없는 번호표(999)로 조회하면 IllegalArgumentException 이 터져요.
    // "id 999 에 해당하는 항목이 없어요." 라는 메시지로 무엇이 잘못됐는지 알려줘요.
    public String triggerIllegalArgument() {
        Repository<String> repository = new Repository<>();
        return repository.findById(999L);
    }

    // 아무것도 쌓이지 않은 빈 스택에서 꺼내려 하면 IllegalStateException 이 터져요.
    // "지금은 꺼낼 게 없는 상태" 라는 뜻이에요.
    public String triggerIllegalState() {
        Stack<String> stack = new Stack<>();
        return stack.pop();
    }

    public static void main(String[] args) {
        ExceptionExample example = new ExceptionExample();

        // 세 가지 예외를 차례로 일으켜 봐요.
        // 잡지 않고 그냥 부르면 첫 예외가 터지는 순간 프로그램이 멈춰요.
        // 그래서 어떤 예외인지 한 줄씩 확인하려고 각각 try-catch 로 감싸 메시지만 찍어 봐요.
        try {
            example.triggerClassCast();
        } catch (ClassCastException e) {
            System.out.println("ClassCastException 발생: 잘못된 형변환이에요.");
        }

        try {
            example.triggerIllegalArgument();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException 발생: " + e.getMessage());
        }

        try {
            example.triggerIllegalState();
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException 발생: " + e.getMessage());
        }
    }
}
