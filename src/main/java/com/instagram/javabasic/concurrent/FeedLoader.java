package com.instagram.javabasic.concurrent;

// com/instagram/javabasic/concurrent/FeedLoader.java
// 스레드를 만드는 두 번째 방법: Runnable 인터페이스를 구현하기.
// Runnable 은 "할 일" 하나만 담는 약속이에요. run() 메서드 하나만 채우면 됩니다.
// 만든 Runnable 을 new Thread(러너블) 에 태워서 start() 하면 새 스레드에서 그 일이 돌아가요.
//
// Thread 를 상속하는 방법도 있지만, Runnable 이 더 권장돼요. 이유는 두 가지예요.
//   (1) 자바는 클래스를 하나만 상속할 수 있어요. Thread 를 상속해 버리면 다른 클래스를 못 물려받아요.
//       Runnable 은 인터페이스라 implements 로 붙이면 돼서 단일 상속 제약에 안 걸려요.
//   (2) "할 일(Runnable)" 과 "그 일을 돌리는 일꾼(Thread)" 을 따로 떼어놓을 수 있어요. 역할이 깔끔하게 나뉘죠.
//
// 인스타 피드를 열면 이미지 여러 장이 동시에 로딩되죠? 그걸 흉내 내봐요.
// 각 FeedLoader 는 자기가 맡은 이미지 한 장만 책임지고 로딩 완료로 표시해요 (서로 간섭하지 않아요).
public class FeedLoader implements Runnable {

    // 이 로더가 책임지는 이미지 번호.
    private final int imageId;

    // 이 이미지 로딩이 끝났는지 표시하는 깃발.
    private boolean loaded;

    public FeedLoader(int imageId) {
        this.imageId = imageId;
    }

    // 이 스레드가 할 일: 자기 담당 이미지를 로딩하고 완료 표시.
    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] 이미지 " + imageId + "번 로딩 완료");
        this.loaded = true;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Runnable 을 구현한 클래스로 스레드 띄우기 ===");
        FeedLoader loader = new FeedLoader(1);
        Thread thread = new Thread(loader);  // Runnable 을 Thread 에 태운다
        thread.start();
        thread.join();
        System.out.println("이미지 1번 로딩됨? → " + loader.isLoaded());

        System.out.println();

        System.out.println("=== 피드 이미지 3장을 동시에 로딩 ===");
        for (int id = 1; id <= 3; id++) {
            new Thread(new FeedLoader(id)).start();
        }

        System.out.println();

        System.out.println("=== 람다로 Runnable 만들기 (클래스 없이 한 줄로) ===");
        Runnable task = () -> System.out.println("[" + Thread.currentThread().getName() + "] 람다 작업 실행");
        new Thread(task).start();
    }
}
