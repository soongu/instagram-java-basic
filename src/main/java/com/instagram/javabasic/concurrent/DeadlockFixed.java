package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/DeadlockFixed.java
// DeadlockDemo 에서 일어난 데드락을 고치는 방법을 보여주는 데모예요.
//
// 데드락은 자물쇠를 잡는 "순서가 엇갈려서" 생겼어요. 한쪽은 A→B, 다른 쪽은 B→A 였죠.
// 해결책은 의외로 간단해요. 모든 스레드가 자물쇠를 "똑같은 순서로" 잡게 하면 돼요.
//
// 여기서는 두 스레드 모두 A 를 먼저 잡고 → 그다음 B 를 잡아요 (둘 다 A→B).
// 그러면 먼저 A 를 잡은 스레드가 B 까지 잡아 일을 끝내고 두 자물쇠를 모두 놓을 때까지,
// 다른 스레드는 A 앞에서 기다리기만 해요. 서로를 마주 보고 막는 순환 대기가 사라지죠.
// 좁은 골목에 "한 방향으로만 지나가세요" 규칙을 붙인 것과 같아요.
//
// 데몬 스레드 처리는 DeadlockDemo 와 같게 두되, 이번엔 데드락이 없어서 두 스레드 모두
// 제때 일을 끝내고 스스로 종료해요.
public class DeadlockFixed {

    private final Object lockA = new Object();
    private final Object lockB = new Object();

    // 두 스레드가 모두 이 메서드를 쓰게 해서, 락 순서를 A → B 로 통일해요.
    public Thread makeOrderedThread(String name) {
        Thread t = new Thread(() -> {
            synchronized (lockA) {        // 항상 A 를 먼저
                System.out.println("[" + name + "] lockA 잡음");
                sleepQuietly(50);
                synchronized (lockB) {    // 그다음 B
                    System.out.println("[" + name + "] lockB 도 잡음 → 일 끝, 두 자물쇠 모두 놓음");
                }
            }
        });
        t.setDaemon(true);
        return t;
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockFixed demo = new DeadlockFixed();
        Thread t1 = demo.makeOrderedThread("스레드1");
        Thread t2 = demo.makeOrderedThread("스레드2");

        System.out.println("=== 락 순서를 A→B 로 통일해 데드락 해결 ===");
        t1.start();
        t2.start();

        t1.join(2000);
        t2.join(2000);

        System.out.println();
        System.out.println("[스레드1] 아직 살아있나요? → " + t1.isAlive());  // false (끝남)
        System.out.println("[스레드2] 아직 살아있나요? → " + t2.isAlive());  // false (끝남)
        System.out.println("둘 다 무사히 끝났죠? 같은 순서로 잡으니 순환 대기가 사라졌어요.");
    }
}
