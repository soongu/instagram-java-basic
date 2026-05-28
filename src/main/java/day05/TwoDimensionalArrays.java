void main() {
    // 2차원 배열 — 인스타 프로필 3x3 피드 썸네일의 좋아요 격자
    int[][] feedGrid = {
            {42, 128, 7},
            {95, 210, 33},
            {67, 150, 88}
    };
    System.out.println("=== 인스타 프로필 3x3 피드 좋아요 격자 ===");
    System.out.println();

    // 격자 형태로 출력 — 이중 for
    System.out.println("[좋아요 격자]");
    for (int row = 0; row < feedGrid.length; row++) {
        for (int col = 0; col < feedGrid[row].length; col++) {
            // 자릿수 맞춰서 보기 좋게
            int v = feedGrid[row][col];
            if (v < 10) {
                System.out.print("  " + v + " ");
            } else if (v < 100) {
                System.out.print(" " + v + " ");
            } else {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }
    System.out.println();

    // 총합 / 평균 — 모든 칸 합치기
    int total = 0;
    int count = 0;
    for (int row = 0; row < feedGrid.length; row++) {
        for (int col = 0; col < feedGrid[row].length; col++) {
            total = total + feedGrid[row][col];
            count++;
        }
    }
    System.out.println("총 좋아요: " + total + "개 (" + count + "개 게시물)");
    System.out.println("평균: " + (total / count) + "개");
    System.out.println();

    // 행마다 합계 — 한 줄씩 들여다보기
    System.out.println("[줄별 합계]");
    for (int row = 0; row < feedGrid.length; row++) {
        int rowSum = 0;
        for (int col = 0; col < feedGrid[row].length; col++) {
            rowSum = rowSum + feedGrid[row][col];
        }
        System.out.println((row + 1) + "째 줄 합계: " + rowSum + "개");
    }
}
