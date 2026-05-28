void main() {
    // 복합 대입 연산자: +=, -=, *=, /=, %=
    int followerCount = 1000;

    // += : 더하고 대입
    followerCount += 50;
    System.out.println("팔로워 50명 증가: " + followerCount);

    // -= : 빼고 대입
    followerCount -= 20;
    System.out.println("팔로워 20명 감소: " + followerCount);

    // *= : 곱하고 대입
    int dailyLikes = 10;
    dailyLikes *= 7;
    System.out.println("일주일 좋아요: " + dailyLikes);

    // /= : 나누고 대입
    int totalViews = 840;
    totalViews /= 7;
    System.out.println("일평균 조회수: " + totalViews);

    // %= : 나머지를 대입
    int remaining = 17;
    remaining %= 5;
    System.out.println("17 % 5 = " + remaining);

    // 증감 연산자: ++, --
    int likeCount = 99;

    likeCount++;
    System.out.println("좋아요 +1: " + likeCount);

    likeCount--;
    System.out.println("좋아요 -1: " + likeCount);

    // 전위 vs 후위 — 결과가 다르다!
    int a = 5;
    System.out.println("후위: " + a++);
    System.out.println("지금 a: " + a);

    int b = 5;
    System.out.println("전위: " + ++b);
    System.out.println("지금 b: " + b);

    // 연산자 우선순위 확인
    int result1 = 2 + 3 * 4;
    int result2 = (2 + 3) * 4;
    System.out.println("2 + 3 * 4 = " + result1);
    System.out.println("(2 + 3) * 4 = " + result2);
}
