package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExceptionChainingDemoTest {

    private Repository<Member> sampleRepo() {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        return repo;
    }

    @Test
    @DisplayName("있는 id 면 회원을 그대로 돌려준다")
    void findMember_정상() {
        ExceptionChainingDemo demo = new ExceptionChainingDemo(sampleRepo());
        assertEquals("minji", demo.findMember(1L).getUsername());
    }

    @Test
    @DisplayName("없는 id 면 저장소 예외를 MemberNotFoundException 으로 재포장해 던진다")
    void findMember_없는id_재포장() {
        ExceptionChainingDemo demo = new ExceptionChainingDemo(sampleRepo());
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> demo.findMember(99L));
        assertEquals("회원을 찾을 수 없어요: id 99", e.getMessage());
    }

    @Test
    @DisplayName("재포장된 예외의 getCause 는 원래의 IllegalArgumentException 이다")
    void getCause_원인추적() {
        ExceptionChainingDemo demo = new ExceptionChainingDemo(sampleRepo());
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> demo.findMember(99L));
        assertInstanceOf(IllegalArgumentException.class, e.getCause());
    }

    @Test
    @DisplayName("원인 예외의 메시지(저장소가 남긴 흔적)가 그대로 보존된다")
    void 원인_메시지_보존() {
        ExceptionChainingDemo demo = new ExceptionChainingDemo(sampleRepo());
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> demo.findMember(99L));
        assertEquals("id 99 에 해당하는 항목이 없어요.", e.getCause().getMessage());
    }
}
