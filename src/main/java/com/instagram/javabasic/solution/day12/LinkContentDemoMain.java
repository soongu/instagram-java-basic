package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/LinkContentDemoMain.java
// 과제 1 데모 — 새로 만든 LinkContent 가 기존 자식들과 똑같이 Content[] 에 섞여
// render() 한 줄로 출력되는지 확인해요. 부모 Content 는 그대로인데,
// 자식만 하나 더 만들어 끼웠더니 피드에 자연스럽게 합류한다는 걸 눈으로 봐요.
import com.instagram.javabasic.domain.content.Content;
import com.instagram.javabasic.domain.content.ImageContent;
import com.instagram.javabasic.domain.content.TextContent;
import com.instagram.javabasic.domain.content.VideoContent;

public class LinkContentDemoMain {

    public static void main(String[] args) {

        // 기존 세 종류 + 새로 만든 링크 종류를 한 배열에 담아요.
        // 부모 타입 Content 자리에 자식 넷을 담는 게 업캐스팅이에요.
        Content[] feed = {
                new ImageContent("minji", 120, "beach.jpg"),
                new VideoContent("jaehoon", 340, "trip.mp4", 45),
                new TextContent("seungwoo", 12, "오늘 날씨가 정말 좋네요 산책하기 딱 좋은 하루"),
                new LinkContent("minji", 8, "https://spartacodingclub.kr")
        };

        // 같은 render() 만 부르는데, 실제 종류에 따라 채워진 빈칸이 달라
        // 종류별로 다른 한 줄이 나와요 (다형성 + 템플릿 메서드).
        for (Content content : feed) {
            System.out.println(content.render());
        }
    }
}
