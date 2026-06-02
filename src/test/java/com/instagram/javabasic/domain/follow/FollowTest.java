package com.instagram.javabasic.domain.follow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.instagram.javabasic.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowTest {

    @Test
    @DisplayName("Follow 는 두 Member 를 follower→followee 관계로 묶는다")
    void follow_linksTwoMembers() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);

        Follow follow = new Follow(jaehoon, minji);

        assertSame(jaehoon, follow.getFollower());
        assertSame(minji, follow.getFollowee());
    }

    @Test
    @DisplayName("toString: @follower → @followee 형식으로 출력된다")
    void toString_showsArrowBetweenUsernames() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);

        Follow follow = new Follow(jaehoon, minji);

        assertEquals("@jaehoon_dev → @minji_cafe", follow.toString());
    }
}
