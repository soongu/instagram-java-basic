package com.instagram.javabasic.collection;

// com/instagram/javabasic/collection/ArrayLimitDemo.java
// 배열 + 수동 카운터로 "팔로잉 명단" 을 직접 관리해 보는 데모예요.
// 지난 시간 Member 가 Member[] following + followingCount 로 들고 있던 그 방식인데,
// 여기서는 이름(String)만 담아 한계 세 가지를 압축해서 보여줘요.
//   1) 처음 정한 크기(3)를 넘으면 더 못 담아요.
//   2) 중간에서 한 명을 빼면 빈칸이 생기거나, 직접 한 칸씩 당겨와야 해요.
//   3) 지금 몇 명인지 따로 센 count 값을 우리가 손으로 챙겨야 해요.
public class ArrayLimitDemo {

    // 처음 정한 그릇 크기예요. 한 번 정하면 늘릴 수 없어요.
    private final String[] following = new String[3];

    // 지금 몇 명이 담겼는지 우리가 직접 세는 카운터예요.
    private int count = 0;

    // 한 명을 더해요. 그릇이 가득 차 있으면(count 가 길이에 닿으면) 더 담지 못하고 false 를 돌려줘요.
    public boolean add(String username) {
        if (count >= following.length) {
            return false; // 가득 참 — 더 못 담아요
        }
        following[count] = username;
        count++;
        return true;
    }

    // 지금 담긴 인원 수예요. 배열은 스스로 세지 못해서 우리가 count 로 챙겨요.
    public int getCount() {
        return count;
    }

    // index 번째 이름을 꺼내요.
    public String get(int index) {
        return following[index];
    }

    // 중간 한 명을 빼요. 당겨오기를 안 하면 그 자리에 빈칸(null)이 남아요.
    // 빈칸 없이 메우려면 뒤 사람들을 한 칸씩 직접 앞으로 당겨와야 해요.
    public void removeWithShift(int index) {
        for (int i = index; i < count - 1; i++) {
            following[i] = following[i + 1]; // 뒷사람을 한 칸 당겨와요
        }
        following[count - 1] = null; // 마지막 자리를 비워요
        count--;
    }

    public static void main(String[] args) {
        ArrayLimitDemo demo = new ArrayLimitDemo();
        System.out.println("minji 추가: " + demo.add("minji"));
        System.out.println("seungwoo 추가: " + demo.add("seungwoo"));
        System.out.println("jaehoon 추가: " + demo.add("jaehoon"));
        System.out.println("yuna 추가(가득 참): " + demo.add("yuna"));
        System.out.println("현재 인원: " + demo.getCount());

        demo.removeWithShift(0); // minji 제거 → 뒷사람 당겨오기
        System.out.println("0번 제거 후 인원: " + demo.getCount());
        System.out.println("0번 자리: " + demo.get(0));
    }
}
