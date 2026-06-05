package com.instagram.javabasic.collection;

import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/collection/FollowingList.java
// 지난 시간 Member 는 팔로잉을 Member[] following + followingCount 로 직접 안고 있었어요.
// 그 방식의 한계 세 가지(가득 참 / 중간 삭제 시 직접 당겨오기 / 카운터 손수 관리)를
// 이제 List<String> 하나로 깔끔하게 졸업해요.
//   1) add 만 하면 알아서 늘어나 — 크기 제한 없음(가득 참 사라짐)
//   2) remove 가 빈칸을 알아서 메워 줘 — 당겨오기 코드 필요 없음
//   3) size() 가 인원을 알아서 세 줘 — 카운터 필드 필요 없음
public class FollowingList {

    // 카운터 없이 List 하나면 끝. 처음 크기를 정하지 않아도 돼요.
    private final List<String> following = new ArrayList<>();

    // 팔로우 추가 — 가득 찰 걱정이 없어요. 그냥 add.
    public void addFollowing(String username) {
        following.add(username);
    }

    // 언팔로우 — "값" 으로 빼면 빈칸 없이 알아서 메워져요(당겨오기 자동).
    public void removeFollowing(String username) {
        following.remove(username);
    }

    // 인원 수 — 우리가 세지 않아도 size() 가 알아서 알려줘요.
    public int getFollowingCount() {
        return following.size();
    }

    // 그 사람을 팔로우 중인지 — contains 한 줄.
    public boolean isFollowing(String username) {
        return following.contains(username);
    }

    public static void main(String[] args) {
        FollowingList me = new FollowingList();
        me.addFollowing("minji");
        me.addFollowing("seungwoo");
        me.addFollowing("jaehoon");
        me.addFollowing("yuna"); // 예전 배열(크기 3)이라면 여기서 막혔을 텐데, 이제 그냥 들어가요
        System.out.println("팔로잉 수: " + me.getFollowingCount());

        me.removeFollowing("seungwoo"); // 중간 삭제 — 빈칸 없이 알아서 메워짐
        System.out.println("seungwoo 언팔 후: " + me.getFollowingCount());
        System.out.println("minji 팔로우 중? " + me.isFollowing("minji"));
        System.out.println("seungwoo 팔로우 중? " + me.isFollowing("seungwoo"));
    }
}
