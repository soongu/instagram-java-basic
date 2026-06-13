package com.instagram.javabasic.io;

import java.io.Serializable;

// com/instagram/javabasic/io/CachedPost.java
// transient(트랜지언트) 키워드를 눈으로 보려고 만든 게시물 클래스예요.
//
// 일반 필드(id·author·caption)는 저장돼요.
// 하지만 displayLabel 처럼 "보여주기용 임시 값" 은 다시 만들 수 있으니 저장할 필요가 없어요.
// 그런 필드 앞에 transient 를 붙이면 "이건 저장하지 마세요" 라는 뜻이에요.
// 그래서 불러오면 transient 필드는 비어 있어요(객체 참조면 null).
//
// (record 컴포넌트에는 transient 를 붙일 수 없어서, 이 시연만 일반 클래스로 만들었어요.)
public class CachedPost implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long id;
    private final String author;
    private final String caption;

    // 저장에서 빠지는 임시 값이에요 → 불러오면 null 로 돌아와요.
    private transient String displayLabel;

    public CachedPost(long id, String author, String caption) {
        this.id = id;
        this.author = author;
        this.caption = caption;
        this.displayLabel = author + " | " + caption;
    }

    public long id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String caption() {
        return caption;
    }

    public String displayLabel() {
        return displayLabel;
    }
}
