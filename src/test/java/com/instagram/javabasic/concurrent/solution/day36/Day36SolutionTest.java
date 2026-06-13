package com.instagram.javabasic.concurrent.solution.day36;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day36SolutionTest {

    @Test
    @DisplayName("[과제 1] supplyAsync 로 만든 이름을 thenApply 로 가공하면 환영 문구가 정확히 나온다")
    void 환영_문구_조립() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // "user7" → "환영합니다, user7님!". join() 으로 받아도 입력에 대해 결과는 항상 같다.
            assertEquals("환영합니다, user7님!", GreetingPipeline.buildGreeting(7));
        });
    }

    @Test
    @DisplayName("[과제 2] thenCombine 으로 본문과 작성자를 합치면 카드 문자열이 정확히 만들어진다")
    void 카드_본문_작성자_합치기() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 본문과 작성자를 동시에 받아 합쳐도 결과 문자열은 결정적이다.
            assertEquals("post#3 본문 - by user3", PostCardAssembler.renderCard(3));
        });
    }

    @Test
    @DisplayName("[과제 3] 한 게시물 조회가 실패하면 exceptionally 가 0 으로 메워 나머지만 합산된다")
    void 일부_실패시_0으로_합산() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // postId 2 만 실패 → 10 + 0(실패) + 30 = 40. 실패는 exceptionally 로 0 이라 결정적이다.
            assertEquals(40, FeedLikeSummary.totalLikes(new int[] {1, 2, 3}, 2));
        });
    }

    @Test
    @DisplayName("[과제 3] 실패가 하나도 없으면 모든 좋아요가 그대로 합산된다")
    void 실패_없으면_전부_합산() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 실패 없음 → 10 + 20 + 30 = 60. 모든 작업이 정상 값을 돌려준다.
            assertEquals(60, FeedLikeSummary.totalLikes(new int[] {1, 2, 3}, -1));
        });
    }
}
