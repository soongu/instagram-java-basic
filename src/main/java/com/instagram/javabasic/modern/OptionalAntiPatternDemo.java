package com.instagram.javabasic.modern;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/OptionalAntiPatternDemo.java
// Optional 을 "이렇게 쓰면 오히려 불편한" 패턴과, 같은 일을 깔끔하게 하는 패턴을 나란히 둬요.
// isPresent() 로 확인하고 get() 으로 꺼내는 건 결국 null 검사와 똑같이 번거로워요 — orElse 류로 한 줄이면 돼요.
public class OptionalAntiPatternDemo {

    // 번거로운 방식 — 상자를 만들어 놓고 if 로 열어보고 get 으로 꺼내요. (예전 null 검사와 다를 게 없어요.)
    public static String clumsy(Member member) {
        Optional<String> box = Optional.ofNullable(member.getEmail());
        if (box.isPresent()) {
            return box.get();
        }
        return "이메일 미등록";
    }

    // 깔끔한 방식 — 같은 결과를 orElse 한 줄로. Optional 을 제대로 쓰는 모습이에요.
    public static String clean(Member member) {
        return Optional.ofNullable(member.getEmail()).orElse("이메일 미등록");
    }

    public static void main(String[] args) {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

        System.out.println(clumsy(joined) + " / " + clean(joined));           // 둘 다 jaehoon@example.com
        System.out.println(clumsy(numbersOnly) + " / " + clean(numbersOnly)); // 둘 다 이메일 미등록
    }
}
