package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/Shareable.java
// "공유할 수 있다" 는 역할(role)을 약속하는 인터페이스예요. 부모-자식(상속)과 달리,
// 인터페이스는 "이 클래스가 어떤 일을 할 수 있는가" 만 정해두는 약속표예요.
// Content 부모와는 직교하는(상관없는) 역할이라, 어떤 콘텐츠든 "공유 가능" 이라는
// 역할만 따로 받아갈 수 있어요.
public interface Shareable {

    // 구현 클래스가 반드시 채워야 할 약속 — 공유 링크 주소.
    // 본문이 없는 빈칸이에요(세미콜론으로 끝나요). 채우는 건 구현 클래스의 몫이에요.
    String getShareUrl();

    // default 메서드 — 인터페이스가 "기본 동작" 을 미리 만들어 물려줘요.
    // 구현 클래스가 따로 만들지 않아도 이 동작을 그대로 쓸 수 있어요.
    // 위에서 약속한 getShareUrl() 을 불러 공유 문구를 조립해요.
    default String share() {
        return "공유 링크가 생성됐어요: " + getShareUrl();
    }
}
