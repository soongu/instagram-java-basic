package com.instagram.javabasic.service;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.exceptionbasic.DuplicateEmailException;
import com.instagram.javabasic.exceptionbasic.InvalidCaptionException;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;
import com.instagram.javabasic.repository.PostRepository;

// com/instagram/javabasic/service/InstagramApp.java
// 저장소 둘과 서비스 셋을 한자리에 조립해, 인스타그램 서비스 계층이 협력하는 모습을 한 흐름으로 보여줘요.
// 가입 → 중복 가입(막힘) → 게시물 작성 → 너무 긴 캡션(막힘) → 팔로우 → 없는 회원 팔로우(막힘) 순서로 굴러가요.
// 막히는 자리마다 예외를 try-catch 로 받아, 사람이 읽을 안내로 바꿔 화면에 보여줘요.
public class InstagramApp {

    private final MemberRepository memberRepository = new MemberRepository();
    private final PostRepository postRepository = new PostRepository();
    private final MemberService memberService = new MemberService(memberRepository);
    private final PostService postService = new PostService(postRepository);
    private final FollowService followService = new FollowService(memberRepository);

    // 화면에 보여준 모든 줄을 그대로 모아 돌려줘요 — 무슨 일이 일어났는지 한눈에 되짚어 볼 수 있어요.
    private final List<String> transcript = new ArrayList<>();

    public static void main(String[] args) {
        new InstagramApp().runDemo();
    }

    public List<String> runDemo() {
        log("=== 인스타그램 서비스 계층 데모 ===");

        // 1) 회원가입 두 명 — 정상 흐름
        Long jaehoonId = memberService.signup("jaehoon", "jaehoon@insta.com");
        Long minjiId = memberService.signup("minji", "minji@insta.com");
        log("가입 완료: jaehoon(id=" + jaehoonId + "), minji(id=" + minjiId + ")");

        // 2) 같은 이메일로 다시 가입 — DuplicateEmailException 으로 막힘
        try {
            memberService.signup("jaehoon2", "jaehoon@insta.com");
        } catch (DuplicateEmailException e) {
            log("가입 거절(중복 이메일): " + e.getMessage());
        }

        // 3) 게시물 작성 — 정상 흐름
        Long postId = postService.createPost(memberService.getMember(jaehoonId), "오늘 날씨 좋다 ☀");
        log("게시물 작성 완료: id=" + postId);

        // 4) 너무 긴 캡션 — InvalidCaptionException 으로 막힘
        try {
            postService.createPost(memberService.getMember(jaehoonId), "가".repeat(200));
        } catch (InvalidCaptionException e) {
            log("작성 거절(캡션 규칙): " + e.getMessage());
        }

        // 5) 팔로우 — 정상 흐름
        followService.follow(jaehoonId, minjiId);
        log("팔로우 완료: jaehoon → minji (맞팔 여부=" + followService.isMutual(jaehoonId, minjiId) + ")");

        // 6) 없는 회원을 팔로우 — MemberNotFoundException 으로 막힘
        try {
            followService.follow(jaehoonId, 999L);
        } catch (MemberNotFoundException e) {
            log("팔로우 거절(회원 없음): " + e.getMessage());
        }

        log("=== 데모 종료: 회원 " + memberRepository.count() + "명, 게시물 " + postRepository.count() + "개 ===");
        return transcript;
    }

    // 한 줄을 화면에 보여주고, 되짚기용 묶음에도 함께 담아요.
    private void log(String line) {
        System.out.println(line);
        transcript.add(line);
    }
}
