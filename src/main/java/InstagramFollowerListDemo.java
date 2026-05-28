void main() {
    // Day 3 프로필 카드 + Day 4 반복 통합
    System.out.println("========================================");
    System.out.println("    인스타그램 프로필 카드 v3");
    System.out.println("========================================");
    System.out.println();

    String username = "java_learner_2026";
    int postCount = 42;
    int followerCount = 1500;
    int followingCount = 280;
    boolean isVerified = true;

    System.out.println("@" + username + (isVerified ? " ✓" : ""));
    System.out.println("게시물 " + postCount + " | 팔로워 " + followerCount + " | 팔로잉 " + followingCount);
    System.out.println();

    // 팔로워 등급 (Day 3 복습)
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
    System.out.println("계정 등급: " + grade);
    System.out.println();

    // 팔로워 목록 — enhanced for
    String[] followers = {"장원영", "카리나", "민지", "하니", "해린"};
    System.out.println("--- 팔로워 목록 ---");
    int number = 0;
    for (String name : followers) {
        number++;
        System.out.println(number + ". " + name);
    }
    System.out.println("총 " + followers.length + "명");
    System.out.println();

    // 최근 게시물 좋아요 막대 그래프 — 중첩 반복
    int[] postLikes = {42, 128, 7, 95, 210};
    System.out.println("--- 최근 게시물 좋아요 ---");
    int total = 0;
    int max = 0;
    int bestPost = 0;

    for (int i = 0; i < postLikes.length; i++) {
        total = total + postLikes[i];
        if (postLikes[i] > max) {
            max = postLikes[i];
            bestPost = i + 1;
        }

        System.out.print("게시물 " + (i + 1) + ": ");
        int barLen = postLikes[i] / 10;
        for (int j = 0; j < barLen; j++) {
            System.out.print("█");
        }
        System.out.println(" " + postLikes[i] + "개");
    }
    System.out.println();
    System.out.println("총 좋아요: " + total + "개");
    System.out.println("평균: " + (total / postLikes.length) + "개");
    System.out.println("최고 인기: 게시물 " + bestPost + " (" + max + "개)");
    System.out.println();

    // 인기 게시물 필터 — break + continue
    System.out.println("--- 좋아요 50개 이상 게시물만 ---");
    int found = 0;
    for (int i = 0; i < postLikes.length; i++) {
        if (postLikes[i] < 50) {
            continue;
        }
        found++;
        System.out.println("게시물 " + (i + 1) + ": " + postLikes[i] + "개");
    }
    System.out.println("인기 게시물 " + found + "개 발견!");
    System.out.println();

    System.out.println("========================================");
    System.out.println("  Day 4 반복문 — 모든 기술 통합 완료!");
    System.out.println("========================================");
}
