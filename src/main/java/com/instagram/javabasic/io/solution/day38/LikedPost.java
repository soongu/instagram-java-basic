package com.instagram.javabasic.io.solution.day38;

// 과제 2 (응용) — 좋아요 수까지 담은 게시물이에요.
// id·작성자·캡션에 더해 likes(int) 를 함께 보관해요.
public record LikedPost(long id, String author, String caption, int likes) {
}
