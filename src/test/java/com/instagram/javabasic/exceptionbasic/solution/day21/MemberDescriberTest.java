package com.instagram.javabasic.exceptionbasic.solution.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;

class MemberDescriberTest {

    private Repository<Member> repoWithMinji() {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        return repo;
    }

    @Test
    @DisplayName("있는 id 면 회원 username 을 담은 문장을 돌려준다")
    void describesExistingMember() {
        MemberDescriber describer = new MemberDescriber();

        String result = describer.describe(repoWithMinji(), 1L);

        assertEquals("회원: minji", result);
    }

    @Test
    @DisplayName("없는 id 면 IllegalArgumentException 을 잡아 안내 문구를 돌려준다")
    void handlesMissingMember() {
        MemberDescriber describer = new MemberDescriber();

        String result = describer.describe(repoWithMinji(), 999L);

        assertEquals("없는 회원이에요.", result);
    }

    @Test
    @DisplayName("없는 id 조회 뒤에도 있는 id 조회가 정상 동작한다(복구)")
    void recoversAfterMissingLookup() {
        MemberDescriber describer = new MemberDescriber();
        Repository<Member> repo = repoWithMinji();

        describer.describe(repo, 999L);
        String result = describer.describe(repo, 1L);

        assertEquals("회원: minji", result);
    }
}
