package com.instagram.javabasic.concurrent.solution.day34;

// com/instagram/javabasic/concurrent/solution/day34/FollowerCounterRace.java
// [과제 1] 팔로워 수 카운터에서 경쟁 상태(race condition) 를 직접 재현해 봐요.
//
// 인기 계정에 여러 사람이 "동시에" 팔로우를 누르는 상황이에요. 보호 장치 없이 여러 스레드가
// 같은 count 를 함께 건드리면, 분명히 100,000번 팔로우했는데 최종 숫자가 그보다 작게 나오는
// 일이 생겨요. 갱신이 통째로 사라지는 거예요.
//
// 범인은 count++ 한 줄이에요. 짧아 보여도 (1)현재값 읽기 → (2)1 더하기 → (3)다시 쓰기,
// 이렇게 세 단계로 쪼개져서 실행돼요. 그 틈에 다른 스레드가 끼어들면 한쪽 갱신이 묻혀버려요.
public class FollowerCounterRace {

    // 팔로워 수. 아무 보호 장치 없이 여러 스레드가 함께 건드려요 (그래서 위험해요).
    private int count;

    // 팔로우 한 번. count++ 는 안전해 보이지만 사실 세 단계라 동시에 부르면 깨질 수 있어요.
    public void follow() {
        count++;
    }

    public int getCount() {
        return count;
    }

    // 주어진 수의 스레드가 각각 perThread 번씩 follow() 를 부르고, 모두 끝나길 join() 으로 기다려요.
    // 끝난 뒤의 count 를 돌려줘요 (보호가 없으니 기대값보다 작을 수 있어요).
    public static int runWorkload(int threads, int perThread) throws InterruptedException {
        FollowerCounterRace counter = new FollowerCounterRace();
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                for (int n = 0; n < perThread; n++) {
                    counter.follow();
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
        int expected = threads * perThread;  // 우리가 "기대하는" 값 → 100,000

        int actual = runWorkload(threads, perThread);

        System.out.println("=== 보호 없는 팔로워 카운터 (경쟁 상태) ===");
        System.out.println("기대한 팔로워 수 → " + expected);
        System.out.println("실제 팔로워 수   → " + actual);
        System.out.println("사라진 팔로우    → " + (expected - actual) + " 개");
        System.out.println();
        System.out.println("분명히 " + expected + "번 팔로우했는데 숫자가 모자라죠?");
        System.out.println("count++ 가 세 단계로 쪼개지는 사이에 갱신이 사라진 거예요.");
    }
}
