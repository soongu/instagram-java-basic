package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/WrapperClassBasics.java
// int, long, double, boolean 같은 "원시 자료형(primitive)" 은 값만 담는 가벼운 타입이에요.
// 그런데 이걸 "객체" 로 다뤄야 할 때가 있어요(다음 시간에 배울 컬렉션은 원시형을 못 담아요).
// 그래서 원시형을 객체로 감싸주는 "포장(wrapper) 클래스" 가 있어요.
// int → Integer, long → Long, double → Double, boolean → Boolean 처럼요.
public class WrapperClassBasics {

    // 문자열 "123" 을 진짜 숫자 int 123 으로 바꿔요(parse = 해석하다).
    public static int parseToInt(String text) {
        return Integer.parseInt(text);
    }

    // valueOf 는 Integer "객체" 를 돌려줘요(parseInt 는 원시형 int 를 돌려줬어요).
    public static Integer toIntegerObject(String text) {
        return Integer.valueOf(text);
    }

    // Integer 객체 안에 든 진짜 int 값을 꺼내요.
    public static int extractInt(Integer boxed) {
        return boxed.intValue();
    }

    // 숫자를 문자열로 바꿔요(toString = 글자로 표현).
    public static String intToText(int number) {
        return Integer.toString(number);
    }

    // int 가 담을 수 있는 가장 큰 값을 돌려줘요(상수로 미리 정해져 있어요).
    public static int maxIntValue() {
        return Integer.MAX_VALUE;
    }

    // long, double, boolean 도 같은 방식으로 포장 클래스를 써요.
    public static double parseToDouble(String text) {
        return Double.parseDouble(text);
    }

    public static boolean parseToBoolean(String text) {
        return Boolean.parseBoolean(text);
    }

    public static void main(String[] args) {
        System.out.println("문자열 → int : " + parseToInt("1240"));        // 1240
        System.out.println("Integer 객체 : " + toIntegerObject("8500"));   // 8500
        System.out.println("객체 → int : " + extractInt(Integer.valueOf(320))); // 320
        System.out.println("int → 문자열 : " + intToText(42));            // 42
        System.out.println("int 최댓값 : " + maxIntValue());              // 2147483647
        System.out.println("문자열 → double : " + parseToDouble("3.14"));  // 3.14
        System.out.println("문자열 → boolean : " + parseToBoolean("true")); // true
    }
}
