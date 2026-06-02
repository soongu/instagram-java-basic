package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/ContentRoleDemoMain.java
// 인터페이스(역할)를 한자리에서 눈으로 확인하는 데모예요.
// 같은 사진 객체 하나를 콘텐츠로도, 공유 가능한 것으로도, 댓글 가능한 것으로도 바라보고,
// default 메서드(share)·static 메서드(isFull)·역할 분화(instanceof)를 차례로 확인해요.
public class ContentRoleDemoMain {

    public static void main(String[] args) {

        // 1) 한 객체를 여러 타입으로 바라보기 (업캐스팅)
        ImageContent image = new ImageContent("minji", 120, "beach.jpg");

        Content content = image;          // "이건 콘텐츠다" 로 보기
        Shareable shareable = image;      // "이건 공유 가능한 것이다" 로 보기
        Commentable commentable = image;  // "이건 댓글 가능한 것이다" 로 보기

        System.out.println(content.getType());          // 이미지
        System.out.println(shareable.getShareUrl());    // instagram.com/p/img-minji
        commentable.addComment("좋아요!");
        System.out.println(commentable.getCommentCount()); // 1

        // 2) default 메서드 share() — 구현 클래스가 안 만들어도 그대로 동작
        System.out.println(image.share());
        // 출력: 공유 링크가 생성됐어요: instagram.com/p/img-minji

        // 3) static 메서드 isFull / 상수 MAX_COMMENTS — 객체 없이 인터페이스 이름으로
        boolean full = Commentable.isFull(100);   // true  (100은 한도에 찼어요)
        boolean ok = Commentable.isFull(99);      // false (99는 아직 여유 있어요)
        System.out.println(full + " " + ok);      // true false
        System.out.println(Commentable.MAX_COMMENTS); // 100

        // 4) 역할 분화 — 텍스트는 Commentable 이지만 Shareable 은 아니에요
        Content textContent = new TextContent("seungwoo", 12, "오늘 점심 맛있었다");

        if (textContent instanceof Shareable s) {
            // 공유 가능한 콘텐츠일 때만 이 안으로 들어와요
            System.out.println(s.getShareUrl());
        } else {
            // 텍스트는 Shareable 이 아니라서 여기로 빠져요
            System.out.println("이 콘텐츠는 공유할 수 없어요");
        }
    }
}
