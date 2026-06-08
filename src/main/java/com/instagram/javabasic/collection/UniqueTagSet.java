package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// com/instagram/javabasic/collection/UniqueTagSet.java
// 같은 Set 이라도 종류에 따라 성격이 달라요.
//   HashSet  — 빠르게 중복만 없애요. 순서는 보장하지 않아요.
//   TreeSet  — 중복도 없애면서, 담는 순간 자동으로 사전순(가나다·ABC)으로 정렬해요.
// 해시태그 모음을 "그냥 중복만 없애면 될 때" 와 "정렬해서 보여줄 때" 로 나눠 써 봐요.
public class UniqueTagSet {

    // HashSet — 중복만 제거해요. "#jeju" 를 두 번 넣어도 한 번만 남아요.
    public Set<String> hashSetTags() {
        Set<String> tags = new HashSet<>();
        tags.add("#travel");
        tags.add("#jeju");
        tags.add("#sunset");
        tags.add("#jeju"); // 중복 — 무시돼요
        return tags;
    }

    // TreeSet — 중복 제거 + 자동 정렬. 뒤섞어 넣어도 안에서는 사전순으로 줄을 서요.
    public TreeSet<String> treeSetTags() {
        TreeSet<String> tags = new TreeSet<>();
        tags.add("#travel");
        tags.add("#jeju");
        tags.add("#sunset");
        tags.add("#cafe");
        return tags;
    }

    // TreeSet 을 처음부터 순회하면 정렬된 순서대로 나와요.
    // 그 순서를 List 에 담아 돌려주면, 어디서든 정렬 결과를 확인할 수 있어요.
    public List<String> sortedTagList() {
        TreeSet<String> tags = treeSetTags();
        List<String> result = new ArrayList<>();
        for (String tag : tags) {
            result.add(tag);
        }
        return result;
    }

    public static void main(String[] args) {
        UniqueTagSet demo = new UniqueTagSet();

        Set<String> hash = demo.hashSetTags();
        System.out.println("HashSet 크기(중복 제거): " + hash.size()); // 3

        TreeSet<String> tree = demo.treeSetTags();
        System.out.println("TreeSet 첫 태그(사전순 최소): " + tree.first());
        System.out.println("TreeSet 끝 태그(사전순 최대): " + tree.last());
        System.out.println("정렬된 전체: " + demo.sortedTagList());
    }
}
