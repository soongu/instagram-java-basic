package com.instagram.javabasic.io.solution.day38;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 과제 3 (심화) 예시 답안 — 프로필 객체를 통째로 저장하고 복원해요.
// ObjectOutputStream/ObjectInputStream 으로 직렬화하면, transient 필드는 자동으로 빠져요.
public class ProfileStore {

    // 프로필 객체를 통째로 파일에 저장해요.
    public void save(File file, UserProfile profile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(file))) {
            out.writeObject(profile);
        }
    }

    // 파일에서 프로필 객체를 되살려 돌려줘요.
    public UserProfile load(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file))) {
            return (UserProfile) in.readObject();
        }
    }
}
