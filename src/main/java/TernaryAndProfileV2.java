void main() {
    // 삼항 연산자: 조건 ? 참일 때 값 : 거짓일 때 값
    int followerCount = 1500;

    String status = followerCount >= 10000 ? "인플루언서" : "일반 사용자";
    System.out.println("상태: " + status);

    // 비공개 여부에 따른 표시
    boolean isPrivate = true;
    String accessLabel = isPrivate ? "비공개 계정" : "공개 계정";
    System.out.println(accessLabel);

    // 숫자 표시 — 만 단위 축약
    String followerDisplay = followerCount >= 10000
            ? (followerCount / 10000) + "만"
            : followerCount + "";
    System.out.println("팔로워: " + followerDisplay);

    // === 인스타그램 프로필 카드 V2 (Day 2 확장) ===
    System.out.println();
    String username = "hong_gildong";
    String displayName = "홍길동";
    int age = 20;
    boolean isVerified = false;
    int postCount = 42;
    int followingCount = 300;

    // 조건 로직으로 프로필 정보 가공
    String verifiedBadge = isVerified ? " [인증]" : "";
    String accountType = isPrivate ? "비공개" : "공개";

    String grade;
    if (followerCount >= 1000000) {
        grade = "메가 인플루언서";
    } else if (followerCount >= 100000) {
        grade = "매크로 인플루언서";
    } else if (followerCount >= 10000) {
        grade = "마이크로 인플루언서";
    } else if (followerCount >= 1000) {
        grade = "나노 인플루언서";
    } else {
        grade = "일반 사용자";
    }

    double ratio = (double) followerCount / followingCount;
    String ratioEval = ratio >= 5.0 ? "우수" : ratio >= 1.0 ? "보통" : "성장 중";

    // 프로필 출력
    System.out.println("=== 인스타그램 프로필 V2 ===");
    System.out.println("사용자명: @" + username + verifiedBadge);
    System.out.println("이름: " + displayName);
    System.out.println("나이: " + age + "세");
    System.out.println("계정: " + accountType);
    System.out.println("등급: " + grade);
    System.out.println("게시물: " + postCount);
    System.out.println("팔로워: " + followerCount);
    System.out.println("팔로잉: " + followingCount);
    System.out.println("팔로워/팔로잉 비율: " + Math.round(ratio * 100.0) / 100.0 + " (" + ratioEval + ")");
    System.out.println("============================");
}
