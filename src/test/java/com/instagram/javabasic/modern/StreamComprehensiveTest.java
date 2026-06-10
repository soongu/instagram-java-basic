package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class StreamComprehensiveTest {

    private List<Post> posts() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Post archived = new Post("작년 여행 #여행", minji, 90);
        archived.setStatus(PostStatus.ARCHIVED);
        return List.of(
                new Post("카페 #카페 #일상", minji, 250),
                new Post("코딩 #개발 #일상", jaehoon, 120),
                archived);
    }

    @Test
    @DisplayName("중복 없는 작성자 가나다순")
    void distinctAuthorsSorted() {
        assertEquals(List.of("jaehoon", "minji"), StreamComprehensive.distinctAuthorsSorted(posts()));
    }

    @Test
    @DisplayName("좋아요 상위 3개 제목 (limit)")
    void top3Titles() {
        assertEquals(List.of("카페 #카페 #일상", "코딩 #개발 #일상", "작년 여행 #여행"),
                StreamComprehensive.top3Titles(posts()));
    }

    @Test
    @DisplayName("중복 없는 해시태그 가나다순")
    void distinctHashtagsSorted() {
        assertEquals(List.of("#개발", "#여행", "#일상", "#카페"),
                StreamComprehensive.distinctHashtagsSorted(posts()));
    }

    @Test
    @DisplayName("팔로워 1000+ 회원을 팔로워 많은 순으로")
    void influencerNames() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);
        assertEquals(List.of("minji", "jaehoon"),
                StreamComprehensive.influencerNames(List.of(minji, jaehoon, seungwoo)));
    }

    @Test
    @DisplayName("보관됨 상태 글의 내용만")
    void archivedContents() {
        assertEquals(List.of("작년 여행 #여행"), StreamComprehensive.archivedContents(posts()));
    }
}
