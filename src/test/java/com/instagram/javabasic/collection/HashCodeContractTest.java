package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class HashCodeContractTest {

    @Test
    @DisplayName("같은 username Member 두 개를 HashSet 에 넣으면 size 는 1 이다")
    void sameUsernameCountsOnce() {
        HashCodeContract demo = new HashCodeContract();
        Set<Member> members = demo.uniqueMembers();

        assertEquals(2, members.size());
    }

    @Test
    @DisplayName("username 이 같으면 equals 도 true, hashCode 도 같다(계약 충족)")
    void equalsAndHashCodeContract() {
        Member a = new Member("minji", 8500, 150, 12, 400);
        Member b = new Member("minji", 9999, 200, 20, 500);

        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());
    }
}
