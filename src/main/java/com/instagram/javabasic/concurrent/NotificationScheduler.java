package com.instagram.javabasic.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

// com/instagram/javabasic/concurrent/NotificationScheduler.java
// 지금까지의 풀은 "지금 당장" 일감을 처리했어요. 그런데 "3초 뒤에 한 번" 이나
// "5초마다 계속" 처럼 시간을 예약하고 싶을 때가 있죠. 인스타로 치면 "새 알림 왔나"
// 를 주기적으로 확인하는 일이에요.
//
// 이런 예약 실행은 ScheduledExecutorService(예약 실행 서비스) 가 맡아요.
//   schedule(일감, 지연, 단위)            — 지연 시간 뒤에 딱 한 번 실행
//   scheduleAtFixedRate(일감, 처음, 주기, 단위) — 처음 지연 뒤부터 주기마다 반복 실행
//
// (스케줄러는 가만히 두면 주기 실행을 영영 반복해요. 그래서 다 쓰면 shutdownNow() 로 꼭 멈춰야 해요.)
public class NotificationScheduler {

    // 일회성 예약: delayMillis 뒤에 딱 한 번 실행해 결과를 돌려줘요 (schedule + 결과 = ScheduledFuture).
    public static String checkOnce(long delayMillis) throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, daemonFactory());
        try {
            ScheduledFuture<String> future =
                    scheduler.schedule(() -> "새 알림 1건", delayMillis, TimeUnit.MILLISECONDS);
            return future.get();   // 예약한 시간이 지나 결과가 나올 때까지 기다렸다 받기
        } finally {
            scheduler.shutdownNow();
        }
    }

    // 주기 예약: periodMillis 마다 반복 실행. targetRuns 번 돌면 멈추고, 실제 실행 횟수를 돌려줘요.
    public static int runPeriodically(int targetRuns, long periodMillis) throws InterruptedException {
        ConcurrentLinkedQueue<Long> runs = new ConcurrentLinkedQueue<>();   // 실행될 때마다 한 칸씩 쌓임
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, daemonFactory());
        try {
            scheduler.scheduleAtFixedRate(() -> runs.add(1L), 0, periodMillis, TimeUnit.MILLISECONDS);

            // targetRuns 번 채워질 때까지 잠깐씩 지켜봐요 (최대 5초 안전선).
            long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
            while (runs.size() < targetRuns && System.nanoTime() < deadline) {
                Thread.sleep(5);
            }
            return runs.size();
        } finally {
            scheduler.shutdownNow();   // 다 봤으면 주기 실행을 멈춰요
        }
    }

    // 메인이 끝나면 스케줄러 스레드도 같이 사라지도록 데몬 스레드로 만들어요.
    private static ThreadFactory daemonFactory() {
        return r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("=== 일회성 예약 (0.1초 뒤 한 번) ===");
        System.out.println("결과 → " + checkOnce(100));

        System.out.println();
        System.out.println("=== 주기 예약 (0.05초마다, 4번 보고 멈춤) ===");
        System.out.println("실제 실행 횟수 → " + runPeriodically(4, 50));
    }
}
