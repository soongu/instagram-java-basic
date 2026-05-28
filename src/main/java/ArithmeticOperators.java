void main() {
    // 산술 연산자: +, -, *, /, %
    int likes = 150;
    int comments = 38;

    int total = likes + comments;
    int diff = likes - comments;
    System.out.println("총 반응: " + total);
    System.out.println("좋아요 - 댓글: " + diff);

    // 곱하기, 나누기
    int postsPerDay = 3;
    int days = 7;
    int weeklyPosts = postsPerDay * days;
    System.out.println("일주일 게시물: " + weeklyPosts);

    // 정수 나누기 — 소수점이 버려진다!
    int totalLikes = 10;
    int postCount = 3;
    int avgInt = totalLikes / postCount;
    System.out.println("평균 좋아요 (정수): " + avgInt);

    // 실수로 나누면 소수점이 살아난다
    double avgDouble = (double) totalLikes / postCount;
    System.out.println("평균 좋아요 (실수): " + avgDouble);

    // 나머지 연산자 % — 짝수/홀수 판별
    int followerCount = 1537;
    int remainder = followerCount % 2;
    System.out.println("1537 % 2 = " + remainder);
    System.out.println("홀수인가? " + (remainder != 0));

    // 나머지로 그룹 나누기
    int userId = 42;
    int group = userId % 5;
    System.out.println("유저 42의 그룹: " + group);
}
