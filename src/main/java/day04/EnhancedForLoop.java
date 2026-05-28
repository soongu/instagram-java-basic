void main() {
    // 배열 맛보기 — 여러 데이터를 한 줄에 담기
    System.out.println("=== 배열 선언과 기본 for 순회 ===");
    String[] followers = {"장원영", "카리나", "민지", "하니", "해린"};

    for (int i = 0; i < followers.length; i++) {
        System.out.println((i + 1) + "번째 팔로워: " + followers[i]);
    }
    System.out.println();

    // enhanced for — 더 간결하게!
    System.out.println("=== enhanced for (향상된 for문) ===");
    for (String name : followers) {
        System.out.println("팔로워: " + name);
    }
    System.out.println();

    // int 배열 + enhanced for로 통계
    System.out.println("=== 게시물별 좋아요 통계 ===");
    int[] likes = {42, 128, 7, 95, 210};

    int total = 0;
    int max = 0;
    for (int like : likes) {
        total = total + like;
        if (like > max) {
            max = like;
        }
    }
    System.out.println("총 좋아요: " + total + "개");
    System.out.println("최고 좋아요: " + max + "개");
    System.out.println("평균 좋아요: " + (total / likes.length) + "개");
    System.out.println();

    // 기본 for vs enhanced for 비교
    System.out.println("=== 기본 for vs enhanced for ===");
    String[] hashtags = {"#맛집", "#여행", "#일상", "#운동"};

    System.out.print("기본 for : ");
    for (int i = 0; i < hashtags.length; i++) {
        System.out.print(hashtags[i] + " ");
    }
    System.out.println();

    System.out.print("enhanced: ");
    for (String tag : hashtags) {
        System.out.print(tag + " ");
    }
    System.out.println();
}
