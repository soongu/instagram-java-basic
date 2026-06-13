package com.instagram.javabasic.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

// com/instagram/javabasic/concurrent/VirtualThreadIntro.java
// 지금까지 우리가 만든 스레드는 전부 "플랫폼 스레드(platform thread)" 였어요.
// 운영체제(OS) 스레드 하나를 통째로 차지하는, 무거운 스레드죠.
//
// 자바 21부터는 "가상 스레드(virtual thread)" 라는 가벼운 스레드를 만들 수 있어요.
// 만드는 법은 두 가지예요.
//   - Thread.ofVirtual().start(작업)   : 빌더로 만들어 바로 시작
//   - Thread.startVirtualThread(작업)  : 위 표현의 짧은 버전 (한 줄로 만들고 시작)
//
// 어떤 스레드가 가상 스레드인지는 isVirtual() 로 물어볼 수 있어요. 가상이면 true 예요.
public class VirtualThreadIntro {

    // 가상 스레드 위에서 작업을 돌려보고, 그 작업이 정말 가상 스레드에서 실행됐는지 알려줘요.
    public static boolean runsOnVirtualThread() throws InterruptedException {
        AtomicBoolean wasVirtual = new AtomicBoolean(false);

        // ofVirtual() 로 가상 스레드를 만들어 start() 로 시작해요.
        Thread vt = Thread.ofVirtual().start(() -> {
            // 지금 이 작업을 돌리는 스레드가 가상인지 직접 물어봐요.
            wasVirtual.set(Thread.currentThread().isVirtual());
        });
        vt.join();   // 작업이 끝날 때까지 기다려요 (Day 33 에서 배운 join)

        return wasVirtual.get();
    }

    // 비교용. 전통적인 플랫폼 스레드는 isVirtual() 이 false 예요.
    public static boolean runsOnPlatformThread() throws InterruptedException {
        AtomicBoolean wasVirtual = new AtomicBoolean(true);

        // ofPlatform() 으로 만들면 예전과 똑같은 무거운 OS 스레드예요.
        Thread pt = Thread.ofPlatform().start(() -> {
            wasVirtual.set(Thread.currentThread().isVirtual());
        });
        pt.join();

        return wasVirtual.get();
    }

    // startVirtualThread 는 ofVirtual().start() 와 똑같지만 더 짧아요.
    public static boolean shortcutAlsoVirtual() throws InterruptedException {
        AtomicBoolean wasVirtual = new AtomicBoolean(false);

        Thread vt = Thread.startVirtualThread(() -> {
            wasVirtual.set(Thread.currentThread().isVirtual());
        });
        vt.join();

        return wasVirtual.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 가상 스레드 vs 플랫폼 스레드 ===");
        System.out.println("ofVirtual().start() 가 가상인가? → " + runsOnVirtualThread());
        System.out.println("ofPlatform().start() 가 가상인가? → " + runsOnPlatformThread());
        System.out.println("startVirtualThread() 가 가상인가? → " + shortcutAlsoVirtual());
    }
}
