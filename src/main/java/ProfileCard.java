void main() {
    // 인스타그램 프로필 카드 — 여러 자료형 종합
    String username = "hong_gildong";
    String displayName = "홍길동";
    int age = 20;
    double height = 175.5;
    boolean isVerified = false;
    boolean isPrivate = true;
    int postCount = 42;
    int followerCount = 1500;
    int followingCount = 300;

    // 프로필 출력
    System.out.println("=== 인스타그램 프로필 ===");
    System.out.println("사용자명: @" + username);
    System.out.println("이름: " + displayName);
    System.out.println("나이: " + age + "세");
    System.out.println("키: " + height + "cm");
    System.out.println("인증 계정: " + isVerified);
    System.out.println("비공개 계정: " + isPrivate);
    System.out.println("게시물: " + postCount);
    System.out.println("팔로워: " + followerCount);
    System.out.println("팔로잉: " + followingCount);
    System.out.println("========================");

    // 팔로워 비율 계산 (형변환 활용)
    double ratio = (double) followerCount / followingCount;
    System.out.println("팔로워/팔로잉 비율: " + ratio);

    // final로 제한 설정
    final int MAX_USERNAME_LENGTH = 30;
    System.out.println("사용자명 최대 길이: " + MAX_USERNAME_LENGTH);

    // var로 간결하게
    var bio = "Java를 배우는 중입니다!";
    System.out.println("자기소개: " + bio);
}
