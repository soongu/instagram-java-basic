package com.instagram.javabasic.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/CompletableFutureIntro.java
// 지난 시간 Future.get() 은 결과가 나올 때까지 그 자리에서 "막혀서" 기다렸어요.
// 한 장이면 괜찮지만, 결과가 나오면 곧바로 다음 작업으로 이어붙이고 싶을 때는
// "기다렸다가 받고, 또 기다렸다가 받고" 가 영 불편했죠.
//
// CompletableFuture(컴플리터블 퓨처) 는 그 불편함을 풀어줘요. "결과가 나오면 이렇게 해줘" 라는
// 후속 작업을 미리 등록해 두는, 한 단계 똑똑해진 Future 예요.
//
// 작업을 시작하는 두 가지 출발점이 있어요.
//   - supplyAsync(서플라이 어싱크): 결과를 return 하는 일감. 나중에 그 결과를 받아요.
//   - runAsync(런 어싱크): 결과 없이 부수효과(로그 남기기 등) 만 내는 일감. 받아올 값이 없어요.
//
// 결과를 꺼내는 방법도 두 가지예요.
//   - get(): 지난 시간에 쓰던 방식. checked 예외(try-catch 강제) 를 던져요.
//   - join(): CompletableFuture 가 새로 준 방식. unchecked 예외라 try-catch 없이 깔끔해요.
// 그래서 이번 시간엔 join() 을 주로 써요.
public class CompletableFutureIntro {

    // supplyAsync: 결과를 돌려주는 비동기 작업. join() 으로 그 결과를 받아요.
    public static String supplyGreeting(String username) {
        return CompletableFuture.supplyAsync(() -> "안녕하세요, " + username + "님!").join();
    }

    // 좋아요 수를 두 배로. 단순한 값도 supplyAsync 안에서 계산해 돌려받을 수 있어요.
    public static int doubleLikes(int likes) {
        return CompletableFuture.supplyAsync(() -> likes * 2).join();
    }

    // runAsync: 돌려줄 결과가 없는 작업. 여기선 카운터를 1 올리는 "부수효과" 만 내요.
    // join() 은 작업이 끝날 때까지 기다리기만 하고, 받아올 값은 없어요.
    public static int runAsyncThenCount() {
        AtomicInteger counter = new AtomicInteger();
        CompletableFuture.runAsync(() -> counter.incrementAndGet()).join();
        return counter.get();
    }

    public static void main(String[] args) {
        System.out.println("=== supplyAsync: 결과 있는 작업 ===");
        System.out.println(supplyGreeting("jaehoon"));
        System.out.println("좋아요 21 → " + doubleLikes(21));

        System.out.println();
        System.out.println("=== runAsync: 결과 없는 작업(부수효과만) ===");
        System.out.println("카운터 → " + runAsyncThenCount());
    }
}
