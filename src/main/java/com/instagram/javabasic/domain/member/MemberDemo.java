package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/MemberDemo.java
// 지난 시간엔 추천 점수 계산이 MemberDemo 안의 static 메서드였는데,
// 이번엔 그 계산을 Member 객체 스스로가 하도록 옮겼어요 (member.calculateRecommendScore()).
// 또 필드가 private 으로 숨겨졌기 때문에, 값을 꺼낼 땐 getter(member.getFollowers() 등)를 써요.
// 같은 데이터 → 같은 결과가 나오도록 가중치와 등급 경계는 그대로 유지했어요.
public class MemberDemo {

    public static void main(String[] args) {
        // 추천 사용자 6명 — 이제 한 명이 Member 객체 하나예요 (지난 시간 평행 배열과 같은 데이터)
        Member[] members = {
                new Member("jaehoon_dev", 1240, 42, 8, 120),
                new Member("minji_cafe", 8500, 150, 23, 365),
                new Member("seungwoo", 320, 12, 2, 30),
                new Member("soyeon_art", 4100, 88, 15, 210),
                new Member("wooseok99", 15800, 320, 40, 500),
                new Member("hayoung_food", 2300, 67, 11, 95)
        };

        if (args.length == 0) {
            // 검색어가 없으면 — 전체 목록 + 추천 목록을 보여줘요 (기본 동작)
            System.out.println("=== 전체 사용자 목록 ===");
            printAllMembers(members);

            System.out.println();
            System.out.println("=== 팔로워 상위 3명 ===");
            int[] topIndexes = findTopFollowers(members, 3);
            for (int rank = 0; rank < topIndexes.length; rank++) {
                int idx = topIndexes[rank];
                System.out.println((rank + 1) + "위  @" + members[idx].getUsername()
                        + "  (팔로워 " + formatFollowers(members[idx].getFollowers()) + ")");
            }

            System.out.println();
            System.out.println("=== 추천 점수 & 등급 ===");
            printRecommendations(members);
        } else {
            // 검색어가 있으면 — args[0] 을 이름으로 찾아 상세 정보를 보여줘요
            String target = args[0];
            int idx = searchMemberByName(members, target);
            if (idx == -1) {
                System.out.println("'" + target + "' 사용자를 찾을 수 없어요.");
                System.out.println("등록된 사용자: ");
                for (int i = 0; i < members.length; i++) {
                    System.out.println("  - " + members[i].getUsername());
                }
            } else {
                Member member = members[idx];
                int score = member.calculateRecommendScore();
                System.out.println("=== @" + member.getUsername() + " 상세 정보 ===");
                System.out.println("팔로워   : " + formatFollowers(member.getFollowers()));
                System.out.println("게시물   : " + member.getPosts() + "개");
                System.out.println("함께 아는 친구: " + member.getMutualFriends() + "명");
                System.out.println("활동 일수 : " + member.getDaysActive() + "일");
                System.out.println("추천 점수 : " + score + "점 → " + member.grade());
            }
        }
    }

    // 1000 이상이면 "1.2K" 처럼 줄여서 보여줘요 — 지난 시간 그대로
    static String formatFollowers(int count) {
        if (count >= 1_000) {
            return (count / 1_000) + "." + ((count / 100) % 10) + "K";
        }
        return String.valueOf(count);
    }

    // 전체 사용자를 한 명씩 순회하며 출력 — 필드가 private 이라 getter 로 꺼내요
    static void printAllMembers(Member[] members) {
        for (int i = 0; i < members.length; i++) {
            Member member = members[i];
            System.out.println("@" + member.getUsername()
                    + "  팔로워 " + formatFollowers(member.getFollowers())
                    + "  게시물 " + member.getPosts() + "개");
        }
    }

    // 이름으로 사용자를 찾아 인덱스를 돌려줘요. 없으면 -1.
    static int searchMemberByName(Member[] members, String target) {
        for (int i = 0; i < members.length; i++) {
            if (members[i].getUsername().equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // 팔로워가 가장 많은 상위 n명의 "인덱스" 를 배열로 돌려줘요.
    // "가장 큰 값의 위치를 n번 골라내는" 방식 — 지난 시간 알고리즘 그대로예요.
    static int[] findTopFollowers(Member[] members, int n) {
        int[] result = new int[n];
        boolean[] used = new boolean[members.length];  // 이미 뽑은 위치는 다시 안 고르도록 표시

        for (int rank = 0; rank < n; rank++) {
            int maxIndex = -1;
            for (int i = 0; i < members.length; i++) {
                if (used[i]) {
                    continue;  // 이미 뽑힌 사람은 건너뛰어요
                }
                if (maxIndex == -1 || members[i].getFollowers() > members[maxIndex].getFollowers()) {
                    maxIndex = i;
                }
            }
            result[rank] = maxIndex;
            used[maxIndex] = true;  // 방금 1등으로 뽑았으니 다음 번엔 제외
        }
        return result;
    }

    // 종합 메서드 — 점수도 등급도 이제 Member 객체 스스로 계산해요
    static void printRecommendations(Member[] members) {
        for (int i = 0; i < members.length; i++) {
            Member member = members[i];
            int score = member.calculateRecommendScore();
            String grade = member.grade();
            System.out.println("@" + member.getUsername()
                    + "  (팔로워 " + formatFollowers(member.getFollowers()) + ")"
                    + "  점수 " + score + "점"
                    + "  →  " + grade);
        }
    }
}
