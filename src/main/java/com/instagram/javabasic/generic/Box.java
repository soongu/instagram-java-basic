package com.instagram.javabasic.generic;

// com/instagram/javabasic/generic/Box.java
// 제네릭(generic, 타입을 나중에 끼워 넣는 틀) 그릇이에요.
// 클래스 이름 옆의 <T> 가 핵심이에요. T 는 Type(타입)의 머리글자로,
// "여기에 들어갈 타입은 객체를 만들 때 정할게요" 라는 빈칸이에요.
//   new Box<String>() 이라고 만들면 그 순간 T 자리에 String 이 끼워져서
//   set 은 String 만 받고, get 은 String 을 그대로 돌려줘요.
// 그래서 ObjectBox 와 달리 꺼낼 때 형변환(casting)이 전혀 필요 없고,
// 엉뚱한 타입을 넣으려 하면 실행 전에 컴파일 단계에서 바로 막혀요(안전).
public class Box<T> {

    // T 타입 값 하나를 담아요. T 가 무엇인지는 객체를 만들 때 정해져요.
    private T item;

    // 담기 — T 타입만 받아요. 다른 타입을 넣으려 하면 컴파일 에러로 미리 막아줘요.
    public void set(T item) {
        this.item = item;
    }

    // 꺼내기 — T 타입 그대로 나와요. 형변환이 필요 없어요.
    public T get() {
        return item;
    }

    public static void main(String[] args) {
        // T 자리에 String 을 끼웠어요. 이 그릇은 이제 String 전용이에요.
        Box<String> nameBox = new Box<>();
        nameBox.set("minji");
        String name = nameBox.get();   // 형변환 (String) 이 필요 없어요!
        System.out.println("이름 그릇: " + name + " (길이 " + name.length() + ")");

        // T 자리에 Integer 를 끼우면 숫자 전용 그릇이 돼요.
        Box<Integer> countBox = new Box<>();
        countBox.set(8500);
        int count = countBox.get();
        System.out.println("숫자 그릇: " + count);
    }
}
