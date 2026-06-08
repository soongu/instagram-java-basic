package com.instagram.javabasic.collection;

import java.util.HashSet;
import java.util.Set;

// com/instagram/javabasic/collection/HashSetBasic.java
// HashSet 은 "같은 값은 한 번만 담는 명단" 이에요.
// 같은 이름을 두 번 add() 해도 안에는 하나만 남아서, 좋아요 누른 사람을
// 중복 없이 모을 때 딱 맞아요. 우리가 직접 "이미 있나?" 검사할 필요가 없어요.
//   Set<String> 의 <String> 은 "이 명단엔 String 만 담아요" 라는 약속이에요.
//   List 와 달리 순서를 보장하지 않고, 같은 값은 자동으로 한 번만 남겨요.
public class HashSetBasic {

    // 좋아요 누른 사람들의 username 을 모아요. 같은 사람이 두 번 눌러도 한 번만 남아요.
    // "minji" 를 두 번 넣었지만 결과 명단의 크기는 2 예요.
    public Set<String> likedUsernames() {
        Set<String> liked = new HashSet<>();
        liked.add("minji");
        liked.add("jaehoon");
        liked.add("minji"); // 중복 — 자동으로 무시돼요
        return liked;
    }

    // add() 는 "정말 새로 담겼는지" 를 true/false 로 알려줘요.
    // 처음 담을 땐 true, 이미 있는 값을 또 담으려 하면 false 예요.
    public boolean addReturnsFalseOnDuplicate() {
        Set<String> liked = new HashSet<>();
        liked.add("minji");          // true (새로 담김)
        return liked.add("minji");   // false (이미 있음)
    }

    // 어떤 username 이 명단에 있는지 contains() 로 물어봐요.
    public boolean hasLiked(Set<String> liked, String username) {
        return liked.contains(username);
    }

    // 좋아요를 취소하면 remove() 로 명단에서 빼요.
    public Set<String> cancelLike(Set<String> liked, String username) {
        liked.remove(username);
        return liked;
    }

    public static void main(String[] args) {
        HashSetBasic demo = new HashSetBasic();

        Set<String> liked = demo.likedUsernames();
        System.out.println("좋아요 누른 사람 수: " + liked.size()); // 2 (minji 중복 제거)
        System.out.println("minji 가 눌렀나요? " + liked.contains("minji"));

        System.out.println("같은 사람 또 담기: " + demo.addReturnsFalseOnDuplicate()); // false

        demo.cancelLike(liked, "minji");
        System.out.println("취소 후 수: " + liked.size()); // 1
    }
}
