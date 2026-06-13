package com.instagram.javabasic.concurrent.solution.day36;

import java.util.concurrent.CompletableFuture;

// com/instagram/javabasic/concurrent/solution/day36/GreetingPipeline.java
// [과제 1] supplyAsync 로 사용자 이름을 만들고, thenApply 로 환영 문구로 변환하기.
// supplyAsync 에서 "user7" 같은 이름을 먼저 만들어 내보내고, 그 결과를 thenApply 가
// 받아서 "환영합니다, user7님!" 으로 한 번 더 가공해요. (값 → 값 변환을 이어 붙이는 흐름)
// 마지막에 join() 으로 완성된 문구를 거두면, 결과는 입력에 대해 항상 같은 값이라 결정적이에요.
public class GreetingPipeline {

    // supplyAsync 로 만든 이름을 thenApply 로 환영 문구로 바꿔서 돌려줘요.
    public static String buildGreeting(int userId) {
        return CompletableFuture.supplyAsync(() -> "user" + userId)
                .thenApply(name -> "환영합니다, " + name + "님!")
                .join();
    }

    public static void main(String[] args) {
        System.out.println("=== supplyAsync + thenApply: 환영 문구 만들기 ===");
        System.out.println(buildGreeting(7));
    }
}
