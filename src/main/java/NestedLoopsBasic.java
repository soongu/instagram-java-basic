void main() {
    // 2중 반복 — 알림 시간표
    System.out.println("=== 알림 시간표 (3일 x 4시간대) ===");
    for (int day = 1; day <= 3; day++) {
        for (int hour = 9; hour <= 12; hour++) {
            System.out.print("Day" + day + "-" + hour + "시  ");
        }
        System.out.println();
    }
    System.out.println();

    // 좋아요 배수표
    System.out.println("=== 좋아요 배수표 (2~5배) ===");
    for (int multiplier = 2; multiplier <= 5; multiplier++) {
        System.out.print(multiplier + "배: ");
        for (int base = 1; base <= 5; base++) {
            int result = base * multiplier;
            if (result < 10) {
                System.out.print(" " + result + "  ");
            } else {
                System.out.print(result + "  ");
            }
        }
        System.out.println();
    }
    System.out.println();

    // 게시물 x 댓글 조합
    System.out.println("=== 게시물별 댓글 목록 ===");
    for (int post = 1; post <= 3; post++) {
        System.out.println("게시물 " + post + ":");
        for (int comment = 1; comment <= 3; comment++) {
            System.out.println("  ㄴ 댓글 " + comment);
        }
    }
    System.out.println();

    // 5x5 좌표 격자
    System.out.println("=== 5x5 좌표 격자 ===");
    for (int row = 1; row <= 5; row++) {
        for (int col = 1; col <= 5; col++) {
            if (row < 10 && col < 10) {
                System.out.print("(" + row + "," + col + ") ");
            }
        }
        System.out.println();
    }
}
