package com.instagram.javabasic.solution.day16;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/solution/day16/Day16SolutionMain.java
// Day 16 과제 1~3 예시답안을 한 번에 실행해보는 main 이에요.
public class Day16SolutionMain {

    public static void main(String[] args) {
        // 과제 1 — 댓글에 작성자를 Member 객체로 연결
        Member jaehoon = new Member("jaehoon", 0, 0, 7, 0);
        MemberAwareComment comment = new MemberAwareComment(jaehoon, "멋진 사진이네요!", 0);
        System.out.println("댓글 작성자 이름: " + comment.getAuthor());
        System.out.println("작성자 객체 따라가기 — 등급: " + comment.getCommenter().grade());

        System.out.println();

        // 과제 2 — 나를 팔로우하는 사람들(팔로워) 묶음
        FollowableMember star = new FollowableMember("star", 1200, 42, 3, 90);
        star.addFollower(new Member("a", 0, 0, 0, 0));
        star.addFollower(new Member("b", 0, 0, 0, 0));
        System.out.println("@star 의 팔로워 수: " + star.getFollowerCount());
        for (int i = 0; i < star.getFollowerCount(); i++) {
            System.out.println("  팔로워: @" + star.getFollower(i).getUsername());
        }

        System.out.println();

        // 과제 3 — 차단 관계를 객체로
        Block block = new Block(jaehoon, new Member("spammer", 0, 0, 0, 0));
        System.out.println("차단 관계: " + block);
    }
}
