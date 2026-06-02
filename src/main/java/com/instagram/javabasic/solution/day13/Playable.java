package com.instagram.javabasic.solution.day13;

// com/instagram/javabasic/solution/day13/Playable.java
// "재생할 수 있다" 는 역할(role)을 약속하는 인터페이스예요. Shareable·Commentable 과 마찬가지로
// 콘텐츠 부모(Content)와는 따로 노는 역할이라, 어떤 콘텐츠든 "재생 가능" 이라는 역할만
// 따로 받아갈 수 있어요. 음성·영상처럼 "틀어서 듣고 보는" 콘텐츠에 입혀요.
public interface Playable {

    // 구현 클래스가 반드시 채워야 할 약속 — 재생 길이(초).
    // 본문이 없는 빈칸이에요(세미콜론으로 끝나요). 채우는 건 구현 클래스의 몫이에요.
    int getDurationSeconds();

    // default 메서드 — 인터페이스가 "기본 동작" 을 미리 만들어 물려줘요.
    // 구현 클래스가 따로 만들지 않아도 이 동작을 그대로 쓸 수 있어요.
    // 위에서 약속한 getDurationSeconds() 를 불러 재생 안내 문구를 조립해요.
    default String play() {
        return "▶ 재생을 시작해요 (" + getDurationSeconds() + "초)";
    }
}
