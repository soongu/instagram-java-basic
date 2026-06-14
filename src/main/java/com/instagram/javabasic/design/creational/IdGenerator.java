package com.instagram.javabasic.design.creational;

// com/instagram/javabasic/design/creational/IdGenerator.java
// 게시물·스토리에 붙일 번호(ID)를 발급하는 "발급기" 예요. 앱 전체에서 딱 하나만 있어야 해요.
// 발급기가 여러 개면 각자 1번부터 세기 시작해서 같은 번호가 두 번 나올 수 있거든요(ID 중복).
// 그래서 enum 으로 만들어요. enum 상수는 자바가 프로그램당 딱 하나만 만들어 주고,
// 여러 곳에서 동시에 불러도 안전해요. 이렇게 "단 하나뿐인 객체" 를 Singleton(싱글턴)이라 불러요.
public enum IdGenerator {

    // 단 하나뿐인 인스턴스 — 이 줄이 곧 "유일한 발급기" 예요.
    // 어디서든 IdGenerator.INSTANCE 로 부르면 항상 같은 발급기를 써요.
    INSTANCE;

    // 마지막으로 발급한 번호 — 다음 발급 때 1을 더해 새 번호를 줘요.
    private long lastId = 0;

    // 새 번호를 하나 발급해요. 같은 발급기를 쓰는 한 번호는 절대 겹치지 않아요.
    // synchronized 는 여러 작업이 동시에 불러도 번호가 꼬이지 않게 막아주는 빗장이에요(Day 34 에서 배웠죠).
    public synchronized long nextId() {
        lastId++;
        return lastId;
    }

    // 지금까지 몇 개를 발급했는지 확인해요.
    public synchronized long issuedCount() {
        return lastId;
    }
}
