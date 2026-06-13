package com.instagram.javabasic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// com/instagram/javabasic/concurrent/ThumbnailFuture.java
// 앞에서 던진 일감(execute) 은 "그냥 해줘" 였어요. 결과를 돌려받지는 않았죠.
// 그런데 "이미지를 받아 썸네일을 만들고, 그 크기를 알려줘" 처럼 결과가 필요할 때가 있어요.
//
// 이럴 땐 submit() 에 Callable(콜러블) 을 줘요. Runnable 이 결과 없는 "할 일" 이라면,
// Callable 은 결과를 return 하는 "할 일" 이에요. submit() 은 그 결과를 나중에 꺼낼 수 있는
// 영수증을 돌려주는데, 이 영수증이 Future(퓨처, 미래의 결과) 예요.
//
// 결과가 필요해지면 future.get() 을 불러요. 단, get() 은 결과가 아직 안 나왔으면 나올 때까지
// 그 자리에서 "막혀서" 기다려요 (이걸 블로킹blocking 이라고 해요). 그래서 여러 장을 맡길 땐
// 먼저 전부 submit 해서 Future 들을 모아두고, 그다음에 하나씩 get() 으로 거두는 게 좋아요.
// (get() 이 매번 막힌다는 점이 살짝 불편하죠? 막힘 없이 결과를 이어 붙이는 도구는 다음 시간에 만나요.)
public class ThumbnailFuture {

    // 원본 너비를 받아 절반 크기 썸네일 너비를 "결과로" 돌려주는 일감 하나.
    public static int generateThumbnail(int originalWidth)
            throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            // submit 에 Callable(결과를 return 하는 일) 을 주면 Future 를 돌려받아요.
            Future<Integer> future = pool.submit(() -> originalWidth / 2);
            return future.get();   // 결과가 나올 때까지 여기서 막혀 기다렸다가 받아와요
        }
    }

    // 여러 장을 한꺼번에 맡기고, Future 를 모아 두었다가 하나씩 결과를 거둬 합쳐요.
    public static int totalThumbnailWidth(int[] originalWidths)
            throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {
            // 1) 먼저 전부 submit 해서 영수증(Future) 을 모아둬요. 이러면 셋이 동시에 일해요.
            List<Future<Integer>> futures = new ArrayList<>();
            for (int width : originalWidths) {
                futures.add(pool.submit(() -> width / 2));
            }

            // 2) 그다음에 하나씩 get() 으로 결과를 거둬 더해요.
            int total = 0;
            for (Future<Integer> f : futures) {
                total += f.get();
            }
            return total;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("=== 썸네일 한 장 ===");
        System.out.println("원본 80 → 썸네일 " + generateThumbnail(80));

        System.out.println();
        System.out.println("=== 썸네일 여러 장 (동시에 만들고 결과 합치기) ===");
        int[] widths = {100, 200, 300};
        System.out.println("합친 썸네일 너비 → " + totalThumbnailWidth(widths));
    }
}
