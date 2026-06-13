package com.instagram.javabasic.concurrent.solution.day35;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/solution/day35/AlbumPublisher.java
// [과제 3] 업로드가 모두 끝나면 공개 + 성공 수를 Atomic 으로 세기.
// CountDownLatch 로 "전부 끝나길" 기다리고, AtomicInteger 로 성공 수를 안전하게 세요.
// countDown() 은 성공/실패와 무관하게 finally 에서 불러야, 한 건이 실패해도 await 가 영영
// 안 풀리는 사고를 막아요 (모든 일감이 반드시 카운트를 줄이도록).
public class AlbumPublisher {

    // 공개 가능 여부와 성공한 업로드 수를 함께 담는 결과.
    public record PublishResult(boolean published, int successCount) {}

    public static PublishResult publish(int imageCount) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(imageCount);   // 전부 끝나길 기다릴 카운트
        AtomicInteger successCount = new AtomicInteger();        // 성공 수를 락 없이 안전하게
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int id = 1; id <= imageCount; id++) {
            pool.submit(() -> {
                try {
                    successCount.incrementAndGet();   // 업로드 성공 (여기선 모두 성공으로 가정)
                } finally {
                    latch.countDown();   // 성공이든 실패든 카운트는 꼭 줄인다
                }
            });
        }

        boolean published = latch.await(5, TimeUnit.SECONDS);   // 전부 끝나면 공개
        pool.shutdown();
        return new PublishResult(published, successCount.get());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 이미지 8장 업로드 후 공개 ===");
        PublishResult result = publish(8);
        System.out.println("공개 가능? → " + result.published());
        System.out.println("성공한 업로드 수 → " + result.successCount());
    }
}
