void main() {
    // char — 글자 하나 (작은따옴표)
    char grade = 'A';
    char initial = '김';
    System.out.println("학점: " + grade);
    System.out.println("성: " + initial);

    // String — 글자 여러 개 (큰따옴표)
    String name = "홍길동";
    String greeting = "안녕하세요!";
    System.out.println(name);
    System.out.println(greeting);

    // char vs String — 따옴표가 다르다
    char singleChar = 'A';
    String singleString = "A";
    System.out.println("char: " + singleChar);
    System.out.println("String: " + singleString);

    // String 연결 (+)
    String firstName = "길동";
    String lastName = "홍";
    String fullName = lastName + firstName;
    System.out.println("이름: " + fullName);

    // 숫자와 String 연결
    int age = 20;
    String message = name + "은 " + age + "살입니다.";
    System.out.println(message);
}
