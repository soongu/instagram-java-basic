package com.instagram.javabasic.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/repository/PostRepository.java
// 게시물을 보관하는 인메모리 저장소예요. MemberRepository 와 똑같은 패턴이에요 —
// Map<Long, Post> 사물함 + 스스로 올라가는 번호표(sequence).
// 한 가지 다른 점: 없는 게시물을 찾을 땐 전용 예외 대신 표준 IllegalArgumentException 을 던져요.
// 회원 조회 실패는 자주 다루는 핵심 상황이라 전용 예외를 만들었지만,
// 게시물 조회 실패는 표준 예외로도 충분하거든요. 모든 실패에 전용 예외가 필요한 건 아니에요.
public class PostRepository {

    private final Map<Long, Post> store = new HashMap<>();

    private long sequence = 0L;

    // 저장 — 다음 번호표를 뽑아 붙이고 보관한 뒤 그 id 를 돌려줘요.
    public Long save(Post post) {
        sequence++;
        Long id = sequence;
        store.put(id, post);
        return id;
    }

    // 조회 — 없으면 표준 예외로 "그 게시물 없어요" 를 알려요.
    public Post findById(Long id) {
        Post found = store.get(id);
        if (found == null) {
            throw new IllegalArgumentException("id " + id + " 에 해당하는 게시물이 없어요.");
        }
        return found;
    }

    // 특정 작성자가 쓴 게시물만 골라 모아요 — 사물함을 훑으며 작성자가 같은 글만 새 리스트에 담아요.
    public List<Post> findByAuthor(Member author) {
        List<Post> result = new ArrayList<>();
        for (Post post : store.values()) {
            if (author != null && author.equals(post.getAuthor())) {
                result.add(post);
            }
        }
        return result;
    }

    // 게시물을 지워요 — 없는 id 를 지우려 하면 표준 예외로 막아요.
    public void deleteById(Long id) {
        if (!store.containsKey(id)) {
            throw new IllegalArgumentException("id " + id + " 에 해당하는 게시물이 없어 지울 수 없어요.");
        }
        store.remove(id);
    }

    public int count() {
        return store.size();
    }
}
