package com.instagram.javabasic.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    @Test
    @DisplayName("save 는 1부터 자동으로 번호표(id)를 붙여 돌려준다")
    void save_자동id_증가() {
        MemberRepository repo = new MemberRepository();
        Long id1 = repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        Long id2 = repo.save(new Member("minji", "minji@insta.com"));
        assertEquals(1L, id1);
        assertEquals(2L, id2);
    }

    @Test
    @DisplayName("저장한 회원을 받은 id 로 다시 꺼낼 수 있다")
    void save_findById_왕복() {
        MemberRepository repo = new MemberRepository();
        Long id = repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        assertEquals("jaehoon", repo.findById(id).getUsername());
    }

    @Test
    @DisplayName("없는 id 를 찾으면 MemberNotFoundException 을 던진다")
    void findById_없는id() {
        MemberRepository repo = new MemberRepository();
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> repo.findById(99L));
        assertEquals("id 99 에 해당하는 회원이 없어요.", e.getMessage());
    }

    @Test
    @DisplayName("existsByEmail 은 같은 이메일이 있으면 true, 없으면 false")
    void existsByEmail() {
        MemberRepository repo = new MemberRepository();
        repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        assertTrue(repo.existsByEmail("jaehoon@insta.com"));
        assertFalse(repo.existsByEmail("none@insta.com"));
    }

    @Test
    @DisplayName("findByEmail 은 이메일로 회원을 찾고, 없으면 예외를 던진다")
    void findByEmail() {
        MemberRepository repo = new MemberRepository();
        repo.save(new Member("minji", "minji@insta.com"));
        assertEquals("minji", repo.findByEmail("minji@insta.com").getUsername());
        assertThrows(MemberNotFoundException.class, () -> repo.findByEmail("none@insta.com"));
    }

    @Test
    @DisplayName("findAll 과 count 는 저장된 회원 전체를 알려준다")
    void findAll_count() {
        MemberRepository repo = new MemberRepository();
        repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        repo.save(new Member("minji", "minji@insta.com"));
        assertEquals(2, repo.findAll().size());
        assertEquals(2, repo.count());
    }
}
