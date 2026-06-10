package com.instagram.javabasic.service.solution.day24;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.repository.MemberRepository;
import com.instagram.javabasic.repository.PostRepository;

// com/instagram/javabasic/service/solution/day24/FeedService.java
// [과제 3 예시답안] 내가 팔로우한 사람들의 게시물을 한데 모아 피드를 만드는 서비스예요.
// 저장소 둘(회원·게시물)을 함께 전달받아, 팔로잉 한 명씩 돌면서 그 사람의 글을 모아요.
// for 안에 findByAuthor 의 for 가 또 도는 "중첩 반복" 이 핵심이에요.
public class FeedService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public FeedService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    // 피드 만들기 — 내가 팔로우한 사람들의 게시물을 모두 모아 돌려줘요.
    public List<Post> buildFeed(Long memberId) {
        Member me = memberRepository.findById(memberId);
        List<Post> feed = new ArrayList<>();
        for (int i = 0; i < me.getFollowingCount(); i++) {
            Member followee = me.getFollowing(i);
            feed.addAll(postRepository.findByAuthor(followee));
        }
        return feed;
    }
}
