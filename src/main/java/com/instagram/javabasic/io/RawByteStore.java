package com.instagram.javabasic.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// com/instagram/javabasic/io/RawByteStore.java
// 파일에 "바이트(byte) 그대로" 를 쓰고 읽는 가장 기본적인 방법이에요.
//
//   FileOutputStream : 파일로 바이트를 흘려보내는(쓰는) 수도꼭지예요.
//   FileInputStream  : 파일에서 바이트를 길어오는(읽는) 수도꼭지예요.
//
// 사진·동영상처럼 글자가 아닌 데이터는 이렇게 바이트 단위로 다뤄요.
// 파일 위치는 java.io.File 로 가리켜요 (파일을 가리키는 주소라고 보면 돼요).
// 스트림은 다 쓰고 나면 반드시 닫아줘야 하는데, try-with-resources 가 자동으로 닫아줘요.
public class RawByteStore {

    // 바이트 배열을 통째로 파일에 써요. try-with-resources 가 끝나면 알아서 닫혀요.
    public void writeBytes(File file, byte[] data) throws IOException {
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data);
        }
    }

    // 파일에 든 바이트를 처음부터 끝까지 전부 읽어 돌려줘요.
    public byte[] readBytes(File file) throws IOException {
        try (FileInputStream in = new FileInputStream(file)) {
            return in.readAllBytes();
        }
    }

    public static void main(String[] args) throws IOException {
        RawByteStore store = new RawByteStore();
        File file = new File(System.getProperty("java.io.tmpdir"), "raw-demo.bin");

        byte[] original = {10, 20, 30, 40, 50};
        store.writeBytes(file, original);

        byte[] loaded = store.readBytes(file);
        System.out.println("쓴 바이트 수   → " + original.length);  // 5
        System.out.println("읽은 바이트 수 → " + loaded.length);    // 5
        System.out.println("첫 바이트      → " + loaded[0]);         // 10
    }
}
