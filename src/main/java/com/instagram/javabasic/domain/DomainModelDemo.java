package com.instagram.javabasic.domain;

import com.instagram.javabasic.domain.comment.Comment;
import com.instagram.javabasic.domain.follow.Follow;
import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/domain/DomainModelDemo.java
// 지금까지 따로 만든 조각들(Member·Post·Comment·Follow)을 한자리에서 연결해보는 시연이에요.
// 회원이 글을 쓰고, 그 글에 댓글이 달리고, 회원끼리 팔로우하는 — 인스타의 뼈대를 실제로 엮어요.
// 핵심은 "객체가 객체를 직접 가리킨다(참조)" 는 점이에요. 이름 문자열만 들고 다니던 때와 비교해보세요.
public class DomainModelDemo {

    public static void main(String[] args) {
        // 1) 회원 세 명을 만들어요
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 30);

        // 2) jaehoon 이 글을 써요 — 작성자를 이름이 아니라 "객체" 로 직접 넘겨요
        Post post = new Post("오늘 점심 맛집 추천!", jaehoon, 12);
        jaehoon.addWrittenPost(post);

        // 글에서 작성자를 따라가면 그 사람의 점수·등급까지 바로 알 수 있어요
        Member writer = post.getAuthor();
        System.out.println("글 작성자: @" + writer.getUsername()
                + " (점수 " + writer.calculateRecommendScore() + ", 등급 " + writer.grade() + ")");
        System.out.println("이름만 베껴둔 값(authorName): " + post.getAuthorName());

        // 3) 글에 댓글을 달아요 — 댓글 객체로도, 텍스트만으로도 달 수 있어요
        post.addComment(new Comment("minji_cafe", "어디예요? 저도 갈래요!", 0));
        post.addComment("좋아요 누르고 갑니다");
        System.out.println("댓글 수: " + post.getCommentCount());
        for (int i = 0; i < post.getCommentCount(); i++) {
            System.out.println("  - " + post.getComment(i));
        }

        // 4) jaehoon 이 minji 와 seungwoo 를 팔로우해요
        jaehoon.follow(minji);
        jaehoon.follow(seungwoo);
        System.out.println("@jaehoon_dev 팔로잉 수: " + jaehoon.getFollowingCount());
        System.out.println("minji 를 팔로우 중인가? " + jaehoon.isFollowing(minji));

        // 5) 관계 자체를 객체로 만들어 출력해요
        Follow follow = new Follow(jaehoon, minji);
        System.out.println("관계 객체: " + follow);
    }
}
