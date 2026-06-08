package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.instagram.javabasic.generic.ObjectBox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasicTryCatchTest {

    @Test
    @DisplayName("String 이 든 box 는 실제 이름을 그대로 돌려준다")
    void readUsernameSafely_정상() {
        BasicTryCatch reader = new BasicTryCatch();
        ObjectBox box = new ObjectBox();
        box.set("minji");
        assertEquals("minji", reader.readUsernameSafely(box));
    }

    @Test
    @DisplayName("잘못된 타입(Integer)이 든 box 는 예외를 잡아 기본값을 돌려준다")
    void readUsernameSafely_복구() {
        BasicTryCatch reader = new BasicTryCatch();
        ObjectBox box = new ObjectBox();
        box.set(42);
        assertEquals("(알 수 없음)", reader.readUsernameSafely(box));
    }
}
