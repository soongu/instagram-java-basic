void main() {
    // if — 조건이 true면 실행
    int followerCount = 1500;

    if (followerCount >= 1000) {
        System.out.println("팔로워 1,000명을 돌파했습니다!");
    }

    // if-else — 둘 중 하나
    boolean isPrivate = true;

    if (isPrivate) {
        System.out.println("비공개 계정입니다. 팔로우 요청을 보내세요.");
    } else {
        System.out.println("공개 계정입니다. 바로 팔로우할 수 있습니다.");
    }

    // if-else if-else — 여러 갈래
    int postCount = 150;

    if (postCount >= 1000) {
        System.out.println("게시물 등급: 다작왕");
    } else if (postCount >= 100) {
        System.out.println("게시물 등급: 활발");
    } else if (postCount >= 10) {
        System.out.println("게시물 등급: 보통");
    } else {
        System.out.println("게시물 등급: 시작 단계");
    }

    // 인플루언서 등급 판정
    if (followerCount >= 1000000) {
        System.out.println("등급: 메가 인플루언서");
    } else if (followerCount >= 100000) {
        System.out.println("등급: 매크로 인플루언서");
    } else if (followerCount >= 10000) {
        System.out.println("등급: 마이크로 인플루언서");
    } else if (followerCount >= 1000) {
        System.out.println("등급: 나노 인플루언서");
    } else {
        System.out.println("등급: 일반 사용자");
    }

    // 중첩 if — 조건 안에 조건
    boolean isLoggedIn = true;
    boolean isVerified = false;

    if (isLoggedIn) {
        System.out.println("로그인 상태입니다.");
        if (isVerified) {
            System.out.println("인증 배지가 있습니다.");
        } else {
            System.out.println("아직 인증 배지가 없습니다.");
        }
    } else {
        System.out.println("로그인이 필요합니다.");
    }

    // 비교 + 논리 연산자 조합
    int age = 20;
    boolean hasId = true;

    if (age >= 18 && hasId) {
        System.out.println("성인 인증 완료!");
    } else {
        System.out.println("성인 인증에 실패했습니다.");
    }
}
