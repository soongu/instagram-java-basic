package com.instagram.javabasic.design.behavioral.observer;

// com/instagram/javabasic/design/behavioral/observer/LikeCountObserver.java
// 좋아요 사건이 날 때마다 숫자를 하나씩 세는 관찰자예요.
// 같은 사건을 받아도 자기 방식(숫자 세기)으로만 반응해요.
public class LikeCountObserver implements PostObserver {

    private int count;

    @Override
    public void onLiked(String postId, String likedBy) {
        count++;
    }

    // 지금까지 센 좋아요 개수를 돌려줘요.
    public int getCount() {
        return count;
    }
}
