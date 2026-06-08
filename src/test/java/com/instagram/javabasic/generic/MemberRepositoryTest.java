package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class MemberRepositoryTest {

    @Test
    @DisplayName("save 후 findById 로 같은 회원을 형변환 없이 꺼낸다")
    void saveAndFindById() {
        MemberRepository repo = new MemberRepository();
        Member minji = new Member("minji", 8500, 150, 12, 400);
        repo.save(1L, minji);

        Member found = repo.findById(1L);

        assertSame(minji, found);
    }

    @Test
    @DisplayName("findAll 은 보관 중인 모든 회원을 돌려주고 count 와 개수가 같다")
    void findAllAndCount() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 3, 120));

        List<Member> all = repo.findAll();

        assertEquals(2, all.size());
        assertEquals(2, repo.count());
    }

    @Test
    @DisplayName("없는 id 로 findById 를 부르면 IllegalArgumentException 이 터진다")
    void findByIdMissingThrows() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));

        assertThrows(IllegalArgumentException.class, () -> repo.findById(99L));
    }

    @Test
    @DisplayName("findByUsername 으로 이름이 같은 회원을 찾는다")
    void findByUsername() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 3, 120));

        assertEquals(1240, repo.findByUsername("jaehoon").getFollowers());
    }
}
