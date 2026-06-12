package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThreadInterruptTest {

    @Test
    @DisplayName("isInterrupted() 루프를 도는 스레드는 interrupt() 신호를 받으면 스스로 멈춘다")
    void loopStopsCooperativelyOnInterrupt() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            CountDownLatch started = new CountDownLatch(1);
            AtomicBoolean stoppedGracefully = new AtomicBoolean(false);

            Thread worker = ThreadInterrupt.busyLoopThread(started, stoppedGracefully);
            worker.start();

            // 스레드가 루프에 진입할 때까지 기다린다 (테스트 인프라용 대기).
            started.await();

            // 협조적 중단 신호를 보낸다 — 강제 종료가 아니라 "그만해 줄래?" 라는 부탁.
            worker.interrupt();
            worker.join();

            // 스레드는 신호를 보고 루프를 빠져나와 깔끔하게 마무리했다.
            assertTrue(stoppedGracefully.get());
        });
    }

    @Test
    @DisplayName("sleep() 중인 스레드를 interrupt() 하면 InterruptedException 으로 깨어난다")
    void sleepingThreadWakesWithInterruptedException() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            AtomicBoolean caughtInterrupt = new AtomicBoolean(false);

            Thread sleeper = new Thread(() -> {
                try {
                    Thread.sleep(10_000);  // 아주 오래 자려고 시도
                } catch (InterruptedException e) {
                    caughtInterrupt.set(true);  // interrupt() 가 잠을 깨웠다
                }
            });
            sleeper.start();

            // 스레드가 sleep 에 들어갈 시간을 잠깐 준 뒤 깨운다.
            Thread.sleep(100);
            sleeper.interrupt();
            sleeper.join();

            assertTrue(caughtInterrupt.get());
        });
    }

    @Test
    @DisplayName("interrupt() 하기 전에는 인터럽트 플래그가 꺼져 있다")
    void notInterruptedBeforeSignal() {
        Thread worker = new Thread(() -> {
        });

        assertFalse(worker.isInterrupted());
    }
}
