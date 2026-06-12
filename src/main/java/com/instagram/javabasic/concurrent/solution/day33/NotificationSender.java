package com.instagram.javabasic.concurrent.solution.day33;

// com/instagram/javabasic/concurrent/solution/day33/NotificationSender.java
// [과제 1] 알림 발송을 별도 스레드에서 처리하고, join 으로 끝까지 기다린 뒤 완료를 확인한다.
public class NotificationSender {

    // 알림 발송이 끝났는지 바깥에서 확인할 수 있게 기록해두는 깃발.
    private boolean sent;

    public boolean isSent() {
        return sent;
    }

    // 알림 발송 작업을 람다 Runnable 로 만들어 새 스레드에 태우고, join 으로 끝까지 기다린다.
    public void sendAndWait() throws InterruptedException {
        Runnable task = () -> {
            System.out.println("[" + Thread.currentThread().getName() + "] 알림 발송 완료");
            this.sent = true;
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();  // 발송 스레드가 끝날 때까지 기다린다 → 돌아오면 sent 가 확정돼 있어요
    }

    public static void main(String[] args) throws InterruptedException {
        NotificationSender sender = new NotificationSender();
        sender.sendAndWait();
        System.out.println("메인: 알림 발송이 끝난 걸 확인했습니다 (sent=" + sender.isSent() + ")");
    }
}
