package com.instagram.javabasic.generic.solution.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class ListUtilsTest {

    @Test
    @DisplayName("lastOf 는 문자열 리스트의 마지막 원소를 돌려준다")
    void lastOfString() {
        List<String> tags = new ArrayList<>();
        tags.add("#일상");
        tags.add("#맛집");
        tags.add("#여행");

        assertEquals("#여행", ListUtils.lastOf(tags));
    }

    @Test
    @DisplayName("lastOf 는 회원 리스트의 마지막 회원을 돌려준다")
    void lastOfMember() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 12, 400));
        members.add(new Member("jaehoon", 1240, 42, 3, 120));

        assertEquals("jaehoon", ListUtils.lastOf(members).getUsername());
    }

    @Test
    @DisplayName("빈 리스트로 lastOf 를 부르면 IllegalArgumentException 이 터진다")
    void lastOfEmptyThrows() {
        List<String> empty = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> ListUtils.lastOf(empty));
    }

    @Test
    @DisplayName("count 는 문자열 리스트에서 같은 값의 개수를 센다")
    void countString() {
        List<String> tags = new ArrayList<>();
        tags.add("#일상");
        tags.add("#맛집");
        tags.add("#일상");

        assertEquals(2, ListUtils.count(tags, "#일상"));
        assertEquals(1, ListUtils.count(tags, "#맛집"));
        assertEquals(0, ListUtils.count(tags, "#여행"));
    }

    @Test
    @DisplayName("count 는 회원 리스트에서 equals(username) 기준으로 개수를 센다")
    void countMember() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 12, 400));
        members.add(new Member("jaehoon", 1240, 42, 3, 120));
        members.add(new Member("minji", 0, 0, 0, 0)); // 같은 username = 같은 사람으로 셈

        assertEquals(2, ListUtils.count(members, new Member("minji", 0, 0, 0, 0)));
        assertEquals(1, ListUtils.count(members, new Member("jaehoon", 0, 0, 0, 0)));
    }
}
