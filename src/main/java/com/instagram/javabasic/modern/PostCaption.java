package com.instagram.javabasic.modern;

import com.instagram.javabasic.exceptionbasic.InvalidCaptionException;

// com/instagram/javabasic/modern/PostCaption.java
// 게시물 캡션이라는 값을 담는 record 예요.
// 그런데 "빈 캡션은 안 된다", "앞뒤 공백은 정리한다" 같은 규칙을 넣고 싶어요.
// 그럴 때 컴팩트 생성자(괄호도 안 쓰는 짧은 생성자) 한 곳에서 검증·정리해요.
public record PostCaption(String text) {

    // 컴팩트 생성자 — 매개변수 목록도, this 할당도 안 써요.
    // 마지막에 자바가 text 를 알아서 필드에 넣어줘요. 그 전에 검사·정리만 해요.
    public PostCaption {
        if (text == null || text.isBlank()) {
            throw new InvalidCaptionException("캡션은 비어 있을 수 없어요.");
        }
        text = text.trim(); // 정규화 — 앞뒤 공백을 정리해서 저장해요.
    }
}
