package com.instagram.javabasic.generic;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.collection.SortableMember;

// com/instagram/javabasic/generic/MaxFinder.java
// 경계 타입(bounded type, 타입 빈칸에 조건을 거는 것) 을 쓰는 제네릭 메서드예요.
// "가장 큰 원소" 를 찾으려면 두 원소를 서로 비교할 수 있어야 해요.
// 그런데 아무 타입 T 나 받으면 비교 방법(compareTo)이 없을 수도 있어요.
//   그래서 <T extends Comparable<T>> 라고 빈칸에 조건을 걸어요 —
//   "T 는 아무거나가 아니라, Comparable(서로 비교할 수 있는) 타입이어야 해요" 라는 뜻이에요.
// 이 조건 덕분에 메서드 안에서 a.compareTo(b) 를 안심하고 부를 수 있어요.
public class MaxFinder {

    // 리스트에서 가장 큰 원소를 찾아요. T 는 서로 비교 가능한(Comparable) 타입이어야 해요.
    // 빈 리스트면 비교할 게 없으니 예외를 던져요.
    public static <T extends Comparable<T>> T max(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("빈 리스트에는 최댓값이 없어요.");
        }
        T best = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            // current 가 best 보다 크면(compareTo 가 양수면) 챔피언을 갈아끼워요.
            if (current.compareTo(best) > 0) {
                best = current;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        // Integer 리스트 — Integer 는 Comparable 이라 조건을 만족해요. 가장 큰 숫자를 찾아요.
        List<Integer> likeCounts = new ArrayList<>();
        likeCounts.add(120);
        likeCounts.add(8500);
        likeCounts.add(42);
        System.out.println("최다 좋아요: " + max(likeCounts)); // 8500

        // String 리스트 — String 도 Comparable 이고, 사전순으로 비교돼요(뒤 글자가 더 큼).
        List<String> names = new ArrayList<>();
        names.add("minji");
        names.add("jaehoon");
        names.add("seungwoo");
        System.out.println("사전순 마지막 이름: " + max(names)); // seungwoo

        // SortableMember 도 Comparable<SortableMember> 라 그대로 넣을 수 있어요(팔로워 기준 비교).
        List<SortableMember> members = new ArrayList<>();
        members.add(new SortableMember("minji", 8500));
        members.add(new SortableMember("jaehoon", 1240));
        System.out.println("팔로워 최다 회원: " + max(members)); // @minji(8500)
    }
}
