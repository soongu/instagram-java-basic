package com.instagram.javabasic.modern;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/OptionalMapDemo.java
// map — 상자를 열지 않고 그 안의 값을 변환해요. 비어 있으면 변환을 건너뛰고 그대로 빈 상자예요.
// 흐름(Stream)의 map 과 똑같은 모양이에요. 다만 원소가 0개 또는 1개인 상자 위에서 동작해요.
public class OptionalMapDemo {

    // 이메일 글자 수 — 상자 안 문자열을 길이(숫자)로 변환해요. 비면 빈 상자(변환 안 함).
    public static Optional<Integer> emailLength(Member member) {
        return Optional.ofNullable(member.getEmail()).map(String::length);
    }

    // 이메일 도메인 — @ 뒤를 잘라내요. 비면 기본값으로 마무리해요.
    public static String emailDomain(Member member) {
        return Optional.ofNullable(member.getEmail())
                .map(email -> email.substring(email.indexOf("@") + 1))
                .orElse("(도메인 없음)");
    }

    public static void main(String[] args) {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

        System.out.println(emailLength(joined));      // Optional[19]
        System.out.println(emailLength(numbersOnly)); // Optional.empty
        System.out.println(emailDomain(joined));      // example.com
        System.out.println(emailDomain(numbersOnly)); // (도메인 없음)
    }
}
