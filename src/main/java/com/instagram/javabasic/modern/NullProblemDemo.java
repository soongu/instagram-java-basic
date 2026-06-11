package com.instagram.javabasic.modern;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/NullProblemDemo.java
// null 이 일으키는 사고와, Optional 이전에 쓰던 "null 방어 코드" 의 번거로움을 보여줘요.
// 회원가입 생성자(이름+이메일)로 만들면 이메일이 채워지지만,
// 숫자 정보 생성자로 만들면 이메일이 null 이에요. 그 null 을 모르고 쓰면 NPE 가 터져요.
public class NullProblemDemo {

    // 위험한 방식 — 이메일이 null 이면 .length() 를 부르는 순간 NullPointerException 이 터져요.
    public static int unsafeEmailLength(Member member) {
        return member.getEmail().length();
    }

    // 방어한 방식 — null 인지 매번 직접 확인해요. 안전하지만, 이런 if 가 코드 곳곳에 번져요.
    public static int safeEmailLength(Member member) {
        String email = member.getEmail();
        if (email == null) {
            return 0;
        }
        return email.length();
    }

    public static void main(String[] args) {
        Member joined = new Member("jaehoon", "jaehoon@example.com"); // 이메일 있음
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);  // 이메일 null

        System.out.println("가입 회원 이메일 길이: " + safeEmailLength(joined));      // 19
        System.out.println("숫자만 회원 이메일 길이: " + safeEmailLength(numbersOnly)); // 0

        // 아래 줄의 주석을 풀면 NullPointerException 으로 프로그램이 멈춰요.
        // System.out.println(unsafeEmailLength(numbersOnly));
    }
}
