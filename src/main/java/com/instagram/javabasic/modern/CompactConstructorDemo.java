package com.instagram.javabasic.modern;

import com.instagram.javabasic.exceptionbasic.InvalidCaptionException;

// com/instagram/javabasic/modern/CompactConstructorDemo.java
// 컴팩트 생성자가 "값을 받을 때마다" 검증·정리해주는 모습을 확인해요.
public class CompactConstructorDemo {

    public static void main(String[] args) {
        // 정상 — 앞뒤 공백이 정리돼서 저장돼요.
        PostCaption caption = new PostCaption("  오늘 날씨 좋네요  ");
        System.out.println("정리된 캡션: [" + caption.text() + "]"); // [오늘 날씨 좋네요]

        // 빈 캡션 — 만드는 순간 막혀요(예외).
        try {
            new PostCaption("   ");
        } catch (InvalidCaptionException e) {
            System.out.println("막힘: " + e.getMessage()); // 막힘: 캡션은 비어 있을 수 없어요.
        }
    }
}
