package com.instagram.javabasic.design.creational.theme;

// com/instagram/javabasic/design/creational/theme/DarkThemeFactory.java
// 다크 테마 한 벌 — 어두운 배경 버튼에 밝은 아이콘으로 맞춰 만들어요.
// 라이트 공장과 똑같은 약속(ThemeFactory)을 지키니, 화면을 그리는 코드는 둘을 구별할 필요가 없어요.
public class DarkThemeFactory implements ThemeFactory {

    @Override
    public ThemeButton createButton() {
        return new ThemeButton("블랙");
    }

    @Override
    public ThemeIcon createIcon() {
        return new ThemeIcon("화이트");
    }
}
