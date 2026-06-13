package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/VisibilityDemo.java
// 메모리 가시성(memory visibility) 과 volatile 키워드를 보는 데모예요.
//
// 한 스레드가 어떤 값을 바꿔도, 다른 스레드가 그 변경을 "곧바로 본다는 보장" 이 없어요.
// 각 스레드(정확히는 CPU 코어) 가 값을 자기 가까운 곳에 캐시해 두고 쓰기 때문이에요.
// 그래서 main 이 running 을 false 로 바꿔도, 워커 스레드는 자기 캐시에 남은 옛날 true 만 계속
// 보면서 영영 안 멈출 수도 있어요. 이게 "변경이 안 보이는" 가시성 문제예요.
//
// 변수에 volatile 을 붙이면 "이 값은 캐시하지 말고 항상 메인 메모리에서 읽고 써라" 는 뜻이 돼요.
// 그러면 한 스레드의 변경을 다른 스레드가 곧바로 보게 돼서 워커가 신호를 받고 멈춰요.
//
// ⚠️ 중요: volatile 은 "가시성" 만 해결해요. count++ 같은 "원자성" 문제는 못 고쳐요.
//    여러 스레드가 함께 값을 바꿔야 한다면 volatile 만으로는 부족하고 synchronized 가 필요해요.
public class VisibilityDemo {

    // volatile 덕분에 main 이 false 로 바꾸면 워커가 곧바로 그 변경을 본다.
    private volatile boolean running = true;

    public void stop() {
        running = false;  // 워커에게 "그만 돌아라" 신호를 보낸다
    }

    public boolean isRunning() {
        return running;
    }

    // running 이 true 인 동안 빙빙 도는 워커 스레드를 띄워서 돌려줘요.
    // running 이 false 가 되면 워커는 그 변경을 보고 루프를 빠져나와 스스로 끝나요.
    public Thread startWorker() {
        Thread worker = new Thread(() -> {
            // volatile 이라 main 의 변경이 곧바로 보여서, 신호가 오면 이 루프를 빠져나온다.
            while (running) {
                // 바쁘게 돌며 신호를 살핀다 (busy-wait).
            }
            System.out.println("[워커] running=false 신호를 보고 멈췄어요");
        });
        worker.start();
        return worker;
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityDemo demo = new VisibilityDemo();

        System.out.println("=== volatile 로 신호가 전달되는 모습 ===");
        Thread worker = demo.startWorker();

        Thread.sleep(100);   // 워커가 한참 돌게 둔다
        System.out.println("[main] 이제 그만 신호를 보냅니다");
        demo.stop();         // running = false

        worker.join();       // 워커가 신호를 보고 멈추면 여기로 돌아온다
        System.out.println("[main] 워커가 무사히 종료됐어요. volatile 덕분에 변경이 보였습니다.");
    }
}
