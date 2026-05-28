void main() {
    // 문제 상황: println을 5번 복사해야 한다
    System.out.println("팔로워 1번째 출력");
    System.out.println("팔로워 2번째 출력");
    System.out.println("팔로워 3번째 출력");
    System.out.println("팔로워 4번째 출력");
    System.out.println("팔로워 5번째 출력");
    System.out.println();

    // for 루프로 같은 일을 한 줄에!
    System.out.println("=== for 루프 ===");
    for (int i = 1; i <= 5; i++) {
        System.out.println("팔로워 " + i + "번째 출력");
    }
    System.out.println();

    // 1부터 10까지 합계
    System.out.println("=== 1부터 10까지 합계 ===");
    int sum = 0;
    for (int i = 1; i <= 10; i++) {
        sum = sum + i;
    }
    System.out.println("합계: " + sum);
    System.out.println();

    // 좋아요 수 누적
    System.out.println("=== 게시물별 좋아요 누적 ===");
    int totalLikes = 0;
    for (int post = 1; post <= 5; post++) {
        int likes = post * 12;
        totalLikes = totalLikes + likes;
        System.out.println("게시물 " + post + ": 좋아요 " + likes + "개 (누적: " + totalLikes + ")");
    }
    System.out.println("총 좋아요: " + totalLikes + "개");
    System.out.println();

    // 카운트다운 (감소)
    System.out.println("=== 스토리 삭제 카운트다운 ===");
    for (int sec = 5; sec >= 1; sec--) {
        System.out.println(sec + "초 후 스토리가 사라집니다...");
    }
    System.out.println("스토리가 삭제되었습니다!");
}
