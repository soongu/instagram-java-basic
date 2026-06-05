package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/StringImmutability.java
// String 은 "불변(immutable)" 이에요. 한 번 만든 문자열은 절대 안 바뀌어요.
// s = s + "x" 처럼 더해 보이는 코드도, 사실은 새 문자열을 만들어 s 에 다시 꽂는 거예요.
// 원래 문자열은 그대로 남아 있어요.
public class StringImmutability {

    // 문자열에 다른 글자를 이어 붙여 "새" 문자열을 돌려줘요.
    // 원본은 손대지 않아요.
    public static String append(String original, String extra) {
        return original + extra;
    }

    // 원본과 "원본 + 추가" 가 서로 다른 상자인지 확인해요.
    // 새 객체가 만들어졌으니 == 는 false 가 돼요.
    public static boolean makesNewObject(String original, String extra) {
        String changed = original + extra;
        return original == changed;
    }

    // 객체마다 부여되는 고유 번호(identityHashCode)로 "상자가 바뀌었나" 를 눈으로 봐요.
    public static int identityOf(String s) {
        return System.identityHashCode(s);
    }

    public static void main(String[] args) {
        String name = "jaehoon";
        int beforeId = System.identityHashCode(name);

        String greeting = name + "_dev";       // 새 문자열이 생겨요
        int afterId = System.identityHashCode(greeting);

        System.out.println("원본 : " + name);                  // jaehoon (그대로)
        System.out.println("이어붙인 결과 : " + greeting);      // jaehoon_dev
        System.out.println("원본 == 결과 : " + (name == greeting)); // false
        System.out.println("원본 상자 번호 : " + beforeId);
        System.out.println("새 상자 번호 : " + afterId);
        System.out.println("번호가 다르면 새 객체가 만들어진 거예요.");
    }
}
