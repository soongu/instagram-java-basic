package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// com/instagram/javabasic/collection/CollectionsUtilDemo.java
// Collections 는 명단을 다루는 편리한 도구들을 모아 둔 도구상자예요.
//   sort(list, 비교기) : 내가 고른 기준(Comparator)으로 정렬
//   reverse(list)      : 순서를 통째로 뒤집기
//   max / min          : 가장 크고 작은 원소 (기본 정렬 기준으로)
//   shuffle(list, 난수) : 무작위로 섞기 (같은 씨앗을 주면 항상 같은 순서로 섞여요)
public class CollectionsUtilDemo {

    // 명단을 새로 만들어 돌려줘요. 매번 같은 출발 상태에서 시작하려고 메서드로 묶었어요.
    public List<SortableMember> sample() {
        List<SortableMember> members = new ArrayList<>();
        members.add(new SortableMember("minji", 8500));
        members.add(new SortableMember("jaehoon", 1240));
        members.add(new SortableMember("seungwoo", 320));
        return members;
    }

    // 팔로워 많은 순(내림차순)으로 정렬 — 명명한 비교기를 넘겨요.
    public List<SortableMember> sortByFollowersDesc() {
        List<SortableMember> members = sample();
        Collections.sort(members, new FollowerComparator());
        return members;
    }

    // 이름 순(가나다/알파벳)으로 정렬.
    public List<SortableMember> sortByUsername() {
        List<SortableMember> members = sample();
        Collections.sort(members, new UsernameComparator());
        return members;
    }

    // 기본 정렬(Comparable, 팔로워 오름차순) 후 뒤집기 → 내림차순.
    public List<SortableMember> sortThenReverse() {
        List<SortableMember> members = sample();
        Collections.sort(members);
        Collections.reverse(members);
        return members;
    }

    // 기본 기준(팔로워)으로 가장 많은 사람.
    public SortableMember mostFollowed() {
        return Collections.max(sample());
    }

    // 기본 기준(팔로워)으로 가장 적은 사람.
    public SortableMember leastFollowed() {
        return Collections.min(sample());
    }

    // 같은 씨앗(42)을 준 Random 으로 섞으면 매번 같은 순서가 나와요(결과를 확인할 수 있게).
    public List<SortableMember> shuffleWithSeed() {
        List<SortableMember> members = sample();
        Collections.shuffle(members, new Random(42));
        return members;
    }

    public static void main(String[] args) {
        CollectionsUtilDemo demo = new CollectionsUtilDemo();
        System.out.println("팔로워 내림차순: " + demo.sortByFollowersDesc());
        System.out.println("이름 순: " + demo.sortByUsername());
        System.out.println("정렬 후 뒤집기: " + demo.sortThenReverse());
        System.out.println("최다 팔로워: " + demo.mostFollowed());
        System.out.println("최소 팔로워: " + demo.leastFollowed());
        System.out.println("섞기(씨앗 42): " + demo.shuffleWithSeed());
    }
}
