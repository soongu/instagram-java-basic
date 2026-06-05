package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/StringBuilderBasics.java
// 문자열을 여러 번 이어 붙여야 할 땐 StringBuilder 가 편하고 빨라요.
// String 은 더할 때마다 새 객체가 생기지만, StringBuilder 는 한 상자 안에서 글자를 채워가요.
// 다 채운 뒤 toString() 으로 진짜 문자열을 꺼내요.
// (참고: StringBuffer 라는 형제도 있는데, 여러 작업이 동시에 끼어드는 상황용이에요.
//  평소엔 StringBuilder 면 충분해요.)
public class StringBuilderBasics {

    // 해시태그 목록을 한 줄 문자열로 누적해 만들어요.
    // for 루프 안에서 append 로 글자를 차곡차곡 쌓아요.
    public static String joinTags(String[] tags) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            sb.append("#").append(tags[i]);
            if (i < tags.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // insert 로 맨 앞에 글자를 끼워 넣고, append 로 뒤에 붙여요.
    public static String wrapWithBrackets(String inner) {
        StringBuilder sb = new StringBuilder(inner);
        sb.insert(0, "[");
        sb.append("]");
        return sb.toString();
    }

    // delete 로 일부 구간을 잘라내요. (시작 위치 ~ 끝 위치 직전)
    public static String deleteRange(String text, int start, int end) {
        StringBuilder sb = new StringBuilder(text);
        sb.delete(start, end);
        return sb.toString();
    }

    // reverse 로 글자 순서를 통째로 뒤집어요.
    public static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    public static void main(String[] args) {
        String[] tags = {"daily", "instagram", "java"};

        System.out.println("해시태그 : " + joinTags(tags));        // #daily #instagram #java
        System.out.println("대괄호로 감싸기 : " + wrapWithBrackets("post")); // [post]
        System.out.println("구간 삭제 : " + deleteRange("instagram", 0, 5)); // gram
        System.out.println("뒤집기 : " + reverse("level"));        // level
    }
}
