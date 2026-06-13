package com.instagram.javabasic.concurrent.solution.day34;

// com/instagram/javabasic/concurrent/solution/day34/PointTransferDeadlock.java
// [과제 3] 포인트를 주고받는 두 계정 사이에서 데드락(deadlock, 교착 상태) 을 일으키고,
//          락을 잡는 "순서를 통일" 해서 그 데드락을 고쳐 봐요.
//
// 데드락은 두 스레드가 서로가 쥐고 있는 자물쇠를 기다리느라 둘 다 영영 못 움직이는 상태예요.
// 좁은 골목에서 두 차가 마주 보고 멈춰, 서로 먼저 비켜주길 기다리는 모습과 똑같아요.
//
// 여기서는 계정 객체 자체를 자물쇠로 써요 (synchronized (account)). 포인트를 옮기려면
// "보내는 계정" 과 "받는 계정" 두 개의 자물쇠를 모두 잡아야 한다고 상상해 봐요.
//   스레드1: A → B 순서로 잡으려 하고
//   스레드2: B → A 순서로 잡으려 해요.
// 첫 자물쇠를 잡은 뒤 sleep 으로 잠깐 틈을 주면, 둘 다 첫 자물쇠는 쥐었는데 두 번째는 상대가
// 쥐고 있어서 영영 못 잡아요. 그렇게 둘 다 멈춰버려요 (결정적 재현).
//
// ⚠️ 데드락에 빠진 스레드는 스스로 끝나지 못해요. 그래서 데몬(daemon) 스레드로 만들어 둬요.
//    데몬 스레드는 main 이 끝나면 JVM 과 함께 자동으로 정리되는 일꾼이라, 프로그램이나 테스트가
//    멈춘 스레드 때문에 영영 안 끝나거나 자원이 새는 일을 막아줘요.
public class PointTransferDeadlock {

    // 포인트를 담는 계정. 객체 자체를 자물쇠로 쓰고, 락 순서를 정하려고 id 를 둬요.
    public static class Account {
        private final int id;
        private int point;

        public Account(int id, int point) {
            this.id = id;
            this.point = point;
        }

        public int getId() {
            return id;
        }

        public int getPoint() {
            return point;
        }

        public void deposit(int amount) {
            this.point += amount;
        }

        public void withdraw(int amount) {
            this.point -= amount;
        }
    }

    // ── 데드락 버전 ───────────────────────────────────────────────────────────
    // from → to 순서 그대로 자물쇠를 잡아요. 두 스레드에 from·to 를 엇갈리게 주면 데드락이 나요.

    public Thread makeDeadlockThread(String name, Account from, Account to, int amount) {
        Thread t = new Thread(() -> {
            synchronized (from) {  // 받은 순서 그대로 from 을 먼저 잡아요
                System.out.println("[" + name + "] " + from.getId() + "번 계정 잡음, "
                        + to.getId() + "번 계정을 기다려요");
                sleepQuietly(100);  // 상대가 반대쪽 자물쇠를 먼저 잡을 틈을 준다
                synchronized (to) {  // 여기서 영영 못 들어가요 (상대가 to 를 쥐고 있음)
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println("[" + name + "] 송금 완료 (여기는 도달 못 해요)");
                }
            }
        });
        t.setDaemon(true);  // main 이 끝나면 함께 정리되는 일꾼으로 만든다
        return t;
    }

    // ── 해결 버전 ─────────────────────────────────────────────────────────────
    // 두 스레드 모두 "id 가 작은 계정부터" 같은 순서로 잡아요. 그러면 순환 대기가 사라져요.

    public Thread makeOrderedThread(String name, Account from, Account to, int amount) {
        Thread t = new Thread(() -> {
            // from·to 를 받은 순서와 상관없이, 항상 id 가 작은 쪽을 first 로 정해요.
            Account first = from.getId() < to.getId() ? from : to;
            Account second = from.getId() < to.getId() ? to : from;

            synchronized (first) {        // 항상 id 가 작은 쪽을 먼저
                System.out.println("[" + name + "] " + first.getId() + "번 계정 잡음");
                sleepQuietly(50);
                synchronized (second) {   // 그다음 id 가 큰 쪽
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println("[" + name + "] 송금 완료 → 두 자물쇠 모두 놓음");
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
        PointTransferDeadlock demo = new PointTransferDeadlock();

        // 1) 데드락 버전 — from·to 를 엇갈리게 줘서 락 순서가 엇갈리게 만든다.
        Account a1 = new Account(1, 1000);
        Account b1 = new Account(2, 1000);
        Thread d1 = demo.makeDeadlockThread("송금1", a1, b1, 100);  // 1 → 2 순서
        Thread d2 = demo.makeDeadlockThread("송금2", b1, a1, 100);  // 2 → 1 순서 (반대!)

        System.out.println("=== 락 순서가 엇갈려 데드락 발생 ===");
        d1.start();
        d2.start();
        d1.join(2000);  // 잠깐 기다려 보고
        d2.join(2000);
        System.out.println();
        System.out.println("[송금1] 아직 살아있나요? → " + d1.isAlive());  // true (멈춤)
        System.out.println("[송금2] 아직 살아있나요? → " + d2.isAlive());  // true (멈춤)
        System.out.println("둘 다 첫 자물쇠는 쥐었는데 두 번째를 서로 기다리느라 멈췄어요. 이게 데드락이에요.");
        System.out.println();

        // 2) 해결 버전 — 같은 엇갈린 송금이지만 id 작은 계정부터 잡으니 데드락이 안 난다.
        Account a2 = new Account(1, 1000);
        Account b2 = new Account(2, 1000);
        Thread f1 = demo.makeOrderedThread("송금1", a2, b2, 100);  // 1 → 2 의도
        Thread f2 = demo.makeOrderedThread("송금2", b2, a2, 100);  // 2 → 1 의도지만 잡는 순서는 통일

        System.out.println("=== 락 순서를 'id 작은 쪽부터' 로 통일해 데드락 해결 ===");
        f1.start();
        f2.start();
        f1.join(2000);
        f2.join(2000);
        System.out.println();
        System.out.println("[송금1] 아직 살아있나요? → " + f1.isAlive());  // false (끝남)
        System.out.println("[송금2] 아직 살아있나요? → " + f2.isAlive());  // false (끝남)
        System.out.println("둘 다 무사히 끝났죠? 같은 순서로 잡으니 순환 대기가 사라졌어요.");
    }
}
