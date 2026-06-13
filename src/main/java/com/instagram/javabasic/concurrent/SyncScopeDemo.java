package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/SyncScopeDemo.java
// 락(lock) 을 "얼마나 넓게 잠그느냐" 를 비교하는 데모예요.
//
// synchronized 를 메서드 전체에 붙이면 메서드에 들어오는 순간부터 나갈 때까지 통째로 잠겨요.
// 메서드 안에 시간이 오래 걸리는 준비 작업이 있어도 그동안 다른 스레드는 못 들어와요.
//
// synchronized 블록을 쓰면 "정말 보호가 필요한 몇 줄만" 골라서 잠글 수 있어요.
//   synchronized (lock) { ... }  ← 중괄호 안에 있는 동안만 lock 을 잡아요.
// 준비 작업은 잠그지 않고 여러 스레드가 같이 하고, 공유 값을 건드리는 임계 구역만 좁게 잠그는 거죠.
//
// 두 방식 다 결과는 똑같이 정확해요. 다만 "꼭 필요한 곳만 좁게 잠그면" 다른 스레드가 기다리는
// 시간이 줄어서 더 잘 돌아가요. 락은 넓을수록 안전한 게 아니라, 필요한 만큼만 잡는 게 좋아요.
public class SyncScopeDemo {

    // 블록 방식에서 사용할 전용 자물쇠 객체예요. 이 객체를 잡은 스레드만 임계 구역에 들어와요.
    private final Object lock = new Object();

    // 공유 카운터.
    private int count;

    // 방법 A: 메서드 전체를 잠근다. 들어오는 순간부터 나갈 때까지 한 스레드만.
    public synchronized void likeWholeMethod() {
        count++;
    }

    // 방법 B: 공유 값을 건드리는 줄만 좁게 잠근다. lock 객체를 잡은 스레드만 이 블록에 들어와요.
    public void likeNarrowBlock() {
        // (여기서 시간이 걸리는 준비 작업을 했다면 잠그지 않고 같이 할 수 있어요)
        synchronized (lock) {
            count++;  // 정말 보호가 필요한 임계 구역만 좁게 잠근다
        }
    }

    public int getCount() {
        return count;
    }

    // 주어진 방식(useBlock 이 true 면 블록, false 면 메서드 전체) 으로 멀티스레드 워크로드를 돌려요.
    public static int runWorkload(boolean useBlock, int threads, int perThread) throws InterruptedException {
        SyncScopeDemo demo = new SyncScopeDemo();
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                for (int n = 0; n < perThread; n++) {
                    if (useBlock) {
                        demo.likeNarrowBlock();
                    } else {
                        demo.likeWholeMethod();
                    }
                }
            });
        }
        for (Thread w : workers) {
            w.start();
        }
        for (Thread w : workers) {
            w.join();
        }
        return demo.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 100;
        int perThread = 1000;
        int expected = threads * perThread;

        int methodResult = runWorkload(false, threads, perThread);
        int blockResult = runWorkload(true, threads, perThread);

        System.out.println("=== 락의 범위: 메서드 전체 잠금 vs 블록만 좁게 잠금 ===");
        System.out.println("기대값                  → " + expected);
        System.out.println("메서드 전체 잠금 결과   → " + methodResult);
        System.out.println("블록만 좁게 잠금 결과   → " + blockResult);
        System.out.println();
        System.out.println("둘 다 정확하죠? 결과는 같아요.");
        System.out.println("다만 '꼭 필요한 곳만' 좁게 잠그면 다른 스레드가 덜 기다려서 더 잘 돌아가요.");
    }
}
