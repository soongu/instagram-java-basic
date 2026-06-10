package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/HandleOrThrow.java
// 예외를 만나면 늘 고민이에요. "여기서 잡을까, 아니면 넘길까?"
//   - 던지는 쪽 : 문제를 직접 처리할 만큼의 정보가 없으면, 잡지 말고 그대로 위로 던져요.
//   - 잡는 쪽   : 어떻게 복구할지 정할 수 있는 곳에서 잡아 기본값으로 대신해요.
// 같은 일(숫자 변환)을 두 방식으로 나란히 만들어 비교해 볼게요.
// 사용자가 입력한 "보고 싶은 게시물 개수" 글자를 숫자로 바꾸는 상황이에요.
public class HandleOrThrow {

    // 던지는 쪽 — 숫자로 못 바꾸면 NumberFormatException 이 그대로 위로 전파돼요.
    // 여기선 "무엇이 옳은 기본값인지" 를 모르니 굳이 잡지 않고 호출자에게 맡겨요.
    public int parseLimit(String raw) {
        return Integer.parseInt(raw);
    }

    // 잡는 쪽 — parseLimit 을 try-catch 로 감싸, 변환이 실패하면 기본값 30 으로 복구해요.
    // 인스타 해시태그 한도와 같은 30 을 안전한 기본값으로 써요.
    public int parseLimitOrDefault(String raw) {
        try {
            return parseLimit(raw);
        } catch (NumberFormatException e) {
            return 30;
        }
    }

    public static void main(String[] args) {
        HandleOrThrow parser = new HandleOrThrow();

        // 정상 — 숫자 글자는 그대로 변환돼요.
        System.out.println("정상 입력 \"50\" → " + parser.parseLimitOrDefault("50"));

        // 사고 — 숫자가 아닌 글자는 변환에 실패하지만, 잡는 쪽이 기본값 30 으로 복구해요.
        System.out.println("잘못된 입력 \"열개\" → " + parser.parseLimitOrDefault("열개"));
    }
}
