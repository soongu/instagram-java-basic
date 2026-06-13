package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeadlockFixedTest {

    @Test
    @DisplayName("락 순서를 통일하면 두 스레드가 모두 제때 종료한다 (데드락 해결)")
    void unifiedLockOrderAvoidsDeadlock() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            DeadlockFixed demo = new DeadlockFixed();
            Thread t1 = demo.makeOrderedThread("스레드1");
            Thread t2 = demo.makeOrderedThread("스레드2");

            t1.start();
            t2.start();

            // 두 스레드가 같은 A→B 순서로 잡으므로 순환 대기가 없다.
            // 따라서 join 으로 기다리면 둘 다 제때 끝나고, 더 이상 살아있지 않다 (결정적 Green).
            t1.join();
            t2.join();

            assertFalse(t1.isAlive(), "스레드1 은 일을 끝내고 종료되어 있어야 한다");
            assertFalse(t2.isAlive(), "스레드2 는 일을 끝내고 종료되어 있어야 한다");
        });
    }
}
