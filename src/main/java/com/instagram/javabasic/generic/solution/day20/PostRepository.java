package com.instagram.javabasic.generic.solution.day20;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.generic.Repository;

// com/instagram/javabasic/generic/solution/day20/PostRepository.java
// 만능 저장소(Repository<T>) 의 T 자리에 Post 를 고정한 "게시물 전용 저장소" 예요.
//   class PostRepository extends Repository<Post> 한 줄이면,
//   save·findById·findAll·count 같은 공통 기능을 그대로 물려받아 게시물용으로 바로 써요.
// 앞서 본 MemberRepository(회원 전용)와 똑같은 방식이라,
// 제네릭 하나가 회원·게시물 저장소를 한 코드에서 찍어내는 셈이에요.
// 게시물만의 편의 기능(작성자 이름으로 모아 보기)은 여기서 더 얹어요.
public class PostRepository extends Repository<Post> {

    // 게시물 저장소만의 편의 기능 — 작성자 이름(username)으로 그 사람의 글만 모아요.
    // 물려받은 findAll() 로 전체를 훑어 작성자가 같은 게시물만 새 리스트에 담아 돌려줘요.
    // 한 명도 없으면 빈 리스트를 돌려줘요(예외를 던지지 않아요 — "검색 결과 없음" 은 정상이니까요).
    public List<Post> findByWriter(String username) {
        List<Post> result = new ArrayList<>();
        List<Post> all = findAll();
        for (Post p : all) {
            if (p.getAuthorName() != null && p.getAuthorName().equals(username)) {
                result.add(p);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PostRepository repo = new PostRepository();
        repo.save(1L, new Post("첫 게시물", "minji", 10));
        repo.save(2L, new Post("점심 인증", "jaehoon", 5));
        repo.save(3L, new Post("저녁 노을", "minji", 8));

        System.out.println("전체 게시물 수: " + repo.count());                     // 3
        System.out.println("minji 의 글 개수: " + repo.findByWriter("minji").size()); // 2
        System.out.println("jaehoon 의 글 개수: " + repo.findByWriter("jaehoon").size()); // 1
        System.out.println("없는 작성자(seoyeon) 의 글 개수: " + repo.findByWriter("seoyeon").size()); // 0
    }
}
