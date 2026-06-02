package com.instagram.javabasic.solution.day15;

// com/instagram/javabasic/solution/day15/CommentSortPolicy.java
// [심화] 과제 3 예시답안 — 댓글 정렬 정책을 상수별 메서드 Enum 으로.
// Step 7(RankingPolicy)과 같은 패턴이에요. 상수마다 score 본문을 자기 방식대로 채워요.
public enum CommentSortPolicy {

    // 좋아요순 — 좋아요가 많을수록 높은 점수
    BEST {
        @Override
        public int score(int likeCount, int ageMinutes) {
            return likeCount;
        }
    },

    // 최신순 — 최근(시간이 적을수록)일수록 높은 점수
    NEWEST {
        @Override
        public int score(int likeCount, int ageMinutes) {
            return -ageMinutes;
        }
    };

    // 본문 없는 약속 — 각 상수가 위에서 자기 방식대로 채웠어요.
    public abstract int score(int likeCount, int ageMinutes);
}
