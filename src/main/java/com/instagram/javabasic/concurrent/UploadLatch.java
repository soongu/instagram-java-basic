package com.instagram.javabasic.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// com/instagram/javabasic/concurrent/UploadLatch.java
// 게시물에 이미지 여러 장을 올릴 때, "전부 다 올라간 다음에" 게시물을 공개하고 싶어요.
// 한 장이라도 아직 올라가는 중이면 공개를 미뤄야 하죠. 즉 메인 흐름이 "N 개의 일이 모두 끝날 때
// 까지" 기다려야 해요. join() 으로도 가능하지만, 일감을 풀에 던졌을 땐 스레드를 직접 안 쥐고
// 있어서 join 을 걸 대상이 없어요.
//
// 이럴 때 쓰는 게 CountDownLatch(카운트다운 래치) 예요. 카운트를 N 으로 시작해두고,
//   - 일 하나가 끝날 때마다 countDown() 으로 카운트를 1 줄이고,
//   - 기다리는 쪽은 await() 로 카운트가 0 이 될 때까지 멈춰 있어요.
// 0 이 되는 순간 await() 가 풀려서 다음(게시물 공개) 으로 넘어가요. 로켓 발사 카운트다운처럼요.
// 한 번 0 이 되면 끝이라, 재사용은 안 돼요 (일회용).
public class UploadLatch {

    // imageCount 장이 모두 업로드되면 true(공개 가능) 를 돌려줘요.
    public static boolean publishWhenAllUploaded(int imageCount) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(imageCount);   // 카운트를 imageCount 로 시작
        ConcurrentLinkedQueue<Integer> uploaded = new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int id = 1; id <= imageCount; id++) {
            int imageId = id;
            pool.submit(() -> {
                uploaded.add(imageId);   // 이미지 한 장 업로드 끝
                latch.countDown();       // 카운트 1 줄이기
            });
        }

        boolean allDone = latch.await(5, TimeUnit.SECONDS);   // 0 이 될 때까지 기다림
        pool.shutdown();
        return allDone && uploaded.size() == imageCount;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 이미지 8장이 모두 올라가면 게시물 공개 ===");
        boolean published = publishWhenAllUploaded(8);
        System.out.println("공개 준비 완료? → " + published);
    }
}
