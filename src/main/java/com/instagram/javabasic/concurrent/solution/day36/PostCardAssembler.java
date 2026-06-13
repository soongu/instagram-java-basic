package com.instagram.javabasic.concurrent.solution.day36;

import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/solution/day36/PostCardAssembler.java
// [과제 2] thenCombine 으로 본문과 작성자를 동시에 받아 카드 한 줄로 합치기.
// 본문 가져오기와 작성자 가져오기는 서로를 기다릴 이유가 없어요. 그래서 둘을 따로 supplyAsync 로
// "동시에" 시작해 놓고, thenCombine 으로 둘 다 끝났을 때 (본문, 작성자) 를 함께 받아 합쳐요.
// join() 으로 완성된 카드 문자열을 거두면 입력에 대해 항상 같은 결과라 결정적이에요.
public class PostCardAssembler {

    // 게시물 본문을 받아오는 비동기 작업.
    public static CompletableFuture<String> fetchBody(int postId) {
        return CompletableFuture.supplyAsync(() -> "post#" + postId + " 본문");
    }

    // 작성자를 받아오는 비동기 작업. 본문과 서로 독립적이에요.
    public static CompletableFuture<String> fetchAuthor(int postId) {
        return CompletableFuture.supplyAsync(() -> "user" + postId);
    }

    // thenCombine 으로 본문과 작성자를 함께 받아 "본문 - by 작성자" 한 줄로 합쳐요.
    public static String renderCard(int postId) {
        return fetchBody(postId)
                .thenCombine(fetchAuthor(postId),
                        (body, author) -> body + " - by " + author)
                .join();
    }

    public static void main(String[] args) {
        System.out.println("=== thenCombine: 본문 + 작성자 합쳐 카드 만들기 ===");
        System.out.println(renderCard(3));
    }
}
