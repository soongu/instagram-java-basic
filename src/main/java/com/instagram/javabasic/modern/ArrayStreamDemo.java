package com.instagram.javabasic.modern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// com/instagram/javabasic/modern/ArrayStreamDemo.java
// 컬렉션 말고도 흐름을 시작하는 길이 두 개 더 있어요.
// (1) 배열은 Arrays.stream(배열) 로 흐름을 만들어요.
// (2) 숫자 범위(0,1,2,3...)는 IntStream.range / rangeClosed 로 흐름을 만들어요.
public class ArrayStreamDemo {

    // 배열 → 흐름 → 리스트. 배열엔 .stream() 이 없어서 Arrays.stream() 으로 감싸요.
    public static List<String> fromArray(String[] names) {
        return Arrays.stream(names).toList();
    }

    // IntStream.range(0, n) 은 0, 1, ... n-1 (끝 제외). .boxed() 로 int 를 Integer 흐름으로 바꿔 모아요.
    public static List<Integer> indexes(int n) {
        return IntStream.range(0, n).boxed().toList();
    }

    // IntStream.rangeClosed(1, n) 은 1, 2, ... n (끝 포함). 순위 라벨로 바꿔봐요.
    public static List<String> rankLabels(int n) {
        return IntStream.rangeClosed(1, n)
                .mapToObj(i -> i + "위")
                .toList();
    }

    public static void main(String[] args) {
        String[] names = {"minji", "jaehoon", "seungwoo"};
        System.out.println("배열에서 시작한 흐름 → " + fromArray(names));  // [minji, jaehoon, seungwoo]
        System.out.println("0부터 5 직전까지 → " + indexes(5));            // [0, 1, 2, 3, 4]
        System.out.println("1위부터 3위까지 → " + rankLabels(3));          // [1위, 2위, 3위]
    }
}
