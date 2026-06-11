package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class LogicalMatchDemoTest {

    private List<Post> withArchived() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post archived = new Post("작년 사진", minji, 90);
        archived.setStatus(PostStatus.ARCHIVED);
        return List.of(new Post("오늘 카페", minji, 250), archived);
    }

    @Test
    @DisplayName("anyMatch: 팔로워 1000+ 인플루언서가 한 명이라도 있으면 true")
    void hasInfluencer() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);
        assertTrue(LogicalMatchDemo.hasInfluencer(List.of(minji, seungwoo)));
        assertFalse(LogicalMatchDemo.hasInfluencer(List.of(seungwoo)));
    }

    @Test
    @DisplayName("allMatch: 보관 글이 섞이면 전부 공개가 아니다")
    void allPublic() {
        assertFalse(LogicalMatchDemo.allPublic(withArchived()));
    }

    @Test
    @DisplayName("noneMatch: 보관 글이 있으면 '보관 글 없음'은 false")
    void noArchived() {
        assertFalse(LogicalMatchDemo.noArchived(withArchived()));
    }
}
