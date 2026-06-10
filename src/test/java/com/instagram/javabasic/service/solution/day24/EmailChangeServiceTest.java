package com.instagram.javabasic.service.solution.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.DuplicateEmailException;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailChangeServiceTest {

    @Test
    @DisplayName("비어 있는 새 이메일로 바꾸면 회원의 이메일이 바뀐다")
    void changeEmail_정상() {
        MemberRepository repo = new MemberRepository();
        EmailChangeService service = new EmailChangeService(repo);
        Long id = repo.save(new Member("jaehoon", "old@insta.com"));
        service.changeEmail(id, "new@insta.com");
        assertEquals("new@insta.com", repo.findById(id).getEmail());
    }

    @Test
    @DisplayName("다른 회원이 쓰는 이메일로 바꾸려 하면 DuplicateEmailException")
    void changeEmail_중복() {
        MemberRepository repo = new MemberRepository();
        EmailChangeService service = new EmailChangeService(repo);
        Long jaehoonId = repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        repo.save(new Member("minji", "minji@insta.com"));
        assertThrows(DuplicateEmailException.class,
                () -> service.changeEmail(jaehoonId, "minji@insta.com"));
    }

    @Test
    @DisplayName("없는 회원의 이메일을 바꾸려 하면 MemberNotFoundException")
    void changeEmail_없는회원() {
        MemberRepository repo = new MemberRepository();
        EmailChangeService service = new EmailChangeService(repo);
        assertThrows(MemberNotFoundException.class, () -> service.changeEmail(99L, "x@insta.com"));
    }

    @Test
    @DisplayName("형식이 틀린 이메일은 IllegalArgumentException 으로 막힌다")
    void changeEmail_형식() {
        MemberRepository repo = new MemberRepository();
        EmailChangeService service = new EmailChangeService(repo);
        Long id = repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        assertThrows(IllegalArgumentException.class, () -> service.changeEmail(id, "no-at-sign"));
    }

    @Test
    @DisplayName("자기가 이미 쓰는 이메일 그대로 바꾸는 건 막지 않는다")
    void changeEmail_자기이메일() {
        MemberRepository repo = new MemberRepository();
        EmailChangeService service = new EmailChangeService(repo);
        Long id = repo.save(new Member("jaehoon", "jaehoon@insta.com"));
        service.changeEmail(id, "jaehoon@insta.com");
        assertEquals("jaehoon@insta.com", repo.findById(id).getEmail());
    }
}
