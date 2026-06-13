package com.instagram.javabasic.io.solution.day38;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ProfileStoreTest {

    private final ProfileStore store = new ProfileStore();

    @Test
    @DisplayName("프로필을 저장하고 불러오면 username·bio 는 복원되고 transient password 는 null 이다")
    void transientPasswordIsNotPersisted(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "profile.ser");
        UserProfile profile = new UserProfile("jaehoon", "사진 찍는 걸 좋아해요", "secret1234");

        // 저장 전에는 password 에 값이 있어요.
        assertNotNull(profile.password());

        store.save(file, profile);
        UserProfile loaded = store.load(file);

        assertEquals("jaehoon", loaded.username());
        assertEquals("사진 찍는 걸 좋아해요", loaded.bio());
        assertNull(loaded.password()); // transient 라 저장에서 빠져 null
    }
}
