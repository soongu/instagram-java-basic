package com.instagram.javabasic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowServiceTest {

    private MemberRepository repo;
    private FollowService service;
    private Long jaehoonId;
    private Long minjiId;

    private void setup() {
        repo = new MemberRepository();
        service = new FollowService(repo);
        jaehoonId = repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        minjiId = repo.save(new Member("minji", "minji@insta.com"));
    }

    @Test
    @DisplayName("follow 하면 팔로워가 대상을 팔로우 중이 된다")
    void follow_정상() {
        setup();
        service.follow(jaehoonId, minjiId);
        assertTrue(repo.findById(jaehoonId).isFollowing(repo.findById(minjiId)));
    }

    @Test
    @DisplayName("자기 자신을 팔로우하면 IllegalArgumentException 을 던진다")
    void follow_자기자신() {
        setup();
        assertThrows(IllegalArgumentException.class, () -> service.follow(jaehoonId, jaehoonId));
    }

    @Test
    @DisplayName("없는 회원을 팔로우하면 MemberNotFoundException 을 던진다")
    void follow_없는회원() {
        setup();
        assertThrows(MemberNotFoundException.class, () -> service.follow(jaehoonId, 999L));
        assertThrows(MemberNotFoundException.class, () -> service.follow(888L, minjiId));
    }

    @Test
    @DisplayName("같은 사람을 두 번 팔로우해도 한 번만 연결된다 (중복 방지)")
    void follow_중복방지() {
        setup();
        service.follow(jaehoonId, minjiId);
        service.follow(jaehoonId, minjiId);
        assertEquals(1, repo.findById(jaehoonId).getFollowingCount());
    }

    @Test
    @DisplayName("isMutual 은 둘이 서로 팔로우할 때만 true")
    void isMutual() {
        setup();
        service.follow(jaehoonId, minjiId);
        assertFalse(service.isMutual(jaehoonId, minjiId));
        service.follow(minjiId, jaehoonId);
        assertTrue(service.isMutual(jaehoonId, minjiId));
    }
}
