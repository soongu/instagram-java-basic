package com.instagram.javabasic.service;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.exceptionbasic.InvalidCaptionException;
import com.instagram.javabasic.repository.PostRepository;

// com/instagram/javabasic/service/PostService.java
// 게시물과 관련된 할 일을 맡는 계층이에요. 작성·조회·수정·삭제(CRUD) 네 가지를 다뤄요.
// 캡션 규칙(비었는지·너무 긴지) 검사는 서비스가 맡고, 어긋나면 InvalidCaptionException 을 던져요.
// 수정할 땐 "보관된 글은 못 고친다" 는 규칙도 여기서 PostStatus 에게 물어 확인해요.
public class PostService {

    // 캡션 최대 길이예요. 이 길이를 넘으면 InvalidCaptionException 으로 막아요.
    public static final int MAX_CAPTION_LENGTH = 100;

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 작성(Create) — 캡션을 검사하고, 통과하면 작성자와 묶어 게시물을 만든 뒤 저장하고 새 id 를 돌려줘요.
    public Long createPost(Member author, String caption) {
        validateCaption(caption);
        Post post = new Post(caption, author, 0);
        if (author != null) {
            author.addWrittenPost(post);
        }
        return postRepository.save(post);
    }

    // 조회(Read) — id 로 게시물을 찾아요(없으면 저장소가 표준 예외를 던져요).
    public Post readPost(Long id) {
        return postRepository.findById(id);
    }

    // 수정(Update) — 보관된 글은 못 고치고, 새 캡션도 다시 검사한 뒤 내용을 바꿔요.
    public void updateCaption(Long id, String newCaption) {
        Post post = postRepository.findById(id);
        if (!post.getStatus().isEditable()) {
            throw new IllegalStateException("보관된 글은 수정할 수 없어요.");
        }
        validateCaption(newCaption);
        post.editContent(newCaption);
    }

    // 삭제(Delete) — 저장소에 그대로 맡겨요(없으면 표준 예외).
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 한 작성자가 쓴 게시물을 모아 돌려줘요.
    public List<Post> postsOf(Member author) {
        return postRepository.findByAuthor(author);
    }

    // 캡션 검사 규칙을 한곳에 모아 둬요 — 작성·수정 두 곳에서 똑같이 불러 써요.
    private void validateCaption(String caption) {
        if (caption == null || caption.isBlank()) {
            throw new InvalidCaptionException("캡션은 비어 있을 수 없어요.");
        }
        if (caption.length() > MAX_CAPTION_LENGTH) {
            throw new InvalidCaptionException(
                    "캡션은 " + MAX_CAPTION_LENGTH + "자를 넘을 수 없어요: 현재 " + caption.length() + "자");
        }
    }
}
