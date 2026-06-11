package com.instagram.javabasic.modern;

import java.util.Objects;

// com/instagram/javabasic/modern/OldImageSize.java
// 사진 한 장의 "크기(가로·세로)" 라는 값만 담는 작은 객체예요.
// 값만 담는데도, 제대로 동작하려면 손으로 적을 게 이렇게나 많아요.
// 생성자 · 접근자(getter) · equals · hashCode · toString — 다섯 가지를 다 써야 해요.
public final class OldImageSize {

    // 값 두 개. 한 번 정하면 안 바뀌도록 final 로 둬요(불변).
    private final int width;
    private final int height;

    // (1) 생성자 — 값을 받아 채워요.
    public OldImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // (2) 접근자 — 숨긴 값을 읽는 통로. 값 두 개니까 두 개.
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // (3) equals — "가로·세로가 같으면 같은 크기" 로 보려고 직접 정의해요.
    //     이걸 빼먹으면 1080x1080 두 개가 서로 다른 것으로 취급돼요(주소 비교).
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OldImageSize other = (OldImageSize) obj;
        return width == other.width && height == other.height;
    }

    // (4) hashCode — equals 와 짝꿍. 같다고 본 두 값은 같은 칸으로 보내야 HashSet 이 중복을 걸러요.
    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    // (5) toString — 그냥 출력하면 주소가 찍히니, 사람이 읽을 글로 바꿔요.
    @Override
    public String toString() {
        return "OldImageSize[width=" + width + ", height=" + height + "]";
    }
}
