package com.instagram.javabasic.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class RawByteStoreTest {

    private final RawByteStore store = new RawByteStore();

    @Test
    @DisplayName("쓴 바이트 배열과 읽은 바이트 배열이 완전히 똑같다(라운드트립)")
    void roundTripPreservesBytes(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "photo.bin");
        byte[] original = {0, 1, 2, 100, (byte) 200, (byte) 255, -1};

        store.writeBytes(file, original);
        byte[] loaded = store.readBytes(file);

        assertArrayEquals(original, loaded);
    }

    @Test
    @DisplayName("빈 바이트 배열을 쓰고 읽으면 길이 0으로 돌아온다")
    void emptyArrayRoundTrips(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "empty.bin");

        store.writeBytes(file, new byte[0]);
        byte[] loaded = store.readBytes(file);

        assertEquals(0, loaded.length);
    }
}
