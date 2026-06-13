package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/DeadlockDemo.java
// 데드락(deadlock, 교착 상태) 을 일부러 일으켜 보는 데모예요.
//
// 데드락은 두 스레드가 서로가 쥐고 있는 자물쇠를 기다리느라 둘 다 영영 못 움직이는 상태예요.
// 좁은 골목에서 두 차가 마주 보고 멈춰서, 서로 상대가 먼저 비켜주길 기다리는 모습과 똑같아요.
//
// 여기서는 자물쇠 두 개(lockA, lockB) 를 두고,
//   스레드1 은 A 를 먼저 잡고 → 그다음 B 를 잡으려 하고
//   스레드2 는 B 를 먼저 잡고 → 그다음 A 를 잡으려 해요.
// 첫 자물쇠를 잡은 뒤 sleep 으로 잠깐 틈을 주면, 둘 다 첫 자물쇠는 쥐었는데
// 두 번째 자물쇠는 상대가 쥐고 있어서 영영 못 잡아요. 그렇게 둘 다 멈춰버려요 (결정적 재현).
//
// ⚠️ 데드락에 빠진 스레드는 스스로 끝나지 못해요. 그래서 이 스레드들을 데몬(daemon) 스레드로
//    만들어 둬요. 데몬 스레드는 main 이 끝나면 JVM 과 함께 자동으로 정리되는 일꾼이라,
//    프로그램이나 테스트가 그 스레드 때문에 영영 안 끝나거나 자원이 새는 일을 막아줘요.
public class DeadlockDemo {

    private final Object lockA = new Object();
    private final Object lockB = new Object();

    // A → B 순서로 자물쇠를 잡으려는 스레드.
    public Thread makeThreadAB() {
        Thread t = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("[AB] lockA 잡음, 이제 lockB 를 기다려요");
                sleepQuietly(100);  // 상대가 lockB 를 먼저 잡을 틈을 준다
                synchronized (lockB) {  // 여기서 영영 못 들어가요 (상대가 lockB 를 쥐고 있음)
                    System.out.println("[AB] lockB 도 잡음 (여기는 도달 못 해요)");
                }
            }
        });
        t.setDaemon(true);  // main 이 끝나면 함께 정리되는 일꾼으로 만든다
        return t;
    }

    // B → A 순서로 자물쇠를 잡으려는 스레드 (순서가 반대라 순환 대기가 생겨요).
    public Thread makeThreadBA() {
        Thread t = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("[BA] lockB 잡음, 이제 lockA 를 기다려요");
                sleepQuietly(100);  // 상대가 lockA 를 먼저 잡을 틈을 준다
                synchronized (lockA) {  // 여기서 영영 못 들어가요 (상대가 lockA 를 쥐고 있음)
                    System.out.println("[BA] lockA 도 잡음 (여기는 도달 못 해요)");
                }
            }
        });
        t.setDaemon(true);
        return t;
    }

    // sleep 의 try-catch 를 깔끔히 감싼 도우미 (틈을 만드는 용도).
    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockDemo demo = new DeadlockDemo();
        Thread ab = demo.makeThreadAB();
        Thread ba = demo.makeThreadBA();

        System.out.println("=== 락 획득 순서가 엇갈려 데드락 발생 ===");
        ab.start();
        ba.start();

        // 잠깐 기다려 보고, 두 스레드가 여전히 살아(멈춰) 있는지 확인한다.
        ab.join(2000);
        ba.join(2000);

        System.out.println();
        System.out.println("[AB] 아직 살아있나요? → " + ab.isAlive());  // true (멈춤)
        System.out.println("[BA] 아직 살아있나요? → " + ba.isAlive());  // true (멈춤)
        System.out.println("둘 다 첫 자물쇠는 쥐었는데 두 번째를 서로 기다리느라 멈췄어요. 이게 데드락이에요.");
    }
}
