package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VirtualThreadIntroTest {

    @Test
    @DisplayName("ofVirtual().start() 로 만든 스레드는 가상 스레드라 isVirtual() 이 true 다")
    void ofVirtualIsVirtual() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            assertTrue(VirtualThreadIntro.runsOnVirtualThread());
        });
    }

    @Test
    @DisplayName("ofPlatform().start() 로 만든 전통 스레드는 isVirtual() 이 false 다")
    void ofPlatformIsNotVirtual() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            assertFalse(VirtualThreadIntro.runsOnPlatformThread());
        });
    }

    @Test
    @DisplayName("startVirtualThread() 는 ofVirtual().start() 의 짧은 버전이라 똑같이 가상이다")
    void shortcutIsAlsoVirtual() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            assertTrue(VirtualThreadIntro.shortcutAlsoVirtual());
        });
    }
}
