package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/LikeCounterSafe.java
// LikeCounterRace 와 똑같은 좋아요 카운터인데, 이번엔 synchronized 로 봉인했어요.
//
// synchronized 를 메서드에 붙이면 "이 메서드는 한 번에 한 스레드만 들어올 수 있다" 는 뜻이 돼요.
// 한 스레드가 like() 안에서 count++ 의 세 단계(읽기·더하기·쓰기) 를 끝낼 때까지,
// 다른 스레드는 문 앞에서 기다려요. 그래서 갱신이 사라지는 일이 없어져요.
//
// 이렇게 "한 번에 한 스레드만" 보장되는 코드 구간을 임계 구역(critical section) 이라고 불러요.
// 이제는 100명이 동시에 눌러도 최종 숫자가 정확히 맞아요.
public class LikeCounterSafe {

    // 좋아요 개수. synchronized 메서드로만 건드리니 안전해요.
    private int count;

    // synchronized 덕분에 한 번에 한 스레드만 들어와 count++ 를 끝까지 마쳐요.
    public synchronized void like() {
        count++;
    }

    public int getCount() {
        return count;
    }

    // LikeCounterRace 와 같은 워크로드지만, synchronized 라 결과가 항상 정확해요.
    public static int runWorkload(int threads, int perThread) throws InterruptedException {
        LikeCounterSafe counter = new LikeCounterSafe();
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

        System.out.println("=== synchronized 로 봉인한 좋아요 카운터 ===");
        System.out.println("기대한 좋아요 수 → " + expected);
        System.out.println("실제 좋아요 수   → " + actual);
        System.out.println("사라진 좋아요    → " + (expected - actual) + " 개");
        System.out.println();
        System.out.println("이번엔 한 개도 안 사라졌죠? 한 번에 한 스레드만 들어오게 막았기 때문이에요.");
    }
}
