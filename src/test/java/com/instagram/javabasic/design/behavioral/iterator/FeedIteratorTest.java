package com.instagram.javabasic.design.behavioral.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.post.Post;

class FeedIteratorTest {

    private Feed sampleFeed() {
        Post[] posts = {
            new Post("첫 게시물", "jaehoon", 10),
            new Post("둘째 게시물", "minji", 20),
            new Post("셋째 게시물", "seungwoo", 30),
        };
        return new Feed(posts);
    }

    @Test
    @DisplayName("우리가 만든 Feed 를 for-each 로 그대로 돌릴 수 있다")
    void worksWithForEach() {
        int total = 0;
        for (Post post : sampleFeed()) {   // ← Iterable 약속 덕분에 가능
            total += post.getLikeCount();
        }

        assertEquals(60, total);
    }

    @Test
    @DisplayName("for-each 가 속으로 부르는 iterator()/hasNext()/next() 를 직접 풀어 써도 결과가 같다")
    void manualIterationMatchesForEach() {
        Iterator<Post> it = sampleFeed().iterator();

        assertTrue(it.hasNext());
        assertEquals("첫 게시물", it.next().getContent());
        assertEquals("둘째 게시물", it.next().getContent());
        assertEquals("셋째 게시물", it.next().getContent());
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("iterator() 를 새로 부르면 처음부터 다시 순회한다")
    void freshIteratorStartsOver() {
        Feed feed = sampleFeed();

        int firstPass = 0;
        for (Post post : feed) {
            firstPass++;
        }

        int secondPass = 0;
        for (Post post : feed) {
            secondPass++;
        }

        assertEquals(3, firstPass);
        assertEquals(3, secondPass);
    }
}
