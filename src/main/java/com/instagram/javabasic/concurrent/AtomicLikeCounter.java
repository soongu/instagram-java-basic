package com.instagram.javabasic.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/AtomicLikeCounter.java
// Day 34 에서 좋아요 카운터(count++) 가 동시에 눌리면 갱신이 새는 걸 봤어요. 그걸 synchronized
// 로 막았죠. synchronized 는 확실하지만, 한 번에 한 스레드만 들여보내느라 나머지는 문 앞에서
// 기다려야 해서 비용이 있어요.
//
// 단순히 "숫자 하나를 안전하게 세는" 정도라면 더 가벼운 도구가 있어요. AtomicInteger 예요.
// incrementAndGet() 은 (읽기 → 더하기 → 쓰기) 세 단계를, 자물쇠 없이도 끊기지 않는 "한 동작"
// 으로 처리해줘요. 비결은 CAS(Compare-And-Swap, 비교 후 교체) 라는 하드웨어 차원의 약속이에요.
// "내가 읽은 값이 아직 그대로면 바꾸고, 그새 누가 바꿨으면 다시 읽어 시도" 를 CPU 가 보장해줘요.
//
// 그래서 100명이 동시에 눌러도 결과가 항상 정확해요. Day 34 의 LikeCounterSafe(synchronized) 와
// 결과는 똑같이 정확하지만, 카운터처럼 단순한 경우엔 Atomic 이 더 가볍고 빨라요.
public class AtomicLikeCounter {

    // 0 으로 시작하는 원자적 정수. 여러 스레드가 동시에 건드려도 안전해요.
    private final AtomicInteger count = new AtomicInteger();

    // synchronized 없이도 안전하게 1 증가. 읽기+더하기+쓰기가 끊기지 않는 한 동작이에요.
    public void like() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    // Day 34 와 똑같은 워크로드(스레드 × 횟수). 이번엔 Atomic 이라 결과가 항상 정확해요.
    public static int runWorkload(int threads, int perThread) throws InterruptedException {
        AtomicLikeCounter counter = new AtomicLikeCounter();
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                for (int n = 0; n < perThread; n++) {
                    counter.like();
                }
            });
        }
        for (Thread w : workers) {
            w.start();
        }
        for (Thread w : workers) {
            w.join();
        }
        return counter.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 100;
        int perThread = 1000;
        int expected = threads * perThread;

        int actual = runWorkload(threads, perThread);

        System.out.println("=== Atomic 으로 안전하게 세는 좋아요 카운터 ===");
        System.out.println("기대값 → " + expected);
        System.out.println("실제값 → " + actual);
        System.out.println("자물쇠(synchronized) 없이도 한 건도 안 새고 정확해요.");
    }
}
