package com.instagram.javabasic.exceptionbasic.solution.day21;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/exceptionbasic/solution/day21/SafePostReader.java
// "이미 던져진 예외를 잡아서 프로그램이 멈추지 않게" 하는 가장 기초적인 연습이에요.
// 게시물 제목 목록(List<String>)에서 index 로 제목을 꺼내는데,
// 목록 크기를 벗어난 번호를 넣으면 자바가 IndexOutOfBoundsException(범위 벗어남 예외)을 던져요.
// 이 예외를 try-catch 로 감싸 잡으면, 프로그램이 그 자리에서 죽지 않고
// 대신 "(없는 게시물)" 이라는 안내 문구를 돌려주고 다음 줄로 넘어갈 수 있어요.
public class SafePostReader {

    // 안전하게 제목 꺼내기 — 정상 번호면 그 제목을, 범위 밖이면 안내 문구를 돌려줘요.
    // try 블록 안에서 titles.get(index) 를 시도해요.
    //   - 번호가 정상이면 그 제목이 그대로 return 돼요.
    //   - 번호가 목록 크기를 벗어나면 get 이 IndexOutOfBoundsException 을 던지는데,
    //     catch 가 그걸 받아내서 "(없는 게시물)" 을 대신 돌려줘요.
    // 예외가 밖으로 새어 나가지 않으니, 이 메서드를 부른 쪽은 멈추지 않고 계속 진행해요.
    public String getTitleSafely(List<String> titles, int index) {
        try {
            return titles.get(index);
        } catch (IndexOutOfBoundsException e) {
            return "(없는 게시물)";
        }
    }

    public static void main(String[] args) {
        SafePostReader reader = new SafePostReader();

        List<String> titles = new ArrayList<>();
        titles.add("첫 게시물");
        titles.add("점심 인증");
        titles.add("저녁 노을");

        // 정상 번호 — 그 자리의 제목이 그대로 나와요.
        System.out.println("0번 제목: " + reader.getTitleSafely(titles, 0));
        System.out.println("2번 제목: " + reader.getTitleSafely(titles, 2));

        // 범위 밖 번호(100) — 예외가 잡혀서 안내 문구가 나오고, 프로그램은 죽지 않아요.
        System.out.println("100번 제목: " + reader.getTitleSafely(titles, 100));

        // 위에서 예외가 났어도 이 줄까지 무사히 실행돼요(복구의 증거).
        System.out.println("끝까지 잘 실행됐어요!");
    }
}
