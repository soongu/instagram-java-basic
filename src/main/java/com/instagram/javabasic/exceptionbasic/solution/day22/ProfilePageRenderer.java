package com.instagram.javabasic.exceptionbasic.solution.day22;

// com/instagram/javabasic/exceptionbasic/solution/day22/ProfilePageRenderer.java
// 예외가 "전파" 된다는 게 무슨 뜻인지 3단 호출 체인으로 눈으로 확인하는 연습이에요.
// 메서드가 서로를 부르는 사슬을 만들었어요:
//   renderProfilePage → buildProfileCard → readBio
// 가장 깊은 readBio 에서 예외가 던져지면, 그걸 잡는 try-catch 가 중간에 하나도 없어요.
// 그래서 예외가 사슬을 타고 거꾸로 올라가다가, 결국 맨 바깥에서 호출한 main 까지
// 도달해서 거기서 잡혀요. 이게 바로 "예외 전파(propagation)" 예요.
public class ProfilePageRenderer {

    // 가장 깊은 곳 — 자기소개가 비었으면 여기서 예외를 던져요.
    // bio 가 null 이거나 빈 문자열이면 IllegalStateException 을 던지고,
    // 정상이면 그 자기소개를 그대로 돌려줘요.
    public String readBio(String bio) {
        if (bio == null || bio.isEmpty()) {
            throw new IllegalStateException("자기소개가 비어 있어요.");
        }
        return bio;
    }

    // 중간 단계 — try-catch 없이 readBio 를 그냥 부르기만 해요.
    // readBio 가 예외를 던지면, 여기선 잡지 않으니 그대로 위로 전파돼요.
    public String buildProfileCard(String bio) {
        return readBio(bio);
    }

    // 가장 바깥 단계 — 역시 try-catch 없이 buildProfileCard 를 부르기만 해요.
    // 아래에서 올라온 예외가 여기서도 잡히지 않고, 이 메서드를 부른 쪽으로 전파돼요.
    public String renderProfilePage(String bio) {
        return buildProfileCard(bio);
    }

    public static void main(String[] args) {
        ProfilePageRenderer renderer = new ProfilePageRenderer();

        // 정상 자기소개 — 사슬을 끝까지 통과해서 그대로 나와요.
        System.out.println("프로필: " + renderer.renderProfilePage("자바 좋아요"));

        // 빈 자기소개(null) — readBio 에서 던진 예외가 buildProfileCard, renderProfilePage 를
        // 거쳐 여기 main 까지 전파돼서, 이 try-catch 가 마침내 받아내요.
        try {
            renderer.renderProfilePage(null);
        } catch (IllegalStateException e) {
            System.out.println("렌더링 실패: " + e.getMessage());
        }

        System.out.println("끝까지 잘 실행됐어요!");
    }
}
