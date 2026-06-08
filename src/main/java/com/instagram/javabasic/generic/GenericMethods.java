package com.instagram.javabasic.generic;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/generic/GenericMethods.java
// 클래스가 아니라 "메서드 하나" 에 타입 빈칸을 붙이는 제네릭 메서드예요.
// 메서드의 반환 타입 앞에 <T> 를 적는 게 핵심이에요 — "이 메서드는 어떤 타입 T 든 다룰 수 있어요" 라는 선언이에요.
//   <T> T first(List<T> list) 처럼요. 호출할 때 우리가 String 리스트를 넘기면 T 가 String 으로,
//   Member 리스트를 넘기면 T 가 Member 로 그 자리에서 정해져요.
// 덕분에 "리스트의 첫 원소 꺼내기" 같은 공통 동작을 타입마다 따로 만들지 않고 하나로 끝내요.
public class GenericMethods {

    // 리스트의 첫 번째 원소를 T 타입 그대로 돌려줘요. 비어 있으면 꺼낼 게 없으니 예외를 던져요.
    public static <T> T first(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("빈 리스트에는 첫 원소가 없어요.");
        }
        return list.get(0);
    }

    // 리스트의 i 번째와 j 번째 원소 자리를 맞바꿔요. 어떤 타입 리스트든 한 메서드로 처리해요.
    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void main(String[] args) {
        // String 리스트 — 호출하는 순간 T 가 String 으로 정해져요.
        List<String> names = new ArrayList<>();
        names.add("minji");
        names.add("jaehoon");
        names.add("seungwoo");
        System.out.println("첫 이름: " + first(names));   // minji

        swap(names, 0, 2);
        System.out.println("0↔2 교환 후 첫 이름: " + first(names)); // seungwoo

        // Member 리스트 — 같은 메서드인데 이번엔 T 가 Member 로 정해져요.
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 12, 400));
        members.add(new Member("jaehoon", 1240, 42, 3, 120));
        Member firstMember = first(members);   // 형변환 없이 Member 그대로
        System.out.println("첫 회원: " + firstMember.getUsername());
    }
}
