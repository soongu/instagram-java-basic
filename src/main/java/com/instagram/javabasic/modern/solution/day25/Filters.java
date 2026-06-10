package com.instagram.javabasic.modern.solution.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// [심화] 과제 3 예시답안 — PostFilter 의 Post 를 T 로 바꿔 "아무 타입이나" 거르게 열었어요.
// 안쪽 코드는 PostFilter 와 똑같아요. 타입만 제네릭 <T> 로 열어준 거예요.
public class Filters {

    // 어떤 타입이든 조건에 맞는 것만 골라 새 리스트로 돌려줘요.
    public static <T> List<T> filter(List<T> items, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (condition.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 5, 365));
        members.add(new Member("newbie", 50, 2, 10, 5));

        // 같은 filter 로 회원을 걸러요
        List<Member> popular = filter(members, m -> m.getFollowers() >= 1000);
        System.out.println("인기 회원 수: " + popular.size());

        // 같은 filter 로 게시물도 걸러요
        Member author = members.get(0);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("a", author, 30));
        posts.add(new Post("b", author, 250));
        List<Post> hot = filter(posts, p -> p.getLikeCount() >= 100);
        System.out.println("인기 글 수: " + hot.size());

        // 메서드 참조로 이름만 뽑아 출력해요
        Function<Member, String> toName = Member::getUsername;
        for (Member m : popular) {
            System.out.println(toName.apply(m));
        }
    }
}
