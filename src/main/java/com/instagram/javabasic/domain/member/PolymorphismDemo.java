package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/PolymorphismDemo.java
// 다형성(Polymorphism) 한 덩어리를 손으로 만져보는 데모 클래스예요.
// 지난 시간엔 AdminMember/PremiumMember 가 Member 를 물려받아(extends) 점수 계산을
// 각자 다르게 오버라이딩했어요. 이번 시간엔 "부모 타입 변수 하나로 여러 자식을
// 똑같이 다루면, 알아서 자식 버전이 불린다" 는 다형성을 메서드별로 확인해요.
// 각 메서드는 main 에서 직접 호출해보며 결과를 눈으로 비교하기 좋게 만들었어요.
public class PolymorphismDemo {

    // ===== 1. 업캐스팅(Upcasting) =====
    // 자식 객체(AdminMember)를 부모 타입(Member) 변수에 담는 걸 업캐스팅이라고 해요.
    // 형 변환을 따로 쓰지 않아도 "자식은 부모의 한 종류" 라서 자동으로 담겨요.

    // 부모 타입 변수로도 부모가 가진 메서드(getUsername)는 그대로 부를 수 있어요.
    public String upcastAndGetUsername(Member member) {
        // admin 을 Member 자리에 그냥 담아요 — 이게 업캐스팅
        return member.getUsername();
    }

    // 부모 타입 변수로 calculateRecommendScore() 를 불러도,
    // 실제 객체가 AdminMember 면 +50 된 자식 버전이 불려요 (다음 항목의 동적 디스패치).
    public int upcastAndGetScore(Member member) {
        return member.calculateRecommendScore();
    }

    // ===== 2. 동적 디스패치(Dynamic Dispatch) =====
    // 부모 타입 배열에 여러 자식을 섞어 담고 같은 메서드를 불러도,
    // 실행되는 건 "변수의 타입" 이 아니라 "실제 객체의 타입" 의 버전이에요.

    // 배열의 각 회원 점수를 모아서 돌려줘요. 같은 호출이지만 타입마다 다른 결과가 나와요.
    public int[] collectScores(Member[] members) {
        int[] scores = new int[members.length];
        for (int i = 0; i < members.length; i++) {
            // members[i] 의 선언 타입은 Member 지만, 실제 객체 타입의 오버라이딩 버전이 불려요
            scores[i] = members[i].calculateRecommendScore();
        }
        return scores;
    }

    // 배열 전체 점수의 합 — 각 요소가 자기 타입의 버전으로 계산돼요.
    public int totalScore(Member[] members) {
        int total = 0;
        for (Member member : members) {
            total = total + member.calculateRecommendScore();
        }
        return total;
    }

    // ===== 3. instanceof 타입 판별 =====
    // 부모 타입으로 받은 객체가 실제로 어떤 자식인지 instanceof 로 물어볼 수 있어요.

    // 실제 타입을 사람이 읽기 좋은 이름으로 돌려줘요.
    public String describeType(Member member) {
        if (member instanceof AdminMember) {
            return "관리자";
        } else if (member instanceof PremiumMember) {
            return "프리미엄 회원";
        } else {
            return "일반 회원";
        }
    }

    // 이 회원이 관리자인지 true/false 로 알려줘요.
    public boolean isAdmin(Member member) {
        return member instanceof AdminMember;
    }

    // ===== 4. 다운캐스팅 + 향상된 instanceof 패턴 변수 (JDK 16+) =====
    // 부모 타입 변수를 다시 자식 타입으로 내려받는 걸 다운캐스팅이라고 해요.
    // instanceof 로 먼저 확인하고, 통과하면 그 자리에서 admin 변수로 바로 받아쓰는
    // "향상된 instanceof 패턴" 을 쓰면 검사와 형 변환을 한 줄로 안전하게 처리해요.

    // 관리자일 때만 자식 전용 메서드 deletePost 를 불러요.
    public String safeDeletePost(Member member, String postId) {
        // member 가 AdminMember 면, 검사 통과와 동시에 admin 변수로 내려받아요
        if (member instanceof AdminMember admin) {
            return admin.deletePost(postId);
        }
        return "관리자만 게시물을 삭제할 수 있어요.";
    }

    // 프리미엄일 때만 자식 전용 메서드 isAdProtected 를 읽어요.
    public boolean checkAdProtected(Member member) {
        if (member instanceof PremiumMember premium) {
            return premium.isAdProtected();
        }
        return false;
    }

    // ===== 5. ClassCastException 안전성 대비 =====
    // instanceof 검사 없이 무턱대고 (AdminMember) 로 강제 캐스팅하면,
    // 실제 객체가 관리자가 아닐 때 ClassCastException 이 터져요.
    // 위 safeDeletePost 처럼 instanceof 를 먼저 보는 게 안전한 형 변환 패턴이에요.

    // 일부러 위험하게 — 검사 없이 바로 캐스팅해요. 관리자가 아니면 예외가 터져요.
    public String unsafeDeletePost(Member member, String postId) {
        // 검사를 건너뛰었기 때문에, member 가 관리자가 아니면 이 줄에서 ClassCastException 발생
        AdminMember admin = (AdminMember) member;
        return admin.deletePost(postId);
    }
}
