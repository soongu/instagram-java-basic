package com.instagram.javabasic.service.solution.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;
import com.instagram.javabasic.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedServiceTest {

    @Test
    @DisplayName("내가 팔로우한 사람들의 게시물을 모두 모아 피드를 만든다")
    void buildFeed_정상() {
        MemberRepository memberRepo = new MemberRepository();
        PostRepository postRepo = new PostRepository();
        FeedService feedService = new FeedService(memberRepo, postRepo);

        Member me = new Member("jaehoon", "jaehoon@insta.com");
        Member a = new Member("minji", "minji@insta.com");
        Member b = new Member("seungwoo", "seungwoo@insta.com");
        Long meId = memberRepo.save(me);
        memberRepo.save(a);
        memberRepo.save(b);

        me.follow(a);
        me.follow(b);
        postRepo.save(new com.instagram.javabasic.domain.post.Post("민지 글1", a, 0));
        postRepo.save(new com.instagram.javabasic.domain.post.Post("민지 글2", a, 0));
        postRepo.save(new com.instagram.javabasic.domain.post.Post("승우 글1", b, 0));

        assertEquals(3, feedService.buildFeed(meId).size());
    }

    @Test
    @DisplayName("아무도 팔로우하지 않았으면 피드는 비어 있다")
    void buildFeed_팔로잉없음() {
        MemberRepository memberRepo = new MemberRepository();
        PostRepository postRepo = new PostRepository();
        FeedService feedService = new FeedService(memberRepo, postRepo);
        Long meId = memberRepo.save(new Member("jaehoon", "jaehoon@insta.com"));
        assertEquals(0, feedService.buildFeed(meId).size());
    }

    @Test
    @DisplayName("없는 회원의 피드를 만들려 하면 MemberNotFoundException")
    void buildFeed_없는회원() {
        MemberRepository memberRepo = new MemberRepository();
        PostRepository postRepo = new PostRepository();
        FeedService feedService = new FeedService(memberRepo, postRepo);
        assertThrows(MemberNotFoundException.class, () -> feedService.buildFeed(99L));
    }
}
