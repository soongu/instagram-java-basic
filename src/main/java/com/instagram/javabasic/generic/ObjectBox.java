package com.instagram.javabasic.generic;

// com/instagram/javabasic/generic/ObjectBox.java
// 제네릭(generic, 타입을 나중에 끼워 넣는 틀)이 없던 시절의 "만능 그릇" 이에요.
// 무엇이든 담으려고 Object(모든 클래스의 부모) 타입으로 받았어요.
// 문제는 꺼낼 때예요. 안에 무엇이 들었는지 그릇이 기억하지 못하니,
// 우리가 직접 "이건 String 이야" 하고 형변환(casting, 타입 강제 변환)을 해 줘야 해요.
// 그런데 잘못된 타입으로 형변환하면 컴파일은 멀쩡히 지나가고
// 실행 중에 ClassCastException(형변환 실패 오류)이 터져요 — 가장 늦게 들키는 사고죠.
public class ObjectBox {

    // 무엇이든 담을 수 있도록 Object 로 받아요. 그래서 타입 정보를 잃어버려요.
    private Object item;

    // 담기 — 어떤 객체든 그대로 들어가요.
    public void set(Object item) {
        this.item = item;
    }

    // 꺼내기 — Object 로 나와요. 쓰려면 우리가 직접 형변환을 해야 해요.
    public Object get() {
        return item;
    }

    public static void main(String[] args) {
        ObjectBox box = new ObjectBox();
        box.set("minji");

        // 꺼낼 때 (String) 형변환이 꼭 필요해요. 안 그러면 String 의 기능을 못 써요.
        String username = (String) box.get();
        System.out.println("꺼낸 이름: " + username + " (길이 " + username.length() + ")");

        // 여기서 사고가 나요 — 안에는 String 이 들었는데 Integer 로 형변환하면
        // 컴파일은 통과하지만 실행 중에 ClassCastException 이 터져요.
        try {
            Integer wrong = (Integer) box.get();
            System.out.println(wrong);
        } catch (ClassCastException e) {
            System.out.println("형변환 실패! 안에는 String 이 들었는데 Integer 로 꺼내려 했어요.");
        }
    }
}
