package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/collection/ArrayListBasics.java
// ArrayList 는 "스스로 크기를 늘리는 배열" 이에요.
// 배열처럼 미리 크기를 정하지 않아도 add() 로 계속 담을 수 있고,
// 지금 몇 개인지도 size() 가 알아서 세 줘요(우리가 카운터를 들고 다닐 필요 없음).
//   List<String> 의 <String> 은 "이 명단엔 String 만 담아요" 라는 약속이에요.
//   List 로 받고 new ArrayList<>() 로 만드는 건 다음 시간에 배우는 다형성의 맛보기예요.
public class ArrayListBasics {

    // 해시태그 명단을 만들어 세 개를 담고 그대로 돌려줘요.
    // 배열이었다면 크기를 미리 정해야 했지만, ArrayList 는 add 만 하면 알아서 늘어나요.
    public List<String> buildTags() {
        List<String> tags = new ArrayList<>();
        tags.add("#sunset");
        tags.add("#jeju");
        tags.add("#travel");
        return tags;
    }

    // 크기 3을 정한 적이 없는데도 다섯 개까지 문제없이 담겨요(동적 크기).
    public List<String> addMany() {
        List<String> tags = new ArrayList<>();
        tags.add("#food");
        tags.add("#cafe");
        tags.add("#daily");
        tags.add("#ootd");
        tags.add("#seoul");
        return tags;
    }

    public static void main(String[] args) {
        ArrayListBasics demo = new ArrayListBasics();

        List<String> tags = demo.buildTags();
        System.out.println("담긴 개수: " + tags.size()); // size() 가 알아서 세요
        System.out.println("첫 번째: " + tags.get(0));    // get(index) 로 꺼내요
        System.out.println("전체: " + tags);              // ArrayList 는 toString 이 깔끔해요

        List<String> many = demo.addMany();
        System.out.println("다섯 개도 OK: " + many.size());
    }
}
