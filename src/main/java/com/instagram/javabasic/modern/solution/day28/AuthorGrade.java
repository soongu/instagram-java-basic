package com.instagram.javabasic.modern.solution.day28;

import java.util.Optional;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/solution/day28/AuthorGrade.java
// [과제 2] 게시물 작성자가 있고(null 아님) 팔로워 1000명 이상일 때만 등급을 뽑아요.
// ofNullable → filter(조건) → map(변환) → orElse(기본값) 한 흐름으로, 단계마다 비면 NPE 없이 기본값으로 마무리돼요.
public class AuthorGrade {

    public static String topAuthorGrade(Post post) {
        return Optional.ofNullable(post.getAuthor())
                .filter(author -> author.getFollowers() >= 1000)
                .map(Member::grade)
                .orElse("등급 없음");
    }
}
