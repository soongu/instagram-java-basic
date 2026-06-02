package com.instagram.javabasic.annotationbasic;

// com/instagram/javabasic/annotationbasic/AnnotationDemo.java
// 어노테이션(annotation)은 코드에 붙이는 "메모지" 예요.
// @ 기호로 시작하고, 코드 자체를 실행하진 않지만 컴파일러나 도구에게 정보를 줘요.
// 우리가 Day 10 부터 계속 봐온 @Override 도 어노테이션의 하나였어요.
// 여기선 자주 쓰는 기본 어노테이션 세 가지를 직접 붙여봐요.
public class AnnotationDemo {

    // @Deprecated — "이건 이제 안 쓰는 게 좋아요" 라는 표시예요.
    // 지우진 않았지만, 이 메서드를 쓰면 IDE 가 취소선으로 경고해줘요.
    @Deprecated
    public String oldShare() {
        return "예전 방식으로 공유했어요";
    }

    // 새로 권장하는 방식 — 같은 일을 하지만 이쪽을 쓰라는 뜻이에요.
    public String newShare() {
        return "새 방식으로 공유했어요";
    }

    // @SuppressWarnings — "이 경고는 알고 있으니 조용히 해줘" 라는 표시예요.
    // 여기선 안 쓰는 지역 변수 경고("unused")를 일부러 눌렀어요.
    @SuppressWarnings("unused")
    public String quietMethod() {
        int draftCount = 0;
        return "경고 없이 처리했어요";
    }
}
