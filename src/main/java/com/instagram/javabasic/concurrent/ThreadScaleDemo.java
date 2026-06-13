package com.instagram.javabasic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/ThreadScaleDemo.java
// 가상 스레드가 가벼운 게 왜 좋을까요? 한꺼번에 아주 많이 만들어도 끄떡없기 때문이에요.
//
// 플랫폼 스레드는 한 개에 보통 1MB 안팎의 메모리(스택)를 잡아요. 수만 개를 만들면
// 메모리가 금세 바닥나서 프로그램이 휘청여요. 반면 가상 스레드는 아주 적은 메모리만
// 쓰기 때문에, 수만 개를 동시에 만들어도 가뿐해요.
//
// 여기서는 가상 스레드를 count 개 만들어, 각자 잠깐 쉰(sleep) 뒤 "나 끝났어요" 하고
// 카운터를 1 올리게 해요. 마지막에 카운터가 count 와 같으면 전부 무사히 끝난 거예요.
public class ThreadScaleDemo {

    // 가상 스레드 count 개를 만들어 전부 실행하고, 끝까지 완료된 개수를 돌려줘요.
    public static int runManyVirtualThreads(int count) throws InterruptedException {
        AtomicInteger completed = new AtomicInteger(0);   // 여러 스레드가 함께 올리므로 Atomic
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Thread vt = Thread.ofVirtual().start(() -> {
                sleepQuietly(50);                 // 짧은 멈춤 = 외부 응답을 기다리는 척
                completed.incrementAndGet();      // 다 했으면 카운터 +1
            });
            threads.add(vt);
        }

        // 만들어 둔 가상 스레드가 전부 끝날 때까지 기다려요.
        for (Thread t : threads) {
            t.join();
        }

        return completed.get();
    }

    // sleep 의 InterruptedException 을 매번 try-catch 로 감싸기 번거로워 작은 도우미로 빼뒀어요.
    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 가상 스레드 1만 개 동시 실행 ===");
        int done = runManyVirtualThreads(10_000);
        System.out.println("완료된 가상 스레드 수 → " + done + " / 10000");
        System.out.println("1만 개가 각자 50ms 씩 쉬었는데도 거의 바로 끝났죠? 막혀도 OS 스레드를 안 잡기 때문이에요.");
    }
}
