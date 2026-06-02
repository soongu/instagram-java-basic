package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/MediaItemDemoMain.java
// 과제 3 데모 — MediaItem 배열을 순회하며 isPlayable() 결과에 따라 분기해요.
// 호출은 모두 같은 isPlayable() 인데, 실제 종류(이미지·영상·텍스트)에 따라 결과가 갈려
// "재생 가능" 과 "재생 불가" 가 나뉘어요 (동적 디스패치).
// 핵심은 따로 있어요 — 부모 MediaItem 에 isPlayable() 빈칸을 더했더니,
// 세 자식 전부 그걸 구현해야만 컴파일이 됐다는 점이에요. 하나라도 빠지면 빨간 줄이 떠요.

public class MediaItemDemoMain {

    public static void main(String[] args) {

        // 자식들을 부모 타입 배열에 담아요 — 업캐스팅
        MediaItem[] items = {
                new ImageItem("minji", "beach.jpg"),
                new VideoItem("jaehoon", "trip.mp4", 45),
                new TextItem("seungwoo", "오늘 날씨가 정말 좋네요 산책하기 딱 좋은 하루")
        };

        // 같은 isPlayable() 을 부르는데 종류마다 결과가 달라요.
        // 영상만 true 라 "재생 가능" 으로, 나머지는 "재생 불가" 로 갈려요.
        for (MediaItem item : items) {
            if (item.isPlayable()) {
                System.out.println("[" + item.getType() + "] " + item.preview() + " → 재생 가능");
            } else {
                System.out.println("[" + item.getType() + "] " + item.preview() + " → 재생 불가");
            }
        }
    }
}
