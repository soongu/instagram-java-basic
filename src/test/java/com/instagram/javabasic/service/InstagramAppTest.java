package com.instagram.javabasic.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InstagramAppTest {

    // 데모 대본(transcript)에 그 문구로 시작하는 줄이 하나라도 있는지 확인하는 작은 도우미예요.
    private boolean hasLineStartingWith(List<String> lines, String prefix) {
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("runDemo 는 정상 흐름과 막히는 흐름을 모두 거쳐 대본을 남긴다")
    void runDemo_전체흐름() {
        List<String> transcript = new InstagramApp().runDemo();

        assertTrue(hasLineStartingWith(transcript, "가입 완료:"));
        assertTrue(hasLineStartingWith(transcript, "가입 거절(중복 이메일):"));
        assertTrue(hasLineStartingWith(transcript, "게시물 작성 완료:"));
        assertTrue(hasLineStartingWith(transcript, "작성 거절(캡션 규칙):"));
        assertTrue(hasLineStartingWith(transcript, "팔로우 완료:"));
        assertTrue(hasLineStartingWith(transcript, "팔로우 거절(회원 없음):"));
    }

    @Test
    @DisplayName("막히는 흐름이 있어도 데모는 끝까지 굴러가, 회원 2명·게시물 1개로 마무리된다")
    void runDemo_종료요약() {
        List<String> transcript = new InstagramApp().runDemo();
        assertTrue(hasLineStartingWith(transcript, "=== 데모 종료: 회원 2명, 게시물 1개"));
    }
}
