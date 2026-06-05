package com.instagram.javabasic.solution.day17;

// com/instagram/javabasic/solution/day17/MentionReceipt.java
// [응용] 과제 2 — 댓글 멘션(@) 영수증 만들기
// 이름 배열을 받아 "@이름 @이름 ..." 멘션 한 줄을 StringBuilder 로 조립하고,
// 마지막에 "총 N명에게 멘션" 통계를 format 으로 덧붙여요.
public class MentionReceipt {

    public static String build(String[] usernames) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < usernames.length; i++) {
            sb.append("@").append(usernames[i]);
            if (i < usernames.length - 1) {
                sb.append(" ");
            }
        }
        // 멘션 한 줄 아래에 통계 한 줄을 줄바꿈으로 이어 붙여요.
        sb.append("\n");
        sb.append(String.format("총 %d명에게 멘션", usernames.length));
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] commenters = {"minji", "seungwoo", "jaehoon"};
        System.out.println(build(commenters));
        // @minji @seungwoo @jaehoon
        // 총 3명에게 멘션
    }
}
