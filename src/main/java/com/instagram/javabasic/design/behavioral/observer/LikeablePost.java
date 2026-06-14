package com.instagram.javabasic.design.behavioral.observer;

// com/instagram/javabasic/design/behavioral/observer/LikeablePost.java

import java.util.ArrayList;
import java.util.List;

// 좋아요를 받을 수 있는 게시물이에요. 자기에게 일어난 일을 관찰자들에게 알려주는 쪽이에요.
// 누가 구독하는지는 알지만, 그들이 무슨 일을 하는지는 신경 쓰지 않아요.
// 그래서 새 관찰자가 늘어나도 이 클래스는 그대로 둬도 돼요.
public class LikeablePost {

    private final String postId;
    // 이 게시물의 좋아요 사건을 구독 중인 관찰자들이에요.
    private final List<PostObserver> observers = new ArrayList<>();

    public LikeablePost(String postId) {
        this.postId = postId;
    }

    // 관찰자를 구독 목록에 등록해요.
    public void subscribe(PostObserver o) {
        observers.add(o);
    }

    // 관찰자를 구독 목록에서 빼요. 그 뒤로는 더 이상 통지받지 않아요.
    public void unsubscribe(PostObserver o) {
        observers.remove(o);
    }

    // 좋아요가 눌렸어요. 등록된 모든 관찰자에게 한 명씩 알려줘요.
    public void like(String likedBy) {
        for (PostObserver o : observers) {
            o.onLiked(postId, likedBy);
        }
    }
}
