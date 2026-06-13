package com.instagram.javabasic.io.solution.day38;

import java.io.Serializable;

// 과제 3 (심화) 예시 답안 — 객체 직렬화로 저장할 사용자 프로필이에요.
// username·bio 는 저장되지만, 민감한 password 는 transient 라 저장에서 빠져요.
// (그래서 불러오면 password 는 null 이에요 — 파일에 비밀번호를 남기지 않는 안전장치예요.)
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String username;
    private final String bio;

    // 민감 정보라 파일에 남기지 않아요 → 역직렬화 후 null 로 돌아와요.
    private transient String password;

    public UserProfile(String username, String bio, String password) {
        this.username = username;
        this.bio = bio;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String bio() {
        return bio;
    }

    public String password() {
        return password;
    }
}
