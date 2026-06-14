package com.instagram.javabasic.design.behavioral.observer;

// com/instagram/javabasic/design/behavioral/observer/PostObserver.java
// 게시물의 좋아요 사건을 구독하는 쪽이 지켜야 할 약속이에요.
// 좋아요가 눌리면 게시물이 이 메서드를 불러서 "방금 이런 일이 있었어요" 라고 알려줘요.
// 무엇을 할지는 약속을 지키는 각 구현 클래스가 알아서 정해요.
public interface PostObserver {

    // 게시물에 좋아요가 눌리면 호출돼요.
    // postId 는 어떤 게시물인지, likedBy 는 누가 눌렀는지예요.
    void onLiked(String postId, String likedBy);
}
