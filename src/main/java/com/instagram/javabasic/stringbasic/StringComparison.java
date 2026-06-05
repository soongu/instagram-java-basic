package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/StringComparison.java
// 문자열을 비교하는 두 가지 방법을 정리해요.
// == 는 "같은 상자(객체)냐?" 를 묻고, equals 는 "내용이 같냐?" 를 물어요.
// 지난 시간(Day 16)에 "왜 equals 를 써야 하나?" 라는 질문을 남겨뒀는데,
// 그 답을 여기서 확인해요.
public class StringComparison {

    // 같은 글자를 담은 두 문자열을 new 로 만들면, 내용은 같지만 상자는 서로 달라요.
    // == 는 상자(주소)를 비교하므로 false 가 돼요.
    public static boolean compareByReference(String a, String b) {
        return a == b;
    }

    // equals 는 글자 하나하나(내용)를 비교해요. 내용이 같으면 true 예요.
    public static boolean compareByContent(String a, String b) {
        return a.equals(b);
    }

    // 따옴표로 직접 쓴 문자열("a" 같은 리터럴)은 자바가 "문자열 풀" 한곳에 모아둬요.
    // 그래서 같은 글자의 리터럴은 같은 상자를 가리켜 == 도 true 가 돼요.
    public static boolean literalsSharePool() {
        String x = "instagram";
        String y = "instagram";
        return x == y;
    }

    public static void main(String[] args) {
        String literal1 = "instagram";
        String literal2 = "instagram";
        String made1 = new String("instagram");
        String made2 = new String("instagram");

        System.out.println("리터럴끼리 == : " + (literal1 == literal2));        // true
        System.out.println("리터럴끼리 equals : " + literal1.equals(literal2)); // true
        System.out.println("new 끼리 == : " + (made1 == made2));               // false
        System.out.println("new 끼리 equals : " + made1.equals(made2));        // true
        System.out.println("내용 비교는 항상 equals 를 쓰면 안전해요.");
    }
}
