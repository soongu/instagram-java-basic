package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/NotificationDemoMain.java
// 과제 2 데모 — 종류가 다른 알림 셋을 Notification 타입 배열 하나에 담고
// 똑같이 render() 만 불러요. 호출은 같은데 실제 알림 종류에 따라 채워진 message() 가 달라
// 출력이 종류별로 갈리는 걸 봐요 (다형성 + 템플릿 메서드).

public class NotificationDemoMain {

    public static void main(String[] args) {

        // 자식들을 만들어 부모 타입 배열에 담아요 — 자식을 부모 자리에 담는 게 업캐스팅
        Notification[] alerts = {
                new FollowNotification("minji", "5분 전", "jaehoon"),
                new LikeNotification("minji", "12분 전", "seungwoo"),
                new CommentNotification("minji", "1시간 전", "yujin", "사진 너무 예뻐요!")
        };

        // 향상된 for 로 순회하며 같은 render() 만 불러요.
        for (Notification alert : alerts) {
            System.out.println(alert.render());
        }
    }
}
