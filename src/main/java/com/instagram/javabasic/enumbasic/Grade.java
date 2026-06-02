package com.instagram.javabasic.enumbasic;

// com/instagram/javabasic/enumbasic/Grade.java
// 회원 등급이에요. 일반·프리미엄·관리자 세 가지로 정해져 있죠.
// 그런데 등급마다 "한글 이름" 과 "추천 점수 보너스" 가 달라요.
// enum 상수도 클래스처럼 자기만의 데이터(필드)를 가질 수 있어요.
// 각 상수 이름 옆 괄호에 넣은 값이 생성자로 전달돼서 필드에 담겨요.
public enum Grade {
    NORMAL("일반", 0),
    PREMIUM("프리미엄", 30),
    ADMIN("관리자", 50);

    // 상수마다 채워지는 데이터 — 한 번 정해지면 안 바뀌니 final 로 둬요
    private final String displayName;
    private final int bonusScore;

    // enum 의 생성자는 항상 보이지 않게 private 이에요(밖에서 new Grade(...) 불가).
    // 위에 적은 NORMAL("일반", 0) 의 괄호 값이 여기로 전달돼요.
    Grade(String displayName, int bonusScore) {
        this.displayName = displayName;
        this.bonusScore = bonusScore;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBonusScore() {
        return bonusScore;
    }
}
