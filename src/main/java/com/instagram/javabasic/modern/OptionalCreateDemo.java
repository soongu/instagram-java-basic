package com.instagram.javabasic.modern;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/OptionalCreateDemo.java
// "있을 수도, 없을 수도 있는 상자" Optional 을 만드는 세 가지 방법이에요.
// of — 값이 절대 null 이 아닐 때. ofNullable — null 일 수도 있을 때. empty — 빈 상자.
public class OptionalCreateDemo {

    // of — 이름은 항상 채워져 있으니 of 로 담아요. (of 에 null 을 넣으면 그 자리에서 NPE 가 나요.)
    public static Optional<String> wrapUsername(Member member) {
        return Optional.of(member.getUsername());
    }

    // ofNullable — 이메일은 null 일 수 있으니 ofNullable 로 담아요. null 이면 자동으로 빈 상자가 돼요.
    public static Optional<String> wrapEmail(Member member) {
        return Optional.ofNullable(member.getEmail());
    }

    // empty — 처음부터 비어 있는 상자가 필요할 때 써요.
    public static Optional<String> emptyBox() {
        return Optional.empty();
    }

    public static void main(String[] args) {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

        System.out.println("이름 상자: " + wrapUsername(joined));         // Optional[jaehoon]
        System.out.println("이메일 상자(있음): " + wrapEmail(joined));      // Optional[jaehoon@example.com]
        System.out.println("이메일 상자(없음): " + wrapEmail(numbersOnly)); // Optional.empty
        System.out.println("빈 상자: " + emptyBox());                     // Optional.empty
    }
}
