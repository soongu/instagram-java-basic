void main() {
    // 배열 선언/초기화 3가지 방식
    System.out.println("=== 인스타그램 배열 3가지 만들기 ===");
    System.out.println();

    // 방식 1: 리터럴 (값을 알고 있을 때 가장 편함)
    String[] followers = {"장원영", "카리나", "윈터", "민지", "하니"};
    System.out.println("[방식 1] 리터럴 — 팔로워 목록");
    System.out.println("첫 팔로워: " + followers[0]);
    System.out.println("마지막 팔로워: " + followers[followers.length - 1]);
    System.out.println("총 인원: " + followers.length + "명");
    System.out.println();

    // 방식 2: new + 크기만 (값은 나중에 채움, 정수는 0으로 자동 초기화)
    int[] likes = new int[5];
    System.out.println("[방식 2] new + 크기 — 좋아요 카운터 5개");
    System.out.println("처음 값 (자동 0 채움): " + likes[0]);
    System.out.println("마지막 칸: " + likes[likes.length - 1]);
    System.out.println("크기: " + likes.length + "칸");
    likes[0] = 42;
    likes[4] = 128;
    System.out.println("값 채운 뒤 첫 칸: " + likes[0]);
    System.out.println("값 채운 뒤 마지막 칸: " + likes[4]);
    System.out.println();

    // 방식 3: new + 값 (방식 1과 비슷하지만 명시적)
    boolean[] isFollowing = new boolean[]{true, false, true, true};
    System.out.println("[방식 3] new + 값 — 팔로우 여부 4명");
    System.out.println("첫 사람 팔로우 중? " + isFollowing[0]);
    System.out.println("마지막 사람 팔로우 중? " + isFollowing[isFollowing.length - 1]);
    System.out.println("배열 크기: " + isFollowing.length);
}
