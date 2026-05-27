void main() {
    // int — 가장 많이 쓰는 정수형
    int population = 51_000_000;
    System.out.println("대한민국 인구: " + population);

    // long — 아주 큰 정수 (끝에 L 붙이기)
    long worldPopulation = 8_000_000_000L;
    System.out.println("세계 인구: " + worldPopulation);

    // byte — 아주 작은 범위 (-128 ~ 127)
    byte smallNumber = 100;
    System.out.println("byte: " + smallNumber);

    // short — 작은 범위 (-32,768 ~ 32,767)
    short mediumNumber = 30000;
    System.out.println("short: " + mediumNumber);

    // 각 타입의 최댓값 확인
    System.out.println("int 최댓값: " + Integer.MAX_VALUE);
    System.out.println("int 최솟값: " + Integer.MIN_VALUE);
    System.out.println("long 최댓값: " + Long.MAX_VALUE);
}
