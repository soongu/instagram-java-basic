package com.instagram.javabasic.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// com/instagram/javabasic/io/PostObjectStore.java
// 게시물 객체를 "통째로" 파일에 저장하고 그대로 불러와요.
//
//   ObjectOutputStream : 객체를 바이트로 바꿔 파일에 써요(직렬화).
//   ObjectInputStream  : 파일의 바이트를 다시 객체로 되살려요(역직렬화).
//
// 손으로 한 줄씩 만들 필요 없이 자바가 알아서 모양을 기억했다가 복원해줘요.
// 단, transient 로 표시한 값은 저장에서 빠지므로, 불러오면 비어 있어요(객체 참조면 null).
public class PostObjectStore {

    // 게시물 객체 하나를 통째로 파일에 저장해요.
    public void save(File file, Serializable post) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(file))) {
            out.writeObject(post);
        }
    }

    // 파일에서 객체를 되살려 돌려줘요. (불러올 타입은 호출하는 쪽이 알고 있어요.)
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T load(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file))) {
            return (T) in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PostObjectStore store = new PostObjectStore();
        File file = new File(System.getProperty("java.io.tmpdir"), "post-object-demo.ser");

        // 1) record 도 Serializable 이면 통째로 저장·복원돼요.
        Post post = new Post(7L, "seungwoo", "객체 통째로 저장!");
        store.save(file, post);
        Post loadedPost = store.load(file);
        System.out.println("불러온 게시물 → " + loadedPost); // id·author·caption 그대로

        // 2) transient 필드는 저장에서 빠져서, 불러오면 null 이에요.
        CachedPost cached = new CachedPost(8L, "minji", "캐시 값이 빠지는 모습");
        System.out.println("저장 전 displayLabel → " + cached.displayLabel()); // 값 있음
        store.save(file, cached);
        CachedPost loadedCached = store.load(file);
        System.out.println("불러온 author        → " + loadedCached.author());       // minji
        System.out.println("불러온 displayLabel  → " + loadedCached.displayLabel()); // null
    }
}
