// day06/MethodBasics.java
// Step 2 — 메서드 기본 문법: 정의와 호출
// 같은 좋아요 포맷팅 로직을 메서드로 묶어서 재사용하는 첫 예제.

public static void main(String[] args) {
    System.out.println("=== 메서드 없이 — 같은 로직을 반복 ===");
    int likesA = 1234;
    String formattedA;
    if (likesA >= 1_000_000) {
        formattedA = (likesA / 1_000_000) + "." + ((likesA / 100_000) % 10) + "M";
    } else if (likesA >= 1_000) {
        formattedA = (likesA / 1_000) + "." + ((likesA / 100) % 10) + "K";
    } else {
        formattedA = String.valueOf(likesA);
    }
    System.out.println("게시물 A: " + formattedA);

    int likesB = 45_678;
    String formattedB;
    if (likesB >= 1_000_000) {
        formattedB = (likesB / 1_000_000) + "." + ((likesB / 100_000) % 10) + "M";
    } else if (likesB >= 1_000) {
        formattedB = (likesB / 1_000) + "." + ((likesB / 100) % 10) + "K";
    } else {
        formattedB = String.valueOf(likesB);
    }
    System.out.println("게시물 B: " + formattedB);

    System.out.println();
    System.out.println("=== 메서드로 묶어서 — 한 줄로 끝 ===");
    System.out.println("게시물 A: " + formatLikes(1234));
    System.out.println("게시물 B: " + formatLikes(45_678));
    System.out.println("게시물 C: " + formatLikes(999));
    System.out.println("게시물 D: " + formatLikes(1_234_567));
    System.out.println("게시물 E: " + formatLikes(42));

    System.out.println();
    System.out.println("=== void 메서드 — 값을 돌려주지 않고 화면 출력만 ===");
    printSeparator();
    System.out.println("이 줄 위아래에 구분선이 찍혀요");
    printSeparator();
}

// 좋아요 수를 사람이 읽기 좋게 포맷팅
// 1234 -> "1.2K", 1234567 -> "1.2M", 999 -> "999"
static String formatLikes(int likes) {
    if (likes >= 1_000_000) {
        return (likes / 1_000_000) + "." + ((likes / 100_000) % 10) + "M";
    }
    if (likes >= 1_000) {
        return (likes / 1_000) + "." + ((likes / 100) % 10) + "K";
    }
    return String.valueOf(likes);
}

// 화면에 구분선을 출력만 하고 돌려주는 값은 없음 (반환 타입 void)
static void printSeparator() {
    System.out.println("--------------------");
}
