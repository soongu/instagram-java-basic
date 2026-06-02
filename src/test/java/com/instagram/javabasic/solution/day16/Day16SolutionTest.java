package com.instagram.javabasic.solution.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.instagram.javabasic.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day16SolutionTest {

    @Test
    @DisplayName("과제1: Member 객체로 만든 댓글은 작성자 객체를 따라가고 이름이 동기화된다")
    void memberAwareComment_linksCommenterAndSyncsName() {
        // mutualFriends 7명 -> 70점 -> "보통" 등급
        Member jaehoon = new Member("jaehoon", 0, 0, 7, 0);
        MemberAwareComment comment = new MemberAwareComment(jaehoon, "멋진 사진이네요!", 0);

        // 객체 받는 생성자는 이름을 commenter.getUsername() 으로 동기화한다
        assertEquals("jaehoon", comment.getAuthor());
        // 작성자 객체를 그대로 따라갈 수 있다
        assertSame(jaehoon, comment.getCommenter());
        // 작성자 객체의 등급까지 따라가진다
        assertEquals("보통", comment.getCommenter().grade());
    }

    @Test
    @DisplayName("과제1: 이름만 받는 생성자로 만들면 commenter 는 연결되지 않는다")
    void memberAwareComment_nameOnlyConstructorHasNoCommenter() {
        MemberAwareComment comment = new MemberAwareComment("minji", "좋아요!", 3);

        assertEquals("minji", comment.getAuthor());
        assertEquals(3, comment.getLikeCount());
        // 이름만 받았으니 객체 연결은 비어 있다(null)
        assertEquals(null, comment.getCommenter());
    }

    @Test
    @DisplayName("과제2: 팔로워 묶음에 담은 만큼 count 가 오르고 인덱스로 꺼낼 수 있다")
    void followableMember_collectsFollowers() {
        FollowableMember star = new FollowableMember("star", 1200, 42, 3, 90);
        Member a = new Member("a", 0, 0, 0, 0);
        Member b = new Member("b", 0, 0, 0, 0);
        Member c = new Member("c", 0, 0, 0, 0);

        star.addFollower(a);
        star.addFollower(b);
        star.addFollower(c);

        assertEquals(3, star.getFollowerCount());
        assertEquals("a", star.getFollower(0).getUsername());
        assertEquals("c", star.getFollower(2).getUsername());
        // 부모 Member 의 기능(팔로워 수·등급)도 그대로 상속받는다
        assertEquals(1200, star.getFollowers());
    }

    @Test
    @DisplayName("과제3: 차단 관계 객체는 두 사람을 안고 toString 으로 한눈에 보여준다")
    void block_holdsTwoMembersAndFormatsToString() {
        Member blocker = new Member("jaehoon", 0, 0, 0, 0);
        Member blocked = new Member("spammer", 0, 0, 0, 0);
        Block block = new Block(blocker, blocked);

        assertSame(blocker, block.getBlocker());
        assertSame(blocked, block.getBlocked());
        assertEquals("@jaehoon ⊘ @spammer", block.toString());
    }
}
