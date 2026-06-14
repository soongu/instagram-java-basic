package com.instagram.javabasic.design.creational.solution.day40;

// com/instagram/javabasic/design/creational/solution/day40/AppConfig.java
// 과제 1 — 앱 전체가 함께 쓰는 설정을 담는 Singleton 이에요.
// enum 으로 만들면 자바가 INSTANCE 하나만 만들어줘서 "딱 하나" 가 저절로 보장돼요.
public enum AppConfig {

    INSTANCE;

    // 한 게시물에 올릴 수 있는 최대 사진 수 — 앱 전체가 같은 값을 써야 해요.
    private final int maxPhotosPerPost = 10;

    // 앱 버전 — 역시 어디서나 같은 값이어야 해요.
    private final String appVersion = "1.0.0";

    public int getMaxPhotosPerPost() {
        return maxPhotosPerPost;
    }

    public String getAppVersion() {
        return appVersion;
    }
}
