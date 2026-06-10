package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/ThrowsDeclaration.java
// 예외를 만난 메서드에는 두 가지 선택지가 있어요.
//   1) 내가 직접 try-catch 로 잡는다 (지난 시간)
//   2) 내가 안 잡고 "호출한 너가 처리해" 하고 넘긴다  ← 이번 시간!
// 2번을 하려면 메서드 뒤에 throws 를 적어요. "이 메서드는 이런 예외를 넘길 수 있어요" 라는 표지판이에요.
//   void follow(...) throws Exception { ... }
// 안 잡고 throws 로 넘기면, 부른 쪽(main)이 대신 try-catch 로 받아야 해요.
public class ThrowsDeclaration {

    // 팔로우 — 자기 자신은 팔로우할 수 없어요.
    // 여기서 잡지 않고 throws Exception 으로 호출한 쪽에 넘겨요.
    public void follow(String me, String target) throws Exception {
        if (me.equals(target)) {
            throw new Exception("자기 자신은 팔로우할 수 없어요.");
        }
        System.out.println(me + " 님이 " + target + " 님을 팔로우했어요.");
    }

    public static void main(String[] args) {
        ThrowsDeclaration service = new ThrowsDeclaration();

        // 정상 — 서로 다른 사람이라 팔로우가 성공해요.
        // follow 가 예외를 넘길 수 있으니, 부른 쪽인 여기서 try-catch 로 받아요.
        try {
            service.follow("minji", "jaehoon");
        } catch (Exception e) {
            System.out.println("실패: " + e.getMessage());
        }

        // 사고 — 자기 자신을 팔로우하면 follow 가 던진 예외가 여기까지 넘어와요.
        try {
            service.follow("minji", "minji");
        } catch (Exception e) {
            System.out.println("실패: " + e.getMessage());
        }
    }
}
