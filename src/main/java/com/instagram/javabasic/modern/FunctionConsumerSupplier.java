package com.instagram.javabasic.modern;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/FunctionConsumerSupplier.java
// 람다를 담는 그릇 3종 더 —
//   Function<T,R> : T 를 받아 R 로 "바꿔요"   (apply)
//   Consumer<T>   : T 를 받아 "쓰기만" 해요    (accept, 돌려주는 값 없음)
//   Supplier<T>   : 아무것도 안 받고 T 를 "만들어 줘요" (get)
public class FunctionConsumerSupplier {

    // Function — 회원을 받아 화면에 띄울 이름표(@minji) 문자열로 바꿔요
    public static final Function<Member, String> TO_TAG = m -> "@" + m.getUsername();

    // Supplier — 부를 때마다 갓 만든 게스트 회원을 하나 만들어 줘요
    public static final Supplier<Member> GUEST = () -> new Member("guest", "guest@instagram.com");

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);

        // Function: 변환 — 회원 → 이름표 문자열
        String tag = TO_TAG.apply(minji);
        System.out.println(tag);  // @minji

        // Consumer: 소비 — 받은 걸 쓰기만 하고, 돌려주는 값은 없어요
        Consumer<String> printer = line -> System.out.println("알림: " + line);
        printer.accept(tag + " 님이 새 글을 올렸어요");

        // Supplier: 공급 — 호출하는 순간 새 객체를 만들어 줘요
        Member guest = GUEST.get();
        System.out.println("게스트: " + guest.getUsername());  // guest
    }
}
