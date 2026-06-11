package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/FeedEventMessages.java
// [과제 3] sealed + record + switch 로 사건마다 사람이 읽을 알림 문장을 만들어요.
// FeedEvent 가 sealed 라 default 없이도 세 종류를 빠짐없이 처리해요.
public class FeedEventMessages {

    public static String describe(FeedEvent event) {
        return switch (event) {
            case StoryViewed(String viewer) ->
                    viewer + "님이 회원님의 스토리를 봤어요.";
            case Mentioned(String actor, String title) ->
                    actor + "님이 '" + title + "' 에서 회원님을 언급했어요.";
            case Tagged(String actor, String photo) ->
                    actor + "님이 '" + photo + "' 사진에 회원님을 태그했어요.";
        };
    }
}
