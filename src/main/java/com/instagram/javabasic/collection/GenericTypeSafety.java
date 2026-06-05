package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/collection/GenericTypeSafety.java
// List<Integer> 의 <Integer> 는 "이 명단엔 정수만 담아요" 라는 약속(제네릭)이에요.
// 약속을 어기는 코드는 실행 전에 컴파일 단계에서 막혀서, 엉뚱한 값이 섞이는 사고를 미리 잡아요.
//
// 두 가지 포인트를 봐요.
//   1) 오토박싱: add(10) 처럼 기본형 int 를 넣으면 자동으로 Integer 로 감싸여 담겨요.
//      꺼낼 때 get(i) 는 Integer 인데, int 변수에 받으면 자동으로 풀려요(언박싱).
//   2) List<int> 는 불가능해요. <> 안에는 클래스 타입만 와서, 기본형 int 대신 래퍼 Integer 를 써요.
public class GenericTypeSafety {

    // 정수 좋아요 수를 담아 합을 구해요. add(int) 가 자동으로 Integer 로 감싸여요(오토박싱).
    public int sumLikes() {
        List<Integer> likes = new ArrayList<>();
        likes.add(120);   // int 120 → Integer 로 자동 박싱
        likes.add(85);
        likes.add(300);

        int total = 0;
        for (int like : likes) { // Integer → int 자동 언박싱
            total = total + like;
        }
        return total;
    }

    // 제네릭이 약속한 타입만 담기게 해 줘요.
    // 만약 likes.add("백이십") 처럼 String 을 넣으려 하면 컴파일 자체가 안 돼요.
    //   likes.add("백이십"); // ← 컴파일 에러: List<Integer> 에는 Integer 만 담을 수 있어요
    // 이렇게 잘못된 타입을 "실행하기도 전에" 막아 주는 게 제네릭의 핵심이에요.
    public int firstLike() {
        List<Integer> likes = new ArrayList<>();
        likes.add(7);
        return likes.get(0); // Integer → int 자동 언박싱
    }

    public static void main(String[] args) {
        GenericTypeSafety demo = new GenericTypeSafety();
        System.out.println("좋아요 합계: " + demo.sumLikes());
        System.out.println("첫 좋아요: " + demo.firstLike());
        // List<int> likes; // ← 이렇게는 못 써요. <> 안엔 클래스 타입(Integer)만 와요.
    }
}
