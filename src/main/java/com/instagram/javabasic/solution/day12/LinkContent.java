package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/LinkContent.java
// 과제 1 — Content 계열의 네 번째 자식이에요.
// 기존 콘텐츠 종류(이미지·영상·텍스트)는 그대로 두고, "링크" 라는 새 종류만 더해요.
// 부모 Content 는 content 패키지에 있으니 import 해서 그대로 물려받아요(extends).
// 부모 코드는 한 글자도 건드리지 않았는데, 빈칸 두 개(getType·preview)만 채우면
// 새 종류가 곧장 피드에 끼어든다는 게 핵심이에요 (확장에는 열려 있어요).
import com.instagram.javabasic.domain.content.Content;

public class LinkContent extends Content {

    // 링크 콘텐츠만 추가로 갖는 정보 — 공유하는 주소
    private String linkUrl;

    // 생성자 — 첫 줄 super(...) 로 부모의 공통 필드(작성자·좋아요)를 먼저 채우고,
    // 그 다음 링크만의 필드를 채워요.
    public LinkContent(String authorName, int likeCount, String linkUrl) {
        super(authorName, likeCount);
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    // 부모의 빈칸을 채워요 — 링크의 종류 이름
    @Override
    public String getType() {
        return "링크";
    }

    // 부모의 빈칸을 채워요 — 링크는 작성자와 공유한 주소를 미리보기로 보여줘요
    @Override
    public String preview() {
        return getAuthorName() + " 님이 공유한 링크: " + linkUrl;
    }
}
