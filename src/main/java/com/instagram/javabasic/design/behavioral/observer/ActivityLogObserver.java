package com.instagram.javabasic.design.behavioral.observer;

// com/instagram/javabasic/design/behavioral/observer/ActivityLogObserver.java

import java.util.ArrayList;
import java.util.List;

// 좋아요 사건이 날 때마다 기록을 한 줄씩 남기는 관찰자예요.
// 같은 사건을 받아도 자기 방식(로그 쌓기)으로 반응해요.
public class ActivityLogObserver implements PostObserver {

    private final List<String> logs = new ArrayList<>();

    @Override
    public void onLiked(String postId, String likedBy) {
        logs.add(postId + " liked by " + likedBy);
    }

    // 지금까지 쌓인 활동 기록을 돌려줘요.
    public List<String> getLogs() {
        return logs;
    }
}
