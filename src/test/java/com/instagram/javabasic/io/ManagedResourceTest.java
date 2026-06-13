package com.instagram.javabasic.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManagedResourceTest {

    @Test
    @DisplayName("try-with-resources 블록이 끝나면 자원이 모두 닫힌다")
    void allResourcesClosedAfterBlock() {
        List<String> log = new ArrayList<>();

        try (ManagedResource a = new ManagedResource("A", log)) {
            assertTrue(a.isOpen());
        }

        assertTrue(log.contains("close:A"));
    }

    @Test
    @DisplayName("자원을 여러 개 열면 선언한 역순으로 close 된다")
    void closesInReverseOrderOfDeclaration() {
        List<String> log = new ArrayList<>();

        try (ManagedResource a = new ManagedResource("A", log);
             ManagedResource b = new ManagedResource("B", log)) {
            // 블록 안에서는 둘 다 열려 있다
            assertTrue(a.isOpen());
            assertTrue(b.isOpen());
        }

        // 연 순서: A, B / 닫힌 순서: B, A (역순)
        assertEquals(List.of("open:A", "open:B", "close:B", "close:A"), log);
    }

    @Test
    @DisplayName("본문에서 예외가 터져도 close 는 빠짐없이 실행되고 닫힘 상태가 된다")
    void closeRunsEvenWhenBodyThrows() {
        List<String> log = new ArrayList<>();
        ManagedResource[] holder = new ManagedResource[1];

        assertThrows(IllegalStateException.class, () -> {
            try (ManagedResource a = new ManagedResource("A", log)) {
                holder[0] = a;
                throw new IllegalStateException("작업 중 문제 발생");
            }
        });

        assertFalse(holder[0].isOpen());
        assertTrue(log.contains("close:A"));
    }

    @Test
    @DisplayName("본문 예외와 close 예외가 함께 나면, 본문 예외가 주 예외이고 close 예외는 억제된 예외로 따라붙는다")
    void closeExceptionBecomesSuppressed() {
        // close() 에서 예외를 던지는 자원
        class FailingResource implements AutoCloseable {
            @Override
            public void close() {
                throw new IllegalStateException("닫는 중 실패");
            }
        }

        RuntimeException primary = assertThrows(RuntimeException.class, () -> {
            try (FailingResource r = new FailingResource()) {
                throw new RuntimeException("본문 작업 실패");
            }
        });

        // 주 예외는 본문에서 던진 것
        assertEquals("본문 작업 실패", primary.getMessage());
        // close 에서 던진 예외는 억제된(suppressed) 예외로 따라붙는다
        assertEquals(1, primary.getSuppressed().length);
        assertEquals("닫는 중 실패", primary.getSuppressed()[0].getMessage());
    }
}
