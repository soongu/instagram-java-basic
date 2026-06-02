package com.instagram.javabasic.design.access;

// com/instagram/javabasic/design/access/Story.java
// 인스타 스토리 한 개예요. 접근 제어자(private·protected·public)로
// "어디까지 보여줄지" 를 단계별로 나눠 보여주는 예제예요.
//
//   private   : 이 클래스 안에서만 — 바깥에선 존재조차 모름 (가장 꽁꽁 숨김)
//   protected : 같은 패키지 + 자식 클래스까지만 — 가족에게만 공개
//   public    : 누구나 — 외부에 정식으로 열어둔 출입문
//
// 외부에는 꼭 필요한 view()·getStatus() 만 열고, 내부 판단(isExpired)과
// 운영용 동작(extend)은 숨겨서 아무나 함부로 못 건드리게 막아요. 이게 정보 은닉이에요.
public class Story {

    private static final int VIEW_LIMIT = 100;

    // private — 조회수는 내부에서만 관리해요. 외부에서 직접 못 바꿔요.
    private int viewCount = 0;

    // private — "만료됐는가" 판단은 내부 사정이에요. 외부에 보일 필요가 없어요.
    private boolean isExpired() {
        return viewCount >= VIEW_LIMIT;
    }

    // protected — 만료 연장은 운영용 동작이라 가족(같은 패키지·자식)에게만 열어요.
    // 조회수를 0 으로 되돌려 다시 활성 상태로 만들어요.
    protected void extend() {
        viewCount = 0;
    }

    // public — 누구나 부를 수 있는 외부 API. 스토리를 한 번 봤다고 기록해요.
    public void view() {
        if (isExpired()) {
            return;   // 이미 만료됐으면 더 올리지 않아요
        }
        viewCount++;
    }

    // public — 외부에 보여줄 상태 문구를 돌려줘요.
    public String getStatus() {
        String state = isExpired() ? "만료" : "활성";
        return "조회수 " + viewCount + " (" + state + ")";
    }
}
