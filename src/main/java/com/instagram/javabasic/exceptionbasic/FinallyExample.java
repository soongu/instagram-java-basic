package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/FinallyExample.java
// try-catch 뒤에 finally 블록을 붙이면, "무슨 일이 있어도 마지막에 꼭 실행할 코드" 를 적을 수 있어요.
//   - try 가 정상으로 끝나도 → finally 실행
//   - try 에서 예외가 나서 catch 로 넘어가도 → finally 실행
//   - try 안에서 return 으로 빠져나가도 → finally 는 그래도 실행
// 인스타 비유로 말하면 "연결을 열었으면 무슨 일이 있어도 꼭 닫는다" 예요.
// 사고가 나든 안 나든 뒷정리(자원 닫기, 기록 남기기)는 빠뜨리면 안 되니까요.
// (자원을 더 깔끔하게 자동으로 닫는 방법은 다음다음 시간(Day 23)에 배워요.)
public class FinallyExample {

    // fail 이 true 면 try 안에서 예외를 일부러 일으켜요.
    // 어느 경로든 finally 의 "정리" 기록이 빠지지 않는다는 걸 실행 순서로 보여줘요.
    public String runWithCleanup(boolean fail) {
        StringBuilder log = new StringBuilder();
        try {
            log.append("try");
            if (fail) {
                // 사고 발생! 여기서 바로 catch 로 점프해요.
                throw new IllegalStateException("연결 도중 사고가 났어요.");
            }
        } catch (IllegalStateException e) {
            // 예외가 났을 때만 들러요.
            log.append(" → catch");
        } finally {
            // 정상이든 사고든, 이 줄은 무조건 실행돼요. 뒷정리를 책임지는 자리예요.
            log.append(" → finally(정리)");
        }
        return log.toString();
    }

    public static void main(String[] args) {
        FinallyExample example = new FinallyExample();

        // 정상 경로 — 예외가 없어 catch 는 건너뛰지만, finally 는 그대로 실행돼요.
        System.out.println("정상 경로: " + example.runWithCleanup(false));

        // 예외 경로 — try 에서 사고가 나 catch 로 갔다가, finally 로 마무리해요.
        System.out.println("예외 경로: " + example.runWithCleanup(true));
    }
}
