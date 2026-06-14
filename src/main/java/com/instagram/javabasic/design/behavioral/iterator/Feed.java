package com.instagram.javabasic.design.behavioral.iterator;

// com/instagram/javabasic/design/behavioral/iterator/Feed.java

import java.util.Iterator;

import com.instagram.javabasic.domain.post.Post;

// 게시물을 배열에 담아두는 우리만의 작은 피드예요.
// List 가 아니라 평범한 배열(Post[])을 쓰는데도, Iterable 약속을 지키면
// 이 Feed 를 for-each(향상된 for문) 로 그대로 돌릴 수 있어요.
// 바로 그게 "for-each 의 비밀" 이에요 — for-each 는 Iterable 의 iterator() 를 불러서 돌아요.
public class Feed implements Iterable<Post> {

    private final Post[] posts;

    public Feed(Post[] posts) {
        this.posts = posts;
    }

    // Iterable 의 약속 — "나를 어떻게 순회할지" 담당하는 Iterator 를 하나 만들어 돌려줘요.
    // for-each 가 속으로 이 메서드를 불러요.
    @Override
    public Iterator<Post> iterator() {
        return new FeedIterator();
    }

    // 순회 담당자 — "지금 어디까지 봤는지(cursor)" 를 기억하면서 하나씩 꺼내줘요.
    // 바깥 클래스(Feed)의 posts 배열을 그대로 들여다볼 수 있는 내부 클래스로 만들어요.
    private class FeedIterator implements Iterator<Post> {

        // 다음에 꺼낼 자리예요. 0부터 시작해 하나 꺼낼 때마다 한 칸 앞으로 가요.
        private int cursor = 0;

        // 아직 더 꺼낼 게 남았는지 — 자리(cursor)가 배열 끝에 닿기 전이면 true.
        @Override
        public boolean hasNext() {
            return cursor < posts.length;
        }

        // 지금 자리의 게시물을 꺼내고, 자리를 한 칸 앞으로 옮겨요.
        @Override
        public Post next() {
            return posts[cursor++];
        }
    }
}
