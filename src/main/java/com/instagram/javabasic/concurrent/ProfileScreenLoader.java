package com.instagram.javabasic.concurrent;

import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/ProfileScreenLoader.java
// 이제 배운 걸 한자리에 모아 실전처럼 써볼게요. 프로필 화면 하나를 그리려면
// 게시물 본문, 댓글 수, 좋아요 수 — 이 세 가지를 외부에서 받아와야 한다고 해봐요.
//
// 셋은 서로 독립적이에요. 그러니 하나씩 순서대로 기다리면 시간이 3배로 들어요.
// 핵심은 "세 호출을 먼저 다 시작해 놓고(병렬)", allOf 로 전부 끝나길 기다린 뒤,
// 각 결과를 join() 으로 거둬 하나의 화면 객체로 조립하는 거예요.
//
// 결과를 담을 그릇으로는 record 를 써요. 세 값을 한 묶음으로 깔끔하게 들고 다닐 수 있어요.
public class ProfileScreenLoader {

    // 화면에 필요한 세 값을 한 묶음으로 담는 그릇.
    public record ProfileScreen(String postBody, int commentCount, int likeCount) {}

    // 게시물 본문을 받아오는 비동기 작업.
    public static CompletableFuture<String> fetchPostBody(int postId) {
        return CompletableFuture.supplyAsync(() -> "post#" + postId + " 본문");
    }

    // 댓글 수를 받아오는 비동기 작업.
    public static CompletableFuture<Integer> fetchCommentCount(int postId) {
        return CompletableFuture.supplyAsync(() -> postId * 5);
    }

    // 좋아요 수를 받아오는 비동기 작업.
    public static CompletableFuture<Integer> fetchLikeCount(int postId) {
        return CompletableFuture.supplyAsync(() -> postId * 10);
    }

    // 세 호출을 먼저 전부 시작(병렬) → allOf 로 다 끝나길 기다림 → join() 으로 조립.
    public static ProfileScreen loadScreen(int postId) {
        // 1) 세 작업을 먼저 다 시작해요. 이 순간 셋이 동시에 일하기 시작해요.
        CompletableFuture<String> bodyFuture = fetchPostBody(postId);
        CompletableFuture<Integer> commentFuture = fetchCommentCount(postId);
        CompletableFuture<Integer> likeFuture = fetchLikeCount(postId);

        // 2) 셋이 전부 끝나길 한 번에 기다려요.
        CompletableFuture.allOf(bodyFuture, commentFuture, likeFuture).join();

        // 3) 다 끝났으니 각 결과를 거둬 하나의 화면으로 조립해요.
        return new ProfileScreen(bodyFuture.join(), commentFuture.join(), likeFuture.join());
    }

    public static void main(String[] args) {
        System.out.println("=== 실전 종합: 세 호출 병렬 조립 ===");
        ProfileScreen screen = loadScreen(2);
        System.out.println("본문   → " + screen.postBody());
        System.out.println("댓글 수 → " + screen.commentCount());
        System.out.println("좋아요 → " + screen.likeCount());
    }
}
