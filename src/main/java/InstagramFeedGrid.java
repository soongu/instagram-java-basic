void main() {
    // 인스타그램 프로필 피드 (3x3 그리드)
    System.out.println("=== 인스타그램 프로필 피드 (3x3) ===");
    System.out.println();

    int rows = 3;
    int cols = 3;
    int postNumber = 0;

    for (int row = 0; row < rows; row++) {
        // 상단 테두리
        for (int col = 0; col < cols; col++) {
            System.out.print("+--------");
        }
        System.out.println("+");

        // 게시물 번호
        for (int col = 0; col < cols; col++) {
            postNumber++;
            if (postNumber < 10) {
                System.out.print("|  [0" + postNumber + "]  ");
            } else {
                System.out.print("|  [" + postNumber + "]  ");
            }
        }
        System.out.println("|");
    }

    // 하단 테두리
    for (int col = 0; col < cols; col++) {
        System.out.print("+--------");
    }
    System.out.println("+");
    System.out.println();

    // 좋아요 히트맵
    System.out.println("=== 게시물 좋아요 히트맵 ===");
    System.out.println();

    int[] likes = {42, 128, 7, 95, 210, 33, 67, 150, 12};
    int index = 0;

    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            String heat;
            if (likes[index] >= 100) {
                heat = " HOT ";
            } else if (likes[index] >= 50) {
                heat = " warm";
            } else {
                heat = "  -  ";
            }
            System.out.print("[" + heat + "]");
            index++;
        }
        System.out.println();
    }

    System.out.println();
    System.out.println("HOT = 100+ 좋아요, warm = 50+, - = 50 미만");
    System.out.println();

    // 4x4 확장 그리드 (break 조합)
    System.out.println("=== 확장 그리드 (최대 12개까지만) ===");
    System.out.println();

    int totalPosts = 12;
    int gridCols = 4;
    int printed = 0;

    for (int row = 0; printed < totalPosts; row++) {
        for (int col = 0; col < gridCols; col++) {
            printed++;
            if (printed > totalPosts) {
                break;
            }
            if (printed < 10) {
                System.out.print("[0" + printed + "] ");
            } else {
                System.out.print("[" + printed + "] ");
            }
        }
        System.out.println();
    }
}
