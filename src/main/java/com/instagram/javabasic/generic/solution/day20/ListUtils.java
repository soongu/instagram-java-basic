package com.instagram.javabasic.generic.solution.day20;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/generic/solution/day20/ListUtils.java
// 어떤 타입의 List 든 다룰 수 있는 정적 제네릭 메서드 모음이에요.
// 메서드 앞에 붙은 <T> 가 "이 메서드 한 번 호출에서 쓸 타입" 을 뜻해요.
//   lastOf(문자열리스트) 면 T 가 String, lastOf(회원리스트) 면 T 가 Member 로 그때그때 정해져요.
// 덕분에 문자열용·회원용 메서드를 따로 만들 필요 없이, 한 코드로 모든 타입을 받아요.
public class ListUtils {

    // 마지막 원소를 돌려줘요 — 리스트의 끝 칸(size()-1)이에요.
    // 비어 있으면 돌려줄 게 없으니 IllegalArgumentException 을 던져 "빈 리스트는 안 돼요" 를 알려요.
    public static <T> T lastOf(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("빈 리스트에는 마지막 원소가 없어요.");
        }
        return list.get(list.size() - 1);
    }

    // target 과 같은 원소가 몇 개인지 세요. 같은지는 equals 로 비교해요.
    // 향상된 for 로 리스트를 처음부터 끝까지 훑으면서 일치할 때마다 1씩 더해요.
    public static <T> int count(List<T> list, T target) {
        int found = 0;
        for (T item : list) {
            if (item.equals(target)) {
                found++;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        // 문자열 리스트에서 마지막 원소와 개수 세기
        List<String> tags = new ArrayList<>();
        tags.add("#일상");
        tags.add("#맛집");
        tags.add("#일상");

        System.out.println("마지막 태그: " + lastOf(tags));          // #일상
        System.out.println("#일상 개수: " + count(tags, "#일상"));   // 2
        System.out.println("#여행 개수: " + count(tags, "#여행"));   // 0

        // 회원 리스트에서도 같은 메서드가 그대로 통해요(Member.equals 는 username 기준)
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 12, 400));
        members.add(new Member("jaehoon", 1240, 42, 3, 120));

        System.out.println("마지막 회원: " + lastOf(members).getUsername()); // jaehoon
        System.out.println("minji 개수: " + count(members, new Member("minji", 0, 0, 0, 0))); // 1
    }
}
