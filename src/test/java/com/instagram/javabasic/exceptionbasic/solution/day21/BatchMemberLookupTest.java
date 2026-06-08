package com.instagram.javabasic.exceptionbasic.solution.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;

class BatchMemberLookupTest {

    private Repository<Member> repoWithTwo() {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 90));
        return repo;
    }

    @Test
    @DisplayName("없는 id 가 섞여도 멈추지 않고 성공한 회원은 모두 처리한다")
    void continuesPastMissingIds() {
        BatchMemberLookup lookup = new BatchMemberLookup();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(999L);
        ids.add(2L);

        String result = lookup.lookupAll(repoWithTwo(), ids);

        assertTrue(result.contains("minji"));
        assertTrue(result.contains("jaehoon"));
    }

    @Test
    @DisplayName("없는 id 는 건너뜀 표시로 남고 흐름이 끊기지 않는다")
    void marksMissingAsSkipped() {
        BatchMemberLookup lookup = new BatchMemberLookup();
        List<Long> ids = new ArrayList<>();
        ids.add(999L);
        ids.add(1L);

        String result = lookup.lookupAll(repoWithTwo(), ids);

        assertTrue(result.contains("건너뜀"));
        assertTrue(result.contains("minji"));
    }

    @Test
    @DisplayName("finally 가 빠짐없이 세어 시도 횟수 = ids 개수가 된다")
    void countsEveryAttemptWithFinally() {
        BatchMemberLookup lookup = new BatchMemberLookup();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(999L);
        ids.add(2L);
        ids.add(888L);

        String result = lookup.lookupAll(repoWithTwo(), ids);

        assertTrue(result.contains("총 4번 시도"));
    }

    @Test
    @DisplayName("모두 없는 id 라도 끝까지 돌며 시도 횟수를 정확히 센다")
    void countsAllEvenWhenAllMissing() {
        BatchMemberLookup lookup = new BatchMemberLookup();
        List<Long> ids = new ArrayList<>();
        ids.add(777L);
        ids.add(888L);
        ids.add(999L);

        String result = lookup.lookupAll(repoWithTwo(), ids);

        assertTrue(result.contains("총 3번 시도"));
    }
}
