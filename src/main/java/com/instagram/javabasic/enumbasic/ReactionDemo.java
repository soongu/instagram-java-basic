package com.instagram.javabasic.enumbasic;

// com/instagram/javabasic/enumbasic/ReactionDemo.java
// ReactionType(열거형)을 실제로 다뤄보는 연습장이에요.
// enum 이 기본으로 주는 기능(name·ordinal·values·valueOf)과
// switch·== 비교를 메서드로 정리해 두었어요.
public class ReactionDemo {

    // 반응 종류를 이모지로 바꿔요.
    // enum 을 switch 에 넣으면 case 에 상수 이름만 적으면 돼요(ReactionType. 안 붙여도 OK).
    // 다섯 상수를 모두 다뤘기 때문에 default 가 없어도 컴파일러가 통과시켜줘요.
    public static String emojiOf(ReactionType type) {
        return switch (type) {
            case LIKE -> "👍";
            case LOVE -> "❤️";
            case HAHA -> "😂";
            case WOW  -> "😮";
            case SAD  -> "😢";
        };
    }

    // enum 상수는 세상에 단 하나씩만 존재해요(같은 LOVE 는 어디서 써도 같은 객체).
    // 그래서 equals 가 아니라 == 로 안전하게 비교할 수 있어요.
    public static boolean isSame(ReactionType a, ReactionType b) {
        return a == b;
    }

    // 전체 반응 종류가 몇 개인지 — values() 가 모든 상수를 배열로 돌려줘요.
    public static int howManyTypes() {
        return ReactionType.values().length;
    }
}
