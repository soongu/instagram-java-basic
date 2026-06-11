// day30/CompactSourceDemo.java
// JDK 25 의 Compact Source Files(간소 소스 파일) — Day 1~5 에서 쓰던 그 형태가 정식 기능이 됐어요.
// package 선언도, class 선언도 없이 void main() 하나만 적으면 그대로 실행돼요.
// 처음 자바를 배울 때 public static void main(String[] args) 의 의식 같은 한 줄을
// 통째로 외워야 했던 부담을, 이 형태가 덜어줘요.
//
// 한 가지 약속: 이 파일은 패키지가 없는 형태라, com/instagram/javabasic/modern/ 안엔 둘 수 없어요.
// 그래서 패키지에 묶이지 않는 day30/ 폴더에 따로 뒀어요(Day 1~5 의 day01/ 와 같은 결).

void main() {
    System.out.println("=== Compact Source File 실행 ===");

    // 클래스도 메서드도 없이, 그냥 코드를 적으면 돼요. 변수 선언도 바로 가능해요.
    String username = "jaehoon";
    int followers = 1240;

    System.out.println("@" + username + " 님, 환영해요!");
    System.out.println("현재 팔로워: " + followers + "명");

    // 같은 파일 안에 도우미 메서드도 둘 수 있어요(아래 grade 메서드를 불러요).
    System.out.println("등급: " + grade(followers));
}

// void main() 옆에 평범한 메서드를 나란히 둘 수 있어요 — 클래스로 감싸지 않아도 돼요.
String grade(int followers) {
    if (followers >= 1000) {
        return "인플루언서";
    } else if (followers >= 100) {
        return "성장 중";
    } else {
        return "새내기";
    }
}
