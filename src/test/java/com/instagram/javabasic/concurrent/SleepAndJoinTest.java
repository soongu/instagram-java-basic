package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SleepAndJoinTest {

    @Test
    @DisplayName("join() 으로 기다린 뒤에는 작업 스레드의 결과가 확정되어 있다")
    void resultIsReadyAfterJoin() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            SleepAndJoin job = new SleepAndJoin();

            int result = job.runAndWait();

            // join() 이 끝까지 기다려줬으니, 작업 스레드가 채운 값을 안전하게 읽을 수 있다.
            assertTrue(job.isFinished());
            assertEquals(42, result);
        });
    }

    @Test
    @DisplayName("sleep() 중인 스레드의 상태는 TIMED_WAITING 으로 관찰된다")
    void sleepingThreadIsTimedWaiting() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            // 충분히 길게 자는 스레드를 띄워서, 자는 동안의 상태를 들여다본다.
            Thread sleeper = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            sleeper.start();

            // 스레드가 sleep 에 들어갈 때까지 잠깐 양보하며 기다린다 (폴링).
            Thread.State observed = Thread.State.NEW;
            long deadline = System.currentTimeMillis() + 2000;
            while (System.currentTimeMillis() < deadline) {
                observed = sleeper.getState();
                if (observed == Thread.State.TIMED_WAITING) {
                    break;
                }
                Thread.sleep(5);
            }

            assertEquals(Thread.State.TIMED_WAITING, observed);
            sleeper.join();
        });
    }
}
