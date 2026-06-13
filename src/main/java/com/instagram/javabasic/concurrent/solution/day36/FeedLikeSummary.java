package com.instagram.javabasic.concurrent.solution.day36;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/solution/day36/FeedLikeSummary.java
// [과제 3] allOf 로 전부 기다리되, 한 건이 실패해도 exceptionally 로 0 처리해 합산하기.
// 게시물마다 좋아요 수를 동시에 받아오는데, 한 게시물 조회가 예외를 던질 수 있어요.
// 그럴 때 그 작업만 무너지지 않도록 exceptionally 로 "실패하면 0 으로 치자" 는 대비책을 달아둬요.
// allOf 로 전부 끝나길 기다린 뒤 각 join() 을 합산하면, 실패한 건은 0 으로 들어와 결과가 결정적이에요.
public class FeedLikeSummary {

    // postIds 각각의 좋아요(=postId*10) 를 동시에 받아 합산해요.
    // failingPostId 와 같은 게시물은 일부러 예외를 던지고, exceptionally 가 그 자리를 0 으로 메워요.
    public static int totalLikes(int[] postIds, int failingPostId) {
        // 1) 게시물마다 비동기 작업을 만들되, exceptionally 로 실패 시 0 을 돌려주게 해요.
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int postId : postIds) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                if (postId == failingPostId) {
                    throw new IllegalStateException("조회 실패");
                }
                return postId * 10;
            }).exceptionally(ex -> 0);   // 실패하면 그 게시물 좋아요는 0 으로 친다
            futures.add(future);
        }

        // 2) allOf 로 "전부 끝남" 을 기다려요. (allOf 는 값이 아니라 완료 신호만 줘요.)
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 3) 다 끝났으니 각 join() 으로 결과를 거둬 합쳐요. 실패한 건은 0 이라 안전해요.
        int total = 0;
        for (CompletableFuture<Integer> f : futures) {
            total += f.join();
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== allOf + exceptionally: 일부 실패해도 합산 ===");
        // postId 2 만 실패 → 10 + 0 + 30 = 40
        System.out.println("2번 게시물 실패 시 합 → " + totalLikes(new int[] {1, 2, 3}, 2));
        // 실패 없음 → 10 + 20 + 30 = 60
        System.out.println("실패 없을 때 합 → " + totalLikes(new int[] {1, 2, 3}, -1));
    }
}
