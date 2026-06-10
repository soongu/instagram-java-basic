package com.instagram.javabasic.modern;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/MethodReferenceDemo.java
// 메서드 참조 :: — 람다가 "기존 메서드 하나를 그대로 부르기만" 할 때, 더 짧게 쓰는 문법이에요.
//   m -> m.getUsername()  →  Member::getUsername
// 람다보다 짧고, "무슨 메서드를 쓰는지" 가 이름으로 바로 보여요. 네 가지 형태가 있어요.
public class MethodReferenceDemo {

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);

        // (1) 타입의 인스턴스 메서드 참조 — m -> m.getUsername() 과 똑같아요
        Function<Member, String> toName = Member::getUsername;
        System.out.println(toName.apply(minji));  // minji

        // (2) 정적 메서드 참조 — s -> Integer.parseInt(s) 와 똑같아요
        Function<String, Integer> toInt = Integer::parseInt;
        System.out.println(toInt.apply("42") + 1);  // 43

        // (3) 생성자 참조 — () -> new StringBuilder() 와 똑같아요
        Supplier<StringBuilder> makeBuilder = StringBuilder::new;
        StringBuilder sb = makeBuilder.get();
        sb.append("hello");
        System.out.println(sb);  // hello

        // (4) 특정 객체의 인스턴스 메서드 참조 — x -> System.out.println(x) 와 똑같아요
        Consumer<String> print = System.out::println;
        print.accept("메서드 참조로 출력!");
    }
}
