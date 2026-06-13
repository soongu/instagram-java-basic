package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/AtomicityDemo.java
// count++ 가 사실은 "한 번에 끝나는 한 동작" 이 아니라는 걸 결정적으로 증명하는 데모예요.
//
// count++ 는 이렇게 세 단계로 쪼개져요.
//   (1) 읽기 : 지금 count 가 몇인지 읽어온다
//   (2) 더하기: 읽어온 값에 1을 더한다
//   (3) 쓰기 : 더한 값을 다시 count 에 써넣는다
//
// 이 세 단계가 끊기지 않고 한 덩어리로 끝나는 성질을 원자성(atomicity) 이라고 불러요.
// count++ 에는 그 원자성이 없어요. 그래서 두 스레드가 (1)에서 똑같이 0을 읽어버리면,
// 둘 다 "0 + 1 = 1" 을 써넣어서, 두 번 더했는데도 결과가 1밖에 안 돼요. 하나가 통째로 사라지죠.
//
// LikeCounterRace 에서는 이 손실이 "운이 나쁠 때 가끔" 일어났지만, 여기서는 (1)과 (3) 사이에
// 일부러 sleep 으로 틈을 벌려서 손실을 "항상" 일어나게 만들어요 (결정적 재현).
public class AtomicityDemo {

    // 두 스레드가 함께 건드릴 카운터.
    private int count;

    // count++ 한 줄을 (읽기 → 끼어들 틈 → 쓰기) 로 손수 펼쳐 쓴 메서드예요.
    // 가운데 sleep 덕분에 두 스레드가 같은 초기값을 읽도록 강제할 수 있어요.
    public void incrementSlowly() throws InterruptedException {
        int read = count;        // (1) 읽기
        Thread.sleep(100);       // 일부러 만든 틈 — 이 사이에 다른 스레드가 끼어들어요
        count = read + 1;        // (2)+(3) 더해서 다시 쓰기
    }

    public int getCount() {
        return count;
    }

    // 두 스레드가 거의 동시에 incrementSlowly() 를 한 번씩 부르게 해서, 둘 다 0을 읽도록 만들어요.
    // 두 번 증가시켰지만 가운데 틈 때문에 최종값은 1로 떨어져요 (갱신 하나가 사라짐).
    public static int runInterleaved() throws InterruptedException {
        AtomicityDemo demo = new AtomicityDemo();

        Runnable task = () -> {
            try {
                demo.incrementSlowly();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();   // 둘 다 곧바로 (1)읽기로 들어가 같은 0을 읽는다
        t1.join();
        t2.join();
        return demo.getCount();
    }

    public static void main(String[] args) throws InterruptedException {
        int result = runInterleaved();

        System.out.println("=== count++ 는 세 단계다 (원자성 없음) ===");
        System.out.println("두 스레드가 각각 +1 → 기대값은 2");
        System.out.println("실제 결과 → " + result);
        System.out.println();
        System.out.println("둘 다 0을 읽고 → 둘 다 '0+1=1' 을 써넣어서 → 최종 1.");
        System.out.println("증가 한 번이 통째로 사라졌어요. 이게 원자성이 없을 때 생기는 일이에요.");
    }
}
