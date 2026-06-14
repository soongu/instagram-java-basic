package com.instagram.javabasic.design.creational;

import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.content.VideoContent;

// com/instagram/javabasic/design/creational/ContentFactory.java
// 업로드 종류에 맞는 콘텐츠 객체를 만들어 주는 "공장" 이에요.
// 종류에 따라 ImageContent·VideoContent·TextContent 중 무엇을 new 할지는 이 공장이 정해요.
// 그래서 콘텐츠를 쓰는 쪽(피드 화면 등)은 "이미지면 ImageContent, 영상이면 VideoContent..." 를
// 일일이 알 필요 없이 ContentFactory.create(종류, ...) 한 줄만 부르면 돼요.
// 나중에 새 콘텐츠 종류가 생겨도, 손대는 곳은 이 공장 한 곳뿐이에요.
public class ContentFactory {

    // 영상은 재생 시간도 필요하지만, 여기선 공장의 핵심(종류에 맞는 객체 고르기)에 집중하려고
    // 길이를 따로 받지 않고 기본값으로 둬요.
    private static final int DEFAULT_VIDEO_SECONDS = 15;

    // 콘텐츠 종류 — 업로드가 이미지인지 영상인지 글인지 골라요.
    public enum ContentType {
        IMAGE, VIDEO, TEXT
    }

    // 종류에 맞는 콘텐츠를 만들어 돌려줘요. 갓 올라온 콘텐츠라 좋아요 수는 0 에서 시작해요.
    // main 은 종류마다 의미가 달라요 — 이미지·영상은 주소, 글은 본문이에요.
    public static Content create(ContentType type, String author, String main) {
        return switch (type) {
            case IMAGE -> new ImageContent(author, 0, main);
            case VIDEO -> new VideoContent(author, 0, main, DEFAULT_VIDEO_SECONDS);
            case TEXT -> new TextContent(author, 0, main);
        };
    }
}
