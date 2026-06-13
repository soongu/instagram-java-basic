package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/LikeCounterRace.java
// 경쟁 상태(race condition) 를 직접 눈으로 보는 데모예요.
//
// 인스타에서 인기 게시물에 여러 사람이 "동시에" 좋아요를 누르는 상황을 흉내 내봐요.
// 여러 스레드가 같은 count 를 동시에 건드리면, 분명히 100번 눌렀는데 최종 숫자가 100보다
// 작게 나오는 일이 생겨요. 이렇게 "누가 언제 끼어드느냐" 에 따라 결과가 들쭉날쭉해지는 걸
// 경쟁 상태(race condition) 라고 불러요.
//
// 범인은 count++ 한 줄이에요. 짧아 보이지만 사실은 (1)현재값 읽기 → (2)1 더하기 → (3)다시 쓰기,
// 이렇게 세 단계로 쪼개져서 실행돼요. 그 사이에 다른 스레드가 끼어들면 갱신이 통째로 사라져요.
// (이 "세 단계" 는 AtomicityDemo 에서 더 또렷하게 증명해 봐요.)
public class LikeCounterRace {

    // 좋아요 개수. 아무 보호 장치 없이 여러 스레드가 함께 건드려요 (그래서 위험해요).
    private int count;

    // 좋아요 한 번. count++ 는 안전해 보이지만 사실 세 단계라 동시에 부르면 깨질 수 있어요.
    public void like() {
        count++;
    }

    public int getCount() {
        return count;
    }

    // 주어진 수의 스레드가 각각 perThread 번씩 like() 를 부르고, 모두 끝나길 join() 으로 기다려요.
    // 끝난 뒤의 count 를 돌려줘요 (보호가 없으니 기대값보다 작을 수 있어요).
    public static int runWorkload(int threads, int perThread) throws InterruptedException {
        LikeCounterRace counter = new LikeCounterRace();
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
            w.join();  // 모든 일꾼이 끝날 때까지 기다린다
        }
        return counter.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 100;
        int perThread = 1000;
        int expected = threads * perThread;  // 우리가 "기대하는" 값

        int actual = runWorkload(threads, perThread);

        System.out.println("=== 보호 없는 좋아요 카운터 (경쟁 상태) ===");
        System.out.println("기대한 좋아요 수 → " + expected);
        System.out.println("실제 좋아요 수   → " + actual);
        System.out.println("사라진 좋아요    → " + (expected - actual) + " 개");
        System.out.println();
        System.out.println("분명히 " + expected + "번 눌렀는데 숫자가 모자라죠?");
        System.out.println("count++ 가 세 단계로 쪼개지는 사이에 갱신이 사라진 거예요.");
    }
}
