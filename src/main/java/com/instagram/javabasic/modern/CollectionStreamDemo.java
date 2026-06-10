package com.instagram.javabasic.modern;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/CollectionStreamDemo.java
// 스트림을 만드는 가장 흔한 길 — 컬렉션의 .stream() 이에요.
// List 든 Set 이든 .stream() 한 번이면 "흐름" 이 시작돼요.
// 흐름은 끝에 .toList() 같은 걸 붙여야 다시 눈에 보이는 리스트로 돌아와요.
public class CollectionStreamDemo {

    // List → stream → 다시 List. 흐름을 한 바퀴 돌려보는 가장 단순한 예예요.
    public static List<Post> fromList(List<Post> posts) {
        return posts.stream().toList();
    }

    // Set 도 똑같이 .stream() 으로 흐름을 시작할 수 있어요.
    // 여기선 순서를 지키는 LinkedHashSet 을 받아 넣은 순서 그대로 돌려줘요.
    public static List<String> fromSet(Set<String> tags) {
        return tags.stream().toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = List.of(
                new Post("첫 글", minji, 30),
                new Post("두 번째 글", minji, 120));

        System.out.println("List 에서 시작한 흐름 → " + fromList(posts).size() + "개");  // 2

        Set<String> tags = new LinkedHashSet<>();
        tags.add("#일상");
        tags.add("#개발");
        System.out.println("Set 에서 시작한 흐름 → " + fromSet(tags));  // [#일상, #개발]
    }
}
