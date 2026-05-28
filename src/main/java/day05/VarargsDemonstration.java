void main() {
    // varargs — 개수가 정해지지 않은 인자를 배열처럼 받기
    // 메서드 자체는 다음 시간에 본격적으로 배워요.
    // 지금은 "배열을 자동으로 받는 메서드" 형태만 살짝 미리 보고 가요.
    System.out.println("=== 인스타 해시태그 다양한 개수로 출력하기 ===");
    System.out.println();

    System.out.println("[게시물 1] 해시태그 1개");
    printHashtags("#일상");
    System.out.println();

    System.out.println("[게시물 2] 해시태그 2개");
    printHashtags("#카페", "#디저트");
    System.out.println();

    System.out.println("[게시물 3] 해시태그 4개");
    printHashtags("#OOTD", "#패션", "#스트릿", "#가을");
    System.out.println();

    System.out.println("[게시물 4] 해시태그 0개");
    printHashtags();
}

// String... tags 는 "0개 이상의 String 을 배열로 받는다" 는 뜻.
// 메서드 안에서는 그냥 String[] 배열처럼 다루면 돼요.
static void printHashtags(String... tags) {
    System.out.println("  해시태그 " + tags.length + "개:");
    if (tags.length == 0) {
        System.out.println("    (없음)");
        return;
    }
    for (String tag : tags) {
        System.out.println("    " + tag);
    }
}
