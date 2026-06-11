package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/RecordDtoComprehensive.java
// 종합 — 무거운 Member 객체 목록을, 화면용 record 카드 목록으로 바꿔요.
// 흐름(Stream) 으로 한 명씩 추려서 ProfileCard 로 만들고, 한 줄로 모아요.
public class RecordDtoComprehensive {

    // Member 목록 → ProfileCard 목록 (화면에 줄 가벼운 데이터로 추리기)
    public static List<ProfileCard> toCards(List<Member> members) {
        return members.stream()
                .map(m -> new ProfileCard(m.getUsername(), m.grade(), m.getFollowers()))
                .toList();
    }

    public static void main(String[] args) {
        List<Member> members = List.of(
                new Member("jaehoon", 30000, 500, 10, 730),
                new Member("minji", 8500, 150, 5, 365),
                new Member("dana", 50, 0, 0, 10));

        List<ProfileCard> cards = toCards(members);
        cards.forEach(System.out::println);
        // ProfileCard[username=jaehoon, grade=강력 추천, followers=30000]
        // ProfileCard[username=minji, grade=추천, followers=8500]
        // ProfileCard[username=dana, grade=관심 낮음, followers=50]
    }
}
