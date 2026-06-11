package com.instagram.javabasic.modern;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/OptionalCheckDemo.java
// 상자에 값이 들어 있는지 확인하고(isPresent·isEmpty), 있을 때만 동작시키는(ifPresent) 방법이에요.
// get() 으로 직접 꺼낼 수도 있지만, 빈 상자에 get() 을 하면 예외가 터져요 — 그래서 잘 안 써요.
public class OptionalCheckDemo {

    // isPresent — 상자에 값이 있으면 true.
    public static boolean hasEmail(Member member) {
        return Optional.ofNullable(member.getEmail()).isPresent();
    }

    // isEmpty — 상자가 비어 있으면 true. (isPresent 의 반대)
    public static boolean missingEmail(Member member) {
        return Optional.ofNullable(member.getEmail()).isEmpty();
    }

    // ifPresent — 값이 있을 때만 람다를 실행해요. 없으면 아무 일도 안 하고 조용히 넘어가요.
    public static String ifPresentMessage(Member member) {
        StringBuilder sb = new StringBuilder("회원 확인");
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> sb.append(" · 이메일 ").append(email));
        return sb.toString();
    }

    // get — 빈 상자에 부르면 NoSuchElementException 이 터져요. 위험해서 권하지 않는 방식이에요.
    public static String dangerousGet(Member member) {
        return Optional.ofNullable(member.getEmail()).get();
    }

    public static void main(String[] args) {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

        System.out.println(hasEmail(joined));              // true
        System.out.println(missingEmail(numbersOnly));     // true
        System.out.println(ifPresentMessage(joined));      // 회원 확인 · 이메일 jaehoon@example.com
        System.out.println(ifPresentMessage(numbersOnly)); // 회원 확인
    }
}
