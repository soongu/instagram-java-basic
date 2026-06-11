package com.instagram.javabasic.modern.solution.day29;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day29SolutionTest {

    // ===== 과제 1: Hashtag + HashtagCollector =====

    @Test
    @DisplayName("과제1 distinctTags: 같은 이름 태그는 record 자동 equals 로 한 번만")
    void distinctTags() {
        List<String> names = List.of("여행", "맛집", "여행", "일상", "맛집");
        Set<Hashtag> tags = HashtagCollector.distinctTags(names);
        assertEquals(3, tags.size());
        assertTrue(tags.contains(new Hashtag("여행")));
        assertTrue(tags.contains(new Hashtag("일상")));
    }

    @Test
    @DisplayName("과제1 Hashtag: 값이 같으면 equals/hashCode 가 같다")
    void hashtagEquality() {
        assertEquals(new Hashtag("여행"), new Hashtag("여행"));
        assertEquals(new Hashtag("여행").hashCode(), new Hashtag("여행").hashCode());
    }

    // ===== 과제 2: LikeCount =====

    @Test
    @DisplayName("과제2 LikeCount: 0 이상은 정상")
    void likeCountValid() {
        assertEquals(0, new LikeCount(0).value());
        assertEquals(150, new LikeCount(150).value());
    }

    @Test
    @DisplayName("과제2 LikeCount: 음수는 만드는 순간 IllegalArgumentException")
    void likeCountNegative() {
        assertThrows(IllegalArgumentException.class, () -> new LikeCount(-1));
    }

    // ===== 과제 3: FeedEvent + FeedEventMessages =====

    @Test
    @DisplayName("과제3 describe: 사건 종류별로 사람이 읽는 문장")
    void describeEvents() {
        assertEquals("dana님이 회원님의 스토리를 봤어요.",
                FeedEventMessages.describe(new StoryViewed("dana")));
        assertEquals("jaehoon님이 '노을 사진' 에서 회원님을 언급했어요.",
                FeedEventMessages.describe(new Mentioned("jaehoon", "노을 사진")));
        assertEquals("minji님이 '단체 사진' 사진에 회원님을 태그했어요.",
                FeedEventMessages.describe(new Tagged("minji", "단체 사진")));
    }
}
