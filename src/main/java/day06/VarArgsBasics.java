// day06/VarArgsBasics.java
// Step 6 — varargs 정식 학습 (지난 시간 미리보기를 정식으로)
// 매개변수 개수가 미리 정해지지 않은 메서드 만들기.

public static void main(String[] args) {
    System.out.println("=== 해시태그 출력 — 개수가 들쭉날쭉 ===");
    printHashtags("#일상");
    printHashtags("#카페", "#디저트");
    printHashtags("#OOTD", "#패션", "#가을", "#스트릿");
    printHashtags(); // 0개도 OK
    System.out.println();

    System.out.println("=== 좋아요 합계 — 인자 몇 개든 ===");
    System.out.println("게시물 2개 합계: " + sumLikes(120, 340));
    System.out.println("게시물 5개 합계: " + sumLikes(120, 340, 50, 700, 900));
    System.out.println("게시물 0개 합계: " + sumLikes()); // 빈 호출
    System.out.println();

    System.out.println("=== 고정 매개변수 + varargs 섞기 ===");
    introduce("재훈", "개발자", "사진가", "커피러버");
    introduce("민지", "디자이너");
    introduce("승우"); // 직함 0개

    System.out.println();
    System.out.println("=== 배열도 그대로 넘길 수 있음 ===");
    String[] preset = {"#운동", "#오운완", "#홈트"};
    printHashtags(preset); // 배열을 그대로 넘겨도 OK
}

// String... tags 는 0개 이상의 String 을 배열로 받음
// 메서드 안에서는 그냥 String[] 처럼 다룬다.
static void printHashtags(String... tags) {
    System.out.print("해시태그 " + tags.length + "개: ");
    if (tags.length == 0) {
        System.out.println("(없음)");
        return;
    }
    for (int i = 0; i < tags.length; i++) {
        System.out.print(tags[i]);
        if (i < tags.length - 1) {
            System.out.print(" ");
        }
    }
    System.out.println();
}

// int... 도 같은 원리 — 0개 이상의 int 를 배열로 받음
static int sumLikes(int... likesArray) {
    int total = 0;
    for (int likes : likesArray) {
        total = total + likes;
    }
    return total;
}

// 고정 매개변수가 앞에 오고, varargs 는 *반드시 맨 뒤* 에만 올 수 있음.
// introduce(String name, String... titles) — name 은 1개 고정, titles 는 0개 이상.
static void introduce(String name, String... titles) {
    System.out.print(name + " 님");
    if (titles.length == 0) {
        System.out.println(" (소개 없음)");
        return;
    }
    System.out.print(" — ");
    for (int i = 0; i < titles.length; i++) {
        System.out.print(titles[i]);
        if (i < titles.length - 1) {
            System.out.print(", ");
        }
    }
    System.out.println();
}
