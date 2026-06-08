package com.instagram.javabasic.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// com/instagram/javabasic/generic/Repository.java
// 제네릭을 종합해 만든 "만능 저장소" 예요. Repository(저장소) 는 객체를 id 로 보관하고 꺼내는 창고예요.
// 클래스 옆 <T> 덕분에 한 번만 만들어 두면, 회원 저장소·게시물 저장소를 따로 짤 필요가 없어요.
//   new Repository<Member>() 면 회원 창고, new Repository<Post>() 면 게시물 창고가 돼요.
// 내부는 Map<Long, T> — "번호표(Long id) → 물건(T)" 짝으로 보관해요.
// 없는 id 를 찾으면 null 을 슬쩍 돌려주지 않고 예외를 던져 "없다" 를 분명히 알려요
// (예외를 제대로 다루는 방법은 다음 시간에 본격적으로 배워요).
public class Repository<T> {

    // 번호표(id) 를 키로, T 타입 객체를 값으로 보관하는 사물함이에요.
    private final Map<Long, T> store = new HashMap<>();

    // 저장 — id 번호표를 붙여 객체를 넣어요. 같은 id 면 덮어써요.
    public void save(Long id, T value) {
        store.put(id, value);
    }

    // 조회 — id 로 객체를 찾아 T 타입 그대로 돌려줘요(형변환 불필요).
    // 없으면 null 대신 예외를 던져 "그 번호는 없어요" 를 분명히 알려요.
    public T findById(Long id) {
        T found = store.get(id);
        if (found == null) {
            throw new IllegalArgumentException("id " + id + " 에 해당하는 항목이 없어요.");
        }
        return found;
    }

    // 전체 목록 — 보관 중인 모든 객체를 리스트로 모아 돌려줘요.
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    // 보관 개수 — 지금 몇 개가 들어 있는지 알려줘요.
    public int count() {
        return store.size();
    }
}
