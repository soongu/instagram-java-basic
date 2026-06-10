package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageUploadSessionTest {

    @Test
    @DisplayName("정상 경로: try-with-resources 블록이 끝나면 close 가 자동 호출된다")
    void 정상경로_자동close() {
        ImageUploadSession session = new ImageUploadSession("피드");
        try (session) {
            session.upload("cat.jpg");
            assertTrue(session.isOpen());
        }
        assertFalse(session.isOpen());
    }

    @Test
    @DisplayName("예외 경로: 블록 도중 예외가 터져도 close 가 자동 호출된다")
    void 예외경로_자동close() {
        ImageUploadSession session = new ImageUploadSession("피드");
        assertThrows(InvalidCaptionException.class, () -> {
            try (session) {
                session.upload("cat.jpg");
                throw new InvalidCaptionException("캡션은 2200자까지만 쓸 수 있어요.");
            }
        });
        assertFalse(session.isOpen());
    }

    @Test
    @DisplayName("닫힌 세션에 업로드하면 IllegalStateException 이 던져진다")
    void 닫힌세션_업로드금지() {
        ImageUploadSession session = new ImageUploadSession("피드");
        session.close();
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> session.upload("dog.jpg"));
        assertEquals("[피드] 이미 닫힌 세션이에요. 업로드할 수 없어요.", e.getMessage());
    }

    @Test
    @DisplayName("자원 2개를 함께 열면 나중에 연 것부터 역순으로 닫힌다")
    void 자원2개_역순close() {
        PrintStream original = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
        try {
            try (ImageUploadSession first = new ImageUploadSession("스토리");
                    ImageUploadSession second = new ImageUploadSession("피드")) {
                first.upload("sunset.jpg");
                second.upload("coffee.jpg");
            }
        } finally {
            System.setOut(original);
        }
        String printed = captured.toString();
        int closeSecond = printed.indexOf("[피드] 업로드 세션을 닫았어요.");
        int closeFirst = printed.indexOf("[스토리] 업로드 세션을 닫았어요.");
        assertTrue(closeSecond >= 0, "두 번째 자원의 close 출력이 있어야 해요");
        assertTrue(closeFirst >= 0, "첫 번째 자원의 close 출력이 있어야 해요");
        assertTrue(closeSecond < closeFirst, "나중에 연 [피드] 가 먼저 닫혀야 해요");
    }
}
