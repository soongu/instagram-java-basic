// day07/InstagramUserManager.java
// Phase 1 종합 실습 — 인스타 사용자 관리 미니 분석기.
// 지난 시간까지 배운 변수 / 배열 / 메서드 / 제어문을 한 파일에 모아
// "평행 배열로 사용자 데이터를 관리하고, 검색하고, 추천하는" 작은 프로그램을 만들어요.
// 메서드가 다른 메서드를 호출하면서 작은 부품들이 큰 기능으로 합쳐지는 모습을 봅니다.

public static void main(String[] args) {
    // 추천 사용자 6명 — 평행 배열 (같은 인덱스가 한 사람을 가리킴)
    // 지난 시간 데이터에 한 명을 더하고, 추천 점수 계산용 배열 두 개를 추가했어요.
    String[] usernames     = {"jaehoon_dev", "minji_cafe", "seungwoo", "soyeon_art", "wooseok99", "hayoung_food"};
    int[] followers        = {  1240,         8500,         320,         4100,         15800,        2300       };
    int[] posts            = {    42,          150,          12,          88,           320,          67         };
    int[] mutualFriends    = {     8,           23,           2,          15,            40,           11         };
    int[] daysActive       = {   120,          365,          30,         210,           500,          95         };

    if (args.length == 0) {
        // 검색어가 없으면 — 전체 사용자 목록 + 추천 목록을 보여줘요 (기본 동작)
        System.out.println("=== 전체 사용자 목록 ===");
        printAllUsers(usernames, followers, posts);

        System.out.println();
        System.out.println("=== 팔로워 상위 3명 ===");
        int[] topIndexes = findTopFollowers(usernames, followers, 3);
        for (int rank = 0; rank < topIndexes.length; rank++) {
            int idx = topIndexes[rank];
            System.out.println((rank + 1) + "위  @" + usernames[idx]
                    + "  (팔로워 " + formatFollowers(followers[idx]) + ")");
        }

        System.out.println();
        System.out.println("=== 추천 점수 & 등급 ===");
        printRecommendations(usernames, followers, posts, mutualFriends, daysActive);
    } else {
        // 검색어가 있으면 — args[0] 을 이름으로 찾아 상세 정보를 보여줘요
        String target = args[0];
        int idx = searchUserByName(usernames, target);
        if (idx == -1) {
            System.out.println("'" + target + "' 사용자를 찾을 수 없어요.");
            System.out.println("등록된 사용자: ");
            for (int i = 0; i < usernames.length; i++) {
                System.out.println("  - " + usernames[i]);
            }
        } else {
            int score = calculateRecommendScore(followers[idx], posts[idx],
                    mutualFriends[idx], daysActive[idx]);
            System.out.println("=== @" + usernames[idx] + " 상세 정보 ===");
            System.out.println("팔로워   : " + formatFollowers(followers[idx]));
            System.out.println("게시물   : " + posts[idx] + "개");
            System.out.println("함께 아는 친구: " + mutualFriends[idx] + "명");
            System.out.println("활동 일수 : " + daysActive[idx] + "일");
            System.out.println("추천 점수 : " + score + "점 → " + classifyScore(score));
        }
    }
}

// 1000 이상이면 "1.2K" 처럼 줄여서 보여줘요 — 지난 시간 formatCount 패턴을 그대로 활용
static String formatFollowers(int count) {
    if (count >= 1_000) {
        return (count / 1_000) + "." + ((count / 100) % 10) + "K";
    }
    return String.valueOf(count);
}

// 전체 사용자를 한 명씩 순회하며 출력 — 안에서 formatFollowers 를 불러요 (메서드가 메서드를 호출)
static void printAllUsers(String[] usernames, int[] followers, int[] posts) {
    for (int i = 0; i < usernames.length; i++) {
        System.out.println("@" + usernames[i]
                + "  팔로워 " + formatFollowers(followers[i])
                + "  게시물 " + posts[i] + "개");
    }
}

// 이름으로 사용자를 찾아 인덱스를 돌려줘요. 없으면 -1.
// (배열에서 "못 찾았다" 를 표현할 때 -1 인덱스를 쓰는 건 아주 흔한 약속이에요)
static int searchUserByName(String[] usernames, String target) {
    for (int i = 0; i < usernames.length; i++) {
        if (usernames[i].equals(target)) {
            return i;
        }
    }
    return -1;
}

// 팔로워가 가장 많은 상위 n명의 "인덱스" 를 배열로 돌려줘요.
// 평행 배열이라 Arrays.sort 로 바로 정렬할 수 없으니,
// "가장 큰 값의 위치를 n번 골라내는" 방식으로 직접 구현해요.
static int[] findTopFollowers(String[] usernames, int[] followers, int n) {
    int[] result = new int[n];
    boolean[] used = new boolean[followers.length];  // 이미 뽑은 위치는 다시 안 고르도록 표시

    for (int rank = 0; rank < n; rank++) {
        int maxIndex = -1;
        for (int i = 0; i < followers.length; i++) {
            if (used[i]) {
                continue;  // 이미 뽑힌 사람은 건너뛰어요
            }
            if (maxIndex == -1 || followers[i] > followers[maxIndex]) {
                maxIndex = i;
            }
        }
        result[rank] = maxIndex;
        used[maxIndex] = true;  // 방금 1등으로 뽑았으니 다음 번엔 제외
    }
    return result;
}

// 추천 점수 계산 — 네 가지 정보를 가중합으로 섞어요 (숫자가 클수록 더 추천)
static int calculateRecommendScore(int followers, int posts, int mutualFriends, int daysActive) {
    int score = 0;
    score = score + followers / 100;     // 팔로워 100명당 1점
    score = score + posts / 5;           // 게시물 5개당 1점
    score = score + mutualFriends * 10;  // 함께 아는 친구는 가중치 큼 (1명당 10점)
    score = score + daysActive / 30;     // 활동 30일당 1점
    return score;
}

// 점수를 사람이 읽기 좋은 등급 문자열로 바꿔줘요
static String classifyScore(int score) {
    if (score >= 300) {
        return "강력 추천";
    } else if (score >= 150) {
        return "추천";
    } else if (score >= 70) {
        return "보통";
    } else {
        return "관심 낮음";
    }
}

// 종합 메서드 — 한 줄 안에서 calculateRecommendScore → classifyScore → formatFollowers 를
// 줄줄이 불러요. 작은 부품들이 합쳐져 "추천 목록 한 표" 가 완성되는 클라이맥스예요.
static void printRecommendations(String[] usernames, int[] followers, int[] posts,
        int[] mutualFriends, int[] daysActive) {
    for (int i = 0; i < usernames.length; i++) {
        int score = calculateRecommendScore(followers[i], posts[i],
                mutualFriends[i], daysActive[i]);
        String grade = classifyScore(score);
        System.out.println("@" + usernames[i]
                + "  (팔로워 " + formatFollowers(followers[i]) + ")"
                + "  점수 " + score + "점"
                + "  →  " + grade);
    }
}
