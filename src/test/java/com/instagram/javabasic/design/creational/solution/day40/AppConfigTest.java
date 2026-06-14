package com.instagram.javabasic.design.creational.solution.day40;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppConfigTest {

    @Test
    @DisplayName("어디서 불러도 같은 설정 하나만 존재한다 (Singleton)")
    void singleInstance() {
        AppConfig a = AppConfig.INSTANCE;
        AppConfig b = AppConfig.INSTANCE;

        assertSame(a, b);
    }

    @Test
    @DisplayName("앱 전체가 공유하는 설정값을 읽을 수 있다")
    void readsSharedConfig() {
        assertEquals(10, AppConfig.INSTANCE.getMaxPhotosPerPost());
        assertEquals("1.0.0", AppConfig.INSTANCE.getAppVersion());
    }
}
