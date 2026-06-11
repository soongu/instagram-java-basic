package com.instagram.javabasic.modern;

import java.util.List;
import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/OptionalComprehensive.java
// 오늘 배운 Optional 을 한 흐름에 모아요 — ofNullable → filter → map → orElse.
// 회원의 이메일에서 도메인만 안전하게 뽑아 프로필 한 줄을 만들어요.
// 어느 단계가 비어도 NPE 없이 기본값으로 마무리돼요.
public class OptionalComprehensive {

    // 이메일 도메인을 안전하게 뽑아요. 이메일이 없거나 @ 가 없으면 "도메인 미상".
    public static String profileLine(Member member) {
        String domain = Optional.ofNullable(member.getEmail())
                .filter(email -> email.contains("@"))
                .map(email -> email.substring(email.indexOf("@") + 1))
                .orElse("도메인 미상");
        return "@" + member.getUsername() + " · " + domain;
    }

    // 회원 목록에서 이메일이 등록된 사람 수를 세요. (없는 사람은 빈 상자라 저절로 빠져요.)
    public static long registeredEmailCount(List<Member> members) {
        return members.stream()
                .map(Member::getEmail)
                .flatMap(email -> Optional.ofNullable(email).stream())
                .count();
    }

    public static void main(String[] args) {
        Member jaehoon = new Member("jaehoon", "jaehoon@example.com");
        Member minji = new Member("minji", 8500, 150, 5, 365); // 이메일 null
        Member weird = new Member("noat", "골뱅이없는주소");       // @ 없음

        System.out.println(profileLine(jaehoon)); // @jaehoon · example.com
        System.out.println(profileLine(minji));   // @minji · 도메인 미상
        System.out.println(profileLine(weird));   // @noat · 도메인 미상
        System.out.println(registeredEmailCount(List.of(jaehoon, minji, weird))); // 2
    }
}
