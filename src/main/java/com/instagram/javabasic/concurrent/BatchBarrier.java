package com.instagram.javabasic.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;

// com/instagram/javabasic/concurrent/BatchBarrier.java
// CountDownLatch 는 "메인이 일꾼들을 기다리는" 한 방향 신호였어요. 그런데 일꾼들끼리 서로
// 발을 맞춰야 할 때도 있어요. 예를 들어 배치 작업을 여러 단계로 나눠 할 때, 모든 일꾼이 1단계를
// 끝낼 때까지 기다렸다가 다 같이 2단계로 넘어가야 결과가 어긋나지 않아요.
//
// 이렇게 "정해진 인원이 모두 도착하면 함께 출발" 하게 해주는 게 CyclicBarrier(순환 장벽) 예요.
//   - 각 스레드는 자기 몫을 끝내면 await() 로 장벽 앞에서 기다려요.
//   - 마지막 한 명이 도착해 인원이 다 차는 순간, 장벽이 열려 전원이 동시에 다음으로 나아가요.
// 이름의 "순환(Cyclic)" 처럼, 한 번 열린 뒤 다시 닫혀 다음 단계에서 또 쓸 수 있어요 (재사용 가능).
// CountDownLatch 가 일회용 신호등이라면, CyclicBarrier 는 매 단계 다시 모이는 집결지예요.
public class BatchBarrier {

    // workers 명이 함께 rounds 단계의 배치를 처리해요. 완료된 단계 수를 돌려줘요 (== rounds).
    public static int runBatches(int workers, int rounds) throws InterruptedException {
        // 한 단계가 통째로 끝날 때마다(=전원이 그 단계 장벽을 통과할 때마다) 한 칸씩 쌓여요.
        ConcurrentLinkedQueue<Integer> finishedRounds = new ConcurrentLinkedQueue<>();

        // 인원 workers 명. 전원이 도착하면 두 번째 인자(장벽 동작) 가 한 번 실행돼요.
        CyclicBarrier barrier = new CyclicBarrier(workers, () -> finishedRounds.add(1));

        Thread[] team = new Thread[workers];
        for (int i = 0; i < workers; i++) {
            team[i] = new Thread(() -> {
                try {
                    for (int r = 0; r < rounds; r++) {
                        // ... 이 단계에서 내가 맡은 몫을 처리한다고 치고 ...
                        barrier.await();   // 동료가 다 올 때까지 기다렸다가 함께 다음 단계로
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        for (Thread t : team) {
            t.start();
        }
        for (Thread t : team) {
            t.join();
        }
        return finishedRounds.size();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 일꾼 3명이 2단계 배치를 발맞춰 처리 ===");
        int done = runBatches(3, 2);
        System.out.println("완료된 단계 수 → " + done + " (모두가 한 단계를 마칠 때마다 1씩 늘어요)");
    }
}
