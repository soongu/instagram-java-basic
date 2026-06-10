package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class LazyEvaluationDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        return List.of(
                new Post("인기 글", minji, 250),
                new Post("평범한 글", minji, 40));
    }

    @Test
    @DisplayName("최종 연산이 없으면 중간 연산(peek)은 한 번도 실행되지 않는다")
    void noTerminalNoRun() {
        List<String> log = new ArrayList<>();
        LazyEvaluationDemo.withoutTerminal(sample(), log);
        assertTrue(log.isEmpty());
    }

    @Test
    @DisplayName("최종 연산(toList)이 붙으면 원소가 하나씩 흐르며 peek 가 기록을 남긴다")
    void terminalTriggersRun() {
        List<String> log = new ArrayList<>();
        List<String> result = LazyEvaluationDemo.withTerminal(sample(), log);

        assertEquals(List.of("인기 글"), result);
        // 원소 하나가 끝까지 흐른 뒤 다음 원소 — 인기 글은 들어옴+통과, 평범한 글은 들어옴만
        assertEquals(List.of("들어옴:인기 글", "통과:인기 글", "들어옴:평범한 글"), log);
    }
}
