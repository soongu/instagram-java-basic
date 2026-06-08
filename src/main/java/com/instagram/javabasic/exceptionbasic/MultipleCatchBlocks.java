package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/MultipleCatchBlocks.java
// 한 try 블록에서 여러 종류의 예외가 날 수 있어요. 그럴 땐 catch 를 여러 개 이어 붙여
// "이 예외는 이렇게, 저 예외는 저렇게" 따로 처리할 수 있어요.
//
// 단, 순서가 중요해요. catch 는 위에서부터 차례로 "이거 내 거야?" 하고 확인하는데,
// 먼저 매치되는 catch 가 그 예외를 가져가요. 그래서 좁고 구체적인 예외를 위에,
// 넓고 일반적인 예외를 아래에 둬야 해요.
//   - IllegalArgumentException 은 RuntimeException 의 자식(더 구체적) → 위에
//   - RuntimeException 은 부모(더 일반적)                              → 아래에
// 만약 순서를 뒤집어 RuntimeException 을 위에 두면, 자식인 IllegalArgumentException 도
// 부모 catch 가 먼저 다 잡아 버려서 아래 catch 는 영영 실행되지 못해요(컴파일 오류로 막혀요).
public class MultipleCatchBlocks {

    // scenario 값에 따라 서로 다른 예외를 일부러 일으키고,
    // 어느 catch 블록이 처리했는지 라벨로 돌려줘요.
    public String handle(int scenario) {
        try {
            if (scenario == 1) {
                // 더 구체적인 예외
                throw new IllegalArgumentException("잘못된 인자예요.");
            } else if (scenario == 2) {
                // IllegalArgumentException 이 아닌 다른 런타임 예외
                throw new IllegalStateException("지금 할 수 없는 상태예요.");
            }
            // 어떤 예외도 안 나면 여기까지 와요.
            return "정상";
        } catch (IllegalArgumentException e) {
            // 구체적인 예외를 먼저 잡아요.
            return "IllegalArgument 처리";
        } catch (RuntimeException e) {
            // 위에서 안 잡힌 나머지 런타임 예외를 여기서 받아요.
            return "그 외 런타임 처리";
        }
    }

    public static void main(String[] args) {
        MultipleCatchBlocks demo = new MultipleCatchBlocks();
        System.out.println("scenario 1 → " + demo.handle(1)); // IllegalArgument 처리
        System.out.println("scenario 2 → " + demo.handle(2)); // 그 외 런타임 처리
        System.out.println("scenario 0 → " + demo.handle(0)); // 정상
    }
}
