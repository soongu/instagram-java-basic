package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/ThreadBasics.java
// 스레드를 만드는 첫 번째 방법: Thread 클래스를 상속해서 run() 을 채우기.
// run() 안에 "이 스레드가 할 일" 을 적어두면, start() 로 새 스레드에서 그 일을 돌릴 수 있어요.
//
// 핵심: start() 와 run() 은 비슷해 보이지만 완전히 다릅니다.
//   - run() 을 직접 부르면 → 그냥 평범한 메서드 호출이에요. 새 스레드가 안 생겨요.
//   - start() 를 부르면 → JVM 이 새 호출 스택(새 스레드)을 만들어 그 위에서 run() 을 돌려요.
public class ThreadBasics extends Thread {

    // run() 이 실제로 어느 스레드에서 돌았는지 기록해 둬요 (main 인지, 새 스레드인지 확인용).
    private String executedThreadName;

    // 작업이 끝까지 수행됐는지 표시하는 깃발.
    private boolean done;

    // 이 스레드가 할 일. start() 로 시작하면 새 스레드 위에서, run() 을 직접 부르면 부른 쪽 스레드에서 돌아요.
    @Override
    public void run() {
        // 지금 이 코드를 돌리고 있는 스레드의 이름을 가져와요.
        this.executedThreadName = Thread.currentThread().getName();
        System.out.println("[" + executedThreadName + "] 피드 새로고침 작업을 시작합니다");
        this.done = true;
    }

    public String getExecutedThreadName() {
        return executedThreadName;
    }

    public boolean isDone() {
        return done;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== run() 을 직접 불러보기 (새 스레드 X) ===");
        ThreadBasics direct = new ThreadBasics();
        direct.run();  // 메서드를 그냥 호출 → 부른 쪽(main) 스레드에서 실행
        System.out.println("run() 을 직접 부른 스레드 → " + direct.getExecutedThreadName());  // main

        System.out.println();

        System.out.println("=== start() 로 새 스레드에서 돌려보기 ===");
        ThreadBasics worker = new ThreadBasics();
        worker.start();   // 새 호출 스택(새 스레드) 생성 → 그 위에서 run() 실행
        worker.join();    // 새 스레드가 끝날 때까지 기다려요
        System.out.println("start() 로 실행된 스레드 → " + worker.getExecutedThreadName());
        System.out.println("main 스레드 이름 → " + Thread.currentThread().getName());
    }
}
