package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/Member.java
// 인스타 추천 사용자 한 명을 표현하는 클래스예요.
// 지난 시간엔 한 사람의 정보가 평행 배열 다섯 개에 흩어져 있었는데,
// 이제 그 다섯 가지를 한 객체 안에 묶어서 "한 명 = 한 덩어리" 로 다뤄요.
// 이번 단계에서는 데이터(필드)와 그것을 채우는 생성자만 만들어요.
// (행동을 담는 메서드는 다음 시간에 추가합니다.)
public class Member {

    // 한 사람을 이루는 다섯 가지 정보 — 지난 시간 평행 배열 다섯 개에 대응돼요
    String username;       // 사용자 이름
    int followers;         // 팔로워 수
    int posts;             // 게시물 수
    int mutualFriends;     // 함께 아는 친구 수
    int daysActive;        // 활동 일수

    // 기본 생성자 — 아무 값도 받지 않고 빈 객체를 만들어요.
    // 이때 필드는 각 타입의 기본값(문자열은 null, 숫자는 0)으로 채워져요.
    public Member() {
    }

    // 매개변수 생성자 — 다섯 가지 정보를 한 번에 받아 객체를 완성해요.
    // this.username 의 this 는 "지금 만들어지는 바로 이 객체" 를 가리켜요.
    public Member(String username, int followers, int posts, int mutualFriends, int daysActive) {
        this.username = username;
        this.followers = followers;
        this.posts = posts;
        this.mutualFriends = mutualFriends;
        this.daysActive = daysActive;
    }
}
