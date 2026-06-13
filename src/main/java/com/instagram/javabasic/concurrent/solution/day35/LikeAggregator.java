package com.instagram.javabasic.concurrent.solution.day35;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// com/instagram/javabasic/concurrent/solution/day35/LikeAggregator.java
// [과제 2] 여러 게시물의 좋아요 수를 동시에 조회해 합산하기.
// 각 게시물의 좋아요 수를 세는 일을 Callable 로 만들어 submit 하고, 돌려받은 Future 들을
// "먼저 전부" 모은 다음, 나중에 하나씩 get() 으로 거둬 합쳐요. 먼저 다 던져야 동시에 일해요.
public class LikeAggregator {

    public static int totalLikes(int[] postLikeCounts) throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {
            // 1) 게시물마다 좋아요 수를 돌려주는 일을 submit 하고 Future 를 먼저 전부 모아요.
            List<Future<Integer>> futures = new ArrayList<>();
            for (int likes : postLikeCounts) {
                futures.add(pool.submit(() -> likes));
            }

            // 2) 그다음에 하나씩 get() 으로 결과를 거둬 합산해요.
            int total = 0;
            for (Future<Integer> f : futures) {
                total += f.get();
            }
            return total;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("=== 게시물 3개의 좋아요 합산 ===");
        System.out.println("총 좋아요 → " + totalLikes(new int[] {10, 20, 30}));
    }
}
