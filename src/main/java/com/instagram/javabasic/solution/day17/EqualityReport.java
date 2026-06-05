package com.instagram.javabasic.solution.day17;

// com/instagram/javabasic/solution/day17/EqualityReport.java
// [심화] 과제 3 — == 함정 디버깅 리포트
// 문자열과 Integer 를 == 로 비교했을 때 왜 어떨 땐 true, 어떨 땐 false 가 나오는지
// 직접 재현하고, 각 결과 옆에 한 줄 이유를 붙여 출력해요.
public class EqualityReport {

    // 따옴표 리터럴 두 개는 문자열 풀의 같은 상자를 가리켜 == 도 true.
    public static boolean literalReference() {
        String a = "instagram";
        String b = "instagram";
        return a == b;
    }

    // new String 두 개는 각각 새 상자라 == 는 false (내용은 equals 로 true).
    public static boolean newStringReference() {
        String a = new String("instagram");
        String b = new String("instagram");
        return a == b;
    }

    // -128~127 범위의 Integer 는 재사용돼서 == 가 true, 범위 밖은 false.
    public static boolean integerReference(int value) {
        Integer a = value;
        Integer b = value;
        return a == b;
    }

    public static void main(String[] args) {
        System.out.println("리터럴 == : " + literalReference()
                + " → 문자열 풀의 같은 상자라 true");
        System.out.println("new String == : " + newStringReference()
                + " → 각각 새 상자라 false (내용 비교는 equals 로 true)");
        System.out.println("127 == 127 : " + integerReference(127)
                + " → 캐시 범위(-128~127) 안이라 같은 객체");
        System.out.println("200 == 200 : " + integerReference(200)
                + " → 캐시 범위 밖이라 새 객체");
        System.out.println("결론: 내용 비교는 항상 equals 를 쓰면 안전하다");
    }
}
