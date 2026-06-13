package com.instagram.javabasic.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

// com/instagram/javabasic/concurrent/AsyncErrorHandling.java
// 비동기 작업도 실패할 수 있어요. 네트워크가 끊기거나, 서버가 에러를 던지거나요.
// 동기 코드라면 try-catch 로 감쌌겠지만, 비동기 조립 라인 안에서는 다른 방식으로 다뤄요.
//
// 세 가지 도구가 있어요.
//   - exceptionally(익셉셔널리): 실패했을 때만 끼어들어 "기본값" 으로 갈아끼워요. (성공이면 그냥 통과)
//   - handle(핸들): 성공이든 실패든 "둘 다" 받아요. 예외가 null 이면 성공, 아니면 실패로 갈라 처리해요.
//   - whenComplete(웬 컴플리트): 결과를 바꾸지 않고 "엿보기" 만 해요. 로그 남길 때 좋아요.
//
// 핵심 차이: exceptionally/handle 은 결과를 "바꿀 수 있고", whenComplete 는 "그대로 흘려보내요".
public class AsyncErrorHandling {

    // exceptionally: 실패하면 -1 이라는 기본값으로 대체해요. 성공하면 그대로 통과.
    public static int loadLikesOrDefault(int postId, boolean fail) {
        return CompletableFuture.supplyAsync(() -> {
            if (fail) {
                throw new IllegalStateException("네트워크 오류");
            }
            return postId * 10;
        }).exceptionally(ex -> -1).join();
    }

    // handle: 성공(ex == null) 과 실패를 한자리에서 갈라 처리해요. 둘 다 문자열을 돌려줘요.
    public static String handleBoth(int postId, boolean fail) {
        return CompletableFuture.supplyAsync(() -> {
            if (fail) {
                throw new IllegalStateException("네트워크 오류");
            }
            return postId * 10;
        }).handle((result, ex) -> ex == null ? ("좋아요 " + result) : "실패: 기본값 사용").join();
    }

    // whenComplete: 결과를 바꾸지 않고 엿보기만 해요. 여기선 결과를 seen 에 기록만 해요.
    public static int peekWithWhenComplete(int postId) {
        AtomicInteger seen = new AtomicInteger();
        CompletableFuture.supplyAsync(() -> postId * 10)
                .whenComplete((res, ex) -> seen.set(res))
                .join();
        return seen.get();
    }

    public static void main(String[] args) {
        System.out.println("=== exceptionally: 실패 시 기본값 ===");
        System.out.println("성공 → " + loadLikesOrDefault(5, false));
        System.out.println("실패 → " + loadLikesOrDefault(5, true));

        System.out.println();
        System.out.println("=== handle: 성공/실패 둘 다 처리 ===");
        System.out.println("성공 → " + handleBoth(5, false));
        System.out.println("실패 → " + handleBoth(5, true));

        System.out.println();
        System.out.println("=== whenComplete: 결과 엿보기 ===");
        System.out.println("엿본 값 → " + peekWithWhenComplete(4));
    }
}
