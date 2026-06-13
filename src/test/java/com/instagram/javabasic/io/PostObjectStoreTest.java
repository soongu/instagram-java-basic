package com.instagram.javabasic.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PostObjectStoreTest {

    private final PostObjectStore store = new PostObjectStore();

    @Test
    @DisplayName("record 객체를 통째로 저장하고 불러오면 모든 필드가 그대로 복원된다")
    void recordRoundTripRestoresObject(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "post.ser");
        Post post = new Post(7L, "seungwoo", "객체 통째로 저장!");

        store.save(file, post);
        Post loaded = store.load(file);

        assertEquals(post, loaded);
        assertEquals(7L, loaded.id());
        assertEquals("seungwoo", loaded.author());
        assertEquals("객체 통째로 저장!", loaded.caption());
    }

    @Test
    @DisplayName("저장 전에는 transient 필드(displayLabel)에 값이 있다")
    void transientFieldHasValueBeforeSave() {
        CachedPost cached = new CachedPost(8L, "minji", "캐시 값");

        assertNotNull(cached.displayLabel());
    }

    @Test
    @DisplayName("transient 로 표시한 필드는 직렬화에서 빠져 역직렬화 후 null 로 돌아온다")
    void transientFieldNotRestored(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "cached.ser");
        CachedPost cached = new CachedPost(8L, "minji", "캐시 값이 빠지는 모습");

        store.save(file, cached);
        CachedPost loaded = store.load(file);

        // 일반 필드는 복원되지만 transient 필드는 복원되지 않는다
        assertEquals(8L, loaded.id());
        assertEquals("minji", loaded.author());
        assertEquals("캐시 값이 빠지는 모습", loaded.caption());
        assertNull(loaded.displayLabel());
    }
}
