package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThreadLifecycleTest {

    @Test
    @DisplayName("start() 전의 스레드는 NEW 상태다")
    void stateIsNewBeforeStart() {
        Thread thread = new Thread(() -> {
        });

        assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("join() 으로 끝까지 기다린 스레드는 TERMINATED 상태다")
    void stateIsTerminatedAfterJoin() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            Thread thread = new Thread(() -> {
            });

            thread.start();
            thread.join();

            assertEquals(Thread.State.TERMINATED, thread.getState());
        });
    }

    @Test
    @DisplayName("ThreadLifecycle.observeBeforeStart() 는 NEW 를 돌려준다")
    void observeBeforeStartReturnsNew() {
        assertEquals(Thread.State.NEW, ThreadLifecycle.observeBeforeStart());
    }

    @Test
    @DisplayName("ThreadLifecycle.observeAfterJoin() 은 TERMINATED 를 돌려준다")
    void observeAfterJoinReturnsTerminated() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            assertEquals(Thread.State.TERMINATED, ThreadLifecycle.observeAfterJoin());
        });
    }
}
