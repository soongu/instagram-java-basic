package com.instagram.javabasic.concurrent;

import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/ProfileChain.java
// 앞에서 thenApply 는 "값 → 값" 변환이었어요. 그런데 후속 작업 자체가 또 비동기라면 어떨까요?
// 예를 들어 "유저 ID 로 프로필 ID 를 받아오고(비동기), 그 프로필 ID 로 팔로워 수를 받아온다(또 비동기)"
// 처럼, 비동기 작업을 비동기 작업에 이어 붙이는 경우예요.
//
// 이때 thenApply 를 쓰면 결과가 CompletableFuture<CompletableFuture<Integer>> 처럼
// 미래 속에 미래가 또 들어간 "이중 포장" 이 돼버려요. 껍데기를 두 번 벗겨야 하죠.
//
// thenCompose(덴 컴포즈) 가 이걸 해결해요. 후속 작업이 CompletableFuture 를 돌려줄 때,
// 그 포장을 한 겹 벗겨서 평평하게(flat) 이어 붙여줘요. 결과는 깔끔한 CompletableFuture<Integer>.
public class ProfileChain {

    // 유저 ID 로 프로필 ID 를 받아오는 비동기 작업.
    public static CompletableFuture<Integer> fetchProfileId(int userId) {
        return CompletableFuture.supplyAsync(() -> userId + 1000);
    }

    // 프로필 ID 로 팔로워 수를 받아오는 비동기 작업.
    public static CompletableFuture<Integer> fetchFollowerCount(int profileId) {
        return CompletableFuture.supplyAsync(() -> profileId * 2);
    }

    // thenCompose 로 "비동기 → 비동기" 를 이어 붙여요. (7+1000) → 1007, 1007*2 → 2014.
    public static int followerCountOf(int userId) {
        return fetchProfileId(userId)
                .thenCompose(ProfileChain::fetchFollowerCount)
                .join();
    }

    public static void main(String[] args) {
        System.out.println("=== thenCompose: 비동기를 비동기로 이어 붙이기 ===");
        System.out.println("userId 7 의 팔로워 수 → " + followerCountOf(7));
    }
}
