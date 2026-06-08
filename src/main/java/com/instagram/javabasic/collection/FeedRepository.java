package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/collection/FeedRepository.java
// 지금까지 배운 Set·Map·List 를 한데 엮어 "작은 피드 저장소" 를 만들어요.
//   Map<Long, List<Post>>  — 회원 번호(memberId) → 그 사람이 쓴 글 목록. "한 사람 : 여러 글" 을 표현해요.
//   Map<Long, Set<Long>>   — 글 번호(postId) → 좋아요 누른 회원 번호 집합. Set 이라 같은 사람이
//                            두 번 눌러도 한 번만 세져요(중복 좋아요 자동 무시).
// 자료구조를 겹겹이 쌓으면 복잡한 관계도 깔끔하게 담을 수 있어요.
public class FeedRepository {

    // 회원 번호 → 그 회원이 쓴 글 목록
    private final Map<Long, List<Post>> postsByMember = new HashMap<>();

    // 글 번호 → 그 글에 좋아요 누른 회원 번호 집합
    private final Map<Long, Set<Long>> likesByPost = new HashMap<>();

    // 회원이 글 하나를 올려요. 그 회원의 목록이 아직 없으면 새로 만들어 담아요.
    public void addPost(Long memberId, Post post) {
        List<Post> posts = postsByMember.get(memberId);
        if (posts == null) {
            posts = new ArrayList<>();
            postsByMember.put(memberId, posts);
        }
        posts.add(post);
    }

    // 특정 회원이 쓴 글 목록을 돌려줘요. 한 개도 없으면 빈 목록을 돌려줘요.
    public List<Post> getPostsOf(Long memberId) {
        List<Post> posts = postsByMember.get(memberId);
        if (posts == null) {
            return new ArrayList<>();
        }
        return posts;
    }

    // 어떤 회원이 글에 좋아요를 눌러요. Set 에 담기 때문에 같은 사람이 또 눌러도 한 번만 세져요.
    public void like(Long postId, Long memberId) {
        Set<Long> likers = likesByPost.get(postId);
        if (likers == null) {
            likers = new HashSet<>();
            likesByPost.put(postId, likers);
        }
        likers.add(memberId);
    }

    // 그 글의 좋아요 수 — 좋아요 누른 회원 집합의 크기예요(중복 없이).
    public int likeCount(Long postId) {
        Set<Long> likers = likesByPost.get(postId);
        if (likers == null) {
            return 0;
        }
        return likers.size();
    }

    public static void main(String[] args) {
        FeedRepository repo = new FeedRepository();

        Long minjiId = 1L;
        repo.addPost(minjiId, new Post("제주 노을", "minji", 0));
        repo.addPost(minjiId, new Post("카페 투어", "minji", 0));
        System.out.println("minji 가 쓴 글 수: " + repo.getPostsOf(minjiId).size()); // 2

        Long postId = 100L;
        repo.like(postId, 2L);
        repo.like(postId, 3L);
        repo.like(postId, 2L); // 같은 사람이 또 좋아요 — Set 이 무시해요
        System.out.println("글 100 의 좋아요 수: " + repo.likeCount(postId)); // 2
    }
}
