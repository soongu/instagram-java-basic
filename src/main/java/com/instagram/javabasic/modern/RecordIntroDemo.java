package com.instagram.javabasic.modern;

import java.util.HashSet;
import java.util.Set;

// com/instagram/javabasic/modern/RecordIntroDemo.java
// 한 줄짜리 record ImageSize 가, 50줄짜리 OldImageSize 와 똑같이 동작하는지 확인해요.
public class RecordIntroDemo {

    public static void main(String[] args) {
        ImageSize square = new ImageSize(1080, 1080);
        ImageSize sameSquare = new ImageSize(1080, 1080);
        ImageSize portrait = new ImageSize(1080, 1350);

        // toString 자동 생성 — record이름[필드=값, ...] 형식으로 나와요.
        System.out.println("정사각 사진 크기: " + square);   // ImageSize[width=1080, height=1080]

        // equals 자동 생성 — 값이 같으면 같다.
        System.out.println("두 1080 정사각은 같은 크기? " + square.equals(sameSquare)); // true
        System.out.println("정사각 vs 세로형은 같은 크기? " + square.equals(portrait));   // false

        // hashCode 자동 생성 — HashSet 중복 제거도 그대로 동작해요.
        Set<ImageSize> sizes = new HashSet<>();
        sizes.add(square);
        sizes.add(sameSquare);
        sizes.add(portrait);
        System.out.println("서로 다른 크기 종류 수: " + sizes.size()); // 2

        // 접근자(읽는 통로)도 자동 — 다만 이름이 getWidth() 가 아니라 width() 예요.
        System.out.println("가로: " + square.width() + ", 세로: " + square.height()); // 1080, 1080
    }
}
