package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/CallStackPropagation.java
// 예외를 아무도 안 잡으면 어떻게 될까요? 호출한 순서를 거슬러 위로 계속 올라가요.
// 메서드가 메서드를 부르는 순서를 "콜스택(call stack, 호출 더미)" 이라고 해요.
//   main → showPage → renderProfile → loadProfileImage   (이렇게 깊이 내려가서 호출돼요)
// 가장 깊은 loadProfileImage 에서 예외가 터지면, 중간 메서드들이 안 잡는 한
// 던진 순서의 반대로 main 까지 거슬러 올라가요. 이걸 "전파(propagation)" 라고 해요.
public class CallStackPropagation {

    // 가장 깊은 메서드 — 실제로 예외가 시작되는 곳이에요.
    // 주소가 없으면 IllegalStateException 을 던져요(unchecked 라 throws 가 필요 없어요).
    public String loadProfileImage(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("프로필 이미지 주소가 없어요.");
        }
        return url;
    }

    // 중간 메서드 — 예외를 잡지 않고 그대로 통과만 시켜요. 예외는 여기를 그냥 지나가요.
    public String renderProfile(String url) {
        return loadProfileImage(url);
    }

    // 또 다른 중간 메서드 — 역시 잡지 않고 통과만. 예외가 한 칸 더 위로 올라가요.
    public String showPage(String url) {
        return renderProfile(url);
    }

    public static void main(String[] args) {
        CallStackPropagation page = new CallStackPropagation();

        // 정상 — 주소가 있으면 맨 깊은 곳에서 끝까지 잘 돌아와요.
        System.out.println("정상: " + page.showPage("profile.jpg"));

        // 사고 — null 을 넣으면 맨 깊은 loadProfileImage 에서 예외가 터져
        // renderProfile → showPage 를 거슬러 올라와 여기 main 의 catch 에서 잡혀요.
        try {
            page.showPage(null);
        } catch (IllegalStateException e) {
            System.out.println("맨 위(main)에서 잡았어요: " + e.getMessage());
        }
    }
}
