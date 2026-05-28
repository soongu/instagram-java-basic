import java.util.Arrays;

void main() {
    // 종합 실습 — 게시물 5개를 해시태그로 필터링 + 좋아요 상위 3개 뽑기
    System.out.println("=== 인스타그램 게시물 필터 + 인기 정렬 ===");
    System.out.println();

    // 게시물 5개 — 같은 인덱스끼리 짝지어진 평행 배열
    String[] postTitles = {
            "홍대 카페 투어",
            "주말 등산 일기",
            "신상 카페 라떼",
            "강아지 산책",
            "카페에서 책 읽기"
    };
    int[] postLikes = {128, 42, 210, 95, 67};
    String[] postHashtags = {
            "#카페 #홍대 #주말",
            "#등산 #자연 #힐링",
            "#카페 #라떼 #신상",
            "#강아지 #산책 #일상",
            "#카페 #책 #힐링"
    };

    // 1) 해시태그 #카페 가 포함된 게시물 카운트
    String target = "#카페";
    System.out.println("[필터] " + target + " 가 들어간 게시물 찾기");
    int matched = 0;
    for (int i = 0; i < postTitles.length; i++) {
        // contains 는 문자열 안에 특정 글자가 들어있는지 확인하는 String 도구
        if (postHashtags[i].contains(target)) {
            matched++;
            System.out.println("  o " + postTitles[i] + " (좋아요 " + postLikes[i] + ")");
        }
    }
    System.out.println("총 " + matched + "개 게시물 발견 (전체 " + postTitles.length + "개 중)");
    System.out.println();

    // 2) 좋아요 상위 3개 뽑기 — 원본은 그대로, 복사본을 정렬
    System.out.println("[인기 순위] 좋아요 상위 3개");
    int[] sortedLikes = Arrays.copyOf(postLikes, postLikes.length);
    Arrays.sort(sortedLikes);
    System.out.println("정렬된 좋아요 (오름차순): " + Arrays.toString(sortedLikes));

    int topN = 3;
    System.out.println("Top " + topN + ":");
    for (int rank = 1; rank <= topN; rank++) {
        int targetLikes = sortedLikes[sortedLikes.length - rank];
        // 좋아요 값으로 원본 배열을 다시 뒤져서 제목 찾기
        for (int i = 0; i < postLikes.length; i++) {
            if (postLikes[i] == targetLikes) {
                System.out.println("  " + rank + "위. " + postTitles[i] + " (" + targetLikes + ")");
                break;
            }
        }
    }
}
