package com.instagram.javabasic.concurrent.solution.day37;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/solution/day37/VirtualNotificationSender.java
// [과제 1] 가상 스레드로 알림 보내기.
// 메시지마다 startVirtualThread 로 가상 스레드를 하나씩 띄워 동시에 전송해요.
// isVirtual() 이 true 일 때만 세서, 정말 가상 스레드에서 도는지도 함께 확인해요.
public class VirtualNotificationSender {

    // 메시지 개수만큼 가상 스레드를 띄워 전송하고, 보낸(=가상 스레드에서 돈) 개수를 돌려줘요.
    public static int sendAll(String[] messages) throws InterruptedException {
        AtomicInteger sent = new AtomicInteger(0);   // 여러 스레드가 함께 올리므로 Atomic
        List<Thread> threads = new ArrayList<>();

        for (String message : messages) {
            // startVirtualThread = ofVirtual().start() 의 짧은 표현
            Thread vt = Thread.startVirtualThread(() -> {
                // 정말 가상 스레드에서 도는지 확인하고, 맞을 때만 "전송됨" 으로 세요.
                if (Thread.currentThread().isVirtual()) {
                    sent.incrementAndGet();
                }
            });
            threads.add(vt);
        }

        for (Thread t : threads) {   // 띄운 스레드가 전부 끝날 때까지 기다리기
            t.join();
        }
        return sent.get();
    }

    public static void main(String[] args) throws InterruptedException {
        String[] messages = {"새 게시물 알림", "좋아요 알림", "댓글 알림"};
        System.out.println("보낸 알림 수 → " + sendAll(messages) + " / " + messages.length);
    }
}
