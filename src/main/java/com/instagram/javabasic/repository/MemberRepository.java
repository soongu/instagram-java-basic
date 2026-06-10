package com.instagram.javabasic.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

// com/instagram/javabasic/repository/MemberRepository.java
// 회원을 보관하는 본격 인메모리 저장소예요.
// 지난 시간엔 id 번호표를 바깥에서 직접 붙여 넘겼는데(save(id, member)),
// 이번엔 저장소가 스스로 다음 번호표를 뽑아 붙여요(save(member) → 새 id 돌려줌).
// 없는 회원을 찾으면 IllegalArgumentException 대신, 우리가 만든 MemberNotFoundException 을 던져
// "회원 조회가 실패했구나" 를 예외 이름만으로 알 수 있게 했어요.
public class MemberRepository {

    // 번호표(id) → 회원 짝을 보관하는 사물함이에요.
    private final Map<Long, Member> store = new HashMap<>();

    // 다음에 발급할 번호표예요. 저장할 때마다 1씩 올라가요(1, 2, 3, ...).
    private long sequence = 0L;

    // 저장 — 다음 번호표를 뽑아 회원에게 붙이고 보관한 뒤, 그 번호표(id)를 돌려줘요.
    public Long save(Member member) {
        sequence++;
        Long id = sequence;
        store.put(id, member);
        return id;
    }

    // 조회 — id 로 회원을 찾아요. 없으면 MemberNotFoundException 을 던져 "그 회원 없어요" 를 분명히 알려요.
    public Member findById(Long id) {
        Member found = store.get(id);
        if (found == null) {
            throw new MemberNotFoundException("id " + id + " 에 해당하는 회원이 없어요.");
        }
        return found;
    }

    // 이 이메일을 쓰는 회원이 이미 있는지 — 사물함 안의 회원을 하나하나 훑어 이메일을 비교해요.
    public boolean existsByEmail(String email) {
        for (Member member : store.values()) {
            if (email != null && email.equals(member.getEmail())) {
                return true;
            }
        }
        return false;
    }

    // 이메일로 회원을 찾아요. 없으면 MemberNotFoundException 을 던져요.
    public Member findByEmail(String email) {
        for (Member member : store.values()) {
            if (email != null && email.equals(member.getEmail())) {
                return member;
            }
        }
        throw new MemberNotFoundException("이메일 " + email + " 로 가입한 회원이 없어요.");
    }

    // 지금까지 저장된 회원 전체를 묶음으로 돌려줘요(원본 사물함을 그대로 넘기지 않으려고 새 리스트에 담아요).
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 저장된 회원 수를 세요.
    public int count() {
        return store.size();
    }
}
