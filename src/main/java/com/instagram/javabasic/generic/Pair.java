package com.instagram.javabasic.generic;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/generic/Pair.java
// 타입 빈칸이 둘인 제네릭 그릇이에요. Box 는 <T> 하나였지만,
// 여기는 <K, V> 로 두 개를 끼워요. K 는 Key(열쇠), V 는 Value(값) 의 머리글자예요.
//   new Pair<String, Member>(...) 라고 만들면 K 자리에 String, V 자리에 Member 가 끼워져서
//   "이름표(String) 와 회원(Member)" 한 쌍을 형변환 없이 묶어 다룰 수 있어요.
// 한번 채운 짝은 바꾸지 않도록 final 로 고정해, 안전한 "읽기 전용 묶음" 으로 만들어요.
public class Pair<K, V> {

    // 열쇠와 값 — 생성자에서 한 번 채우고 바꾸지 않아요(final).
    private final K key;
    private final V value;

    // 생성자 — 열쇠와 값을 한 번에 받아 한 쌍으로 묶어요.
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // 열쇠를 K 타입 그대로 돌려줘요 (형변환 불필요).
    public K getKey() {
        return key;
    }

    // 값을 V 타입 그대로 돌려줘요 (형변환 불필요).
    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + key + " → " + value + ")";
    }

    public static void main(String[] args) {
        // K=String(이름표), V=Member(회원) 한 쌍을 묶었어요.
        Member minji = new Member("minji", 8500, 150, 12, 400);
        Pair<String, Member> entry = new Pair<>("minji", minji);

        // 꺼낼 때 형변환이 필요 없어요 — getKey 는 String, getValue 는 Member 그대로 나와요.
        String username = entry.getKey();
        int followers = entry.getValue().getFollowers();
        System.out.println("이름표: " + username + ", 팔로워: " + followers);

        // 타입을 바꿔도 같은 Pair 틀을 그대로 써요 — K=String, V=Integer.
        Pair<String, Integer> likeCount = new Pair<>("게시물 좋아요", 505);
        System.out.println(likeCount);
    }
}
