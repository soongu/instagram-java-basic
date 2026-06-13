package com.instagram.javabasic.concurrent.solution.day35;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// com/instagram/javabasic/concurrent/solution/day35/NotificationDispatcher.java
// [과제 1] 댓글 알림을 스레드풀로 한꺼번에 보내기.
// 알림 하나하나를 일감으로 만들어 고정 풀에 던지고, try-with-resources 로 풀을 자동으로 닫아요.
// close() 가 모든 일감이 끝날 때까지 기다려주므로, 블록을 벗어난 뒤 수거함 크기는 항상 정확해요.
public class NotificationDispatcher {

    public static int dispatchAll(int notificationCount) {
        ConcurrentLinkedQueue<Integer> sent = new ConcurrentLinkedQueue<>();

        try (ExecutorService pool = Executors.newFixedThreadPool(4)) {
            for (int i = 1; i <= notificationCount; i++) {
                int notificationId = i;
                pool.submit(() -> sent.add(notificationId));   // 알림 한 건 발송을 일감으로
            }
        } // ← close(): 던진 알림이 전부 처리될 때까지 기다린 뒤 풀을 닫아요

        return sent.size();
    }

    public static void main(String[] args) {
        System.out.println("=== 알림 20건을 스레드풀로 발송 ===");
        System.out.println("보낸 알림 수 → " + dispatchAll(20));
    }
}
