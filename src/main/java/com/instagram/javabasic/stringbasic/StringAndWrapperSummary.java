package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/StringAndWrapperSummary.java
// 오늘 배운 세 도구(문자열 비교/조립, 포장 클래스, 서식)를 한자리에서 묶어 써봐요.
// 인스타 프로필 한 줄 요약을 만드는 작은 종합 예제예요.
//   1) 팔로워 수는 문자열로 들어오니 포장 클래스로 진짜 숫자로 바꿔요.
//   2) StringBuilder 로 해시태그를 조립해요.
//   3) formatted 로 깔끔한 한 줄을 찍어내요.
// 다음 시간(Day 18)엔 이 데이터들을 여러 개 모아 다루는 "컬렉션" 으로 넘어가요.
public class StringAndWrapperSummary {

    // 문자열로 들어온 팔로워 수를 진짜 정수로 바꿔 비교/계산에 쓸 수 있게 해요.
    public static int parseFollowers(String raw) {
        return Integer.parseInt(raw);
    }

    // 해시태그 배열을 StringBuilder 로 한 줄로 조립해요.
    public static String buildTagLine(String[] tags) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            sb.append("#").append(tags[i]);
            if (i < tags.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // 세 도구를 모두 묶어 프로필 한 줄 요약을 완성해요.
    public static String summarize(String username, String followersRaw, String[] tags) {
        int followers = parseFollowers(followersRaw);
        String tagLine = buildTagLine(tags);
        return "@%s · 팔로워 %d명 · %s".formatted(username, followers, tagLine);
    }

    public static void main(String[] args) {
        String[] tags = {"daily", "coding", "instagram"};
        String summary = summarize("jaehoon_dev", "1240", tags);

        System.out.println(summary);
        // @jaehoon_dev · 팔로워 1240명 · #daily #coding #instagram
        System.out.println("문자열 비교·조립, 포장 클래스, 서식이 한 줄에 모두 들어 있어요.");
    }
}
