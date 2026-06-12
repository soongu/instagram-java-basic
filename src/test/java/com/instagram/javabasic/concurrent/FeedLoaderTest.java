package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedLoaderTest {

    @Test
    @DisplayName("Runnable 을 구현한 FeedLoader 는 실행되면 자기 담당 이미지를 로딩 완료로 표시한다")
    void loaderMarksItsOwnImageLoaded() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            FeedLoader loader = new FeedLoader(7);

            // Runnable 을 Thread 에 태워서 새 스레드로 실행한다.
            Thread thread = new Thread(loader);
            thread.start();
            thread.join();

            assertTrue(loader.isLoaded());
            assertEquals(7, loader.getImageId());
        });
    }

    @Test
    @DisplayName("실행 전에는 아직 로딩되지 않은 상태다")
    void notLoadedBeforeRun() {
        FeedLoader loader = new FeedLoader(3);

        assertFalse(loader.isLoaded());
    }

    @Test
    @DisplayName("여러 FeedLoader 를 각자의 스레드로 띄우면 join 후 모두 자기 몫을 완료한다")
    void allLoadersFinishTheirOwnWork() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            List<FeedLoader> loaders = List.of(
                    new FeedLoader(1),
                    new FeedLoader(2),
                    new FeedLoader(3));
            List<Thread> threads = loaders.stream()
                    .map(Thread::new)
                    .toList();

            // 셋 다 동시에 띄운다.
            threads.forEach(Thread::start);
            // 셋 다 끝날 때까지 기다린 뒤에 결과를 읽는다 (안전하게 단일 스레드에서 집계).
            for (Thread thread : threads) {
                thread.join();
            }

            assertTrue(loaders.stream().allMatch(FeedLoader::isLoaded));
        });
    }

    @Test
    @DisplayName("람다로 만든 Runnable 도 새 스레드에서 똑같이 실행된다")
    void lambdaRunnableRunsToo() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            boolean[] ran = {false};
            Runnable task = () -> ran[0] = true;

            Thread thread = new Thread(task);
            thread.start();
            thread.join();

            assertTrue(ran[0]);
        });
    }
}
