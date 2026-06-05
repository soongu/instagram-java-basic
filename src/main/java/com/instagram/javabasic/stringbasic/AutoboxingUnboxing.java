package com.instagram.javabasic.stringbasic;

// com/instagram/javabasic/stringbasic/AutoboxingUnboxing.java
// 원시형(int)과 포장 클래스(Integer) 사이를 자바가 자동으로 변환해줘요.
// int → Integer 로 알아서 포장하는 걸 "오토박싱(autoboxing)" 이라고 해요.
// Integer → int 로 알아서 꺼내는 걸 "언박싱(unboxing)" 이라고 해요.
// 편하지만, 비어 있는(null) 포장을 꺼내려 하면 사고가 나요. 그것도 확인해요.
public class AutoboxingUnboxing {

    // int 값을 그냥 Integer 변수에 넣으면 자바가 알아서 포장해요(오토박싱).
    public static Integer box(int value) {
        return value;
    }

    // Integer 를 int 변수에 넣으면 자바가 알아서 값을 꺼내요(언박싱).
    public static int unbox(Integer boxed) {
        return boxed;
    }

    // 비어 있는(null) Integer 를 int 로 꺼내려 하면 NullPointerException 이 나요.
    // "꺼낼 값이 없는데 꺼내라" 고 하니 자바가 멈추는 거예요.
    public static int unboxNull() {
        Integer empty = null;
        return empty; // 여기서 언박싱하다가 NullPointerException 발생
    }

    // -128 ~ 127 범위의 작은 Integer 는 자바가 미리 만들어 캐시에 보관해 재사용해요.
    // 그래서 같은 작은 값은 == 도 true 가 돼요(같은 상자를 가리키니까요).
    public static boolean sameCachedObject(int value) {
        Integer a = value;
        Integer b = value;
        return a == b;
    }

    public static void main(String[] args) {
        Integer boxed = box(10);   // int 10 이 Integer 로 포장돼요
        int plain = unbox(boxed);  // Integer 가 int 로 풀려요

        System.out.println("오토박싱 : " + boxed);   // 10
        System.out.println("언박싱 : " + plain);     // 10

        // 캐시 범위 안(127)은 == true, 범위 밖(200)은 == false
        System.out.println("127 캐시 비교 : " + sameCachedObject(127)); // true
        System.out.println("200 캐시 비교 : " + sameCachedObject(200)); // false
        System.out.println("Integer 끼리 비교도 equals 가 안전해요.");
    }
}
