package com.instagram.javabasic.exceptionbasic.solution.day23;

// com/instagram/javabasic/exceptionbasic/solution/day23/SimpleConnection.java
// [심화] 과제 3 — AutoCloseable 을 구현한 "연결" 자원이에요.
// try-with-resources 의 괄호 안에서 만들면, 블록이 끝날 때(정상이든 예외든)
// close() 가 자동으로 불려요. Step 6 의 ImageUploadSession 과 같은 틀이에요.
public class SimpleConnection implements AutoCloseable {

    // 연결이 열려 있는지 — 닫힌 연결에 query 하는 사고를 막는 안전장치예요.
    private boolean open = true;

    // 연결이 아직 열려 있는지 알려줘요.
    public boolean isOpen() {
        return open;
    }

    // 쿼리 실행 — 열려 있으면 실행하고, 닫혀 있으면 IllegalStateException 으로 막아요.
    public void query(String sql) {
        if (!open) {
            throw new IllegalStateException("연결이 닫혔어요");
        }
        System.out.println("쿼리 실행: " + sql);
    }

    // 자원 정리 — try-with-resources 가 블록 끝에서 자동으로 불러 줘요.
    // 닫다가 사고 날 일이 없으니 throws 없이 재정의해요.
    @Override
    public void close() {
        open = false;
        System.out.println("연결을 닫습니다");
    }

    public static void main(String[] args) {
        // 정상 경로 — 블록이 끝나면 close() 가 자동으로 불려요.
        try (SimpleConnection conn = new SimpleConnection()) {
            conn.query("SELECT * FROM member");
        }

        // 예외 경로 — 도중에 사고가 나도 close() 는 자동으로 불려요.
        // "연결을 닫습니다" 가 먼저 나오고, 그 다음에 바깥 catch 가 받아요.
        try {
            try (SimpleConnection conn = new SimpleConnection()) {
                conn.query("SELECT * FROM post");
                throw new IllegalStateException("도중 사고!");
            }
        } catch (IllegalStateException e) {
            System.out.println("사고 발생: " + e.getMessage());
        }
    }
}
