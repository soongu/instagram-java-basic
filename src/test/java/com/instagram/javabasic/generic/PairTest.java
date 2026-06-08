package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class PairTest {

    @Test
    @DisplayName("이름표(String) 와 회원(Member) 한 쌍을 형변환 없이 꺼낸다")
    void stringMemberPair() {
        Member minji = new Member("minji", 8500, 150, 12, 400);
        Pair<String, Member> entry = new Pair<>("minji", minji);

        String key = entry.getKey();
        Member value = entry.getValue();

        assertEquals("minji", key);
        assertSame(minji, value);
    }

    @Test
    @DisplayName("키와 값의 타입이 서로 달라도 한 Pair 로 묶는다")
    void differentKeyValueTypes() {
        Pair<String, Integer> likeCount = new Pair<>("게시물 좋아요", 505);

        assertEquals("게시물 좋아요", likeCount.getKey());
        assertEquals(505, likeCount.getValue());
    }
}
