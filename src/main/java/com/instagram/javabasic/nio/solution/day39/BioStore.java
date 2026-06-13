package com.instagram.javabasic.nio.solution.day39;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// com/instagram/javabasic/nio/solution/day39/BioStore.java
// 과제 1 (기초): 프로필 소개글을 한 줄로 저장하고 불러오기.
//
// 지난 시간 BufferedWriter/BufferedReader 로 대여섯 줄이 필요했던 일을
// Files.writeString / readString 한 줄씩으로 끝내요. 인코딩은 기본값 UTF-8 이라 따로 안 줘요.
public class BioStore {

    // 소개글을 파일에 통째로 써요. (한 줄)
    public void save(Path file, String bio) throws IOException {
        Files.writeString(file, bio);
    }

    // 파일 전체를 문자열 하나로 읽어요. (한 줄)
    public String load(Path file) throws IOException {
        return Files.readString(file);
    }
}
