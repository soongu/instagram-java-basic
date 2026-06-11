package com.instagram.javabasic.modern;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

// com/instagram/javabasic/modern/OptionalExtractDemo.java
// 상자에서 값을 안전하게 꺼내는 세 가지 — orElse · orElseGet · orElseThrow.
// orElse 는 기본값을 늘 준비해두고, orElseGet 은 필요할 때만 만들고, orElseThrow 는 없으면 예외를 던져요.
public class OptionalExtractDemo {

    // orElse — 비어 있으면 미리 준비한 기본값을 줘요. (기본값은 상자가 차 있어도 늘 만들어져요.)
    public static String emailOrDefault(Member member) {
        return Optional.ofNullable(member.getEmail()).orElse("이메일 미등록");
    }

    // orElseGet — 비어 있을 때만 람다(Supplier)를 실행해 기본값을 만들어요. 만드는 비용이 클 때 유리해요.
    public static String emailOrComputed(Member member) {
        return Optional.ofNullable(member.getEmail())
                .orElseGet(() -> "guest-" + member.getUsername() + "@temp.com");
    }

    // orElseThrow — 비어 있으면 우리가 만든 예외를 던져요. "없으면 진행 불가" 인 자리에 딱이에요.
    public static String emailOrThrow(Member member) {
        return Optional.ofNullable(member.getEmail())
                .orElseThrow(() -> new MemberNotFoundException(
                        member.getUsername() + " 의 이메일이 등록돼 있지 않아요."));
    }

    public static void main(String[] args) {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

        System.out.println(emailOrDefault(numbersOnly));  // 이메일 미등록
        System.out.println(emailOrComputed(numbersOnly)); // guest-minji@temp.com
        System.out.println(emailOrThrow(joined));         // jaehoon@example.com
    }
}
