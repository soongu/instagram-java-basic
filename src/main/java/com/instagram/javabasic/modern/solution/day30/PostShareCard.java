package com.instagram.javabasic.modern.solution.day30;

// com/instagram/javabasic/modern/solution/day30/PostShareCard.java
// [과제 1] 게시물을 친구에게 공유할 때 보여줄 "공유 카드" 문자열을 만들어요.
// 텍스트 블록(""")으로 세 줄짜리 카드 모양을 잡고, 빈칸(%s·%d)에 .formatted(...) 로 값을 끼워 넣어요.
public class PostShareCard {

    public static String shareCard(String title, String author, int likeCount) {
        return """
                📷 %s
                by @%s
                ❤️ %d""".formatted(title, author, likeCount);
    }
}
