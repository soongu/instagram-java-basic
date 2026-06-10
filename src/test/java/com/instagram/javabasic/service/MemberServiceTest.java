package com.instagram.javabasic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.exceptionbasic.DuplicateEmailException;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private MemberService newService() {
        return new MemberService(new MemberRepository());
    }

    @Test
    @DisplayName("signup 은 새 id 를 돌려주고, 그 id 로 가입한 회원을 찾을 수 있다")
    void signup_정상() {
        MemberService service = newService();
        Long id = service.signup("jaehoon", "jaehoon@insta.com");
        assertEquals("jaehoon", service.getMember(id).getUsername());
        assertEquals("jaehoon@insta.com", service.getMember(id).getEmail());
    }

    @Test
    @DisplayName("이미 가입된 이메일로 또 가입하면 DuplicateEmailException 을 던진다")
    void signup_중복이메일() {
        MemberService service = newService();
        service.signup("jaehoon", "shared@insta.com");
        DuplicateEmailException e = assertThrows(DuplicateEmailException.class,
                () -> service.signup("another", "shared@insta.com"));
        assertEquals("이미 가입된 이메일이에요: shared@insta.com", e.getMessage());
    }

    @Test
    @DisplayName("이메일이 비었거나 @ 가 없으면 IllegalArgumentException 을 던진다")
    void signup_이메일형식() {
        MemberService service = newService();
        assertThrows(IllegalArgumentException.class, () -> service.signup("a", "  "));
        assertThrows(IllegalArgumentException.class, () -> service.signup("b", "no-at-sign"));
    }

    @Test
    @DisplayName("없는 id 로 회원을 찾으면 MemberNotFoundException 을 던진다")
    void getMember_없는id() {
        MemberService service = newService();
        assertThrows(MemberNotFoundException.class, () -> service.getMember(99L));
    }
}
