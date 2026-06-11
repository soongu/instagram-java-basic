package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/Hashtag.java
// [과제 1] 해시태그 이름 하나를 담는 record.
// 자동 equals/hashCode 덕에 "같은 이름이면 같은 태그" 가 공짜로 생겨, 중복 제거에 바로 써요.
public record Hashtag(String name) {
}
