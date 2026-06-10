package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/ImageUploadSession.java
// try-with-resources 실습용 가상 자원 — 이미지 업로드 세션이에요.
// 서버와 연결을 열어 두고 사진을 올리는 동안만 살아 있다가, 다 쓰면 반드시 닫아야 하는 자원이에요.
// AutoCloseable 을 구현하면 try-with-resources 가 블록이 끝날 때(정상이든 예외든)
// close() 를 자동으로 불러 줘요. "닫는 일을 깜빡하는 사고" 를 문법이 막아 주는 거예요.
public class ImageUploadSession implements AutoCloseable {

    // 세션 이름 — 어느 세션이 열리고 닫히는지 출력으로 구분하려고 붙여요.
    private final String name;

    // 세션이 열려 있는지 — 닫힌 세션에 업로드하는 사고를 막는 안전장치예요.
    private boolean open = true;

    public ImageUploadSession(String name) {
        this.name = name;
        System.out.println("[" + name + "] 업로드 세션을 열었어요.");
    }

    // 세션이 아직 열려 있는지 알려줘요.
    public boolean isOpen() {
        return open;
    }

    // 사진 한 장을 업로드해요. 닫힌 세션이면 IllegalStateException 으로 막아요.
    public void upload(String filename) {
        if (!open) {
            throw new IllegalStateException("[" + name + "] 이미 닫힌 세션이에요. 업로드할 수 없어요.");
        }
        System.out.println("[" + name + "] " + filename + " 업로드 완료!");
    }

    // 자원 정리 — try-with-resources 가 블록 끝에서 자동으로 불러 줘요.
    // AutoCloseable 의 close() 는 원래 Exception 을 던질 수 있다고 선언돼 있지만,
    // 우리는 던질 일이 없으니 throws 없이 재정의해요(호출자가 잡을 필요가 없어져요).
    @Override
    public void close() {
        open = false;
        System.out.println("[" + name + "] 업로드 세션을 닫았어요.");
    }

    public static void main(String[] args) {
        // ① 정상 경로 — 블록이 끝나면 close() 가 자동으로 불려요. 닫는 코드를 쓴 적이 없는데도요.
        try (ImageUploadSession session = new ImageUploadSession("피드")) {
            session.upload("cat.jpg");
            session.upload("coffee.jpg");
        }

        // ② 예외 경로 — 업로드 도중 캡션 검증이 터져도 close() 는 자동으로 불려요.
        // 출력 순서를 보세요. "닫았어요" 가 먼저 나오고, 그 다음에 catch 가 받아요.
        try {
            try (ImageUploadSession session = new ImageUploadSession("피드")) {
                session.upload("sunset.jpg");
                throw new InvalidCaptionException("캡션은 2200자까지만 쓸 수 있어요.");
            }
        } catch (InvalidCaptionException e) {
            System.out.println("업로드 중단: " + e.getMessage());
        }

        // ③ 자원 2개 — 세미콜론으로 나란히 열면, 나중에 연 것부터 거꾸로 닫혀요.
        // [스토리] 열기 → [피드] 열기 → ... → [피드] 닫기 → [스토리] 닫기 순서예요.
        try (ImageUploadSession story = new ImageUploadSession("스토리");
                ImageUploadSession feed = new ImageUploadSession("피드")) {
            story.upload("daily.jpg");
            feed.upload("brunch.jpg");
        }
    }
}
