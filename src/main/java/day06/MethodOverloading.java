// day06/MethodOverloading.java
// Step 5 — 메서드 오버로딩: 같은 이름, 다른 매개변수
// 같은 이름의 메서드를 매개변수 타입/개수만 바꿔서 여러 버전 만들기.

public static void main(String[] args) {
    System.out.println("=== 사용자 소개 — 정보가 있는 만큼만 출력 ===");
    System.out.println(describeUser("jaehoon_dev"));
    System.out.println(describeUser("minji_cafe", 1240));
    System.out.println(describeUser("seungwoo", 8500, "여행 좋아요"));

    System.out.println();
    System.out.println("=== 좋아요 수 — int 든 long 이든 같은 이름으로 ===");
    // 인기 게시물은 int 범위(약 21억) 를 넘을 수 있어서 long 이 필요할 수 있어요
    System.out.println("작은 게시물: " + formatLikes(1234));
    System.out.println("초대형 게시물: " + formatLikes(5_000_000_000L));

    System.out.println();
    System.out.println("=== 합계 — 인자 2개 / 3개 ===");
    System.out.println("두 게시물 합계: " + sumLikes(120, 340));
    System.out.println("세 게시물 합계: " + sumLikes(120, 340, 50));
}

// 1. username 만 — 짧은 소개
static String describeUser(String username) {
    return "@" + username;
}

// 2. username + 팔로워 수 — 팔로워까지 포함
static String describeUser(String username, int followers) {
    return "@" + username + " (팔로워 " + followers + "명)";
}

// 3. username + 팔로워 + 한 줄 소개 — 풀 정보
static String describeUser(String username, int followers, String bio) {
    return "@" + username + " (팔로워 " + followers + "명) — " + bio;
}

// int 버전 — 일반 게시물
static String formatLikes(int likes) {
    if (likes >= 1_000_000) {
        return (likes / 1_000_000) + "." + ((likes / 100_000) % 10) + "M";
    }
    if (likes >= 1_000) {
        return (likes / 1_000) + "." + ((likes / 100) % 10) + "K";
    }
    return String.valueOf(likes);
}

// long 버전 — 21억 넘는 초대형 게시물용 (이름은 같지만 매개변수 타입이 달라 따로 인식)
static String formatLikes(long likes) {
    if (likes >= 1_000_000_000L) {
        return (likes / 1_000_000_000L) + "." + ((likes / 100_000_000L) % 10) + "B";
    }
    if (likes >= 1_000_000L) {
        return (likes / 1_000_000L) + "." + ((likes / 100_000L) % 10) + "M";
    }
    if (likes >= 1_000L) {
        return (likes / 1_000L) + "." + ((likes / 100L) % 10) + "K";
    }
    return String.valueOf(likes);
}

// 인자 2개 합계
static int sumLikes(int a, int b) {
    return a + b;
}

// 인자 3개 합계 — 이름은 같지만 매개변수 개수가 달라 따로 인식
static int sumLikes(int a, int b, int c) {
    return a + b + c;
}
