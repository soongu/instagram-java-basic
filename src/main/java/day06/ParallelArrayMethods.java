// day06/ParallelArrayMethods.java
// Step 7 — 평행 배열 처리를 메서드로 추출
// 지난 시간에 봤던 "여러 배열이 같은 인덱스를 공유하는" 패턴.
// 반복되는 로직을 메서드로 묶으면 main 이 한눈에 읽혀요.

public static void main(String[] args) {
    // 추천 사용자 5명 — 평행 배열 패턴 (같은 인덱스가 한 사람을 가리킴)
    String[] usernames = {"jaehoon_dev", "minji_cafe", "seungwoo", "soyeon_art", "wooseok99"};
    int[] followers   = {  1240,         8500,         320,         4100,         15800   };
    int[] posts       = {    42,          150,          12,          88,           320     };

    System.out.println("=== 메서드 없이 — main 안에 모든 출력 로직이 다 박혀 있음 ===");
    for (int i = 0; i < usernames.length; i++) {
        // 한 사용자를 출력하는 데에만 4~5 줄이 필요
        System.out.print("@" + usernames[i]);
        System.out.print("  팔로워 ");
        if (followers[i] >= 1_000) {
            System.out.print((followers[i] / 1_000) + "." + ((followers[i] / 100) % 10) + "K");
        } else {
            System.out.print(followers[i]);
        }
        System.out.println("  게시물 " + posts[i] + "개");
    }

    System.out.println();
    System.out.println("=== 메서드로 묶고 나면 — main 이 두 줄짜리로 깨끗해짐 ===");
    printAllUsers(usernames, followers, posts);

    System.out.println();
    System.out.println("=== 한 사람만 따로 출력하고 싶을 때도 재사용 ===");
    printOneUser(usernames[2], followers[2], posts[2]);
    System.out.println();
    System.out.println("=== 같은 데이터로 통계도 메서드 한 줄 ===");
    System.out.println("팔로워 합계: " + sumFollowers(followers));
    System.out.println("평균 게시물 수: " + averagePosts(posts));

    // 다음 시간 다리:
    // 세 배열 (usernames / followers / posts) 가 항상 같이 다녀야 한다는 불편함이 남아요.
    // "한 사용자" 를 하나의 묶음으로 표현할 방법은 다음 시간 즈음에 만나게 됩니다.
}

// 한 사용자의 한 줄 출력 — 추출 핵심
static void printOneUser(String username, int followerCount, int postCount) {
    System.out.print("@" + username);
    System.out.print("  팔로워 " + formatCount(followerCount));
    System.out.println("  게시물 " + postCount + "개");
}

// 전체 순회 — 평행 배열 3개를 받아 한 사람씩 출력에 위임
static void printAllUsers(String[] usernames, int[] followers, int[] posts) {
    for (int i = 0; i < usernames.length; i++) {
        printOneUser(usernames[i], followers[i], posts[i]);
    }
}

// 1000 이상이면 K 표기로 — 통계 출력에서 재사용
static String formatCount(int count) {
    if (count >= 1_000) {
        return (count / 1_000) + "." + ((count / 100) % 10) + "K";
    }
    return String.valueOf(count);
}

// 팔로워 합계
static int sumFollowers(int[] followers) {
    int total = 0;
    for (int f : followers) {
        total = total + f;
    }
    return total;
}

// 평균 게시물 수 (소수점 한 자리 정수 근사)
static int averagePosts(int[] posts) {
    if (posts.length == 0) {
        return 0;
    }
    int total = 0;
    for (int p : posts) {
        total = total + p;
    }
    return total / posts.length;
}
