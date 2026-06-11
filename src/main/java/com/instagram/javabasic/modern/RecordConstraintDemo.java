package com.instagram.javabasic.modern;

// com/instagram/javabasic/modern/RecordConstraintDemo.java
// record 의 두 가지 약속을 확인해요.
// (1) 한 번 만들면 값이 안 바뀐다(불변) — 바꾸려면 "새로 만든다".
// (2) 다른 클래스를 물려받을 수 없다 — 대신 인터페이스는 구현할 수 있다(다음 단계에서 써요).
public class RecordConstraintDemo {

    public static void main(String[] args) {
        ImageSize original = new ImageSize(1080, 1080);

        // 값을 바꾸는 setter 가 없어요. "썸네일 크기로 바꾸기" 는 새 객체를 만드는 걸로 해요.
        // (original.width = 150; 같은 직접 변경은 아예 문법 오류라 쓸 수 없어요.)
        ImageSize thumbnail = new ImageSize(150, 150);

        // 원본은 그대로예요 — 누가 가져가도 내 값이 바뀔 걱정이 없어요(불변의 장점).
        System.out.println("원본: " + original);     // ImageSize[width=1080, height=1080]
        System.out.println("썸네일: " + thumbnail);   // ImageSize[width=150, height=150]
        System.out.println("원본은 그대로? " + original.equals(new ImageSize(1080, 1080))); // true
    }
}
