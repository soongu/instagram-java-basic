package com.instagram.javabasic.solution.day13;

// com/instagram/javabasic/solution/day13/Day13SolutionMain.java
// Day 13 과제 1·2·3 을 한자리에서 눈으로 확인하는 데모예요.
//  - 과제 1: 새 역할 Playable 을 만들어 AudioContent 에 입히고, default play() 를 그대로 써봐요.
//  - 과제 2: Content[] 에 네 종류를 섞어 담고, "공유 가능한 것" 만 골라 링크를 뽑아요.
//  - 과제 3: 콘텐츠 하나가 가진 역할들(공유·댓글·재생)을 보고하는 도우미를 써봐요.
import com.instagram.javabasic.domain.content.Commentable;
import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.Shareable;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.content.VideoContent;

public class Day13SolutionMain {

    public static void main(String[] args) {

        // ===== 과제 1: 새 역할 Playable 을 AudioContent 에 입히기 =====
        AudioContent audio = new AudioContent("minji", 50, 30);
        System.out.println(audio.play());   // ▶ 재생을 시작해요 (30초)

        // 음성은 댓글과 재생은 되지만 공유는 안 돼요 (역할 분화)
        System.out.println(audio instanceof Commentable); // true
        System.out.println(audio instanceof Playable);    // true
        System.out.println(audio instanceof Shareable);   // false

        // ===== 과제 2: 공유 가능한 콘텐츠만 골라 링크 뽑기 =====
        Content[] feed = {
                new ImageContent("minji", 120, "beach.jpg"),
                new VideoContent("jaehoon", 340, "trip.mp4", 45),
                new TextContent("seungwoo", 12, "오늘 점심 맛있었다"),
                new AudioContent("yujin", 8, 30)
        };

        // 향상된 for + instanceof Shareable 로 "공유 가능한 것" 만 걸러요.
        // 텍스트·음성은 Shareable 이 아니라서 자동으로 빠져요.
        for (Content content : feed) {
            if (content instanceof Shareable s) {
                System.out.println(s.getShareUrl());
            }
        }
        System.out.println("공유 가능한 콘텐츠 개수: " + countShareable(feed)); // 2

        // ===== 과제 3: 콘텐츠가 가진 역할 보고하기 =====
        System.out.println(describeRoles(feed[0])); // 공유 가능, 댓글 가능
        System.out.println(describeRoles(feed[2])); // 댓글 가능
        System.out.println(describeRoles(feed[3])); // 댓글 가능, 재생 가능
    }

    // 과제 2 도우미 — 배열에서 "공유 가능한(Shareable)" 콘텐츠가 몇 개인지 세요.
    // 컬렉션을 아직 안 배웠으니 카운터(int) 하나로 세고 그 수를 돌려줘요.
    public static int countShareable(Content[] feed) {
        int count = 0;
        for (Content content : feed) {
            if (content instanceof Shareable) {
                count++;
            }
        }
        return count;
    }

    // 과제 3 도우미 — 콘텐츠 하나가 가진 역할을 보고 쉼표로 이어붙여 돌려줘요.
    // 순서는 공유 → 댓글 → 재생. 없는 역할은 그냥 건너뛰어요.
    public static String describeRoles(Content content) {
        String result = "";

        if (content instanceof Shareable) {
            result = append(result, "공유 가능");
        }
        if (content instanceof Commentable) {
            result = append(result, "댓글 가능");
        }
        if (content instanceof Playable) {
            result = append(result, "재생 가능");
        }
        return result;
    }

    // 글자를 쉼표로 이어붙이는 작은 도우미 — 첫 역할이면 그대로, 두 번째부터는 ", " 를 앞에 붙여요.
    private static String append(String base, String role) {
        if (base.isEmpty()) {
            return role;
        }
        return base + ", " + role;
    }
}
