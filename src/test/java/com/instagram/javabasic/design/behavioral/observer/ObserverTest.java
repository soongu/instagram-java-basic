package com.instagram.javabasic.design.behavioral.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObserverTest {

    @Test
    @DisplayName("좋아요가 나면 등록된 모든 관찰자가 통지받는다")
    void notifiesAllSubscribers() {
        LikeablePost post = new LikeablePost("post-1");
        LikeCountObserver counter = new LikeCountObserver();
        NotificationObserver notifier = new NotificationObserver();
        ActivityLogObserver logger = new ActivityLogObserver();
        post.subscribe(counter);
        post.subscribe(notifier);
        post.subscribe(logger);

        post.like("minji");

        assertEquals(1, counter.getCount());
        assertTrue(notifier.getLastMessage().contains("minji"));
        assertEquals(1, logger.getLogs().size());
    }

    @Test
    @DisplayName("관찰자를 해지하면 더는 통지받지 않는다")
    void unsubscribedObserverStopsReceiving() {
        LikeablePost post = new LikeablePost("post-2");
        LikeCountObserver counter = new LikeCountObserver();
        post.subscribe(counter);

        post.like("jaehoon");
        assertEquals(1, counter.getCount());

        post.unsubscribe(counter);
        post.like("seungwoo");

        assertEquals(1, counter.getCount());
    }

    @Test
    @DisplayName("같은 사건에 관찰자마다 다르게 반응한다")
    void observersReactDifferently() {
        LikeablePost post = new LikeablePost("post-3");
        LikeCountObserver counter = new LikeCountObserver();
        NotificationObserver notifier = new NotificationObserver();
        post.subscribe(counter);
        post.subscribe(notifier);

        post.like("jiwoo");

        assertEquals(1, counter.getCount());
        assertEquals("jiwoo님이 회원님의 게시물을 좋아합니다", notifier.getLastMessage());
    }
}
