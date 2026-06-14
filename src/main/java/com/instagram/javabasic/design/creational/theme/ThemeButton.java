package com.instagram.javabasic.design.creational.theme;

// com/instagram/javabasic/design/creational/theme/ThemeButton.java
// 화면에 그릴 버튼 한 개 — 어떤 테마에서 만들어졌는지(배경색)를 품고 있어요.
public class ThemeButton {

    private final String background;  // 배경색 — 테마에 따라 달라져요

    public ThemeButton(String background) {
        this.background = background;
    }

    // 화면에 보일 모습을 한 줄로 — 색이 섞이지 않았는지 눈으로 확인하기 좋아요.
    public String render() {
        return "[버튼 · 배경 " + background + "]";
    }

    public String getBackground() {
        return background;
    }
}
