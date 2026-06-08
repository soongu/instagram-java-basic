// com/instagram/javabasic/solution/day18/TagBoard.java
package com.instagram.javabasic.solution.day18;

import java.util.ArrayList;
import java.util.List;

/**
 * 과제 1: 해시태그를 담는 보드.
 * 같은 태그를 두 번 넣어도 한 번만 담기도록 막아둔다.
 */
class TagBoard {

    private final List<String> tags = new ArrayList<>();

    /** 이미 들어 있는 태그면 그냥 무시하고, 새 태그만 추가한다. */
    void add(String tag) {
        if (tags.contains(tag)) {
            return;
        }
        tags.add(tag);
    }

    /** 태그를 빼고, 실제로 빠졌으면 true 를 돌려준다. */
    boolean remove(String tag) {
        return tags.remove(tag);
    }

    /** 현재 담긴 태그 개수. */
    int size() {
        return tags.size();
    }

    /** "#a #b #c" 처럼 한 줄로 그려준다. 비어 있으면 빈 문자열. */
    String render() {
        String line = "";
        for (String tag : tags) {
            line = line + "#" + tag + " ";
        }
        return line.trim();
    }
}
