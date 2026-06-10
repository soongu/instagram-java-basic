package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberLookupFlowV2Test {

    private MemberRepository sampleRepo() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 200));
        return repo;
    }

    @Test
    @DisplayName("있는 id 면 회원을 그대로 돌려준다")
    void findMember_정상() {
        MemberLookupFlowV2 flow = new MemberLookupFlowV2(sampleRepo());
        assertEquals("minji", flow.findMember(1L).getUsername());
    }

    @Test
    @DisplayName("없는 id 면 MemberNotFoundException 으로 바뀌어 던져진다")
    void findMember_없는id_커스텀예외() {
        MemberLookupFlowV2 flow = new MemberLookupFlowV2(sampleRepo());
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> flow.findMember(99L));
        assertEquals("회원을 찾을 수 없어요: id 99", e.getMessage());
    }

    @Test
    @DisplayName("end-to-end: 던져진 예외의 Caused by 는 저장소의 IllegalArgumentException 이다")
    void findMember_원인추적_endToEnd() {
        MemberLookupFlowV2 flow = new MemberLookupFlowV2(sampleRepo());
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> flow.findMember(99L));
        IllegalArgumentException cause = assertInstanceOf(IllegalArgumentException.class,
                e.getCause());
        assertEquals("id 99 에 해당하는 회원이 없어요.", cause.getMessage());
    }

    @Test
    @DisplayName("받는 쪽: 있는 id 면 환영 인사를 돌려준다")
    void greet_정상() {
        MemberLookupFlowV2 flow = new MemberLookupFlowV2(sampleRepo());
        assertEquals("minji님 환영합니다!", flow.greet(1L));
    }

    @Test
    @DisplayName("받는 쪽: 없는 id 면 getMessage 와 getCause 를 모두 살린 안내 문구를 돌려준다")
    void greet_없는id_메시지와원인() {
        MemberLookupFlowV2 flow = new MemberLookupFlowV2(sampleRepo());
        String result = flow.greet(99L);
        assertEquals("회원을 찾을 수 없어요: id 99 (저장소 기록: id 99 에 해당하는 회원이 없어요.)",
                result);
    }
}
