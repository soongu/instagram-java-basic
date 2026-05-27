void main() {
    // final — 변하지 않는 값 (상수)
    final double PI = 3.14159;
    final int MAX_RETRY = 3;
    final String GREETING = "안녕하세요";

    System.out.println("원주율: " + PI);
    System.out.println("최대 재시도: " + MAX_RETRY);
    System.out.println(GREETING);

    // final 변수는 변경 불가 — 아래 주석을 해제하면 에러!
    // PI = 3.14;

    // 상수를 활용한 계산
    int radius = 5;
    double area = PI * radius * radius;
    System.out.println("반지름 " + radius + "인 원의 넓이: " + area);

    // var — 타입 추론 (JDK 10+)
    var age = 25;
    var name = "홍길동";
    var height = 175.5;
    var isStudent = true;

    System.out.println("나이: " + age);
    System.out.println("이름: " + name);
    System.out.println("키: " + height);
    System.out.println("학생: " + isStudent);

    // var는 선언과 동시에 값을 줘야 함
    // var unknown;  // 에러 — 뭘로 추론할지 모름!
}
