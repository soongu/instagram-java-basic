void main() {
    // 논리 연산자: &&(그리고), ||(또는), !(아니다)
    int age = 16;
    int height = 145;

    // && (AND) — 두 조건이 모두 true여야 true
    boolean canRide = age >= 14 && height >= 140;
    System.out.println("놀이기구 탑승 가능? " + canRide);

    // || (OR) — 하나만 true여도 true
    boolean isWeekend = true;
    boolean isHoliday = false;
    boolean isDayOff = isWeekend || isHoliday;
    System.out.println("쉬는 날인가? " + isDayOff);

    // ! (NOT) — true를 false로, false를 true로
    boolean isPrivate = true;
    boolean isPublic = !isPrivate;
    System.out.println("공개 계정인가? " + isPublic);

    // 인스타그램 예시 — 게시물 공개 조건
    boolean isFollower = false;
    boolean canSeePost = isPublic || isFollower;
    System.out.println("게시물을 볼 수 있는가? " + canSeePost);

    // 복합 조건 — 인플루언서 인증 조건
    int followerCount = 12000;
    int postCount = 50;
    boolean isVerified = false;
    boolean qualifiesForBadge = followerCount >= 10000 && postCount >= 30 && !isVerified;
    System.out.println("인증 배지 신청 가능? " + qualifiesForBadge);

    // 단축 평가 — && 앞이 false면 뒤는 확인하지 않는다
    int divisor = 0;
    boolean isSafe = divisor != 0 && (10 / divisor > 2);
    System.out.println("안전한 나눗셈? " + isSafe);
}
