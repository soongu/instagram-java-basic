// day06/PassByValueVsReference.java
// Step 4 — 값 전달 vs 참조 전달
// 기본형은 메서드 안에서 바꿔도 원본이 안 바뀌고, 배열은 바뀐다.
// 메모리에서 무엇이 복사되는지 직접 출력으로 확인.

public static void main(String[] args) {
    System.out.println("=== 기본형 (int) — 값 전달 ===");
    int followerCount = 100;
    System.out.println("호출 전 followerCount: " + followerCount);

    addOneFollower(followerCount);

    System.out.println("호출 후 followerCount: " + followerCount);
    System.out.println("-> 메서드 안에서 +1 했지만 원본은 그대로 100");

    System.out.println();
    System.out.println("=== 배열 (int[]) — 참조 전달 ===");
    int[] likesPerPost = {10, 20, 30, 40, 50};
    System.out.println("호출 전 likesPerPost[0]: " + likesPerPost[0]);

    doubleAllLikes(likesPerPost);

    System.out.println("호출 후 likesPerPost[0]: " + likesPerPost[0]);
    System.out.print("전체 배열: [");
    for (int i = 0; i < likesPerPost.length; i++) {
        System.out.print(likesPerPost[i]);
        if (i < likesPerPost.length - 1) {
            System.out.print(", ");
        }
    }
    System.out.println("]");
    System.out.println("-> 메서드 안에서 곱했더니 원본 배열이 그대로 바뀌어 있어요");

    System.out.println();
    System.out.println("=== 새 배열을 *return* 으로 돌려받는 안전한 방법 ===");
    int[] original = {1, 2, 3, 4, 5};
    int[] doubled = makeDoubledCopy(original);
    System.out.print("원본 (변경 없음): [");
    for (int i = 0; i < original.length; i++) {
        System.out.print(original[i]);
        if (i < original.length - 1) System.out.print(", ");
    }
    System.out.println("]");
    System.out.print("두 배 복사본    : [");
    for (int i = 0; i < doubled.length; i++) {
        System.out.print(doubled[i]);
        if (i < doubled.length - 1) System.out.print(", ");
    }
    System.out.println("]");
}

// 기본형 int 를 받아 +1 한다 — 하지만 메서드 안의 n 만 바뀌고 원본은 안 바뀐다.
static void addOneFollower(int n) {
    n = n + 1;
    System.out.println("  (메서드 안) 받은 n + 1 = " + n);
}

// 배열을 받아 원소들을 두 배로 만든다 — 메서드 밖 원본도 같이 바뀐다.
static void doubleAllLikes(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        arr[i] = arr[i] * 2;
    }
    System.out.println("  (메서드 안) 배열을 모두 두 배로");
}

// 원본을 건드리지 않고 두 배 복사본을 새로 만들어 돌려준다 — 안전한 패턴.
static int[] makeDoubledCopy(int[] source) {
    int[] result = new int[source.length];
    for (int i = 0; i < source.length; i++) {
        result[i] = source[i] * 2;
    }
    return result;
}
