package com.instagram.javabasic.io;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/io/ManagedResource.java
// 직접 만든 "자동으로 닫히는 자원" 이에요. AutoCloseable 을 구현하면
// try-with-resources 의 소괄호 안에 넣을 수 있고, 블록이 끝나면 close() 가 자동으로 불려요.
//
// 자원을 여러 개 열면 어떤 순서로 닫힐까요? "선언한 역순" 으로 닫혀요.
// 마지막에 연 것부터 닫는 거예요 — 옷을 입은 역순으로 벗는 것과 같아요.
// 그 순서를 눈으로 보려고, 열릴 때와 닫힐 때 공유 로그에 이름을 남겨둬요.
public class ManagedResource implements AutoCloseable {

    private final String name;
    private final List<String> log;
    private boolean open;

    // 생성되는 순간 "열림" 으로 표시하고, 로그에 "open:이름" 을 남겨요.
    public ManagedResource(String name, List<String> log) {
        this.name = name;
        this.log = log;
        this.open = true;
        log.add("open:" + name);
    }

    public boolean isOpen() {
        return open;
    }

    // try 블록이 끝나면 자동으로 불려요. "닫힘" 으로 바꾸고 로그에 "close:이름" 을 남겨요.
    @Override
    public void close() {
        this.open = false;
        log.add("close:" + name);
    }

    public static void main(String[] args) {
        List<String> log = new ArrayList<>();

        // A, B 순서로 열면 → 닫힐 때는 B, A 순서(역순)예요.
        try (ManagedResource a = new ManagedResource("A", log);
             ManagedResource b = new ManagedResource("B", log)) {
            System.out.println("블록 안: A 열림? " + a.isOpen() + ", B 열림? " + b.isOpen());
        }

        System.out.println("기록된 순서 → " + log);
        // [open:A, open:B, close:B, close:A]
    }
}
