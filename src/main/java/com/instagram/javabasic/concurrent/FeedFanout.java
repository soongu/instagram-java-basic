package com.instagram.javabasic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/FeedFanout.java
// 지금까지는 작업 한두 개를 이어 붙이거나 합쳤어요. 그런데 피드처럼 게시물이 여러 개라면,
// "여러 작업을 한꺼번에 시작" 해 놓고 묶어서 기다리고 싶어요. 이걸 팬아웃(fan-out) 이라고 해요.
//
// 두 가지 묶음 도구가 있어요.
//   - allOf(올 오브): 넘긴 작업이 "전부 다" 끝날 때까지 기다려요. 모든 결과가 필요할 때.
//   - anyOf(애니 오브): 그중 "가장 먼저" 끝난 하나만 받아요. 빠른 쪽만 쓰고 싶을 때.
//
// 한 가지 주의할 점이 있어요. allOf 와 anyOf 자체는 결과 값을 직접 돌려주지 않아요.
// allOf 는 "다 끝났다" 는 신호만 줘서, 끝난 뒤에 각 작업의 join() 으로 결과를 따로 거둬야 해요.
public class FeedFanout {

    // 각 게시물의 좋아요 수를 동시에 받아온 뒤, allOf 로 전부 끝나길 기다렸다가 합산해요.
    public static int totalLikesOfFeed(int[] postIds) {
        // 1) 먼저 전부 supplyAsync 로 시작해서 모아둬요. 이러면 동시에 일해요.
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int postId : postIds) {
            futures.add(CompletableFuture.supplyAsync(() -> postId * 10));
        }

        // 2) allOf 로 "전부 끝남" 을 기다려요. (allOf 는 값이 아니라 완료 신호만 줘요.)
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 3) 다 끝났으니 각 join() 으로 결과를 거둬 합쳐요.
        int total = 0;
        for (CompletableFuture<Integer> f : futures) {
            total += f.join();
        }
        return total;
    }

    // anyOf 로 "가장 먼저 끝난" 작업의 결과를 받아요.
    // 어느 게 먼저 끝날지는 그때그때 다르므로(비결정적), 특정 승자를 단정하지 않아요.
    public static int firstReady(int[] postIds) {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int postId : postIds) {
            futures.add(CompletableFuture.supplyAsync(() -> postId));
        }
        // anyOf 의 결과 타입은 Object 라서, 우리가 넣은 int 로 캐스팅해서 받아요.
        return (int) CompletableFuture.anyOf(futures.toArray(new CompletableFuture[0])).join();
    }

    public static void main(String[] args) {
        System.out.println("=== allOf: 전부 끝나면 합산 ===");
        System.out.println("좋아요 합 → " + totalLikesOfFeed(new int[] {1, 2, 3}));

        System.out.println();
        System.out.println("=== anyOf: 가장 먼저 끝난 하나 ===");
        System.out.println("먼저 끝난 postId → " + firstReady(new int[] {1, 2, 3}));
    }
}
