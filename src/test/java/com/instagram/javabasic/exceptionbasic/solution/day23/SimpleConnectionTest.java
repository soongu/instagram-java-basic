package com.instagram.javabasic.exceptionbasic.solution.day23;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleConnectionTest {

    @Test
    @DisplayName("try-with-resources 블록을 정상으로 빠져나오면 연결이 닫힌다")
    void closesAfterNormalBlock() {
        SimpleConnection captured;

        try (SimpleConnection conn = new SimpleConnection()) {
            captured = conn;
            assertTrue(conn.isOpen());
            conn.query("SELECT 1");
        }

        assertFalse(captured.isOpen());
    }

    @Test
    @DisplayName("블록 안에서 예외가 나도 연결은 닫힌다")
    void closesEvenWhenExceptionThrown() {
        SimpleConnection captured = null;

        try (SimpleConnection conn = new SimpleConnection()) {
            captured = conn;
            conn.query("SELECT 1");
            throw new IllegalStateException("도중 사고!");
        } catch (IllegalStateException e) {
            // 사고를 받아도 close() 는 이미 자동으로 불린 뒤예요.
        }

        assertFalse(captured.isOpen());
    }

    @Test
    @DisplayName("닫힌 연결에 query 하면 IllegalStateException 을 던진다")
    void queryAfterCloseThrows() {
        SimpleConnection conn = new SimpleConnection();
        conn.close();

        assertThrows(IllegalStateException.class,
                () -> conn.query("SELECT 1"));
    }

    @Test
    @DisplayName("close() 를 부르면 isOpen 이 false 가 된다")
    void closeFlipsOpenFlag() {
        SimpleConnection conn = new SimpleConnection();

        conn.close();

        assertFalse(conn.isOpen());
    }
}
