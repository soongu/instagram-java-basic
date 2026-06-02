package com.instagram.javabasic.design.srp;

// com/instagram/javabasic/design/srp/Account.java
// 좋은 예 (1/2) — "계정 인증" 이라는 한 가지 일만 맡아요.
// BigMember 에 뒤섞여 있던 책임 중에서 인증 부분만 떼어냈어요.
// 비밀번호가 바뀌어야 할 때 우리는 이 클래스 하나만 들여다보면 돼요.
public class Account {

    private String username;
    private String password;   // 비밀번호는 숨겨서 외부에서 직접 못 읽어요

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 이 클래스의 단 하나의 책임 — 입력한 비밀번호가 맞는지 확인해요
    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public String getUsername() {
        return username;
    }
}
