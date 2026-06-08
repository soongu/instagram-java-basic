package com.instagram.javabasic.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

// com/instagram/javabasic/collection/ImmutableCollectionsDemo.java
// List.of() · Set.of() · Map.of() 는 "한 번 만들면 못 바꾸는" 불변(immutable) 컬렉션이에요.
// 절대 바뀌면 안 되는 값(허용된 등급 목록, 고정 안내 문구 같은 것) 을 담아 두면,
// 누가 실수로 add/remove 를 불러도 자바가 UnsupportedOperationException(지원 안 함 예외) 으로 막아 줘요.
//   "바뀌면 안 되는 값" 이라는 의도를 코드로 못 박아 두는 거예요.
public class ImmutableCollectionsDemo {

    // 고정된 추천 해시태그 목록 — 바뀌지 않아요.
    public List<String> constantTags() {
        return List.of("#daily", "#instagood", "#photo");
    }

    // 허용된 역할(권한) 집합 — Set.of 로 중복 없이 고정해요.
    public Set<String> allowedRoles() {
        return Set.of("USER", "ADMIN", "GUEST");
    }

    // 등급 코드 → 한글 라벨 — Map.of 로 고정 매핑을 만들어요.
    public Map<String, String> gradeLabels() {
        return Map.of(
                "S", "강력 추천",
                "A", "추천",
                "B", "보통");
    }

    // 불변 리스트에 add 를 시도하는 잘못된 예 — UnsupportedOperationException 이 터져요.
    public void tryModify(List<String> immutableList) {
        immutableList.add("#newtag"); // 불변 컬렉션은 수정을 거부해요
    }

    public static void main(String[] args) {
        ImmutableCollectionsDemo demo = new ImmutableCollectionsDemo();

        System.out.println("고정 태그: " + demo.constantTags());
        System.out.println("허용 역할: " + demo.allowedRoles());
        System.out.println("등급 라벨: " + demo.gradeLabels());

        try {
            demo.tryModify(demo.constantTags());
        } catch (Exception e) {
            System.out.println("수정 시도가 막혔어요: " + e.getClass().getSimpleName());
        }
    }
}
