package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowingListTest {

    @Test
    @DisplayName("배열 한계1 해소: 크기 제한 없이 네 명 이상 담긴다")
    void noCapacityLimit() {
        FollowingList me = new FollowingList();
        me.addFollowing("minji");
        me.addFollowing("seungwoo");
        me.addFollowing("jaehoon");
        me.addFollowing("yuna"); // 예전 크기 3 배열이면 막혔을 자리

        assertEquals(4, me.getFollowingCount());
    }

    @Test
    @DisplayName("배열 한계2 해소: 중간 삭제 시 빈칸 없이 자동으로 메워진다")
    void removeAutoShifts() {
        FollowingList me = new FollowingList();
        me.addFollowing("minji");
        me.addFollowing("seungwoo");
        me.addFollowing("jaehoon");

        me.removeFollowing("seungwoo");

        assertEquals(2, me.getFollowingCount());
        assertTrue(me.isFollowing("minji"));
        assertTrue(me.isFollowing("jaehoon"));
        assertFalse(me.isFollowing("seungwoo"));
    }

    @Test
    @DisplayName("배열 한계3 해소: size() 가 인원을 자동으로 센다")
    void sizeIsAutomatic() {
        FollowingList me = new FollowingList();
        assertEquals(0, me.getFollowingCount());
        me.addFollowing("minji");
        assertEquals(1, me.getFollowingCount());
    }
}
