package com.instagram.javabasic.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// com/instagram/javabasic/concurrent/FeedTaskExecutor.java
// 인스타 피드를 열면 이미지 여러 장이 한꺼번에 로딩되죠? 그 일감들을 풀에 던져 처리해봐요.
// 여기서는 풀을 "다 쓰고 정리하는" 두 가지 방법을 나란히 봐요.
//
//   방법 A. 손으로 정리   : shutdown() 으로 "이제 새 일감 안 받아" 라고 알리고,
//                          awaitTermination() 으로 받은 일감이 다 끝날 때까지 기다려요.
//   방법 B. 자동으로 정리 : try-with-resources 괄호 안에 풀을 넣으면, 블록을 벗어날 때
//                          close() 가 자동으로 shutdown + 대기를 해줘요. Day 23 에서 배운
//                          그 문법이에요. ExecutorService 는 AutoCloseable 이라 가능해요.
//
// 정리를 빼먹으면 풀의 일꾼 스레드가 계속 살아남아 프로그램이 안 끝날 수 있어요. 그래서
// 풀은 반드시 닫아줘야 하고, 방법 B 가 깜빡할 일이 없어 더 안전해요.
public class FeedTaskExecutor {

    // 방법 A: 풀의 생명주기를 손으로 다뤄요 (shutdown → awaitTermination).
    public static int loadImagesExplicit(int imageCount) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> loaded = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int id = 1; id <= imageCount; id++) {
            int imageId = id;
            pool.submit(() -> loaded.add(imageId));   // 이미지 한 장 로딩을 일감으로 던지기
        }

        pool.shutdown();                            // 새 일감 그만 받기 (받은 건 마저 처리)
        pool.awaitTermination(5, TimeUnit.SECONDS); // 받은 일감이 끝날 때까지 대기
        return loaded.size();
    }

    // 방법 B: try-with-resources. 블록을 벗어나면 close() 가 알아서 정리해줘요.
    public static int loadImages(int imageCount) {
        ConcurrentLinkedQueue<Integer> loaded = new ConcurrentLinkedQueue<>();

        try (ExecutorService pool = Executors.newFixedThreadPool(4)) {
            for (int id = 1; id <= imageCount; id++) {
                int imageId = id;
                pool.submit(() -> loaded.add(imageId));
            }
        } // ← 여기서 close() 호출: 모든 일감이 끝날 때까지 자동으로 기다린 뒤 풀을 닫아요

        return loaded.size();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 방법 A: 손으로 shutdown ===");
        System.out.println("로딩된 이미지 수 → " + loadImagesExplicit(12));

        System.out.println();
        System.out.println("=== 방법 B: try-with-resources (자동 정리) ===");
        System.out.println("로딩된 이미지 수 → " + loadImages(12));
    }
}
