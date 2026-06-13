package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AtomicityDemoTest {

    @Test
    @DisplayName("읽기와 쓰기 사이에 틈을 벌리면 두 번 증가시켜도 결과가 1이 된다 (갱신 손실)")
    void interleavingLosesOneIncrement() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            // 두 스레드가 똑같이 0을 읽도록 sleep 으로 충분히 틈을 벌렸기 때문에,
            // 결과는 2가 아니라 항상 1이다. 이건 어떤 환경에서도 재현되는 결정적 결과다.
            int result = AtomicityDemo.runInterleaved();
            assertEquals(1, result,
                    "읽기-쓰기 사이 틈 때문에 증가 하나가 사라져 결과가 1이어야 한다");
        });
    }
}
