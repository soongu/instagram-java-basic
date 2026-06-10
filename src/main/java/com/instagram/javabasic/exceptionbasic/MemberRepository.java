package com.instagram.javabasic.exceptionbasic;

import java.util.HashMap;
import java.util.Map;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/exceptionbasic/MemberRepository.java
// 회원 전용 인메모리 저장소예요. Map<Long, Member> 에 "번호표 → 회원" 짝으로 보관해요.
// 지난 시간의 만능 저장소(Repository<T>)와 약속이 같아요 — 없는 id 를 찾으면
// null 을 슬쩍 돌려주지 않고 IllegalArgumentException 을 던져 "없다" 를 분명히 알려요.
// 다음 시간에 이 저장소를 도메인별로 갖춰 진짜 서비스 모양으로 키워 볼 거예요.
public class MemberRepository {

    // 번호표(id) 를 키로, 회원을 값으로 보관하는 사물함이에요.
    private final Map<Long, Member> store = new HashMap<>();

    // 저장 — id 번호표를 붙여 회원을 넣어요. 같은 id 면 덮어써요.
    public void save(Long id, Member member) {
        store.put(id, member);
    }

    // 조회 — id 로 회원을 찾아요. 없으면 예외를 던져 "그 번호는 없어요" 를 분명히 알려요.
    public Member findById(Long id) {
        Member found = store.get(id);
        if (found == null) {
            throw new IllegalArgumentException("id " + id + " 에 해당하는 회원이 없어요.");
        }
        return found;
    }
}
