package com.instagram.javabasic.solution.day14;

// com/instagram/javabasic/solution/day14/Day14SolutionMain.java
// Day 14 과제 1·2·3 을 한자리에서 눈으로 확인하는 데모예요.
//  - 과제 1: 데이터(PostData) 와 표시(PostCard) 의 책임을 나눠서 써봐요.
//  - 과제 2: 새 KakaoChannel 을 끼워도 Notifier 가 그대로 함께 보내요.
//  - 과제 3: 불변 Tag 의 withUse() 가 새 객체를 돌려주고 원본은 안 바뀌어요.
import com.instagram.javabasic.design.ocp.EmailChannel;
import com.instagram.javabasic.design.ocp.NotificationChannel;
import com.instagram.javabasic.design.ocp.Notifier;
import com.instagram.javabasic.design.ocp.PushChannel;

public class Day14SolutionMain {

    public static void main(String[] args) {

        // ===== 과제 1: 데이터 보관(PostData) 과 화면 표시(PostCard) 의 책임 분리 =====
        PostData data = new PostData("minji", "오늘 점심 최고", 12);
        System.out.println(data.getWriter() + " / " + data.getContent() + " / " + data.getLikeCount());

        PostCard card = new PostCard(data.getWriter(), data.getContent(), data.getLikeCount());
        System.out.println(card.formatCard()); // @minji: 오늘 점심 최고 (좋아요 12)

        // ===== 과제 2: 새 KakaoChannel 을 끼워도 Notifier 는 한 줄도 안 바뀜 =====
        Notifier notifier = new Notifier();
        NotificationChannel[] channels = {
                new EmailChannel(),
                new PushChannel(),
                new KakaoChannel()
        };

        String[] results = notifier.broadcast(channels, "새 댓글이 달렸어요");
        for (String result : results) {
            System.out.println(result); // [Email] ... / [Push] ... / [Kakao] ...
        }

        // ===== 과제 3: 불변 Tag — 바꾸는 게 아니라 새로 만들기 =====
        Tag travel = new Tag("여행", 5);
        Tag usedOnce = travel.withUse();

        System.out.println(travel);   // #여행 (5회 사용)  ← 원본은 그대로
        System.out.println(usedOnce); // #여행 (6회 사용)  ← 새로 만든 것
    }
}
