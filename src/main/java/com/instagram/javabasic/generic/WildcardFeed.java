package com.instagram.javabasic.generic;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.member.AdminMember;
import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.member.PremiumMember;

// com/instagram/javabasic/generic/WildcardFeed.java
// 와일드카드(wildcard, 물음표 ? 로 "어떤 타입의 자식이든" 받는 표시) 를 쓰는 곳이에요.
// 핵심 규칙은 PECS — Producer Extends, Consumer Super 라는 짧은 외움말이에요.
//   - 리스트에서 값을 "읽기만(생산자)" 할 거면 <? extends 부모> 로 받아요.
//     그러면 List<PremiumMember> 처럼 자식 타입 리스트도 받을 수 있어요.
//   - 리스트에 값을 "넣기만(소비자)" 할 거면 <? super 자식> 으로 받아요.
//     그러면 List<Object> 처럼 부모 타입 리스트에도 안전하게 담을 수 있어요.
// 인스타 맥락: 여러 등급(일반·프리미엄·관리자)의 회원 묶음을 한 메서드로 처리해요.
public class WildcardFeed {

    // 읽기(생산자) — ? extends Member 라서 List<Member> 뿐 아니라
    // List<PremiumMember>·List<AdminMember> 같은 자식 타입 리스트도 받아요.
    // 안에서는 꺼내 읽기만 해요(팔로워 합산). 새로 넣지는 않아요.
    public int totalFollowers(List<? extends Member> members) {
        int sum = 0;
        for (Member m : members) {
            sum = sum + m.getFollowers();
        }
        return sum;
    }

    // 읽기(생산자) — ? extends Content 라서 이미지·텍스트·영상 어떤 콘텐츠 리스트든 받아요.
    // 각 콘텐츠의 render() 를 모아 한 줄 설명 목록을 만들어 돌려줘요.
    public List<String> describeAll(List<? extends Content> contents) {
        List<String> lines = new ArrayList<>();
        for (Content c : contents) {
            lines.add(c.render());
        }
        return lines;
    }

    // 쓰기(소비자) — ? super Member 라서 List<Member> 뿐 아니라
    // List<Object> 같은 부모 타입 리스트에도 회원을 안전하게 담을 수 있어요.
    public void addMembers(List<? super Member> target, Member... members) {
        for (Member m : members) {
            target.add(m);
        }
    }

    public static void main(String[] args) {
        WildcardFeed feed = new WildcardFeed();

        // 프리미엄 회원만 담은 리스트도 ? extends Member 자리에 그대로 들어가요.
        List<PremiumMember> premiums = new ArrayList<>();
        premiums.add(new PremiumMember("minji", 8500, 150, 12, 400, true));
        premiums.add(new PremiumMember("jaehoon", 1240, 42, 3, 120, false));
        System.out.println("프리미엄 팔로워 합: " + feed.totalFollowers(premiums)); // 9740

        // 관리자 회원 리스트도 같은 메서드로 처리돼요.
        List<AdminMember> admins = new ArrayList<>();
        admins.add(new AdminMember("admin1", 300, 10, 1, 50, "콘텐츠 관리자"));
        System.out.println("관리자 팔로워 합: " + feed.totalFollowers(admins)); // 300

        // ? super Member 자리에 List<Object> 를 넘겨도 회원이 안전하게 담겨요.
        List<Object> anything = new ArrayList<>();
        feed.addMembers(anything,
                new Member("seungwoo", 320, 12, 2, 80),
                new Member("hana", 540, 20, 4, 200));
        System.out.println("Object 리스트에 담긴 회원 수: " + anything.size()); // 2
    }
}
