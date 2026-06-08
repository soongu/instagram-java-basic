package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/ThrowableHierarchy.java
// 자바의 모든 예외에는 "족보"가 있어요. 맨 윗조상이 Throwable(던질 수 있는 것) 이고,
// 그 아래로 두 갈래로 갈라져요.
//
//                 Throwable (맨 윗조상)
//                 ├── Error      (시스템이 망가진 심각한 사고 — 우리가 손쓸 수 없음)
//                 └── Exception  (프로그램에서 다룰 수 있는 사건)
//                     └── RuntimeException (실행 중에 터지는, 미리 처리 강제 안 함 = unchecked)
//
// 같은 ClassCastException 도 사실은 RuntimeException 이고, Exception 이고, Throwable 이에요.
// 자식은 부모의 성격을 모두 물려받기 때문이에요. 그래서 instanceof(어떤 타입인지 확인) 로
// "이 예외가 어느 갈래에 속하는지" 를 가려낼 수 있어요.
public class ThrowableHierarchy {

    // 예외 하나를 받아 족보의 어느 자리에 있는지 한국어 라벨로 알려줘요.
    // 가장 구체적인(좁은) 타입부터 차례로 확인하는 게 핵심이에요.
    // RuntimeException 은 Exception 의 자식이라서, RuntimeException 을 먼저 걸러야
    // "RuntimeException 인데 Exception 으로 뭉뚱그려지는" 일을 막을 수 있어요.
    public String classify(Throwable t) {
        if (t instanceof RuntimeException) {
            return "unchecked (RuntimeException 계열)";
        } else if (t instanceof Exception) {
            return "checked (Exception 계열)";
        } else if (t instanceof Error) {
            return "Error (복구 대상 아님)";
        } else {
            return "알 수 없는 Throwable";
        }
    }

    // RuntimeException 계열이면 true 예요.
    // unchecked(언체크드) 란 "미리 처리하라고 컴파일러가 강제하지 않는" 예외를 뜻해요.
    public boolean isUnchecked(Throwable t) {
        return t instanceof RuntimeException;
    }

    public static void main(String[] args) {
        ThrowableHierarchy hierarchy = new ThrowableHierarchy();

        // 지난 시간에 만난 세 예외는 모두 RuntimeException 계열이라 unchecked 로 나와요.
        System.out.println(new ClassCastException() + " → " + hierarchy.classify(new ClassCastException()));
        System.out.println(new IllegalArgumentException() + " → " + hierarchy.classify(new IllegalArgumentException()));
        System.out.println(new IllegalStateException() + " → " + hierarchy.classify(new IllegalStateException()));

        // 평범한 Exception 은 checked 계열이에요.
        System.out.println(new Exception() + " → " + hierarchy.classify(new Exception()));

        // Error 는 우리가 복구할 수 없는 심각한 사고예요.
        System.out.println(new OutOfMemoryError() + " → " + hierarchy.classify(new OutOfMemoryError()));
    }
}
