void main() {
    // while — 팔로워 2배 성장 시뮬레이션
    System.out.println("=== while: 팔로워 2배 성장 시뮬레이션 ===");
    int followers = 100;
    int weeks = 0;

    while (followers < 10000) {
        followers = followers * 2;
        weeks++;
        System.out.println(weeks + "주차: 팔로워 " + followers + "명");
    }
    System.out.println("1만 돌파까지 " + weeks + "주 걸렸습니다!");
    System.out.println();

    // while — 피드 스크롤
    System.out.println("=== while: 피드 스크롤 ===");
    int position = 0;
    int totalPosts = 20;

    while (position < totalPosts) {
        position = position + 5;
        if (position > totalPosts) {
            position = totalPosts;
        }
        System.out.println("로딩 완료: " + position + "/" + totalPosts + "개");
    }
    System.out.println("모든 게시물을 불러왔습니다!");
    System.out.println();

    // do-while — 좋아요 목표 달성 (최소 한 번은 확인)
    System.out.println("=== do-while: 좋아요 목표 달성 ===");
    int likes = 0;
    int checkCount = 0;

    do {
        checkCount++;
        likes = likes + 18;
        System.out.println("확인 " + checkCount + "회: 좋아요 " + likes + "개");
    } while (likes < 50);
    System.out.println("50개 돌파! 인기 게시물 등록!");
    System.out.println();

    // while vs do-while 차이: 조건이 처음부터 false면?
    System.out.println("=== while vs do-while 차이 ===");
    int count = 10;

    System.out.print("while (count < 5): ");
    while (count < 5) {
        System.out.print(count + " ");
        count++;
    }
    System.out.println("→ 실행 안 됨!");

    count = 10;
    System.out.print("do-while (count < 5): ");
    do {
        System.out.print(count + " ");
        count++;
    } while (count < 5);
    System.out.println("→ 한 번은 실행됨!");
}
