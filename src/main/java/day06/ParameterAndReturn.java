// day06/ParameterAndReturn.java
// Step 3 — 매개변수와 반환값: 실제 데이터 흐름
// 매개변수 여러 개를 받아 계산한 결과를 반환하고, 그 결과를 또 다른 메서드에 넘기는 흐름.

public static void main(String[] args) {
    System.out.println("=== 추천 점수 계산 ===");
    // (서로 맞팔 수, 공통 해시태그 수, 마지막 활동 일수, 게시물 수)
    int scoreUserA = calculateRecommendScore(12, 5, 2, 30);
    int scoreUserB = calculateRecommendScore(3, 0, 30, 5);
    int scoreUserC = calculateRecommendScore(20, 8, 0, 100);

    System.out.println("재훈님 추천 점수: " + scoreUserA);
    System.out.println("민지님 추천 점수: " + scoreUserB);
    System.out.println("승우님 추천 점수: " + scoreUserC);

    System.out.println();
    System.out.println("=== 추천 등급으로 분류 ===");
    // 메서드의 반환값을 다른 메서드의 매개변수로 — 흐름 연결
    System.out.println("재훈님: " + classifyRecommendation(scoreUserA));
    System.out.println("민지님: " + classifyRecommendation(scoreUserB));
    System.out.println("승우님: " + classifyRecommendation(scoreUserC));

    System.out.println();
    System.out.println("=== 한 줄로 묶어서 부르기 ===");
    // 메서드 호출 안에 메서드 호출 — 결과가 곧장 다음 메서드로 전달
    System.out.println("새 사용자: " + classifyRecommendation(calculateRecommendScore(0, 0, 90, 1)));
}

// 추천 점수 계산
// - 서로 맞팔 수 (mutualFollows): 1명당 5점
// - 공통 해시태그 수 (commonHashtags): 1개당 3점
// - 마지막 활동 일수 (daysSinceActive): 적을수록 좋음 -> (30 - days) 점, 최대 30
// - 게시물 수 (postCount): 1개당 1점 (단 100점 상한)
static int calculateRecommendScore(int mutualFollows, int commonHashtags, int daysSinceActive, int postCount) {
    int score = 0;
    score = score + mutualFollows * 5;
    score = score + commonHashtags * 3;

    int activityScore = 30 - daysSinceActive;
    if (activityScore < 0) {
        activityScore = 0;
    }
    score = score + activityScore;

    int postScore = postCount;
    if (postScore > 100) {
        postScore = 100;
    }
    score = score + postScore;

    return score;
}

// 점수를 사람이 읽는 등급으로 분류
static String classifyRecommendation(int score) {
    if (score >= 150) {
        return "강력 추천 (" + score + "점)";
    }
    if (score >= 80) {
        return "추천 (" + score + "점)";
    }
    if (score >= 30) {
        return "관심 있을 수도 (" + score + "점)";
    }
    return "추천 보류 (" + score + "점)";
}
