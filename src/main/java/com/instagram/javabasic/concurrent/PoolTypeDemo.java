package com.instagram.javabasic.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// com/instagram/javabasic/concurrent/PoolTypeDemo.java
// 풀에도 종류가 있어요. 일감의 성격에 따라 골라 써요. 대표 둘을 비교해봐요.
//
//   newFixedThreadPool(n) — 고정 크기 풀.
//       일꾼 수가 n 으로 딱 정해져요. 일감이 일꾼보다 많으면, 남는 일감은 줄을 서서(큐) 차례를
//       기다려요. 한 번에 도는 일꾼이 n 개를 절대 안 넘어서, 시스템에 거는 부하가 예측돼요.
//       → 동시에 처리할 양을 제한하고 싶을 때 (대부분의 서버 작업).
//
//   newCachedThreadPool() — 탄력 풀.
//       일감이 오는데 노는 일꾼이 없으면 새로 만들고, 일 끝낸 일꾼은 잠깐 뒀다 재사용해요.
//       크기 제한이 없어서 짧은 일감이 우르르 몰릴 때 빠르지만, 일감이 폭주하면 일꾼이
//       끝없이 늘어날 위험도 있어요.
//       → 가볍고 짧은 일감이 간헐적으로 몰릴 때.
public class PoolTypeDemo {

    // 고정 풀의 결과: 끝낸 일감 수 + 실제로 일한 서로 다른 스레드 수.
    public record FixedResult(int completed, int distinctThreads) {}

    // 고정 크기 풀로 돌려요. 일꾼이 poolSize 개를 절대 안 넘는다는 걸 distinctThreads 로 확인해요.
    public static FixedResult runOnFixed(int poolSize, int taskCount) throws InterruptedException {
        Set<String> threadNames = Collections.synchronizedSet(new HashSet<>());
        ConcurrentLinkedQueue<Integer> done = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        for (int i = 1; i <= taskCount; i++) {
            int id = i;
            pool.submit(() -> {
                threadNames.add(Thread.currentThread().getName());  // 누가 이 일감을 했나 기록
                done.add(id);
            });
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        return new FixedResult(done.size(), threadNames.size());
    }

    // 탄력 풀로 돌려요. 일감은 모두 처리돼요 (일꾼 수는 그때그때 달라져 단정하지 않아요).
    public static int runOnCached(int taskCount) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> done = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 1; i <= taskCount; i++) {
            int id = i;
            pool.submit(() -> done.add(id));
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        return done.size();
    }

    public static void main(String[] args) throws InterruptedException {
        FixedResult fixed = runOnFixed(2, 6);
        System.out.println("=== 고정 풀 (크기 2) 에 일감 6개 ===");
        System.out.println("끝낸 일감 → " + fixed.completed());
        System.out.println("실제 일한 일꾼 수 → " + fixed.distinctThreads() + " (2를 절대 안 넘어요)");

        System.out.println();
        System.out.println("=== 탄력 풀 에 일감 6개 ===");
        System.out.println("끝낸 일감 → " + runOnCached(6));
    }
}
