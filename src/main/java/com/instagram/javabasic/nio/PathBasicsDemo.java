package com.instagram.javabasic.nio;

import java.nio.file.Path;

// com/instagram/javabasic/nio/PathBasicsDemo.java
// Path 는 "파일 시스템의 경로를 가리키는 순수 객체" 예요. 지난 시간 쓰던 java.io.File 의 후계자예요.
//
// 핵심: Path 는 경로를 "가리키기만" 해요. 실제로 파일을 읽거나 쓰지 않아요.
// (진짜 파일 작업은 다음 단계의 Files 가 맡아요. Path 는 주소, Files 는 그 주소로 일하는 일꾼이에요.)
//
// 경로를 슬래시(/) 로 직접 이어 붙이면 윈도우(\)·맥(/) 에서 깨질 수 있어요.
// resolve 로 이으면 운영체제에 맞는 구분자를 알아서 넣어줘요.
public class PathBasicsDemo {

    public static void main(String[] args) {
        // 경로 조립: 문자열을 직접 잇지 않고 of/resolve 로 안전하게 이어요.
        Path base = Path.of("uploads");
        Path userDir = base.resolve("jaehoon");           // uploads/jaehoon
        Path photo = userDir.resolve("post1.jpg");        // uploads/jaehoon/post1.jpg

        System.out.println("전체 경로 → " + photo);
        System.out.println("파일 이름 → " + photo.getFileName());    // post1.jpg
        System.out.println("부모 폴더 → " + photo.getParent());      // uploads/jaehoon
        System.out.println("경로 깊이 → " + photo.getNameCount());   // 3

        // 절대 경로로 바꾸기 (지금 작업 폴더 기준). 실제 파일이 없어도 계산만 해요.
        Path absolute = photo.toAbsolutePath();
        System.out.println("절대 경로 → " + absolute);
    }
}
