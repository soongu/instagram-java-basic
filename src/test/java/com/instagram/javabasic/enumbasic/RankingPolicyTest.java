package com.instagram.javabasic.enumbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankingPolicyTest {

    @Test
    @DisplayName("LATEST: 오래된 글일수록 점수가 낮다")
    void latest_prefersFresh() {
        int fresh = RankingPolicy.LATEST.score(10, 1);
        int old = RankingPolicy.LATEST.score(10, 100);
        assertTrue(fresh > old);
    }

    @Test
    @DisplayName("POPULAR: 좋아요 수가 곧 점수다")
    void popular_usesLikeCount() {
        assertEquals(250, RankingPolicy.POPULAR.score(250, 50));
    }

    @Test
    @DisplayName("TRENDING: 좋아요는 두 배로, 오래된 만큼 깎는다")
    void trending_weightsLikesMinusAge() {
        assertEquals(80, RankingPolicy.TRENDING.score(50, 20));
    }

    @Test
    @DisplayName("같은 입력이라도 정책마다 점수가 다르다")
    void eachPolicyScoresDifferently() {
        int likes = 100;
        int age = 10;
        assertEquals(-10, RankingPolicy.LATEST.score(likes, age));
        assertEquals(100, RankingPolicy.POPULAR.score(likes, age));
        assertEquals(190, RankingPolicy.TRENDING.score(likes, age));
    }
}
