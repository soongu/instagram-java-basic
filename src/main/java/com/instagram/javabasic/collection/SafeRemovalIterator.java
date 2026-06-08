package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// com/instagram/javabasic/collection/SafeRemovalIterator.java
// 명단을 "순회하면서 동시에 삭제" 하려면 조심해야 해요.
// for-each 로 돌면서 list.remove() 를 부르면 자바가 "지금 명단이 바뀌었어!" 라며
// ConcurrentModificationException(순회 중 변경 예외) 을 던져요.
// 대신 Iterator(순회 도우미) 를 직접 꺼내 iterator.remove() 를 쓰면 안전하게 지울 수 있어요.
//   Iterator 는 hasNext()(다음 있나?) → next()(다음 꺼내기) → remove()(방금 꺼낸 것 지우기) 로 동작해요.
public class SafeRemovalIterator {

    // 안전한 방법 — Iterator 로 순회하며 비활성 username 을 그 자리에서 지워요.
    public List<String> removeInactive(List<String> usernames, List<String> inactive) {
        List<String> result = new ArrayList<>(usernames);
        Iterator<String> it = result.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (inactive.contains(name)) {
                it.remove(); // 순회 도우미에게 삭제를 맡기면 안전해요
            }
        }
        return result;
    }

    // 위험한 방법 — for-each 로 돌면서 list.remove() 를 직접 불러요.
    // 이 메서드는 ConcurrentModificationException 을 던져요(일부러 보여주는 잘못된 예).
    public void unsafeRemove(List<String> usernames, List<String> inactive) {
        List<String> copy = new ArrayList<>(usernames);
        for (String name : copy) {
            if (inactive.contains(name)) {
                copy.remove(name); // 순회 중에 직접 지우면 예외가 터져요
            }
        }
    }

    public static void main(String[] args) {
        SafeRemovalIterator demo = new SafeRemovalIterator();

        List<String> all = new ArrayList<>(List.of("minji", "jaehoon", "seungwoo", "yuna"));
        List<String> inactive = List.of("jaehoon", "yuna");

        List<String> survivors = demo.removeInactive(all, inactive);
        System.out.println("정리 후 남은 사람: " + survivors); // [minji, seungwoo]

        try {
            demo.unsafeRemove(all, inactive);
        } catch (Exception e) {
            System.out.println("잘못된 방법이 던진 예외: " + e.getClass().getSimpleName());
        }
    }
}
