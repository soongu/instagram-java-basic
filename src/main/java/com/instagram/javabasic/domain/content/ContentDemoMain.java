package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/ContentDemoMain.java
// 추상 클래스 + 다형성 + 템플릿 메서드를 한자리에서 눈으로 확인하는 데모예요.
// 종류가 다른 콘텐츠 셋을 Content 타입 배열 하나에 담고 똑같이 render() 만 불러요.
// 호출은 전부 같은데, 실제 객체 종류에 따라 채워진 빈칸(getType·preview)이 달라
// 출력이 종류별로 갈리는 걸 보려고요.
public class ContentDemoMain {

    public static void main(String[] args) {

        // Content 는 abstract 라 직접 만들 수 없어요. 아래 줄은 컴파일 에러가 나요.
        // Content c = new Content("minji", 10);   // 'Content' is abstract; cannot be instantiated

        // 대신 자식들을 만들어 부모 타입 배열에 담아요 — 자식을 부모 자리에 담는 게 업캐스팅
        Content[] feed = {
                new ImageContent("minji", 120, "beach.jpg"),
                new VideoContent("jaehoon", 340, "trip.mp4", 45),
                new TextContent("seungwoo", 12, "오늘 날씨가 정말 좋네요 산책하기 딱 좋은 하루")
        };

        // 향상된 for 로 순회하며 같은 render() 만 불러요.
        // render() 는 부모가 만든 틀 하나뿐인데, 그 안의 빈칸을 자식이 다르게 채워
        // 종류마다 다른 한 줄이 나와요 (다형성 + 템플릿 메서드).
        for (Content content : feed) {
            System.out.println(content.render());
        }
    }
}
