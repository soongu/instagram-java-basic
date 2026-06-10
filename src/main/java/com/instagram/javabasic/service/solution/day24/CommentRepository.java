package com.instagram.javabasic.service.solution.day24;

import java.util.HashMap;
import java.util.Map;

import com.instagram.javabasic.domain.comment.Comment;

// com/instagram/javabasic/service/solution/day24/CommentRepository.java
// [과제 1 예시답안] 댓글 전용 인메모리 저장소예요.
// Step 2·3 의 저장소 패턴(사물함 Map + 스스로 올라가는 번호표 sequence)을 그대로 복제했어요.
// 댓글 조회·삭제 실패는 게시물과 마찬가지로 표준 IllegalArgumentException 으로 충분해요.
public class CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();

    private long sequence = 0L;

    // 저장 — 다음 번호표를 뽑아 붙이고 보관한 뒤 그 id 를 돌려줘요.
    public Long save(Comment comment) {
        sequence++;
        Long id = sequence;
        store.put(id, comment);
        return id;
    }

    // 조회 — 없으면 표준 예외로 막아요.
    public Comment findById(Long id) {
        Comment found = store.get(id);
        if (found == null) {
            throw new IllegalArgumentException("id " + id + " 에 해당하는 댓글이 없어요.");
        }
        return found;
    }

    // 삭제 — 없는 id 를 지우려 하면 표준 예외로 막아요.
    public void deleteById(Long id) {
        if (!store.containsKey(id)) {
            throw new IllegalArgumentException("id " + id + " 에 해당하는 댓글이 없어 지울 수 없어요.");
        }
        store.remove(id);
    }

    public int count() {
        return store.size();
    }
}
