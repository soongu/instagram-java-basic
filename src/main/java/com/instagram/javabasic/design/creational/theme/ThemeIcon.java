package com.instagram.javabasic.design.creational.theme;

// com/instagram/javabasic/design/creational/theme/ThemeIcon.java
// 화면에 그릴 아이콘 한 개 — 버튼과 마찬가지로 어떤 테마에서 나왔는지(아이콘 색)를 품어요.
public class ThemeIcon {

    private final String tint;  // 아이콘 색 — 테마에 따라 달라져요

    public ThemeIcon(String tint) {
        this.tint = tint;
    }

    public String render() {
        return "[아이콘 · 색 " + tint + "]";
    }

    public String getTint() {
        return tint;
    }
}
