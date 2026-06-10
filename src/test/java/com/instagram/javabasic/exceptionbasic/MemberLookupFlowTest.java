package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberLookupFlowTest {

    private Repository<Member> sampleRepo() {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 200));
        return repo;
    }

    @Test
    @DisplayName("던지는 쪽: 있는 id 면 회원을 그대로 돌려준다")
    void findMember_정상() {
        MemberLookupFlow flow = new MemberLookupFlow(sampleRepo());
        assertEquals("minji", flow.findMember(1L).getUsername());
    }

    @Test
    @DisplayName("던지는 쪽: 없는 id 면 저장소의 예외가 그대로 전파된다")
    void findMember_없는id_전파() {
        MemberLookupFlow flow = new MemberLookupFlow(sampleRepo());
        assertThrows(IllegalArgumentException.class, () -> flow.findMember(99L));
    }

    @Test
    @DisplayName("받는 쪽: 있는 id 면 환영 인사를 돌려준다")
    void greet_정상() {
        MemberLookupFlow flow = new MemberLookupFlow(sampleRepo());
        assertEquals("minji님 환영합니다!", flow.greet(1L));
    }

    @Test
    @DisplayName("받는 쪽: 없는 id 면 전파돼 온 예외를 잡아 안내 문구를 돌려준다")
    void greet_없는id_복구() {
        MemberLookupFlow flow = new MemberLookupFlow(sampleRepo());
        String result = flow.greet(99L);
        assertEquals("회원을 찾을 수 없어요: id 99 에 해당하는 항목이 없어요.", result);
    }
}
