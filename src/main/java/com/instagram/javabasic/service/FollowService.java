package com.instagram.javabasic.service;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.repository.MemberRepository;

// com/instagram/javabasic/service/FollowService.java
// 회원과 회원을 잇는 "팔로우" 를 맡는 계층이에요.
// 두 회원을 저장소에서 찾고(없으면 MemberNotFoundException 이 저장소에서 올라와요),
// 자기 자신을 팔로우하는 건 막은 뒤, 이미 팔로우 중이 아니면 연결해요.
// 회원을 잇는 실제 동작(follow·isFollowing)은 Member 가 이미 갖고 있어서 그대로 빌려 써요.
public class FollowService {

    private final MemberRepository memberRepository;

    public FollowService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 팔로우 — followerId 회원이 targetId 회원을 팔로우해요.
    public void follow(Long followerId, Long targetId) {
        Member follower = memberRepository.findById(followerId);
        Member target = memberRepository.findById(targetId);
        if (follower.equals(target)) {
            throw new IllegalArgumentException("자기 자신은 팔로우할 수 없어요.");
        }
        if (follower.isFollowing(target)) {
            return;
        }
        follower.follow(target);
    }

    // 서로 맞팔(둘 다 서로를 팔로우)인지 확인해요.
    public boolean isMutual(Long aId, Long bId) {
        Member a = memberRepository.findById(aId);
        Member b = memberRepository.findById(bId);
        return a.isFollowing(b) && b.isFollowing(a);
    }
}
