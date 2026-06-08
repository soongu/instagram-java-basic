package com.instagram.javabasic.generic;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/generic/MemberRepository.java
// 만능 저장소(Repository<T>) 의 T 자리에 Member 를 고정한 "회원 전용 저장소" 예요.
//   class MemberRepository extends Repository<Member> 한 줄이면,
//   save·findById·findAll·count 같은 공통 기능을 그대로 물려받아 회원용으로 바로 써요.
// 같은 방식으로 Repository<Post> 를 물려받으면 게시물 저장소가 되니,
// 제네릭 하나가 회원·게시물 저장소를 한 코드에서 찍어내는 셈이에요.
// 회원만의 편의 기능(이름으로 찾기)은 여기서 더 얹어요.
public class MemberRepository extends Repository<Member> {

    // 회원 저장소만의 편의 기능 — username 으로 회원을 찾아요.
    // 물려받은 findAll() 로 전체를 훑어 이름이 같은 사람을 돌려줘요. 없으면 예외를 던져요.
    public Member findByUsername(String username) {
        List<Member> all = findAll();
        for (Member m : all) {
            if (m.getUsername().equals(username)) {
                return m;
            }
        }
        throw new IllegalArgumentException("username " + username + " 인 회원이 없어요.");
    }

    public static void main(String[] args) {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 3, 120));

        System.out.println("보관 회원 수: " + repo.count());                 // 2
        System.out.println("1번 회원: " + repo.findById(1L).getUsername()); // minji
        System.out.println("이름으로 찾기: " + repo.findByUsername("jaehoon").getFollowers()); // 1240
        System.out.println("전체 회원 수: " + repo.findAll().size());        // 2
    }
}
