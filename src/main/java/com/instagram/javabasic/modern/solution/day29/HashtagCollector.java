package com.instagram.javabasic.modern.solution.day29;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// com/instagram/javabasic/modern/solution/day29/HashtagCollector.java
// [과제 1] 태그 이름 목록을 Hashtag record 로 감싸 중복 없이 모아요.
// record 가 equals/hashCode 를 자동으로 만들어줘서, 같은 이름은 한 번만 들어가요.
// (LinkedHashSet 이라 처음 등장한 순서를 그대로 지켜요.)
public class HashtagCollector {

    public static Set<Hashtag> distinctTags(List<String> names) {
        Set<Hashtag> tags = new LinkedHashSet<>();
        for (String name : names) {
            tags.add(new Hashtag(name));
        }
        return tags;
    }
}
