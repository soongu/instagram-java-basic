package com.instagram.javabasic.exceptionbasic;

import com.instagram.javabasic.generic.ObjectBox;

// com/instagram/javabasic/exceptionbasic/BasicTryCatch.java
// 예외가 터지면 프로그램이 그 자리에서 멈춰 버려요. 하지만 우리가 미리 대비해 두면
// 멈추지 않고 "대신 이렇게 하자" 며 다음으로 넘어갈 수 있어요. 그 도구가 try-catch 예요.
//   try  { 위험할 수 있는 코드 }   ← 여기서 사고가 날 수 있어요
//   catch (예외 e) { 대신 할 일 }   ← 사고가 나면 여기로 넘어와요
// 예외를 "잡는다(catch)" 는 건, 터진 예외를 받아서 프로그램을 계속 살려 두는 거예요.
public class BasicTryCatch {

    // box 에서 이름을 안전하게 꺼내요.
    // 안에 String 이 들어 있으면 그대로 돌려주고,
    // 엉뚱한 타입이 들어 있어 형변환이 실패하면 ClassCastException 을 잡아
    // 프로그램을 멈추는 대신 기본값 "(알 수 없음)" 을 돌려줘요.
    public String readUsernameSafely(ObjectBox box) {
        try {
            // 안에 String 이 아니면 이 줄에서 ClassCastException 이 터져요.
            String username = (String) box.get();
            return username;
        } catch (ClassCastException e) {
            // 예외를 잡았으니 프로그램은 멈추지 않아요. 안전한 기본값으로 대신해요.
            return "(알 수 없음)";
        }
    }

    public static void main(String[] args) {
        BasicTryCatch reader = new BasicTryCatch();

        // 정상 — String 이 들어 있어 실제 이름이 나와요.
        ObjectBox good = new ObjectBox();
        good.set("minji");
        System.out.println("정상 box: " + reader.readUsernameSafely(good));

        // 사고 — Integer 가 들어 있지만 예외를 잡아 기본값으로 복구돼요. 프로그램은 안 멈춰요.
        ObjectBox wrong = new ObjectBox();
        wrong.set(42);
        System.out.println("잘못된 box: " + reader.readUsernameSafely(wrong));

        System.out.println("프로그램이 멈추지 않고 끝까지 실행됐어요!");
    }
}
