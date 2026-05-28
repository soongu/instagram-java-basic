void main() {
    // 자동 형변환 — 작은 그릇에서 큰 그릇으로 (안전)
    int number = 100;
    long bigNumber = number;
    double decimal = number;
    System.out.println("int: " + number);
    System.out.println("int → long: " + bigNumber);
    System.out.println("int → double: " + decimal);

    // 강제 형변환 — 큰 그릇에서 작은 그릇으로 (명시적)
    double pi = 3.14159;
    int intPi = (int) pi;
    System.out.println("double: " + pi);
    System.out.println("double → int: " + intPi);

    // 데이터 손실 주의
    int bigValue = 300;
    byte smallValue = (byte) bigValue;
    System.out.println("int 300 → byte: " + smallValue);

    // long → int
    long longValue = 2_000_000_000L;
    int intValue = (int) longValue;
    System.out.println("long → int: " + intValue);

    // char와 int 사이의 변환
    char letter = 'A';
    int letterNumber = letter;
    System.out.println("'A'의 숫자 값: " + letterNumber);

    char fromNumber = (char) 66;
    System.out.println("66의 문자: " + fromNumber);
}
