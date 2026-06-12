package com.instagram.javabasic.concurrent.solution.day33;

// com/instagram/javabasic/concurrent/solution/day33/FeedWatcher.java
// [과제 3] "최신 피드 확인" 을 무한히 반복하다가, interrupt 신호를 받으면 협조적으로 멈춘다.
public class FeedWatcher {

    // 백그라운드 작업이 협조적으로 잘 멈췄는지 기록하는 깃발.
    private boolean stopped;

    public boolean isStopped() {
        return stopped;
    }

    // 피드 확인을 반복하는 스레드를 만들어 띄우고, 그 스레드를 돌려준다.
    public Thread startWatching() {
        Thread watcher = new Thread(() -> {
            // 중단 신호가 안 왔으면 계속 확인한다.
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("[" + Thread.currentThread().getName() + "] 피드 확인 중...");
                try {
                    Thread.sleep(200);  // 너무 빨리 돌지 않게 잠깐 쉰다
                } catch (InterruptedException e) {
                    // sleep 중 신호를 받으면 깃발이 꺼지니, 다시 살려 루프를 빠져나가게 한다.
                    Thread.currentThread().interrupt();
                }
            }
            this.stopped = true;
            System.out.println("[" + Thread.currentThread().getName() + "] 피드 확인을 멈췄어요");
        });
        watcher.start();
        return watcher;
    }

    public static void main(String[] args) throws InterruptedException {
        FeedWatcher watcher = new FeedWatcher();
        Thread thread = watcher.startWatching();

        Thread.sleep(1000);  // 1초 동안 백그라운드로 피드를 확인하게 둔다
        thread.interrupt();  // "그만해 줄래?" 협조적 중단 신호
        thread.join();       // 실제로 멈출 때까지 기다린다

        System.out.println("백그라운드 작업이 안전하게 종료됐습니다 (stopped=" + watcher.isStopped() + ")");
    }
}
