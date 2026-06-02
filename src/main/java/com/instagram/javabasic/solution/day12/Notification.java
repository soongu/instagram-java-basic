package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/Notification.java
// 과제 2 — 콘텐츠와는 완전히 무관한 새 도메인의 "뼈대 부모" 예요.
// 인스타 알림은 팔로우·좋아요·댓글 등 종류가 다양한데, "누구에게, 언제" 라는 틀은 똑같아요.
// 그래서 공통(받는 사람·시점)은 부모가 한 번만 정해두고, "알림 문구" 처럼
// 종류마다 다른 부분만 abstract 빈칸으로 남겨 자식이 채우게 해요.
// new Notification(...) 으로 "알림 자체" 만 만들면 무슨 알림인지 알 수 없으니 abstract 로 막아요.
public abstract class Notification {

    // 모든 알림이 공통으로 갖는 정보 — 자식이 super(...) 로 채워요.
    private String receiverName;   // 받는 사람
    private String createdAgo;     // 언제 왔는지 (예: "5분 전") — 시간 API 없이 글자로만

    // 생성자 — abstract 클래스도 생성자를 가져요. 자식이 super(...) 로 공통 필드를 채우는 통로예요.
    public Notification(String receiverName, String createdAgo) {
        this.receiverName = receiverName;
        this.createdAgo = createdAgo;
    }

    // ===== abstract 메서드 — 본문 없는 빈칸이에요 (세미콜론으로 끝나요) =====
    // 알림 문구는 종류마다 다르니, 부모는 "문구가 반드시 있어야 한다" 고 선언만 하고
    // 실제 문장은 자식이 채워요. 안 채우면 컴파일 에러가 나요.
    public abstract String message();

    // ===== 템플릿 메서드 (concrete) — 알림 한 줄을 조립하는 틀은 부모가 정해요 =====
    // 받는 사람과 시점은 부모가 끼우고, 가운데 문구만 자식의 message() 에 맡겨요.
    // 자식은 render() 를 다시 만들지 않아도, 빈칸만 채우면 완성된 한 줄이 나와요.
    public String render() {
        return "[" + receiverName + "] " + message() + " (" + createdAgo + ")";
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getCreatedAgo() {
        return createdAgo;
    }
}
