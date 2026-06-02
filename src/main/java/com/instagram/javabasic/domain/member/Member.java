package com.instagram.javabasic.domain.member;

import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/domain/member/Member.java
// 인스타 추천 사용자 한 명을 표현하는 클래스예요.
// 지난 시간엔 한 사람의 정보가 평행 배열 다섯 개에 흩어져 있었는데,
// 이제 그 다섯 가지를 한 객체 안에 묶어서 "한 명 = 한 덩어리" 로 다뤄요.
// 데이터(필드)와 생성자에 더해, 그 데이터로 무엇을 하는지(추천 점수·등급 계산)도
// 이제 객체 스스로가 담당해요. 필드는 private 으로 숨기고 getter/setter 로만 드나들어요.
// 이번 시간엔 한 사람이 "쓴 글들" 과 "팔로우하는 사람들" 을 배열로 직접 안고 있도록 넓혀요.
public class Member {

    // 묶음 배열의 처음 크기예요. 작성한 글·팔로잉을 이만큼까지 담아요.
    private static final int CAPACITY = 16;

    // 지금까지 생성된 전체 회원 수 — 객체마다 따로가 아니라 클래스에 하나뿐인 값(모든 객체가 공유)
    static int totalMembers = 0;

    // 한 사람을 이루는 다섯 가지 정보 — 이제 private 으로 숨겨 직접 접근을 막아요
    private String username;       // 사용자 이름
    private int followers;         // 팔로워 수
    private int posts;             // 게시물 수
    private int mutualFriends;     // 함께 아는 친구 수
    private int daysActive;        // 활동 일수

    // 이 사람이 쓴 글들 — 배열에 모으고, 몇 개 찼는지 카운터로 세요(1:N)
    private Post[] writtenPosts = new Post[CAPACITY];
    private int writtenPostCount = 0;

    // 이 사람이 팔로우하는 사람들 — 같은 방식(배열 + 카운터)으로 모아요
    private Member[] following = new Member[CAPACITY];
    private int followingCount = 0;

    // 기본 생성자 — 아무 값도 받지 않고 빈 객체를 만들어요.
    // 이때 필드는 각 타입의 기본값(문자열은 null, 숫자는 0)으로 채워져요.
    public Member() {
        totalMembers++;
    }

    // 매개변수 생성자 — 다섯 가지 정보를 한 번에 받아 객체를 완성해요.
    // this.username 의 this 는 "지금 만들어지는 바로 이 객체" 를 가리켜요.
    public Member(String username, int followers, int posts, int mutualFriends, int daysActive) {
        this.username = username;
        this.followers = followers;
        this.posts = posts;
        this.mutualFriends = mutualFriends;
        this.daysActive = daysActive;
        totalMembers++;
    }

    // 추천 점수 — 이제 인자 없이 자기 자신(this)의 필드를 직접 써요 (지난 시간엔 MemberDemo 밖에 있었음)
    public int calculateRecommendScore() {
        int score = 0;
        score = score + this.followers / 100;     // 팔로워 100명당 1점
        score = score + this.posts / 5;           // 게시물 5개당 1점
        score = score + this.mutualFriends * 10;  // 함께 아는 친구 1명당 10점
        score = score + this.daysActive / 30;     // 활동 30일당 1점
        return score;
    }

    // 등급 — 같은 객체의 calculateRecommendScore() 를 다시 불러서 판정해요
    public String grade() {
        int score = calculateRecommendScore();
        if (score >= 300) {
            return "강력 추천";
        } else if (score >= 150) {
            return "추천";
        } else if (score >= 70) {
            return "보통";
        } else {
            return "관심 낮음";
        }
    }

    // ===== 캡슐화: private 필드를 읽는(getter) 통로 =====
    public String getUsername() {
        return username;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPosts() {
        return posts;
    }

    public int getMutualFriends() {
        return mutualFriends;
    }

    public int getDaysActive() {
        return daysActive;
    }

    // setter — 값을 넣기 전에 검사할 수 있어요. 팔로워는 음수가 될 수 없으니 막아요.
    public void setFollowers(int followers) {
        if (followers < 0) {
            System.out.println("팔로워 수는 음수가 될 수 없어요. 0으로 설정해요.");
            this.followers = 0;
            return;
        }
        this.followers = followers;
    }

    // static 메서드 — 객체 없이 클래스 이름(Member.getTotalMembers())으로 부를 수 있어요
    public static int getTotalMembers() {
        return totalMembers;
    }

    // ===== 작성한 글 묶음(1:N) =====

    // 이 사람이 쓴 글 하나를 묶음에 더해요. 자리가 차면 더 담지 않고 넘어가요.
    public void addWrittenPost(Post p) {
        if (writtenPostCount >= writtenPosts.length) {
            System.out.println("작성 글 묶음이 가득 찼어요. 더 담지 않아요.");
            return;
        }
        writtenPosts[writtenPostCount] = p;
        writtenPostCount++;
    }

    public int getWrittenPostCount() {
        return writtenPostCount;
    }

    public Post getWrittenPost(int index) {
        return writtenPosts[index];
    }

    // ===== 팔로잉 묶음(1:N) =====

    // 다른 회원을 팔로우해요. 팔로잉 묶음에 더하고, 자리가 차면 넘어가요.
    public void follow(Member target) {
        if (followingCount >= following.length) {
            System.out.println("팔로잉 묶음이 가득 찼어요. 더 담지 않아요.");
            return;
        }
        following[followingCount] = target;
        followingCount++;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public Member getFollowing(int index) {
        return following[index];
    }

    // 이미 그 사람을 팔로우하고 있는지 — 묶음을 처음부터 훑어 equals 로 비교해요.
    // Member.equals 는 username 기준이라, 이름이 같으면 같은 사람으로 봐요.
    public boolean isFollowing(Member target) {
        for (int i = 0; i < followingCount; i++) {
            if (following[i].equals(target)) {
                return true;
            }
        }
        return false;
    }

    // toString — 객체를 그대로 출력하면 알아보기 힘든 주소(@1b6d3586)가 찍혀요.
    // 이 메서드를 새로 정의(오버라이딩)하면 우리가 원하는 사람 친화적인 글로 바뀌어요.
    // username 과 followers 는 private 이지만 같은 클래스 안이라 직접 쓸 수 있어요.
    @Override
    public String toString() {
        return "@" + username + " (팔로워 " + followers + ", 점수 " + calculateRecommendScore() + "점)";
    }

    // equals — 두 객체가 "같은 사람인가" 를 username 기준으로 비교해요.
    // 기본 동작은 메모리 주소 비교라서, 값으로 같은지 보려면 이렇게 새로 정의해요.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member other = (Member) obj;
        return username != null && username.equals(other.username);
    }
}
