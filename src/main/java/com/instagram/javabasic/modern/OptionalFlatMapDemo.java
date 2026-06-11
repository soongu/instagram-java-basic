package com.instagram.javabasic.modern;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/OptionalFlatMapDemo.java
// flatMap — 상자를 열었더니 또 상자가 나오는 "상자 속 상자" 를 한 겹으로 펴줘요.
// 게시물 → (있을 수도 없는) 작성자 → (있을 수도 없는) 이메일 처럼, 비어 있을 수 있는 단계가 이어질 때 써요.
// filter — 상자 안 값이 조건을 통과하면 그대로, 못 통과하면 빈 상자로 바꿔요.
public class OptionalFlatMapDemo {

    // 작성자 상자 — 문자열 이름으로만 만든 게시물은 작성자(Member)가 없어요(null).
    public static Optional<Member> authorOf(Post post) {
        return Optional.ofNullable(post.getAuthor());
    }

    // 작성자의 이메일 — map 으로 하면 Optional<Optional<String>> 이 돼요.
    // flatMap 은 그 두 겹을 한 겹(Optional<String>)으로 펴줘요.
    public static Optional<String> authorEmail(Post post) {
        return authorOf(post).flatMap(author -> Optional.ofNullable(author.getEmail()));
    }

    public static String authorEmailOrDefault(Post post) {
        return authorEmail(post).orElse("(작성자 이메일 없음)");
    }

    // filter — 좋아요 100개 이상인 글만 통과시켜요. 못 미치면 빈 상자가 돼요.
    public static Optional<Post> popularOnly(Post post) {
        return Optional.of(post).filter(p -> p.getLikeCount() >= 100);
    }

    public static void main(String[] args) {
        Member jaehoon = new Member("jaehoon", "jaehoon@example.com");
        Member minji = new Member("minji", 8500, 150, 5, 365); // 이메일 null
        Post withEmail = new Post("카페 다녀옴", jaehoon, 250);
        Post authorNoEmail = new Post("산책", minji, 40);
        Post noAuthor = new Post("익명 글", "anon", 10); // 작성자 객체 없음

        System.out.println(authorEmailOrDefault(withEmail));        // jaehoon@example.com
        System.out.println(authorEmailOrDefault(authorNoEmail));    // (작성자 이메일 없음)
        System.out.println(authorEmailOrDefault(noAuthor));         // (작성자 이메일 없음)
        System.out.println(popularOnly(withEmail).isPresent());     // true
        System.out.println(popularOnly(authorNoEmail).isPresent()); // false
    }
}
