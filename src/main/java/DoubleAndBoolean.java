void main() {
    // double — 소수점이 있는 숫자 (가장 많이 씀)
    double height = 175.5;
    System.out.println("키: " + height + "cm");

    double pi = 3.141592653589793;
    System.out.println("원주율: " + pi);

    // float — double보다 정밀도가 낮음 (끝에 F 붙이기)
    float temperature = 36.5F;
    System.out.println("체온: " + temperature + "도");

    // 소수점 계산의 함정
    System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));

    // boolean — 참 또는 거짓만 담는 자료형
    boolean isStudent = true;
    boolean isGraduated = false;
    System.out.println("학생인가요? " + isStudent);
    System.out.println("졸업했나요? " + isGraduated);

    // boolean 값 바꾸기
    isGraduated = true;
    System.out.println("졸업했나요? (변경 후) " + isGraduated);
}
