package com.instagram.javabasic.concurrent;

import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/PostAssembler.java
// thenCompose 는 "앞 결과가 있어야 뒤가 시작되는" 순서가 있는 작업이었어요.
// 그런데 서로 상관없는 두 작업을 "둘 다 동시에 시작" 해 놓고, 둘 다 끝나면 합치고 싶을 때가 있어요.
//
// 게시물 화면을 떠올려 보세요. 본문 글과 좋아요 수는 서로를 기다릴 이유가 없어요.
// 동시에 받아온 뒤, 마지막에 "본문 (좋아요 N)" 처럼 한 줄로 합치면 되죠.
//
// thenCombine(덴 컴바인) 이 바로 이거예요. 두 개의 독립된 CompletableFuture 를 받아,
// 둘 다 끝나면 두 결과를 함께 넘겨주는 합치기 함수를 실행해요. (결과1, 결과2) → 합친 값.
public class PostAssembler {

    // 게시물 본문을 받아오는 비동기 작업.
    public static CompletableFuture<String> fetchPostBody(int postId) {
        return CompletableFuture.supplyAsync(() -> "post#" + postId + " 본문");
    }

    // 좋아요 수를 받아오는 비동기 작업. 본문과 서로 독립적이에요.
    public static CompletableFuture<Integer> fetchLikeCount(int postId) {
        return CompletableFuture.supplyAsync(() -> postId * 10);
    }

    // thenCombine 으로 본문과 좋아요 수를 함께 받아 한 줄로 합쳐요.
    public static String renderPost(int postId) {
        return fetchPostBody(postId)
                .thenCombine(fetchLikeCount(postId),
                        (body, likes) -> body + " (좋아요 " + likes + ")")
                .join();
    }

    public static void main(String[] args) {
        System.out.println("=== thenCombine: 독립된 두 결과 합치기 ===");
        System.out.println(renderPost(3));
    }
}
