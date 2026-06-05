package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// com/instagram/javabasic/collection/ListImplementations.java
// List 는 "명단" 이라는 약속(인터페이스)이고, 그 약속을 지키는 실제 일꾼이 여럿이에요.
//   ArrayList  : 안이 배열이라 index 로 꺼내기(get)가 빨라요
//   LinkedList : 칸들이 손을 잡고 줄지어 있어, 앞/중간에 끼워넣기가 가벼워요
// 둘 다 List 약속을 지키니까, 변수는 List 로 받아 두면 일꾼만 바꿔 끼울 수 있어요(다형성).
public class ListImplementations {

    // 변수는 List 로 받고 실제 일꾼은 ArrayList 로 만들어요.
    public List<String> withArrayList() {
        List<String> names = new ArrayList<>();
        names.add("minji");
        names.add("seungwoo");
        return names;
    }

    // 같은 List 변수에 일꾼만 LinkedList 로 바꿔 끼워요. 쓰는 코드(add/get)는 똑같아요.
    public List<String> withLinkedList() {
        List<String> names = new LinkedList<>();
        names.add("minji");
        names.add("seungwoo");
        return names;
    }

    // LinkedList 는 맨 앞에 끼워넣기를 add(0, 값) 으로 가볍게 해요.
    // index 0 에 넣으면 기존 사람들이 한 칸씩 뒤로 밀려요.
    public List<String> addFront() {
        List<String> names = new LinkedList<>();
        names.add("minji");
        names.add("seungwoo");
        names.add(0, "jaehoon"); // 맨 앞에 끼워넣기
        return names;
    }

    public static void main(String[] args) {
        ListImplementations demo = new ListImplementations();
        System.out.println("ArrayList: " + demo.withArrayList());
        System.out.println("LinkedList: " + demo.withLinkedList());
        System.out.println("앞쪽 삽입: " + demo.addFront());
    }
}
