void main() {
    // break — 인기 게시물을 찾으면 멈추기
    System.out.println("=== break: 인기 게시물 찾기 ===");
    for (int postId = 1; postId <= 20; postId++) {
        int likes = postId * 7;
        System.out.println("게시물 " + postId + ": 좋아요 " + likes + "개");

        if (likes >= 50) {
            System.out.println(">>> 인기 게시물 발견! 검색 중단");
            break;
        }
    }
    System.out.println();

    // continue — 비공개 계정 건너뛰기
    System.out.println("=== continue: 비공개 계정 건너뛰기 ===");
    for (int userId = 1; userId <= 10; userId++) {
        boolean isPrivate = (userId % 3 == 0);

        if (isPrivate) {
            System.out.println("사용자 " + userId + ": 비공개 → 건너뜀");
            continue;
        }

        System.out.println("사용자 " + userId + ": 공개 → 프로필 표시");
    }
    System.out.println();

    // break + while — 목표 팔로워 달성
    System.out.println("=== break + while: 목표 팔로워 달성 ===");
    int targetFollowers = 500;
    int currentFollowers = 0;
    int day = 0;

    while (true) {
        day++;
        currentFollowers = currentFollowers + 45;

        if (currentFollowers >= targetFollowers) {
            System.out.println(day + "일차에 " + targetFollowers + "명 달성!");
            break;
        }

        if (day % 3 == 0) {
            System.out.println(day + "일차: " + currentFollowers + "명 (진행 중...)");
        }
    }
    System.out.println();

    // continue — 짝수 번호 게시물만 출력
    System.out.println("=== continue: 짝수 번호만 표시 ===");
    for (int i = 1; i <= 10; i++) {
        if (i % 2 != 0) {
            continue;
        }
        System.out.println("게시물 #" + i + " 표시");
    }
}
