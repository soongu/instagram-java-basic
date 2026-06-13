package com.instagram.javabasic.concurrent.solution.day34;

// com/instagram/javabasic/concurrent/solution/day34/ViewCounter.java
// [과제 2] 조회수 카운터를 synchronized 로 봉인해, 동시에 눌러도 항상 정확하게 세요.
//
// 과제 1의 팔로워 카운터는 보호가 없어 갱신이 사라졌어요. 이번엔 같은 구조인데 view() 에
// synchronized 를 붙였어요. "이 메서드는 한 번에 한 스레드만 들어올 수 있다" 는 뜻이라,
// 한 스레드가 count++ 의 세 단계(읽기·더하기·쓰기) 를 끝낼 때까지 다른 스레드는 문 앞에서
// 기다려요. 그래서 갱신이 사라지는 일이 없어져요.
//
// 이렇게 "한 번에 한 스레드만" 보장되는 코드 구간을 임계 구역(critical section) 이라고 불러요.
// 이제는 50개 스레드가 동시에 조회해도 최종 숫자가 정확히 맞아요.
public class ViewCounter {

    // 조회수. synchronized 메서드로만 건드리니 안전해요.
    private int count;

    // synchronized 덕분에 한 번에 한 스레드만 들어와 count++ 를 끝까지 마쳐요.
    public synchronized void view() {
        count++;
    }

    public int getCount() {
        return count;
    }

    // 과제 1과 같은 워크로드지만, synchronized 라 결과가 항상 정확해요.
    public static int runWorkload(int threads, int perThread) throws InterruptedException {
        ViewCounter counter = new ViewCounter();
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                for (int n = 0; n < perThread; n++) {
                    counter.view();
                }
            });
        }
        for (Thread w : workers) {
            w.start();
        }
        for (Thread w : workers) {
            w.join();  // 모든 일꾼이 끝날 때까지 기다린다
        }
        return counter.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 50;
        int perThread = 2000;
        int expected = threads * perThread;  // 100,000

        int actual = runWorkload(threads, perThread);

        System.out.println("=== synchronized 로 봉인한 조회수 카운터 ===");
        System.out.println("기대한 조회수 → " + expected);
        System.out.println("실제 조회수   → " + actual);
        System.out.println("사라진 조회   → " + (expected - actual) + " 개");
        System.out.println();
        System.out.println("이번엔 한 개도 안 사라졌죠? 한 번에 한 스레드만 들어오게 막았기 때문이에요.");
    }
}
