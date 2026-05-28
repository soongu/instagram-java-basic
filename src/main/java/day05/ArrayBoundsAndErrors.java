void main() {
    // 배열의 인덱스 범위 — 안전하게 다루기
    String[] followers = {"장원영", "카리나", "윈터", "민지", "하니"};
    System.out.println("=== 배열 인덱스 안전하게 쓰기 ===");
    System.out.println();

    // 1) .length 로 끝까지 안전하게 순회
    System.out.println("[안전 패턴 1] .length 로 끝까지");
    System.out.println("팔로워 수: " + followers.length + "명");
    for (int i = 0; i < followers.length; i++) {
        System.out.println("  [" + i + "] " + followers[i]);
    }
    System.out.println();

    // 2) if 로 범위 체크 후 접근 (사용자가 입력한 번호가 안전한지 확인)
    System.out.println("[안전 패턴 2] if 로 범위 확인 후 접근");
    int[] requestedIndexes = {0, 2, 4, 7, -1};
    for (int idx : requestedIndexes) {
        if (idx >= 0 && idx < followers.length) {
            System.out.println("  요청 " + idx + "번 -> " + followers[idx]);
        } else {
            System.out.println("  요청 " + idx + "번 -> 범위 밖! 접근 안 함");
        }
    }
    System.out.println();

    // 3) 자주 하는 실수: .length 가 아니라 .length - 1 이 마지막 인덱스
    System.out.println("[헷갈리는 자리] 마지막 인덱스는 length - 1");
    System.out.println("  .length = " + followers.length);
    System.out.println("  마지막 인덱스 = " + (followers.length - 1));
    System.out.println("  마지막 사람 = " + followers[followers.length - 1]);
    System.out.println();

    // 4) (실험용) 일부러 범위 밖 접근 — 주석 풀어보기
    //    실행하면 ArrayIndexOutOfBoundsException 이 발생해요.
    //    실제 코드에서는 절대 이렇게 두지 않아요.
    //
    // String wrong = followers[5];
    // System.out.println(wrong);

    System.out.println("(맨 아래 주석을 풀어서 일부러 범위 밖 접근 시 어떤 에러가 뜨는지 확인해보세요)");
}
