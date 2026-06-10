package com.instagram.javabasic.modern;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/LambdaIntro.java
// 같은 "팔로워 많은 순 정렬" 을 두 가지 방법으로 해봐요 — (1) 익명 클래스, (2) 람다.
// 둘 다 결과는 똑같아요. 코드가 짧아지는 걸 눈으로 확인하는 게 목적이에요.
// 지난 시간(Day 18)엔 정렬 기준 하나 때문에 Comparator 클래스 파일을 통째로 만들었죠.
// 이번엔 그 클래스를 파일 없이, 마지막엔 화살표 한 줄로 줄여요.
public class LambdaIntro {

    // (1) 익명 클래스 — 파일을 따로 만들진 않지만, new Comparator<>(){...} 가 길어요.
    public static List<Member> sortByFollowersAnonymous(List<Member> members) {
        List<Member> copy = new ArrayList<>(members);
        copy.sort(new Comparator<Member>() {
            @Override
            public int compare(Member a, Member b) {
                return b.getFollowers() - a.getFollowers();
            }
        });
        return copy;
    }

    // (2) 람다 — 위 익명 클래스에서 군더더기를 걷어내면 화살표 한 줄만 남아요.
    public static List<Member> sortByFollowersLambda(List<Member> members) {
        List<Member> copy = new ArrayList<>(members);
        copy.sort((a, b) -> b.getFollowers() - a.getFollowers());
        return copy;
    }

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        members.add(new Member("jaehoon", 1240, 42, 3, 200));
        members.add(new Member("minji", 8500, 150, 5, 365));
        members.add(new Member("seungwoo", 320, 12, 1, 90));

        System.out.println("익명 클래스 정렬: " + sortByFollowersAnonymous(members));
        System.out.println("람다 정렬:      " + sortByFollowersLambda(members));
    }
}
