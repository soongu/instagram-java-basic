package com.instagram.javabasic.enumbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReactionTypeTest {

    @Test
    @DisplayName("values(): 정의한 다섯 종류가 모두 들어 있다")
    void values_containsAllFive() {
        assertEquals(5, ReactionType.values().length);
        assertEquals(5, ReactionDemo.howManyTypes());
    }

    @Test
    @DisplayName("name(): 상수 이름을 문자열로 돌려준다")
    void name_returnsConstantName() {
        assertEquals("LOVE", ReactionType.LOVE.name());
    }

    @Test
    @DisplayName("ordinal(): 선언 순서대로 0부터 번호가 매겨진다")
    void ordinal_followsDeclarationOrder() {
        assertEquals(0, ReactionType.LIKE.ordinal());
        assertEquals(1, ReactionType.LOVE.ordinal());
        assertEquals(4, ReactionType.SAD.ordinal());
    }

    @Test
    @DisplayName("valueOf(): 문자열로 상수를 찾아온다")
    void valueOf_findsConstant() {
        assertSame(ReactionType.WOW, ReactionType.valueOf("WOW"));
    }

    @Test
    @DisplayName("switch: 반응 종류마다 알맞은 이모지를 돌려준다")
    void emojiOf_mapsEachReaction() {
        assertEquals("👍", ReactionDemo.emojiOf(ReactionType.LIKE));
        assertEquals("❤️", ReactionDemo.emojiOf(ReactionType.LOVE));
        assertEquals("😂", ReactionDemo.emojiOf(ReactionType.HAHA));
        assertEquals("😮", ReactionDemo.emojiOf(ReactionType.WOW));
        assertEquals("😢", ReactionDemo.emojiOf(ReactionType.SAD));
    }

    @Test
    @DisplayName("== 비교: 같은 상수면 true, 다른 상수면 false")
    void isSame_comparesByReference() {
        assertTrue(ReactionDemo.isSame(ReactionType.LOVE, ReactionType.LOVE));
        assertFalse(ReactionDemo.isSame(ReactionType.LOVE, ReactionType.SAD));
    }
}
