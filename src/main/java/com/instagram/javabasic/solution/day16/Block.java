package com.instagram.javabasic.solution.day16;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/solution/day16/Block.java
// [응용] 과제 3 예시답안 — 차단(Block) "관계" 한 건을 객체로 표현하는 클래스예요.
// 팔로우 관계를 Follow 객체로 만든 것(Step 6)과 똑같은 결이에요.
// "누가 누구를 차단한다" 는 두 사람 사이의 연결이고, 그 연결 자체를 객체로 만들어요.
// 한 번 맺어진 차단 관계는 바뀌지 않으니 두 필드 모두 final 로 잠가 불변으로 만들어요.
public class Block {

    // 차단을 거는 사람
    private final Member blocker;
    // 차단을 당하는 사람
    private final Member blocked;

    // 두 사람을 받아 차단 관계를 완성해요. 한 번 정해지면 못 바꿔요.
    public Block(Member blocker, Member blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
    }

    public Member getBlocker() {
        return blocker;
    }

    public Member getBlocked() {
        return blocked;
    }

    // 관계를 한눈에 보이게 — "@차단한사람 ⊘ @차단당한사람" 형식으로 출력해요.
    @Override
    public String toString() {
        return "@" + blocker.getUsername() + " ⊘ @" + blocked.getUsername();
    }
}
