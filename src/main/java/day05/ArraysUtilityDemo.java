import java.util.Arrays;

void main() {
    // Arrays 도구 상자 — 정렬, 복사, 출력
    int[] likes = {42, 128, 7, 95, 210, 33, 67};
    System.out.println("=== Arrays 도구 상자 활용 ===");
    System.out.println();

    // Arrays.toString — 디버깅할 때 배열 통째로 보기
    System.out.println("[Arrays.toString] 배열을 한 줄 문자열로");
    System.out.println("원본: " + Arrays.toString(likes));
    System.out.println();

    // Arrays.sort — 오름차순 정렬 (원본 배열을 직접 정렬함)
    Arrays.sort(likes);
    System.out.println("[Arrays.sort] 오름차순 정렬 후");
    System.out.println("정렬됨: " + Arrays.toString(likes));
    System.out.println("최저 좋아요: " + likes[0]);
    System.out.println("최고 좋아요: " + likes[likes.length - 1]);
    System.out.println();

    // Arrays.copyOf — 상위 N개만 추리기 (원본은 그대로, 새 배열 반환)
    // 정렬은 오름차순이므로 뒤쪽이 큰 값. 뒤집어서 보려면 직접 인덱스를 거꾸로
    System.out.println("[Arrays.copyOf] 좋아요 상위 3개 추리기");
    int topN = 3;
    int[] top = new int[topN];
    for (int i = 0; i < topN; i++) {
        top[i] = likes[likes.length - 1 - i];
    }
    System.out.println("Top " + topN + ": " + Arrays.toString(top));
    System.out.println();

    // Arrays.copyOf 본래 형태도 한 번 보여주기 — 앞에서부터 N개
    int[] firstThree = Arrays.copyOf(likes, 3);
    System.out.println("[Arrays.copyOf] 정렬된 배열 앞 3개 (가장 작은 3개)");
    System.out.println("앞 3개: " + Arrays.toString(firstThree));
}
