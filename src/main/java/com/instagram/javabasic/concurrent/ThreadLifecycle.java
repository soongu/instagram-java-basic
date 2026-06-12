package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/ThreadLifecycle.java
// 스레드는 태어나서 죽을 때까지 여러 "상태(State)" 를 거쳐요. getState() 로 지금 상태를 들여다볼 수 있어요.
//
// 자바 스레드의 6가지 상태(Thread.State):
//   NEW           : 만들어졌지만 아직 start() 를 안 부른 상태 (대기 중인 신입)
//   RUNNABLE      : 실행 중이거나, CPU 차례를 기다리는 중 (일하는 중)
//   TIMED_WAITING : sleep(시간) 처럼 정해진 시간만큼 쉬는 중
//   WAITING       : 다른 스레드의 신호를 기다리며 무한정 멈춘 상태
//   BLOCKED       : 자물쇠(lock)를 기다리며 막힌 상태
//   TERMINATED    : run() 이 끝나서 완전히 종료된 상태 (퇴근 완료)
//
// 이 중 NEW(시작 전) 와 TERMINATED(종료 후) 는 "딱 정해진 시점" 이라 항상 확실하게 관찰돼요.
// RUNNABLE 같은 상태는 순식간에 지나가서 정확히 잡아내기 어려워요 — 일단 NEW/TERMINATED 부터 눈에 익혀봐요.
public class ThreadLifecycle {

    // start() 를 부르기 직전의 상태를 돌려준다 → 항상 NEW.
    public static Thread.State observeBeforeStart() {
        Thread thread = new Thread(() -> {
        });
        return thread.getState();  // NEW
    }

    // join() 으로 끝까지 기다린 직후의 상태를 돌려준다 → 항상 TERMINATED.
    public static Thread.State observeAfterJoin() throws InterruptedException {
        Thread thread = new Thread(() -> {
        });
        thread.start();
        thread.join();
        return thread.getState();  // TERMINATED
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] 작업 실행 중");
        });

        System.out.println("start() 전 상태   → " + thread.getState());  // NEW
        thread.start();
        thread.join();
        System.out.println("join() 후 상태    → " + thread.getState());  // TERMINATED
    }
}
