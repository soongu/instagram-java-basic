// com/instagram/javabasic/Day32Review.java
package com.instagram.javabasic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.repository.MemberRepository;
import com.instagram.javabasic.repository.PostRepository;

// 30일 동안 만든 "두뇌"를 한자리에 모아 실제로 돌려보는 종합 복습이에요.
// 우리가 직접 설계한 Member·Post·PostStatus·저장소·전용 예외가 그대로 맞물려 돌아가요.
public class Day32Review {

    // Day 29 의 record — 피드 화면에 보여줄 요약 한 줄을 불변 DTO 로 묶어요.
    // 필드 3개를 적으면 생성자·접근자·toString·equals 가 한 줄로 다 만들어져요.
    public record FeedSummary(String username, int postCount, int totalLikes) {
    }

    // Day 26~27 의 Stream + Day 15 의 Enum + Day 25 의 람다.
    // 공개(PUBLIC) 상태인 게시물만 람다 조건으로 걸러, 좋아요 많은 순으로 정렬해 돌려줘요.
    public static List<Post> publicPostsByLikes(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLIC)
                .sorted(Comparator.comparingInt(Post::getLikeCount).reversed())
                .toList();
    }

    // Day 26~27 의 Stream groupingBy + Day 18~19 의 Map.
    // 작성자 이름별로 게시물을 모아 "이름 → 글 묶음" 지도를 만들어요.
    public static Map<String, List<Post>> groupByAuthor(List<Post> posts) {
        return posts.stream()
                .collect(Collectors.groupingBy(Post::getAuthorName));
    }

    // Day 28 의 Optional + Day 21~23 의 커스텀 예외.
    // 저장소에서 회원을 찾되, 없으면 우리가 만든 MemberNotFoundException 을 던져요.
    // 저장소의 findById 는 이미 예외를 던지므로, Optional 로 한 번 감싸 "정책은 호출자" 형태로 보여줘요.
    public static Member requireMember(MemberRepository repository, Long id) {
        return Optional.ofNullable(findOrNull(repository, id))
                .orElseThrow(() -> new MemberNotFoundException("id " + id + " 회원이 없어 요약을 만들 수 없어요."));
    }

    // 저장소가 예외를 던지는 형태라, Optional 로 감싸기 위해 "없으면 null" 로 한 번 변환해요.
    private static Member findOrNull(MemberRepository repository, Long id) {
        try {
            return repository.findById(id);
        } catch (MemberNotFoundException e) {
            return null;
        }
    }

    // Day 29 record + Day 26~27 Stream.
    // 한 작성자의 게시물 묶음을 받아, 글 개수와 좋아요 총합을 계산해 FeedSummary 한 줄로 만들어요.
    public static FeedSummary summarize(String username, List<Post> authorPosts) {
        int totalLikes = authorPosts.stream()
                .mapToInt(Post::getLikeCount)
                .sum();
        return new FeedSummary(username, authorPosts.size(), totalLikes);
    }

    public static void main(String[] args) {
        // 우리가 30일 동안 만든 저장소를 그대로 꺼내 써요 — 인메모리 사물함이에요.
        MemberRepository memberRepository = new MemberRepository();
        PostRepository postRepository = new PostRepository();

        // 회원 두 명을 저장하고, 발급받은 번호표(id)를 받아둬요.
        Member jaehoon = new Member("jaehoon", "jaehoon@example.com");
        Member minji = new Member("minji", "minji@example.com");
        Long jaehoonId = memberRepository.save(jaehoon);
        memberRepository.save(minji);

        // 게시물을 만들어 저장소에 넣어요. 작성자는 Member 객체로 직접 연결해요.
        Post p1 = new Post("한강 러닝 5km", jaehoon, 320);
        Post p2 = new Post("오늘의 카페", jaehoon, 88);
        Post secret = new Post("비밀 메모", jaehoon, 999);
        secret.setStatus(PostStatus.PRIVATE);          // Day 15 Enum — 비공개로 바꿔요
        Post p3 = new Post("주말 산책", minji, 150);
        postRepository.save(p1);
        postRepository.save(p2);
        postRepository.save(secret);
        postRepository.save(p3);

        List<Post> all = List.of(p1, p2, secret, p3);

        // 1) 공개 게시물만 좋아요 순으로 — Stream + Enum + 람다 정렬
        System.out.println("=== 공개 게시물 (좋아요 많은 순) ===");
        for (Post post : publicPostsByLikes(all)) {
            System.out.println("  " + post.getAuthorName() + " | " + post.getContent()
                    + " | 좋아요 " + post.getLikeCount());
        }

        // 2) 작성자별 묶음 — Stream groupingBy + Map
        System.out.println("=== 작성자별 게시물 수 ===");
        Map<String, List<Post>> byAuthor = groupByAuthor(all);
        for (Map.Entry<String, List<Post>> entry : byAuthor.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue().size() + "개");
        }

        // 3) 작성자별 요약 — record + Optional + 커스텀 예외
        System.out.println("=== 피드 요약 (record) ===");
        Member jaehoonFound = requireMember(memberRepository, jaehoonId);
        FeedSummary summary = summarize(jaehoonFound.getUsername(), byAuthor.get(jaehoonFound.getUsername()));
        System.out.println("  " + summary);

        // 4) 없는 회원 조회 — 커스텀 예외가 흐름을 막아요 (Day 21~23)
        System.out.println("=== 없는 회원 조회 (예외 처리) ===");
        try {
            requireMember(memberRepository, 999L);
        } catch (MemberNotFoundException e) {
            System.out.println("  예외를 잡았어요: " + e.getMessage());
        }
    }
}
