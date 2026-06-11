package com.instagram.javabasic.modern;

import java.util.HashSet;
import java.util.Set;

// com/instagram/javabasic/modern/BoilerplateProblemDemo.java
// "값만 담는 객체" 를 옛 방식으로 직접 만든 OldImageSize 를 써봐요.
// 다 잘 동작하지만 — 그걸 위해 손으로 적은 코드가 50줄 가까이 됐다는 게 핵심이에요.
public class BoilerplateProblemDemo {

    public static void main(String[] args) {
        OldImageSize square = new OldImageSize(1080, 1080);
        OldImageSize sameSquare = new OldImageSize(1080, 1080);
        OldImageSize portrait = new OldImageSize(1080, 1350);

        // toString 을 정의했으니 사람이 읽을 수 있어요.
        System.out.println("정사각 사진 크기: " + square);   // OldImageSize[width=1080, height=1080]

        // equals 를 정의했으니 "값이 같으면 같다" 가 돼요.
        System.out.println("두 1080 정사각은 같은 크기? " + square.equals(sameSquare)); // true
        System.out.println("정사각 vs 세로형은 같은 크기? " + square.equals(portrait));   // false

        // hashCode 까지 맞췄으니 HashSet 이 중복을 걸러줘요(지난 컬렉션 시간 약속).
        Set<OldImageSize> sizes = new HashSet<>();
        sizes.add(square);
        sizes.add(sameSquare);  // 값이 같아 중복 — 안 들어가요
        sizes.add(portrait);
        System.out.println("서로 다른 크기 종류 수: " + sizes.size()); // 2
    }
}
