package com.instagram.javabasic.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/AsyncPipeline.java
// CompletableFuture 의 진짜 매력은 "결과가 나오면 이렇게 이어서 해줘" 를 줄줄이 붙이는 거예요.
// 결과를 받아 막힘 없이 다음 단계로 흘려보내는, 작은 조립 라인(pipeline) 을 만드는 셈이죠.
//
// 세 가지 후속 작업을 구분해서 써요.
//   - thenApply(덴 어플라이): 결과를 받아 "다른 값으로 변환" 해서 다음으로 넘겨요. (값 → 값)
//   - thenAccept(덴 액셉트): 결과를 받아 "소비" 만 해요. 돌려줄 값이 없어요. (값 → 없음)
//   - thenRun(덴 런): 앞 결과를 받지도 않고, "그냥 끝나면 이것도 해줘" 예요. (없음 → 없음)
//
// thenApply 는 여러 번 이어 붙일 수 있어요. "user7" → 대문자 "USER7" → 길이 5 처럼
// 변환을 차곡차곡 연결하면, 그게 그대로 비동기 조립 라인이 돼요.
public class AsyncPipeline {

    // thenApply 를 두 번 이어 붙여요: 문자열 만들기 → 대문자 → 길이.
    public static int profileNameLength(int userId) {
        return CompletableFuture.supplyAsync(() -> "user" + userId)
                .thenApply(String::toUpperCase)
                .thenApply(String::length)
                .join();
    }

    // thenApply 로 결과를 다른 값(대문자) 으로 변환해요.
    public static String pipelineToUpper(String raw) {
        return CompletableFuture.supplyAsync(() -> raw)
                .thenApply(String::toUpperCase)
                .join();
    }

    // thenAccept 는 결과를 소비만 하고 Void 를 돌려줘요. 그 뒤 thenRun 은 입력 없이 후속 작업만.
    // 여기선 100 을 더하고(소비), 끝나면 1 을 더 올려요(후속). 그래서 101.
    public static int acceptThenRun() {
        AtomicInteger log = new AtomicInteger();
        CompletableFuture.supplyAsync(() -> 100)
                .thenAccept(v -> log.addAndGet(v))
                .thenRun(() -> log.incrementAndGet())
                .join();
        return log.get();
    }

    public static void main(String[] args) {
        System.out.println("=== thenApply 체인: 변환을 이어 붙이기 ===");
        System.out.println("user7 의 대문자 길이 → " + profileNameLength(7));
        System.out.println("hello → " + pipelineToUpper("hello"));

        System.out.println();
        System.out.println("=== thenAccept(소비) + thenRun(후속) ===");
        System.out.println("로그 누적 → " + acceptThenRun());
    }
}
