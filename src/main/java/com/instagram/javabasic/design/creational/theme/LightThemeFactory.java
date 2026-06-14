package com.instagram.javabasic.design.creational.theme;

// com/instagram/javabasic/design/creational/theme/LightThemeFactory.java
// 라이트 테마 한 벌 — 밝은 배경 버튼에 어두운 아이콘으로 맞춰 만들어요.
public class LightThemeFactory implements ThemeFactory {

    @Override
    public ThemeButton createButton() {
        return new ThemeButton("화이트");
    }

    @Override
    public ThemeIcon createIcon() {
        return new ThemeIcon("블랙");
    }
}
