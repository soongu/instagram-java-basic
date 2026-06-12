package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/SleepAndJoin.java
// 스레드를 다룰 때 자주 쓰는 두 가지 제어 도구를 배워봐요.
//
//   Thread.sleep(밀리초) : 지금 스레드를 그 시간만큼 잠깐 재워요. 자는 동안 상태는 TIMED_WAITING 이에요.
//   작업스레드.join()    : 그 작업 스레드가 끝날 때까지 "나" 를 멈춰서 기다려요.
//
// join() 이 왜 중요할까요? 작업 스레드가 어떤 값을 채워주는데, 그게 다 끝나기 전에 읽으면 아직 빈 값일 수 있어요.
// join() 으로 "끝날 때까지 기다린 다음" 에 읽으면 결과가 확정돼 있어서 안전해요.
//
// sleep() 과 join() 둘 다 InterruptedException 을 던질 수 있어요 (지난 시간에 배운 checked 예외예요).
// "기다리는 중에 누가 그만하라고 깨우면" 이 예외가 터져요. 그래서 try-catch 로 감싸줘야 해요.
public class SleepAndJoin {

    // 작업 스레드가 채워줄 결과 값. join() 으로 기다린 뒤에 읽어야 안전해요.
    private int result;

    // 작업이 끝났는지 표시하는 깃발.
    private boolean finished;

    // 작업 스레드를 띄우고, join() 으로 끝까지 기다린 뒤 결과를 돌려줘요.
    public int runAndWait() throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                // 무거운 계산을 흉내 내려고 잠깐 재워봐요 (이 동안 상태는 TIMED_WAITING).
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // 기다리다 깨어났다는 신호를 다시 살려두는 게 예의 바른 처리예요.
                Thread.currentThread().interrupt();
            }
            this.result = 42;
            this.finished = true;
        });

        worker.start();
        worker.join();  // worker 가 끝날 때까지 main 을 멈춰서 기다린다
        return result;  // 여기 도착했을 땐 result 가 확정돼 있어요
    }

    public boolean isFinished() {
        return finished;
    }

    public static void main(String[] args) throws InterruptedException {
        SleepAndJoin job = new SleepAndJoin();

        System.out.println("작업 시작 전 finished? → " + job.isFinished());  // false
        int answer = job.runAndWait();
        System.out.println("join() 후 finished?   → " + job.isFinished());  // true
        System.out.println("확정된 결과           → " + answer);             // 42
    }
}
