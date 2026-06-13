package com.instagram.javabasic.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TextCaptionStoreTest {

    private final TextCaptionStore store = new TextCaptionStore();

    @Test
    @DisplayName("한글·이모지 캡션이 깨지지 않고 그대로 라운드트립된다")
    void koreanCaptionRoundTripsWithoutCorruption(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "caption.txt");
        String caption = "안녕하세요 🌙 첫 게시물";

        store.writeText(file, caption);
        String loaded = store.readText(file);

        assertEquals(caption, loaded);
    }

    @Test
    @DisplayName("UTF-8에서 한글이 섞이면 바이트 수가 글자 수보다 크다")
    void byteLengthExceedsCharLengthForKorean() {
        String caption = "사진"; // 글자 2개

        // 한글 1자 = UTF-8 3바이트 → 2글자면 6바이트
        assertEquals(2, caption.length());
        assertEquals(6, store.byteLength(caption));
        assertTrue(store.byteLength(caption) > caption.length());
    }

    @Test
    @DisplayName("영문·숫자만 있으면 글자 수와 바이트 수가 같다")
    void asciiByteLengthEqualsCharLength() {
        String caption = "post42";

        assertEquals(caption.length(), store.byteLength(caption));
    }
}
