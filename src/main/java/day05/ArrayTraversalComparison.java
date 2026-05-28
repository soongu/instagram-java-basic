void main() {
    // 같은 배열을 두 가지 방법으로 순회
    int[] likes = {42, 128, 7, 95, 210};
    System.out.println("=== 게시물 좋아요 — 두 가지 방법으로 보기 ===");
    System.out.println();

    // 방법 1: 기본 for + 인덱스 (몇 번째인지 알고 싶을 때)
    System.out.println("[방법 1] 기본 for — 게시물 번호와 함께");
    for (int i = 0; i < likes.length; i++) {
        System.out.println((i + 1) + "번째 게시물: " + likes[i] + "개");
    }
    System.out.println();

    // 방법 2: enhanced for (값만 필요할 때 더 깔끔)
    System.out.println("[방법 2] enhanced for — 합계 계산");
    int total = 0;
    for (int like : likes) {
        total = total + like;
    }
    System.out.println("총 좋아요: " + total + "개");
    System.out.println("평균: " + (total / likes.length) + "개");
    System.out.println();

    // 두 방법을 같이 — 인덱스도 필요하고 값도 누적할 때
    System.out.println("[두 방법 합치기] 가장 인기 있는 게시물 찾기");
    int max = 0;
    int bestIndex = 0;
    for (int i = 0; i < likes.length; i++) {
        if (likes[i] > max) {
            max = likes[i];
            bestIndex = i;
        }
    }
    System.out.println("최고 인기: " + (bestIndex + 1) + "번 게시물 (" + max + "개)");
}
