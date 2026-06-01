package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberDemoTest {

    // 지난 시간(평행 배열)과 같은 데이터로 만든 객체 배열 — 회귀 검증용
    private Member[] sampleMembers() {
        return new Member[] {
                new Member("jaehoon_dev", 1240, 42, 8, 120),
                new Member("minji_cafe", 8500, 150, 23, 365),
                new Member("seungwoo", 320, 12, 2, 30),
                new Member("soyeon_art", 4100, 88, 15, 210),
                new Member("wooseok99", 15800, 320, 40, 500),
                new Member("hayoung_food", 2300, 67, 11, 95)
        };
    }

    @Test
    @DisplayName("calculateRecommendScore: jaehoon_dev 는 12+8+80+4 = 104점")
    void calculateRecommendScore_jaehoon() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        assertEquals(104, MemberDemo.calculateRecommendScore(jaehoon));
    }

    @Test
    @DisplayName("calculateRecommendScore: minji_cafe 는 85+30+230+12 = 357점")
    void calculateRecommendScore_minji() {
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);
        assertEquals(357, MemberDemo.calculateRecommendScore(minji));
    }

    @Test
    @DisplayName("classifyScore: 경계값 300/150/70 기준 등급이 갈린다")
    void classifyScore_boundaries() {
        assertEquals("강력 추천", MemberDemo.classifyScore(357));
        assertEquals("강력 추천", MemberDemo.classifyScore(300));
        assertEquals("추천", MemberDemo.classifyScore(150));
        assertEquals("보통", MemberDemo.classifyScore(104));
        assertEquals("보통", MemberDemo.classifyScore(70));
        assertEquals("관심 낮음", MemberDemo.classifyScore(69));
    }

    @Test
    @DisplayName("formatFollowers: 1000 이상은 K 단위로 축약한다")
    void formatFollowers_abbreviates() {
        assertEquals("1.2K", MemberDemo.formatFollowers(1240));
        assertEquals("8.5K", MemberDemo.formatFollowers(8500));
        assertEquals("320", MemberDemo.formatFollowers(320));
    }

    @Test
    @DisplayName("searchMemberByName: 이름으로 인덱스를 찾고, 없으면 -1")
    void searchMemberByName_findsIndexOrMinusOne() {
        Member[] members = sampleMembers();
        assertEquals(0, MemberDemo.searchMemberByName(members, "jaehoon_dev"));
        assertEquals(4, MemberDemo.searchMemberByName(members, "wooseok99"));
        assertEquals(-1, MemberDemo.searchMemberByName(members, "nobody"));
    }

    @Test
    @DisplayName("findTopFollowers: 팔로워 상위 3명의 인덱스를 내림차순으로 돌려준다")
    void findTopFollowers_returnsTopIndexes() {
        Member[] members = sampleMembers();
        int[] top = MemberDemo.findTopFollowers(members, 3);

        // wooseok99(15800) > minji_cafe(8500) > soyeon_art(4100)
        assertEquals(3, top.length);
        assertEquals(4, top[0]);
        assertEquals(1, top[1]);
        assertEquals(3, top[2]);
    }
}
