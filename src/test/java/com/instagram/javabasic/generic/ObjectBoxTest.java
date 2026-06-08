package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObjectBoxTest {

    @Test
    @DisplayName("올바른 타입으로 형변환하면 값을 그대로 꺼낸다")
    void correctCastReturnsValue() {
        ObjectBox box = new ObjectBox();
        box.set("minji");

        String username = (String) box.get();

        assertEquals("minji", username);
    }

    @Test
    @DisplayName("잘못된 타입으로 형변환하면 실행 중에 ClassCastException 이 터진다")
    void wrongCastThrowsClassCastException() {
        ObjectBox box = new ObjectBox();
        box.set("minji");

        assertThrows(ClassCastException.class, () -> {
            Integer wrong = (Integer) box.get();
            wrong.intValue();
        });
    }

    @Test
    @DisplayName("Object 그릇은 서로 다른 타입을 차례로 담을 수 있다")
    void canHoldAnyType() {
        ObjectBox box = new ObjectBox();

        box.set(42);
        assertEquals(42, (Integer) box.get());

        box.set("jaehoon");
        assertEquals("jaehoon", (String) box.get());
    }
}
