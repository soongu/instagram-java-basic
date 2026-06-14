package com.instagram.javabasic.design.behavioral.solution.day41;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowableUserTest {

    @Test
    @DisplayName("팔로우가 나면 구독한 관찰자들이 각자 반응한다")
    void notifyAllObservers() {
        FollowableUser user = new FollowableUser();
        FollowerCountObserver counter = new FollowerCountObserver();
        FollowNotificationObserver notifier = new FollowNotificationObserver();
        user.subscribe(counter);
        user.subscribe(notifier);

        user.follow("jaehoon");

        // 같은 사건을 받았지만, 횟수를 세는 쪽과 알림을 만드는 쪽이 각자 반응해요.
        assertEquals(1, counter.getCount());
        assertTrue(notifier.getLastMessage().contains("jaehoon"));
    }

    @Test
    @DisplayName("관찰자를 해지하면 더는 통지받지 않는다")
    void unsubscribeStopsNotification() {
        FollowableUser user = new FollowableUser();
        FollowerCountObserver counter = new FollowerCountObserver();
        user.subscribe(counter);

        user.follow("minji");
        // 한 번 통지받은 뒤 구독을 끊어요.
        user.unsubscribe(counter);
        user.follow("seungwoo");

        // 해지 이후의 팔로우는 세지 않으니 count 는 1 에서 멈춰요.
        assertEquals(1, counter.getCount());
    }

    @Test
    @DisplayName("팔로우 알림 문구가 정확히 만들어진다")
    void exactMessage() {
        FollowableUser user = new FollowableUser();
        FollowNotificationObserver notifier = new FollowNotificationObserver();
        user.subscribe(notifier);

        user.follow("jaehoon");

        assertEquals("jaehoon님이 회원님을 팔로우하기 시작했습니다", notifier.getLastMessage());
    }
}
