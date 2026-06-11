package com.instagram.javabasic.modern;

// com/instagram/javabasic/modern/TextBlockBasics.java
// 여러 줄 문자열을 만드는 두 가지 방법을 나란히 비교해요.
// 옛날 방식: 따옴표마다 + 로 잇고, 줄바꿈은 \n 으로 직접 적어요 — 눈이 아파요.
// 텍스트 블록: """ 세 개로 열고 닫으면, 화면에 보이는 모양 그대로 문자열이 돼요.
public class TextBlockBasics {

    public static void main(String[] args) {
        // 옛날 방식 — 줄마다 따옴표 + 더하기 + \n 이 섞여서 읽기 어려워요.
        String oldBio = "안녕하세요, 재훈입니다.\n" +
                "사진 찍는 걸 좋아해요.\n" +
                "서울에서 활동 중이에요.";

        // 텍스트 블록 — """ 안에 보이는 그대로가 문자열이에요. \n 도, + 도 필요 없어요.
        String newBio = """
                안녕하세요, 재훈입니다.
                사진 찍는 걸 좋아해요.
                서울에서 활동 중이에요.""";

        System.out.println("=== 옛날 방식 ===");
        System.out.println(oldBio);
        System.out.println("=== 텍스트 블록 ===");
        System.out.println(newBio);

        // 두 방식이 만든 문자열이 글자 하나까지 똑같은지 확인해요.
        System.out.println("두 문자열이 똑같나요? " + oldBio.equals(newBio));

        // 게시물 캡션도 마찬가지로 여러 줄을 깔끔하게 담을 수 있어요.
        String caption = """
                오늘의 노을 🌅
                #노을 #하늘 #일상
                위치: 한강공원""";
        System.out.println("=== 캡션 ===");
        System.out.println(caption);
    }
}
