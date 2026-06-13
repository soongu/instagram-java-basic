package com.instagram.javabasic.concurrent.solution.day37;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/solution/day37/FollowerNotifier.java
// [과제 2] 팔로워에게 알림 팬아웃하기.
// 팔로워가 수천 명이어도, 가상 스레드 executor 면 풀 크기 고민 없이 한 명당 작업 하나씩 던져요.
// newVirtualThreadPerTaskExecutor 가 작업마다 가벼운 가상 스레드를 새로 띄워줘요.
public class FollowerNotifier {

    // 팔로워 수만큼 알림을 동시에 보내고, 보낸 총개수를 돌려줘요.
    public static int notifyFollowers(int followerCount) {
        AtomicInteger notified = new AtomicInteger(0);

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= followerCount; i++) {
                executor.submit(() -> {
                    sleepQuietly(10);              // 푸시 알림을 보내며 잠깐 막히는 척 (블로킹)
                    notified.incrementAndGet();
                });
            }
        } // ← 블록을 벗어나면 모든 알림이 끝날 때까지 기다린 뒤 닫혀요

        return notified.get();
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        System.out.println("보낸 알림 수 → " + notifyFollowers(500) + " / 500");
    }
}
