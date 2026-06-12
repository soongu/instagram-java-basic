package com.instagram.javabasic.concurrent.solution.day33;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day33SolutionTest {

    @Test
    @DisplayName("[과제 1] sendAndWait 후 알림 발송이 완료(sent=true)되어 있다")
    void 알림_발송_join() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            NotificationSender sender = new NotificationSender();
            sender.sendAndWait();
            assertTrue(sender.isSent());
        });
    }

    @Test
    @DisplayName("[과제 2] 이미지 5장을 동시에 로딩하면 join 후 모두 완료된다")
    void 피드_5장_동시_로딩() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            FeedBatchLoader batch = new FeedBatchLoader();
            assertTrue(batch.loadAll(5));
        });
    }

    @Test
    @DisplayName("[과제 3] 무한히 도는 watcher 를 interrupt 하면 협조적으로 멈춘다(stopped=true)")
    void 피드_워처_interrupt_종료() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            FeedWatcher watcher = new FeedWatcher();
            Thread thread = watcher.startWatching();
            Thread.sleep(300);
            thread.interrupt();
            thread.join();
            assertTrue(watcher.isStopped());
        });
    }
}
