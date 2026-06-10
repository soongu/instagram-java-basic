package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/CheckedVsUnchecked.java
// 예외에는 두 종류가 있어요.
//  - unchecked(언체크) 예외 : 컴파일러가 강제하지 않아요. throws 를 안 적어도 컴파일돼요.
//      (IllegalArgumentException 처럼 보통 "내 실수" 로 생기는 예외들이에요)
//  - checked(체크) 예외     : 컴파일러가 "처리하라!" 고 강제해요. 메서드에 throws 를 꼭 적어야 해요.
//      (Exception 처럼 "바깥 세계 사정으로 실패할 수 있는" 상황을 표현할 때 써요)
// 두 종류를 나란히 만들어 차이를 직접 느껴 볼게요.
public class CheckedVsUnchecked {

    // unchecked 예외 — throws 선언이 없어요. 그래도 컴파일이 돼요.
    // 나이가 음수면 IllegalArgumentException 을 던져요.
    public void uncheckedExample(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("나이는 음수일 수 없어요: " + age);
        }
        System.out.println("나이 확인 완료: " + age);
    }

    // checked 예외 — 메서드 뒤에 throws Exception 을 꼭 적어야 컴파일돼요.
    // 컴파일러가 "이 예외는 호출하는 쪽이 반드시 처리해야 해" 라고 강제하는 거예요.
    public void checkedExample(boolean fail) throws Exception {
        if (fail) {
            throw new Exception("처리를 강제하는 checked 예외예요.");
        }
        System.out.println("checked 작업 성공!");
    }

    // checkedExample 을 부르려면 main 에도 throws Exception 이 필요해요(전파).
    public static void main(String[] args) throws Exception {
        CheckedVsUnchecked demo = new CheckedVsUnchecked();

        // unchecked — 정상 호출. throws 없이 그냥 부르면 돼요.
        demo.uncheckedExample(25);

        // checked — fail=false 면 성공해요. throws Exception 이 있어 그냥 부를 수 있어요.
        demo.checkedExample(false);
    }
}
