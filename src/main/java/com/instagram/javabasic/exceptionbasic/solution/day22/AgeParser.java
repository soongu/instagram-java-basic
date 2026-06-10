package com.instagram.javabasic.exceptionbasic.solution.day22;

// com/instagram/javabasic/exceptionbasic/solution/day22/AgeParser.java
// "던질까, 잡을까" 를 한 클래스 안에서 비교해보는 연습이에요.
// 같은 문자열 변환 작업을 두 메서드로 나눠봤어요.
//   - parseAge: 잡지 않고 그대로 던져요(전파). 변환에 실패하면 부른 쪽이 책임져요.
//   - parseAgeOrDefault: 안에서 잡아서 기본값으로 대신 돌려줘요. 부른 쪽은 멈추지 않아요.
// 정답이 하나 있는 게 아니라, "이 실패를 누가 처리하는 게 맞나" 에 따라 골라 쓰는 거예요.
public class AgeParser {

    // 잡지 않고 던지는 쪽 — Integer.parseInt 가 숫자가 아닌 글자를 만나면
    // NumberFormatException(숫자 형식 예외)을 던지는데, 여기선 try-catch 로 감싸지 않아요.
    // 그래서 그 예외가 이 메서드를 부른 쪽으로 그대로 전파돼요.
    public int parseAge(String raw) {
        return Integer.parseInt(raw);
    }

    // 잡아서 기본값으로 막는 쪽 — parseAge 를 try 로 감싸 호출하고,
    // NumberFormatException 이 올라오면 catch 가 받아서 기본값 0 을 대신 돌려줘요.
    // 예외가 밖으로 새지 않으니, 부른 쪽은 잘못된 입력에도 멈추지 않아요.
    public int parseAgeOrDefault(String raw) {
        try {
            return parseAge(raw);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        AgeParser parser = new AgeParser();

        // 정상 입력 — 숫자로 잘 변환돼요.
        System.out.println("\"20\" 변환 결과: " + parser.parseAgeOrDefault("20"));

        // 잘못된 입력 — 변환에 실패하지만, 안에서 잡아 기본값 0 이 나와요.
        System.out.println("\"스무살\" 변환 결과: " + parser.parseAgeOrDefault("스무살"));

        System.out.println("끝까지 잘 실행됐어요!");
    }
}
