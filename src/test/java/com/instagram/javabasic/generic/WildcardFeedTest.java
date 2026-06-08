package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.member.AdminMember;
import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.member.PremiumMember;

class WildcardFeedTest {

    private final WildcardFeed feed = new WildcardFeed();

    @Test
    @DisplayName("? extends Member 는 List<PremiumMember> 를 받아 팔로워를 합산한다")
    void totalFollowersAcceptsPremiumSubtypeList() {
        List<PremiumMember> premiums = new ArrayList<>();
        premiums.add(new PremiumMember("minji", 8500, 150, 12, 400, true));
        premiums.add(new PremiumMember("jaehoon", 1240, 42, 3, 120, false));

        assertEquals(9740, feed.totalFollowers(premiums));
    }

    @Test
    @DisplayName("? extends Member 는 List<AdminMember> 도 같은 메서드로 받는다")
    void totalFollowersAcceptsAdminSubtypeList() {
        List<AdminMember> admins = new ArrayList<>();
        admins.add(new AdminMember("admin1", 300, 10, 1, 50, "콘텐츠 관리자"));

        assertEquals(300, feed.totalFollowers(admins));
    }

    @Test
    @DisplayName("? extends Content 는 이미지·텍스트 섞인 콘텐츠 리스트를 설명으로 만든다")
    void describeAllAcceptsMixedContentSubtypes() {
        List<Content> contents = new ArrayList<>();
        contents.add(new ImageContent("minji", 10, "photo.jpg"));
        contents.add(new TextContent("jaehoon", 5, "안녕하세요 반갑습니다 오늘도 좋은 하루"));

        List<String> lines = feed.describeAll(contents);

        assertEquals(2, lines.size());
        assertEquals("[이미지] minji 님의 사진: photo.jpg (♥ 10)", lines.get(0));
    }

    @Test
    @DisplayName("? super Member 는 List<Object> 에도 회원을 담는다")
    void addMembersAcceptsObjectSupertypeList() {
        List<Object> anything = new ArrayList<>();

        feed.addMembers(anything,
                new Member("seungwoo", 320, 12, 2, 80),
                new Member("hana", 540, 20, 4, 200));

        assertEquals(2, anything.size());
    }

    @Test
    @DisplayName("? super Member 는 List<Member> 에도 그대로 담는다")
    void addMembersAcceptsMemberList() {
        List<Member> members = new ArrayList<>();

        feed.addMembers(members, new Member("seungwoo", 320, 12, 2, 80));

        assertEquals(1, members.size());
        assertEquals("seungwoo", members.get(0).getUsername());
    }
}
