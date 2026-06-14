package com.instagram.javabasic.design.creational.theme;

// com/instagram/javabasic/design/creational/theme/ThemeFactory.java
// 테마 한 벌을 만들어 주는 공장의 "약속" 이에요. 버튼과 아이콘을 함께 만들어 내요.
// 라이트 테마 공장에서 뽑으면 버튼도 아이콘도 전부 라이트로 맞춰지고,
// 다크 테마 공장에서 뽑으면 전부 다크로 맞춰져요 — 한 공장에서 나온 것끼리는 서로 안 어긋나요.
// Factory Method 가 "한 개" 를 만들었다면, 이건 "어울리는 한 벌" 을 통째로 만들어요.
public interface ThemeFactory {

    ThemeButton createButton();

    ThemeIcon createIcon();
}
