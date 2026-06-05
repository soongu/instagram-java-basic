package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/StringFormatting.java
// 숫자와 글자를 정해진 틀에 맞춰 깔끔한 문자열로 찍어내는 방법이에요.
// String.format 에 "틀(서식)" 을 주고, 빈칸(%d, %s, %.2f ...)에 값을 채워 넣어요.
//   %d = 정수, %s = 문자열, %f = 실수, %x = 16진수
//   %.2f 는 소수점 아래 둘째 자리까지 보여줘요.
// 같은 일을 문자열에 점 찍어 부르는 "틀문자열".formatted(...) 로도 할 수 있어요.
public class StringFormatting {

    // 회원 프로필 한 줄을 서식에 맞춰 만들어요.
    // 이름은 %s, 팔로워 수는 %d 로 채워요.
    public static String profileLine(String username, int followers) {
        return String.format("@%s · 팔로워 %d명", username, followers);
    }

    // 소수점 둘째 자리까지 — 참여율 같은 값을 깔끔하게 보여줄 때 써요.
    public static String engagementRate(double rate) {
        return String.format("참여율 %.2f%%", rate);
    }

    // 16진수(%x) — 색상 코드처럼 16진수로 보여줘야 할 때 써요.
    public static String toHex(int number) {
        return String.format("%x", number);
    }

    // formatted 는 format 과 같은 일을 해요. 틀 문자열에 점을 찍어 부르는 방식이에요.
    public static String profileLineFormatted(String username, int followers) {
        return "@%s · 팔로워 %d명".formatted(username, followers);
    }

    public static void main(String[] args) {
        System.out.println(profileLine("jaehoon_dev", 1240));      // @jaehoon_dev · 팔로워 1240명
        System.out.println(engagementRate(8.567));                 // 참여율 8.57%
        System.out.println("16진수 : " + toHex(255));               // ff
        System.out.println(profileLineFormatted("minji.kim", 8500)); // @minji.kim · 팔로워 8500명
    }
}
