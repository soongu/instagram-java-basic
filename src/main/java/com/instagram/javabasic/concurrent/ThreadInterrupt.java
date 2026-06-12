package com.instagram.javabasic.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

// com/instagram/javabasic/concurrent/ThreadInterrupt.java
// 돌고 있는 스레드를 "그만 멈춰" 하고 싶을 때 어떻게 할까요?
//
// 자바에는 interrupt() 라는 메서드가 있어요. 그런데 이건 스레드를 강제로 죽이는 게 아니에요.
// "이제 그만해 주면 좋겠어" 라고 살짝 신호(깃발)를 꽂아두는 것뿐이에요 — 협조적 중단이라고 불러요.
// 멈출지 말지는 그 스레드가 스스로 isInterrupted() 로 깃발을 확인하고 결정해요.
//
// 옛날엔 Thread.stop() 으로 스레드를 강제로 멈출 수 있었는데, 지금은 쓰면 안 돼요 (deprecated).
// 강제로 끊으면 하던 작업이 어중간한 상태에서 끊겨 데이터가 깨질 수 있거든요.
// 그래서 "신호를 보내고, 받는 쪽이 안전한 지점에서 스스로 정리하고 멈추는" 협조적 방식이 표준이에요.
//
// 멈추는 신호를 확인하는 방법은 두 갈래예요.
//   (1) 루프 안에서 isInterrupted() 로 깃발을 직접 확인하기 (busy loop)
//   (2) sleep() 처럼 기다리는 중이면 InterruptedException 이 터지면서 깨어나기
public class ThreadInterrupt {

    // (1) 루프를 돌며 isInterrupted() 로 중단 신호를 확인하는 작업 스레드를 만들어요.
    //     핵심은 while (!isInterrupted()) 한 줄이에요 — 이건 아래 main 에서 직접 보여줄게요.
    //     아래 두 파라미터(started, stoppedGracefully)는 "스레드가 정말 멈췄는지" 를 바깥에서
    //     확인하기 위한 관찰 장치일 뿐이에요. 협조적 중단의 원리 자체와는 상관없어요.
    public static Thread busyLoopThread(CountDownLatch started, AtomicBoolean stoppedGracefully) {
        return new Thread(() -> {
            started.countDown();  // "나 루프 시작했어" 라고 알림
            // isInterrupted() 가 true 가 될 때까지 계속 일한다 → 신호가 오면 루프를 빠져나간다.
            while (!Thread.currentThread().isInterrupted()) {
                // 여기서 한 묶음씩 일을 한다고 상상해요 (실제로는 비워둠).
            }
            // 루프를 빠져나왔다 = 중단 신호를 보고 스스로 멈췄다는 뜻.
            stoppedGracefully.set(true);
        });
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== sleep() 중인 스레드를 interrupt() 로 깨우기 ===");
        Thread sleeper = new Thread(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] 10초 동안 잘게요...");
                Thread.sleep(10_000);
                System.out.println("푹 잤어요");  // 여기까진 도달 못 해요 (중간에 깨워질 거라서)
            } catch (InterruptedException e) {
                System.out.println("[" + Thread.currentThread().getName() + "] 누가 깨웠어요! 잠에서 깸 (InterruptedException)");
            }
        });
        sleeper.start();
        Thread.sleep(100);   // 잠들 시간을 잠깐 준 뒤
        sleeper.interrupt(); // "그만 자고 일어나" 신호
        sleeper.join();

        System.out.println();

        System.out.println("=== isInterrupted() 루프로 협조적 중단 ===");
        Thread worker = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                count++;  // 신호가 올 때까지 계속 일하는 척
            }
            System.out.println("[" + Thread.currentThread().getName() + "] 중단 신호를 받고 멈췄어요");
        });
        worker.start();
        Thread.sleep(100);
        worker.interrupt();  // 강제 종료가 아니라 "그만해 줄래?" 부탁
        worker.join();

        System.out.println("두 스레드 모두 안전하게 정리하고 종료됐어요");
    }
}
