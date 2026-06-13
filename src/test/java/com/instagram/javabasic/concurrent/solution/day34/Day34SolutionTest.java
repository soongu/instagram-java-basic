package com.instagram.javabasic.concurrent.solution.day34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day34SolutionTest {

    private static final int THREADS = 50;
    private static final int PER_THREAD = 2000;
    private static final int EXPECTED = THREADS * PER_THREAD;  // 100,000

    @Test
    @DisplayName("[과제 1] 보호 없는 카운터는 동시에 부르면 갱신이 사라져 합계가 기대값을 넘지 못하고, 손실이 관찰된다")
    void 경쟁_상태_손실_관찰() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            boolean lossSeenAtLeastOnce = false;

            // 경쟁 상태는 "타이밍" 에 좌우돼서 한 번엔 운 좋게 안 깨질 수 있어요.
            // 그래서 워크로드를 여러 번 돌려 "적어도 한 번은 손실이 보인다" 만 단정해요.
            for (int rep = 0; rep < 5; rep++) {
                int actual = FollowerCounterRace.runWorkload(THREADS, PER_THREAD);

                // 더해지기만 하므로 기대값을 "넘는" 일은 절대 없어요 (이건 항상 참).
                assertTrue(actual <= EXPECTED,
                        "보호 없는 카운터라도 합계가 기대값을 넘으면 안 돼요: actual=" + actual);

                if (actual < EXPECTED) {
                    lossSeenAtLeastOnce = true;
                }
            }

            assertTrue(lossSeenAtLeastOnce,
                    "5회 반복 동안 경쟁 상태로 인한 손실(count < " + EXPECTED + ")이 한 번도 관찰되지 않았어요");
        });
    }

    @Test
    @DisplayName("[과제 2] synchronized 로 봉인한 조회수 카운터는 동시에 눌러도 항상 정확히 100,000이다")
    void synchronized_정확한_합계() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // synchronized 라 결과가 결정적이에요. 여러 번 돌려도 매번 정확히 기대값이 나와요.
            for (int rep = 0; rep < 5; rep++) {
                int actual = ViewCounter.runWorkload(THREADS, PER_THREAD);
                assertTrue(actual == EXPECTED,
                        "synchronized 카운터는 항상 정확해야 해요: expected=" + EXPECTED + ", actual=" + actual);
            }
        });
    }

    @Test
    @DisplayName("[과제 3] 락 순서가 엇갈린 데드락 버전은 두 스레드가 둘 다 멈춰(isAlive=true) 끝나지 않는다")
    void 데드락_발생_두_스레드_멈춤() {
        // 데몬 스레드라 테스트가 끝나면 함께 정리돼요. assertTimeoutPreemptively 로 leak 까지 막아요.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            PointTransferDeadlock demo = new PointTransferDeadlock();
            PointTransferDeadlock.Account a = new PointTransferDeadlock.Account(1, 1000);
            PointTransferDeadlock.Account b = new PointTransferDeadlock.Account(2, 1000);

            // from·to 를 엇갈리게 줘서 락 순서를 엇갈리게 만들어요 (1→2 vs 2→1).
            Thread t1 = demo.makeDeadlockThread("송금1", a, b, 100);
            Thread t2 = demo.makeDeadlockThread("송금2", b, a, 100);

            t1.start();
            t2.start();

            // 충분히 기다려도 데드락이라 안 끝나요. join 타임아웃 뒤 둘 다 여전히 살아있어야 해요.
            t1.join(1000);
            t2.join(1000);

            assertTrue(t1.isAlive(), "데드락이라면 송금1 스레드가 멈춰 있어야 해요");
            assertTrue(t2.isAlive(), "데드락이라면 송금2 스레드가 멈춰 있어야 해요");
        });
    }

    @Test
    @DisplayName("[과제 3] 락 순서를 통일한 해결 버전은 두 스레드가 둘 다 정상 종료(isAlive=false)된다")
    void 데드락_해결_두_스레드_종료() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            PointTransferDeadlock demo = new PointTransferDeadlock();
            PointTransferDeadlock.Account a = new PointTransferDeadlock.Account(1, 1000);
            PointTransferDeadlock.Account b = new PointTransferDeadlock.Account(2, 1000);

            // 같은 엇갈린 송금이지만, 두 스레드 모두 id 작은 계정부터 잡으니 데드락이 안 나요.
            Thread t1 = demo.makeOrderedThread("송금1", a, b, 100);
            Thread t2 = demo.makeOrderedThread("송금2", b, a, 100);

            t1.start();
            t2.start();

            t1.join(2000);
            t2.join(2000);

            assertFalse(t1.isAlive(), "락 순서를 통일했으면 송금1 스레드가 끝나야 해요");
            assertFalse(t2.isAlive(), "락 순서를 통일했으면 송금2 스레드가 끝나야 해요");
        });
    }
}
