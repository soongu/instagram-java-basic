package com.instagram.javabasic.collection;

import java.util.Comparator;

// com/instagram/javabasic/collection/UsernameComparator.java
// 또 다른 정렬 기준이에요. 이번엔 숫자가 아니라 "이름(가나다/알파벳 순)" 으로 줄을 세워요.
// 문자열 비교는 String 이 이미 가진 compareTo 에 맡기면 사전 순서를 알아서 계산해 줘요.
public class UsernameComparator implements Comparator<SortableMember> {

    @Override
    public int compare(SortableMember a, SortableMember b) {
        return a.getUsername().compareTo(b.getUsername());
    }
}
