package com.instagram.javabasic.modern;

// com/instagram/javabasic/modern/RecordAccessorDemo.java
// record 가 자동으로 만들어준 것들을 하나씩 뜯어봐요.
// (1) 표준 생성자  (2) 접근자 이름 규칙  (3) equals/hashCode/toString
public class RecordAccessorDemo {

    public static void main(String[] args) {
        // (1) 표준 생성자 — 괄호 안 순서대로 값을 받아요. ImageSize(int width, int height)
        ImageSize size = new ImageSize(1080, 1350);

        // (2) 접근자 — 필드 이름 그대로예요. getWidth() 가 아니라 width().
        //     "get" 을 안 붙이는 게 record 의 약속이에요.
        int w = size.width();
        int h = size.height();
        System.out.println("가로 " + w + " / 세로 " + h); // 가로 1080 / 세로 1350

        // (3) toString — 디버깅할 때 값이 한눈에 보여요.
        System.out.println(size); // ImageSize[width=1080, height=1350]

        // equals & hashCode — 값이 같으면 같고, 같으면 해시도 같아요.
        ImageSize copy = new ImageSize(1080, 1350);
        System.out.println("값이 같으면 equals? " + size.equals(copy));          // true
        System.out.println("그러면 hashCode 도 같아야죠? " + (size.hashCode() == copy.hashCode())); // true
    }
}
