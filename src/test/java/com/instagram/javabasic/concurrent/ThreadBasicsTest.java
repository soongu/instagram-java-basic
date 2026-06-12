package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThreadBasicsTest {

    @Test
    @DisplayName("run() 을 직접 부르면 새 스레드가 생기지 않고 호출한 쪽 스레드에서 실행된다")
    void runDirectlyUsesCallerThread() {
        ThreadBasics worker = new ThreadBasics();
        String callerThreadName = Thread.currentThread().getName();

        worker.run();

        // run() 은 그냥 메서드 호출이라 호출한 쪽 스레드에서 그대로 돈다.
        assertEquals(callerThreadName, worker.getExecutedThreadName());
    }

    @Test
    @DisplayName("start() 를 부르면 호출한 쪽이 아닌 새 스레드에서 실행된다")
    void startUsesNewThread() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            ThreadBasics worker = new ThreadBasics();
            String callerThreadName = Thread.currentThread().getName();

            worker.start();
            worker.join();

            // start() 는 새 호출 스택(새 스레드)을 만든다 → 호출한 쪽 스레드와 다르다.
            assertNotEquals(callerThreadName, worker.getExecutedThreadName());
        });
    }

    @Test
    @DisplayName("start() 로 실행하면 작업이 실제로 수행되어 완료 플래그가 켜진다")
    void startActuallyRunsTheTask() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            ThreadBasics worker = new ThreadBasics();

            worker.start();
            worker.join();

            assertTrue(worker.isDone());
        });
    }
}
