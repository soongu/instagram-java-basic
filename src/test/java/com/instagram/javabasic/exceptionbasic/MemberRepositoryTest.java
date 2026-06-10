package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    @Test
    @DisplayName("저장한 회원을 같은 id 로 다시 꺼낼 수 있다")
    void save_findById_왕복() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        assertEquals("minji", repo.findById(1L).getUsername());
    }

    @Test
    @DisplayName("없는 id 를 찾으면 IllegalArgumentException 을 던진다 (저장소의 약속)")
    void findById_없는id() {
        MemberRepository repo = new MemberRepository();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> repo.findById(99L));
        assertEquals("id 99 에 해당하는 회원이 없어요.", e.getMessage());
    }

    @Test
    @DisplayName("같은 id 로 다시 저장하면 덮어쓴다")
    void save_같은id_덮어쓰기() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(1L, new Member("jaehoon", 1240, 42, 5, 200));
        assertEquals("jaehoon", repo.findById(1L).getUsername());
    }
}
