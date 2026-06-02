package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/TextItem.java
// 과제 3 — 텍스트 미디어예요. MediaItem 의 빈칸 셋을 모두 채워요.
// 글은 읽는 거지 재생하는 게 아니라 isPlayable() 은 false 예요.
public class TextItem extends MediaItem {

    private String body;

    public TextItem(String authorName, String body) {
        super(authorName);
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String getType() {
        return "텍스트";
    }

    @Override
    public String preview() {
        if (body.length() > 10) {
            return body.substring(0, 10) + "...";
        }
        return body;
    }

    // 텍스트는 재생 대상이 아니에요 — false
    @Override
    public boolean isPlayable() {
        return false;
    }
}
