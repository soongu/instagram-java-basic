package com.instagram.javabasic.design.creational.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThemeFactoryTest {

    @Test
    @DisplayName("라이트 공장은 버튼·아이콘을 라이트 한 벌로 일관되게 만든다")
    void light_consistentFamily() {
        ThemeFactory factory = new LightThemeFactory();

        ThemeButton button = factory.createButton();
        ThemeIcon icon = factory.createIcon();

        assertEquals("화이트", button.getBackground());
        assertEquals("블랙", icon.getTint());
    }

    @Test
    @DisplayName("다크 공장은 버튼·아이콘을 다크 한 벌로 일관되게 만든다")
    void dark_consistentFamily() {
        ThemeFactory factory = new DarkThemeFactory();

        assertEquals("블랙", factory.createButton().getBackground());
        assertEquals("화이트", factory.createIcon().getTint());
    }

    @Test
    @DisplayName("공장만 바꾸면 화면 전체가 통째로 그 테마로 바뀐다 (그리는 코드는 그대로)")
    void switchFactory_switchesWholeFamily() {
        // 화면을 그리는 renderScreen 은 ThemeFactory 만 받을 뿐, 라이트인지 다크인지 몰라도 돼요.
        String lightScreen = renderScreen(new LightThemeFactory());
        String darkScreen = renderScreen(new DarkThemeFactory());

        assertTrue(lightScreen.contains("화이트"));
        assertTrue(darkScreen.contains("블랙"));
        assertNotEquals(lightScreen, darkScreen);
    }

    // 한 공장에서 버튼과 아이콘을 함께 뽑아 한 줄로 그려요 — 어떤 테마든 똑같은 코드로 동작해요.
    private String renderScreen(ThemeFactory factory) {
        ThemeButton button = factory.createButton();
        ThemeIcon icon = factory.createIcon();
        return button.render() + " " + icon.render();
    }
}
