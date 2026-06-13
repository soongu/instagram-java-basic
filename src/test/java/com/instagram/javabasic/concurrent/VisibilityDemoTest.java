package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VisibilityDemoTest {

    @Test
    @DisplayName("volatile 변수에 멈춤 신호를 주면 워커가 그것을 보고 종료한다")
    void volatileWorkerStopsWhenSignaled() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            VisibilityDemo demo = new VisibilityDemo();
            Thread worker = demo.startWorker();

            // 워커가 충분히 돌게 둔 뒤 멈춤 신호를 준다.
            Thread.sleep(100);
            demo.stop();

            // volatile 이라 변경이 곧바로 보여서 워커는 신호를 받고 끝난다.
            // join 으로 끝까지 기다린 뒤, 더 이상 살아있지 않음을 확인한다 (reliable Green).
            worker.join();
            assertFalse(worker.isAlive(), "신호를 받은 워커는 종료되어 있어야 한다");
        });
    }
}
