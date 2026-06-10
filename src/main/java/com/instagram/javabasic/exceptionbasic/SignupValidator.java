package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/SignupValidator.java
// 실제 서비스의 회원 가입은 검사할 게 여러 개예요. 이름이 비었는지, 너무 긴지, 나이가 맞는지…
// 검사를 통과하지 못하면 throw 로 즉시 막아요. throw 가 실행되면 그 줄에서 메서드가 멈추니까,
// 위에서부터 하나씩 막다가 모두 통과하면 조용히 끝나요(아무 일도 안 일어나면 성공이에요).
public class SignupValidator {

    // 회원 가입 정보를 검사해요. 규칙을 어기면 IllegalArgumentException 으로 막고,
    // 모두 통과하면 아무것도 돌려주지 않고 조용히 끝나요(검사 통과 = 무사 통과).
    public void validate(String username, int age) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("사용자 이름을 입력해주세요.");
        }
        if (username.length() > 30) {
            throw new IllegalArgumentException("사용자 이름은 30자까지예요.");
        }
        if (age < 14) {
            throw new IllegalArgumentException("만 14세 이상만 가입할 수 있어요.");
        }
    }

    public static void main(String[] args) {
        SignupValidator validator = new SignupValidator();

        // 정상 — 모든 규칙을 통과하면 예외 없이 조용히 끝나요.
        validator.validate("minji", 25);
        System.out.println("minji 님 가입 정보가 통과했어요.");

        // 사고 1 — 빈 이름은 첫 검사에서 막혀요.
        try {
            validator.validate("", 25);
        } catch (IllegalArgumentException e) {
            System.out.println("막혔어요: " + e.getMessage());
        }

        // 사고 2 — 나이가 모자라면 마지막 검사에서 막혀요.
        try {
            validator.validate("jaehoon", 13);
        } catch (IllegalArgumentException e) {
            System.out.println("막혔어요: " + e.getMessage());
        }
    }
}
