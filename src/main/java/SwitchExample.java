void main() {
    // switch 표현식 (JDK 14+) — 화살표 구문
    String notificationType = "like";

    // 알림 종류별 메시지
    String message = switch (notificationType) {
        case "like" -> "누군가 회원님의 게시물을 좋아합니다.";
        case "comment" -> "누군가 댓글을 남겼습니다.";
        case "follow" -> "새로운 팔로워가 생겼습니다.";
        case "mention" -> "누군가 회원님을 언급했습니다.";
        default -> "새로운 알림이 있습니다.";
    };
    System.out.println("알림: " + message);

    // 요일별 인스타그램 활동 추천
    int dayOfWeek = 3;

    String activity = switch (dayOfWeek) {
        case 1 -> "월요일: 한 주의 시작을 기록하세요";
        case 2 -> "화요일: 일상 스토리를 올려보세요";
        case 3 -> "수요일: 릴스를 촬영해 보세요";
        case 4 -> "목요일: #throwback 사진을 공유하세요";
        case 5 -> "금요일: 주말 계획을 공유하세요";
        case 6, 7 -> "주말: 나들이 사진을 올려보세요";
        default -> "잘못된 요일입니다";
    };
    System.out.println(activity);

    // 계정 등급에 따른 기능 제한
    String accountTier = "premium";

    int maxPostsPerDay = switch (accountTier) {
        case "basic" -> 5;
        case "premium" -> 20;
        case "business" -> 50;
        default -> 3;
    };
    System.out.println("하루 최대 게시물 수: " + maxPostsPerDay);

    // 이모지 반응 — 여러 case를 묶기
    String reaction = "heart";

    String emoji = switch (reaction) {
        case "heart", "love" -> "❤️";
        case "fire", "hot" -> "🔥";
        case "laugh", "haha", "lol" -> "😂";
        case "sad", "cry" -> "😢";
        default -> "👍";
    };
    System.out.println("반응: " + emoji);
}
