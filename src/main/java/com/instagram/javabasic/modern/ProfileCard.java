package com.instagram.javabasic.modern;

// com/instagram/javabasic/modern/ProfileCard.java
// 화면에 뿌릴 "프로필 카드" 한 장에 필요한 값만 담는 record 예요.
// Member 는 글·팔로잉까지 잔뜩 안고 있는 무거운 객체인데,
// 화면엔 이름·등급·팔로워 수만 있으면 되니까, 가벼운 record 로 추려서 담아요.
public record ProfileCard(String username, String grade, int followers) {
}
