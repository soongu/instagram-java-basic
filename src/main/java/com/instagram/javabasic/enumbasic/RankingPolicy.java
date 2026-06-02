package com.instagram.javabasic.enumbasic;

// com/instagram/javabasic/enumbasic/RankingPolicy.java
// 피드를 어떤 기준으로 정렬할지 정하는 "정책" 이에요.
// 최신순·인기순·급상승순 — 상수는 정해져 있지만, 점수 계산 방법은 셋이 다 달라요.
// 이럴 때 enum 상수마다 메서드 본문을 따로 쓸 수 있어요(상수별 메서드 구현).
// Day 12 에서 본 추상 메서드를 떠올려보세요. 여기서도 score 는 본문 없는 약속이고,
// 각 상수(LATEST·POPULAR·TRENDING)가 자기만의 본문을 채워요.
public enum RankingPolicy {

    // 최신순 — 올라온 지 얼마 안 됐을수록(시간이 적을수록) 높은 점수
    LATEST {
        @Override
        public int score(int likeCount, int ageHours) {
            return -ageHours;
        }
    },

    // 인기순 — 좋아요가 많을수록 높은 점수
    POPULAR {
        @Override
        public int score(int likeCount, int ageHours) {
            return likeCount;
        }
    },

    // 급상승순 — 좋아요는 크게 반영하되, 오래된 글은 깎아요
    TRENDING {
        @Override
        public int score(int likeCount, int ageHours) {
            return likeCount * 2 - ageHours;
        }
    };

    // 본문 없는 약속(추상 메서드) — 각 상수가 위에서 자기 방식대로 채웠어요.
    public abstract int score(int likeCount, int ageHours);
}
