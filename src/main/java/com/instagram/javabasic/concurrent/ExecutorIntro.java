package com.instagram.javabasic.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// com/instagram/javabasic/concurrent/ExecutorIntro.java
// 지금까지(Day 33~34)는 일이 생길 때마다 new Thread(...) 로 일꾼을 "직접" 만들었어요.
// 그런데 스레드를 새로 만드는 건 공짜가 아니에요. 만들고 없애는 데 시간이 걸리고, 한 개당
// 메모리도 꽤 차지해요. 좋아요 1만 건을 1만 개의 스레드로 처리하려 들면 만들다가 지쳐버려요.
//
// 그래서 발상을 바꿔요. 일꾼 몇 명을 "미리 고용" 해두고, 우리는 일감(task) 만 던지는 거예요.
// 이렇게 일꾼을 모아둔 곳을 스레드풀(thread pool), 풀에 일감을 맡아 돌려주는 매니저를
// ExecutorService(실행 서비스) 라고 불러요. 식당으로 치면, 손님이 올 때마다 요리사를 새로
// 채용하는 게 아니라, 요리사 3명을 미리 두고 주문서만 계속 넣는 셈이에요.
public class ExecutorIntro {

    // 일꾼 3명짜리 풀에게 taskCount 개의 일감을 던지고, 끝난 일감 수를 돌려줘요.
    public static int runTasks(int taskCount) throws InterruptedException {
        // 여러 스레드가 동시에 넣어도 안전한 수거함이에요 (끝낸 일감을 여기 모아요).
        ConcurrentLinkedQueue<Integer> done = new ConcurrentLinkedQueue<>();

        // 일꾼 3명을 미리 고용해 둬요. 이제 우리는 스레드를 직접 안 만들고 일감만 던지면 돼요.
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= taskCount; i++) {
            int taskId = i;
            // execute() 에 "할 일" 을 던지면, 노는 일꾼이 알아서 집어가 처리해요.
            pool.execute(() -> done.add(taskId));
        }

        pool.shutdown();                              // 더는 새 일감 안 받음 (받은 건 마저 끝냄)
        pool.awaitTermination(5, TimeUnit.SECONDS);   // 다 끝날 때까지 잠깐 기다림
        return done.size();
    }

    public static void main(String[] args) throws InterruptedException {
        int done = runTasks(10);
        System.out.println("=== 일꾼 3명에게 일감 10개 던지기 ===");
        System.out.println("끝낸 일감 수 → " + done);
        System.out.println("스레드를 10개 새로 만들지 않고, 일꾼 3명이 10개를 나눠 처리했어요.");
    }
}
