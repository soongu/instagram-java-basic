package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/SignupValidatorV2.java
// 지난 시간의 SignupValidator 를 커스텀 예외 버전으로 키운 두 번째 검증기예요.
// 그때는 모든 위반을 IllegalArgumentException 하나로 던져서, 잡는 쪽이
// "이름 문제인지 나이 문제인지" 를 메시지를 읽어 봐야만 알 수 있었어요.
// 이제 위반의 종류마다 예외 타입을 나눠 던지니, 잡는 쪽이 catch 블록을
// 타입별로 갈라 다르게 대응할 수 있어요. 검사 규칙 자체는 그대로예요.
public class SignupValidatorV2 {

    // 회원 가입 정보를 검사해요. 규칙을 어기면 "그 규칙 전용 예외" 로 막고,
    // 모두 통과하면 아무것도 돌려주지 않고 조용히 끝나요(검사 통과 = 무사 통과).
    public void validate(String username, int age) {
        if (username == null || username.isEmpty()) {
            throw new InvalidUsernameException("사용자 이름을 입력해주세요.");
        }
        if (username.length() > 30) {
            throw new InvalidUsernameException("사용자 이름은 30자까지예요.");
        }
        if (age < 14) {
            throw new UnderageMemberException("만 14세 이상만 가입할 수 있어요.");
        }
    }

    public static void main(String[] args) {
        SignupValidatorV2 validator = new SignupValidatorV2();

        // 정상 — 모든 규칙을 통과하면 예외 없이 조용히 끝나요.
        validator.validate("minji", 25);
        System.out.println("minji 님 가입 정보가 통과했어요.");

        // 사고 1 — 빈 이름. 이름 전용 예외가 날아오니 이름 안내 catch 가 받아요.
        // 사고 2 — 나이 미달. 나이 전용 예외라 아래쪽 catch 가 받아요.
        // 같은 try 인데도 예외 타입 덕분에 대응이 갈라지는 걸 확인해보세요.
        try {
            validator.validate("", 25);
        } catch (InvalidUsernameException e) {
            System.out.println("이름을 확인해주세요 → " + e.getMessage());
        } catch (UnderageMemberException e) {
            System.out.println("가입 연령을 확인해주세요 → " + e.getMessage());
        }

        try {
            validator.validate("jaehoon", 13);
        } catch (InvalidUsernameException e) {
            System.out.println("이름을 확인해주세요 → " + e.getMessage());
        } catch (UnderageMemberException e) {
            System.out.println("가입 연령을 확인해주세요 → " + e.getMessage());
        }
    }
}
