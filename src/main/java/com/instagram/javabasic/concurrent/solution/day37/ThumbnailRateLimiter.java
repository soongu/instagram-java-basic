package com.instagram.javabasic.concurrent.solution.day37;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/solution/day37/ThumbnailRateLimiter.java
// [과제 3] 썸네일 생성 동시성을 제한하기.
// 썸네일 생성은 CPU 를 빡세게 쓰는 작업이라, 무작정 다 동시에 돌리면 서버가 버거워요.
// 가상 스레드는 싸서 수백 개를 띄울 수 있지만, CPU 작업은 코어 수가 한계라 동시 개수를 막아야 해요.
// 세마포어로 "동시에 maxConcurrent 장까지만" 정원을 정하고, 실제 동시 실행 피크를 재서 돌려줘요.
public class ThumbnailRateLimiter {

    // 이미지 imageCount 장을 처리하되, 세마포어로 동시 실행을 maxConcurrent 로 제한해요.
    // 돌려주는 값은 "실제로 한 번에 동시에 일한 최대 개수(피크)" 예요 → 항상 maxConcurrent 이하.
    public static int generateWithLimit(int imageCount, int maxConcurrent) {
        Semaphore gate = new Semaphore(maxConcurrent);   // 동시 입장 정원
        AtomicInteger inside = new AtomicInteger(0);     // 지금 안에서 일하는 수
        AtomicInteger peak = new AtomicInteger(0);       // 지금까지 본 최대 동시 수

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < imageCount; i++) {
                executor.submit(() -> {
                    gate.acquireUninterruptibly();       // 자리가 날 때까지 기다렸다 입장
                    try {
                        int now = inside.incrementAndGet();
                        peak.accumulateAndGet(now, Math::max);   // 동시 실행 최대치 갱신
                        sleepQuietly(5);                 // 썸네일 만드는 동안 자리를 차지
                        inside.decrementAndGet();
                    } finally {
                        gate.release();                  // 자리 비우기 → 다음 이미지 입장
                    }
                });
            }
        }

        return peak.get();
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        System.out.println("이미지 100장, 동시 4장 제한 → 실제 피크: "
                + generateWithLimit(100, 4) + " (≤ 4)");
    }
}
