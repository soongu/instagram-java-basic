// day06/RecursionBasics.java
// Step 8 — 재귀 기초: 팩토리얼과 피보나치
// 메서드가 *자기 자신을 부르는* 형태. 종료 조건을 꼭 챙겨야 무한 호출을 막아요.

public static void main(String[] args) {
    System.out.println("=== 팩토리얼 — 가장 익숙한 재귀 예제 ===");
    System.out.println("0! = " + factorial(0)); // 1 (정의)
    System.out.println("1! = " + factorial(1));
    System.out.println("5! = " + factorial(5));
    System.out.println("10! = " + factorial(10));

    System.out.println();
    System.out.println("=== 같은 일을 반복문으로도 가능 — 결과는 같음 ===");
    System.out.println("5! (반복문 버전) = " + factorialLoop(5));
    System.out.println("10! (반복문 버전) = " + factorialLoop(10));

    System.out.println();
    System.out.println("=== 피보나치 — 재귀의 위력과 한계 ===");
    System.out.println("fib(0) = " + fibonacci(0));
    System.out.println("fib(1) = " + fibonacci(1));
    System.out.println("fib(5) = " + fibonacci(5));
    System.out.println("fib(10) = " + fibonacci(10));
    System.out.println("fib(20) = " + fibonacci(20));
    // 주의: fib(40) 부터는 같은 계산을 반복해서 수 초가 걸려요.
    // 큰 n 은 반복문 버전이 훨씬 빠릅니다.

    System.out.println();
    System.out.println("=== 피보나치 반복문 버전 — 같은 답, 훨씬 빠름 ===");
    System.out.println("fib(20) (반복문) = " + fibonacciLoop(20));
    System.out.println("fib(40) (반복문) = " + fibonacciLoop(40));

    System.out.println();
    System.out.println("=== 카운트다운 — 재귀의 *흐름* 을 눈으로 확인 ===");
    countdown(5);
    System.out.println("끝!");
}

// 팩토리얼 — n! = n * (n-1)!
// 종료 조건 (base case): n <= 1 이면 1 을 그대로 돌려준다 (더 이상 자기 호출 안 함).
static long factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

// 반복문 버전 — 비교용
static long factorialLoop(int n) {
    long result = 1;
    for (int i = 2; i <= n; i++) {
        result = result * i;
    }
    return result;
}

// 피보나치 — fib(n) = fib(n-1) + fib(n-2)
// 종료 조건: n <= 1 이면 n 자체를 돌려준다 (0 또는 1).
// 자기 자신을 *두 번* 부르기 때문에 같은 계산을 중복하게 됨 -> 큰 n 은 매우 느려요.
static int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

// 피보나치 반복문 버전 — O(n) 으로 훨씬 빠름
static long fibonacciLoop(int n) {
    if (n <= 1) {
        return n;
    }
    long prev = 0;
    long curr = 1;
    for (int i = 2; i <= n; i++) {
        long next = prev + curr;
        prev = curr;
        curr = next;
    }
    return curr;
}

// 카운트다운 — 재귀가 *위에서 아래로* 흘러가는 모습을 출력으로 보여줌
static void countdown(int n) {
    if (n <= 0) {
        return; // 종료 조건
    }
    System.out.println("  카운트: " + n);
    countdown(n - 1); // 자기 호출 — n 이 하나씩 줄어듦
}
