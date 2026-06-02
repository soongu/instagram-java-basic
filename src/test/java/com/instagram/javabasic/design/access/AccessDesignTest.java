package com.instagram.javabasic.design.access;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 이 테스트는 Story 와 같은 패키지(design.access)에 둬요.
// 그래야 protected 로 막아둔 extend() 를 호출할 수 있어요.
// (다른 패키지였다면 protected 라서 부르지 못해요 — 그게 바로 정보 은닉이에요.)
class AccessDesignTest {

    // ===== public: 외부에 열어둔 API 는 어디서든 부를 수 있다 =====

    @Test
    @DisplayName("public view: 조회할 때마다 조회수가 1씩 늘어난다")
    void publicView_increasesViewCount() {
        Story story = new Story();

        story.view();
        story.view();
        story.view();

        // getStatus 는 public 이라 외부에서 상태를 읽을 수 있어요
        assertEquals("조회수 3 (활성)", story.getStatus());
    }

    // ===== protected: 같은 패키지에서는 호출할 수 있다 =====

    @Test
    @DisplayName("protected extend: 같은 패키지에서는 만료 연장을 호출할 수 있다")
    void protectedExtend_callableInSamePackage() {
        Story story = new Story();

        // 조회수를 한도까지 올려 만료 상태로 만들어요
        for (int i = 0; i < 100; i++) {
            story.view();
        }
        assertEquals("조회수 100 (만료)", story.getStatus());

        // protected extend() 를 같은 패키지에서 호출 — 만료를 연장해요
        story.extend();
        assertEquals("조회수 0 (활성)", story.getStatus());
    }
}
