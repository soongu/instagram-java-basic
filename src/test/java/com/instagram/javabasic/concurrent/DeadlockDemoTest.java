package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeadlockDemoTest {

    @Test
    @DisplayName("락 순서가 엇갈리면 두 스레드가 서로를 기다리며 둘 다 멈춘다 (데드락)")
    void mismatchedLockOrderCausesDeadlock() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            DeadlockDemo demo = new DeadlockDemo();
            Thread ab = demo.makeThreadAB();
            Thread ba = demo.makeThreadBA();

            ab.start();
            ba.start();

            // 첫 자물쇠를 잡은 뒤 sleep 으로 틈을 줘서 순환 대기를 결정적으로 만든다.
            // join 에 타임아웃을 줘서 끝나길 기다려 보지만, 데드락이라 시간이 지나도 안 끝난다.
            ab.join(2000);
            ba.join(2000);

            // 타임아웃 뒤에도 둘 다 여전히 살아(멈춰) 있으면 데드락이 증명된 것이다.
            // 데몬 스레드라 테스트가 끝나면 JVM 과 함께 정리되어 leak/hang 이 없다.
            assertTrue(ab.isAlive(), "AB 스레드는 lockB 를 기다리며 멈춰 있어야 한다");
            assertTrue(ba.isAlive(), "BA 스레드는 lockA 를 기다리며 멈춰 있어야 한다");
        });
    }
}
