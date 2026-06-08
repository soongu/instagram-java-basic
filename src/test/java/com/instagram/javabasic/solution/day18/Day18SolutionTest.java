package com.instagram.javabasic.solution.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day18SolutionTest {

    // ---------- 과제 1: TagBoard ----------

    @Test
    @DisplayName("과제1: 같은 태그를 두 번 넣어도 한 번만 담긴다")
    void tagBoardIgnoresDuplicate() {
        TagBoard board = new TagBoard();
        board.add("seoul");
        board.add("seoul");
        board.add("food");
        assertEquals(2, board.size());
    }

    @Test
    @DisplayName("과제1: render 는 담긴 순서대로 한 줄로 그려준다")
    void tagBoardRendersOneLine() {
        TagBoard board = new TagBoard();
        board.add("seoul");
        board.add("food");
        board.add("daily");
        assertEquals("#seoul #food #daily", board.render());
    }

    @Test
    @DisplayName("과제1: 비어 있으면 render 는 빈 문자열")
    void tagBoardRendersEmpty() {
        TagBoard board = new TagBoard();
        assertEquals("", board.render());
    }

    @Test
    @DisplayName("과제1: remove 는 실제로 뺐을 때 true, 없으면 false")
    void tagBoardRemoveReturnsResult() {
        TagBoard board = new TagBoard();
        board.add("seoul");
        assertTrue(board.remove("seoul"));
        assertFalse(board.remove("seoul"));
        assertEquals(0, board.size());
    }

    // ---------- 과제 2: RankMember + RankBoard ----------

    @Test
    @DisplayName("과제2: RankMember 는 팔로워 내림차순으로 비교된다")
    void rankMemberCompareToDescending() {
        RankMember more = new RankMember("a", 8500);
        RankMember less = new RankMember("b", 1240);
        assertTrue(more.compareTo(less) < 0);
        assertTrue(less.compareTo(more) > 0);
    }

    @Test
    @DisplayName("과제2: RankMember toString 은 @이름(수) 형식")
    void rankMemberToString() {
        assertEquals("@jaehoon(1240)", new RankMember("jaehoon", 1240).toString());
    }

    @Test
    @DisplayName("과제2: top(n) 은 팔로워 많은 순으로 상위 n명을 돌려준다")
    void rankBoardTopOrdersByFollowers() {
        RankBoard board = new RankBoard();
        board.add(new RankMember("minji", 8500));
        board.add(new RankMember("jaehoon", 1240));
        board.add(new RankMember("seungwoo", 320));

        List<RankMember> top2 = board.top(2);
        assertEquals(2, top2.size());
        assertEquals("minji", top2.get(0).getUsername());
        assertEquals("jaehoon", top2.get(1).getUsername());
    }

    @Test
    @DisplayName("과제2: top(n) 은 인원보다 큰 n 이면 있는 만큼만 돌려준다")
    void rankBoardTopClampsToSize() {
        RankBoard board = new RankBoard();
        board.add(new RankMember("minji", 8500));
        board.add(new RankMember("jaehoon", 1240));

        List<RankMember> top5 = board.top(5);
        assertEquals(2, top5.size());
        assertEquals("minji", top5.get(0).getUsername());
    }

    // ---------- 과제 3: FollowUser + 명명 Comparator 2개 ----------

    @Test
    @DisplayName("과제3: UsernameOrder 는 이름 가나다순으로 정렬한다")
    void usernameOrderSortsAlphabetically() {
        List<FollowUser> users = new ArrayList<>();
        users.add(new FollowUser("seungwoo", 5));
        users.add(new FollowUser("jaehoon", 2));
        users.add(new FollowUser("minji", 9));

        Collections.sort(users, new UsernameOrder());

        assertEquals("jaehoon", users.get(0).getUsername());
        assertEquals("minji", users.get(1).getUsername());
        assertEquals("seungwoo", users.get(2).getUsername());
    }

    @Test
    @DisplayName("과제3: ActiveOrder 는 최근 활동(작은 일수) 순으로 정렬한다")
    void activeOrderSortsByRecency() {
        List<FollowUser> users = new ArrayList<>();
        users.add(new FollowUser("seungwoo", 5));
        users.add(new FollowUser("jaehoon", 2));
        users.add(new FollowUser("minji", 9));

        Collections.sort(users, new ActiveOrder());

        assertEquals("jaehoon", users.get(0).getUsername());
        assertEquals("seungwoo", users.get(1).getUsername());
        assertEquals("minji", users.get(2).getUsername());
    }

    @Test
    @DisplayName("과제3: 같은 리스트를 다른 Comparator 로 다시 정렬할 수 있다")
    void twoComparatorsOnSameList() {
        List<FollowUser> users = new ArrayList<>();
        users.add(new FollowUser("seungwoo", 5));
        users.add(new FollowUser("jaehoon", 2));

        Collections.sort(users, new UsernameOrder());
        assertEquals("jaehoon", users.get(0).getUsername());

        Collections.sort(users, new ActiveOrder());
        assertEquals("jaehoon", users.get(0).getUsername());
    }
}
