package com.instagram.javabasic.concurrent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/VirtualThreadPitfalls.java
// 가상 스레드는 만능이 아니에요. 두 가지만 기억하면 돼요.
//
//  (1) 가상 스레드는 풀(pool)에 모아 재사용하지 마세요.
//      플랫폼 스레드는 비싸서 풀에 모아두고 돌려 썼지만, 가상 스레드는 싸니까
//      작업마다 새로 하나씩 만들면 돼요. newVirtualThreadPerTaskExecutor 가 바로 그렇게 해요.
//      아래 distinctThreadCount 로 "작업마다 매번 새 스레드" 라는 걸 눈으로 확인해요.
//
//  (2) CPU 를 계속 쓰는 계산 작업은 가상 스레드로도 빨라지지 않아요.
//      가상 스레드가 빛나는 건 "기다리느라 막히는(블로킹)" 작업이에요. 계산만 빡세게 하는
//      작업은 결국 CPU 코어 수가 한계라, 수천 개를 띄워도 코어보다 빨라지진 않아요.
//      이럴 땐 세마포어(Semaphore) 로 "동시에 몇 개까지" 를 제한하는 게 좋아요.
//      세마포어는 놀이기구 정원처럼, 정해진 인원만 동시에 들여보내는 도구예요.
public class VirtualThreadPitfalls {

    // (1) 작업 taskCount 개를 던지고, 작업들이 사용한 서로 다른 스레드의 개수를 세요.
    //     가상 스레드는 작업마다 새로 만들어지므로, 서로 다른 스레드 수 = 작업 수가 돼요.
    //     (= 재사용이 없다 = 풀로 모아둘 이유가 없다)
    public static int distinctThreadCount(int taskCount) {
        Set<Long> threadIds = ConcurrentHashMap.newKeySet();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> threadIds.add(Thread.currentThread().threadId()));
            }
        }

        return threadIds.size();
    }

    // (2) 세마포어로 동시 실행 개수를 maxConcurrent 로 묶고, 실제로 한 번에 몇 개까지
    //     동시에 일했는지(피크) 를 돌려줘요. 세마포어가 잘 막아주면 피크는 maxConcurrent 이하예요.
    public static int peakConcurrency(int taskCount, int maxConcurrent) {
        Semaphore gate = new Semaphore(maxConcurrent);   // 동시 입장 정원 = maxConcurrent
        AtomicInteger inside = new AtomicInteger(0);      // 지금 안에서 일하는 수
        AtomicInteger peak = new AtomicInteger(0);        // 지금까지 본 최대 동시 수

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    gate.acquireUninterruptibly();        // 자리가 날 때까지 기다렸다 입장
                    try {
                        int now = inside.incrementAndGet();
                        peak.accumulateAndGet(now, Math::max);   // 최대치 갱신
                        sleepQuietly(5);                  // 잠깐 일하는 동안 자리를 차지
                        inside.decrementAndGet();
                    } finally {
                        gate.release();                   // 자리 비우기 (다음 작업 입장 가능)
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
        System.out.println("=== (1) 가상 스레드는 작업마다 새로 만들어진다 (풀링 불필요) ===");
        System.out.println("작업 50개가 쓴 서로 다른 스레드 수 → " + distinctThreadCount(50) + " (= 50, 재사용 없음)");

        System.out.println();
        System.out.println("=== (2) 세마포어로 동시 실행을 4개로 제한 ===");
        System.out.println("작업 100개를 던졌을 때 동시 실행 피크 → " + peakConcurrency(100, 4) + " (≤ 4)");
    }
}
