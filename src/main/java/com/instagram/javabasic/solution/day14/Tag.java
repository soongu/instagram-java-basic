package com.instagram.javabasic.solution.day14;

// com/instagram/javabasic/solution/day14/Tag.java
// 과제 3 — 한 번 만들면 절대 바뀌지 않는 '불변(immutable)' 태그예요.
// 두 필드 모두 final 이라 생성자에서 한 번 정해지면 그 뒤로는 못 바꿔요.
// setter 가 아예 없어서 바깥에서 값을 흔들 방법이 없어요.
// 값을 바꾸고 싶으면? 원본은 그대로 두고 '새 Tag' 를 하나 더 만들어 돌려줘요.
public final class Tag {

    private final String name;
    private final int useCount;

    public Tag(String name, int useCount) {
        this.name = name;
        this.useCount = useCount;
    }

    public String getName() {
        return name;
    }

    public int getUseCount() {
        return useCount;
    }

    // 사용 횟수를 1 늘린 '새 Tag' 를 돌려줘요. 원본(this)은 전혀 건드리지 않아요.
    // 이게 불변 객체가 값을 '바꾸는' 방식이에요 — 바꾸는 게 아니라 새로 만드는 거예요.
    public Tag withUse() {
        return new Tag(name, useCount + 1);
    }

    @Override
    public String toString() {
        return "#" + name + " (" + useCount + "회 사용)";
    }
}
