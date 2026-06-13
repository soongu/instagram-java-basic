package com.instagram.javabasic.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/VirtualFeedLoader.java
// 지난 시간(Day 35)에 ExecutorService 로 일감을 풀에 던졌어요. 그때는 풀 크기를 몇으로
// 잡을지 늘 고민이었죠. 너무 작으면 일꾼이 부족해 줄이 밀리고, 너무 크면 무거운 스레드가
// 메모리를 잡아먹으니까요.
//
// 가상 스레드 executor 는 이 고민을 없애줘요.
//   Executors.newVirtualThreadPerTaskExecutor()
// 이름 그대로 "작업(Task) 하나당 가상 스레드 하나" 를 새로 띄워줘요. 가상 스레드는 가벼우니
// 작업이 1만 개여도 1만 개를 그냥 띄우면 돼요. 풀 크기를 정할 필요가 없어진 거예요.
//
// 그래서 "한 작업에 한 스레드" 라는 가장 단순한 모델이 다시 통하게 됐어요. 막혀도 부담이
// 없으니, 복잡하게 비동기로 쪼개지 않고도 코드를 쉽게 쓸 수 있어요.
public class VirtualFeedLoader {

    // 게시물 postCount 개를 동시에 불러오고, 다 불러온 개수를 돌려줘요.
    public static int loadFeed(int postCount) {
        AtomicInteger loaded = new AtomicInteger(0);

        // try-with-resources: 블록을 벗어나면 close() 가 모든 작업 완료를 기다린 뒤 정리해요.
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int postId = 1; postId <= postCount; postId++) {
                executor.submit(() -> {
                    sleepQuietly(20);             // 게시물 한 개를 네트워크로 불러오는 척 (블로킹)
                    loaded.incrementAndGet();
                });
            }
        } // ← 여기서 모든 게시물 로딩이 끝날 때까지 기다린 뒤 닫혀요

        return loaded.get();
    }

    // 각 작업이 정말 가상 스레드 위에서 돌았는지 세어 확인해요. (전부 가상이면 postCount 와 같음)
    public static int virtualTaskCount(int postCount) {
        AtomicInteger virtualCount = new AtomicInteger(0);

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int postId = 1; postId <= postCount; postId++) {
                executor.submit(() -> {
                    if (Thread.currentThread().isVirtual()) {
                        virtualCount.incrementAndGet();
                    }
                });
            }
        }

        return virtualCount.get();
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 가상 스레드 executor 로 피드 로딩 ===");
        System.out.println("불러온 게시물 수 → " + loadFeed(100) + " / 100");
        System.out.println("가상 스레드에서 돈 작업 수 → " + virtualTaskCount(100) + " / 100");
    }
}
