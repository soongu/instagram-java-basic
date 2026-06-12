package com.instagram.javabasic.concurrent.solution.day33;

import com.instagram.javabasic.concurrent.FeedLoader;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/concurrent/solution/day33/FeedBatchLoader.java
// [과제 2] 피드 이미지 여러 장을 동시에 로딩하고, 모든 스레드를 join 으로 기다린 뒤 전부 완료됐는지 확인한다.
public class FeedBatchLoader {

    // 이미지 imageCount 장을 동시에 로딩하고, 전부 끝나면 true 를 돌려준다.
    public boolean loadAll(int imageCount) throws InterruptedException {
        List<FeedLoader> loaders = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // 1) FeedLoader 를 imageCount 개 만들어 각각 스레드로 동시에 띄운다.
        for (int id = 1; id <= imageCount; id++) {
            FeedLoader loader = new FeedLoader(id);
            Thread thread = new Thread(loader);
            loaders.add(loader);
            threads.add(thread);
            thread.start();
        }

        // 2) 만들어둔 모든 스레드가 끝날 때까지 join 으로 기다린다.
        for (Thread thread : threads) {
            thread.join();
        }

        // 3) join 이 다 끝났으니, 모든 로더가 로딩 완료됐는지 안전하게 확인한다.
        for (FeedLoader loader : loaders) {
            if (!loader.isLoaded()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        FeedBatchLoader batch = new FeedBatchLoader();
        boolean allLoaded = batch.loadAll(5);
        if (allLoaded) {
            System.out.println("이미지 5장 모두 로딩 완료!");
        }
    }
}
