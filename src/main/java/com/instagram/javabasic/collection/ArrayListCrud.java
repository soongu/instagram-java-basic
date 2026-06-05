package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/collection/ArrayListCrud.java
// ArrayList 에 담은 뒤 자주 쓰는 동작들을 한자리에 모았어요.
//   set(index, 값)     : index 자리의 값을 바꿔요
//   remove(int index)  : index 번째를 빼요
//   remove(Object 값)  : 그 "값" 을 찾아서 빼요
//   contains / indexOf : 들어있는지 / 몇 번째인지
//   isEmpty / clear    : 비었는지 / 전부 비우기
// 특히 remove 는 같은 이름인데 int 를 주면 "위치", 값 객체를 주면 "값" 으로 동작해요. 이 함정을 직접 봐요.
public class ArrayListCrud {

    // set 으로 값 교체. 0번 태그를 다른 값으로 바꿔요.
    public List<String> replaceFirst(String newTag) {
        List<String> tags = sampleTags();
        tags.set(0, newTag);
        return tags;
    }

    // 향상된 for 로 전체를 훑어 하나의 문자열로 이어 붙여요.
    public String joinAll() {
        List<String> tags = sampleTags();
        String result = "";
        for (String tag : tags) {
            result = result + tag + " ";
        }
        return result.trim();
    }

    public boolean containsTag(String tag) {
        return sampleTags().contains(tag);
    }

    public int indexOfTag(String tag) {
        return sampleTags().indexOf(tag);
    }

    // remove(int index) — 정수를 주면 "그 위치" 를 빼요.
    public List<String> removeByIndex(int index) {
        List<String> tags = sampleTags();
        tags.remove(index);
        return tags;
    }

    // ===== remove 함정: List<Integer> 에서 정수 vs 값 =====
    // numbers = [10, 20, 30] 일 때
    //   remove(2)                  → "2번 위치" 인 30 을 빼요 (int 로 해석)
    //   remove(Integer.valueOf(20)) → "값 20" 을 찾아 빼요 (Object 로 해석)
    public List<Integer> removeByPosition() {
        List<Integer> numbers = sampleNumbers();
        numbers.remove(2); // 위치 2 → 값 30 제거
        return numbers;
    }

    public List<Integer> removeByValue() {
        List<Integer> numbers = sampleNumbers();
        numbers.remove(Integer.valueOf(20)); // 값 20 제거
        return numbers;
    }

    // clear 후 isEmpty 확인
    public boolean clearAndCheck() {
        List<String> tags = sampleTags();
        tags.clear();
        return tags.isEmpty();
    }

    private List<String> sampleTags() {
        List<String> tags = new ArrayList<>();
        tags.add("#sunset");
        tags.add("#jeju");
        tags.add("#travel");
        return tags;
    }

    private List<Integer> sampleNumbers() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        return numbers;
    }

    public static void main(String[] args) {
        ArrayListCrud demo = new ArrayListCrud();
        System.out.println("set 교체: " + demo.replaceFirst("#first"));
        System.out.println("향상된 for 이어붙이기: " + demo.joinAll());
        System.out.println("#jeju 포함? " + demo.containsTag("#jeju"));
        System.out.println("#travel 위치: " + demo.indexOfTag("#travel"));
        System.out.println("remove(2) → 위치로: " + demo.removeByPosition());
        System.out.println("remove(값 20): " + demo.removeByValue());
        System.out.println("clear 후 비었나: " + demo.clearAndCheck());
    }
}
