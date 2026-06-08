package com.instagram.javabasic.exceptionbasic.solution.day21;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;

// com/instagram/javabasic/exceptionbasic/solution/day21/BatchMemberLookup.java
// 회원 번호 목록을 한 번에 쭉 조회하는 연습이에요. 중간에 없는 번호가 끼어 있어도
// 거기서 멈추지 않고 끝까지 돌면서, 찾은 회원은 모으고 없는 번호는 "건너뜀" 으로 표시해요.
// 두 가지 도구를 함께 써요.
//   - try-catch : 없는 id 일 때 findById 가 던지는 IllegalArgumentException 을 그 자리에서 잡아
//                 다음 번호로 넘어가게 해요(한 건의 실패가 전체를 멈추지 못하게).
//   - finally   : try 가 성공하든 실패하든 "반드시" 실행되는 블록이에요. 여기서 시도 횟수를
//                 한 번씩 더하면, 성공·실패와 무관하게 시도 횟수가 정확히 ids 개수와 맞아요.
public class BatchMemberLookup {

    // 목록 전체 조회 — 각 id 를 차례로 찾아 성공/건너뜀을 한 줄씩 모으고,
    // 마지막에 "총 N번 시도" 를 덧붙여 한 덩어리 문자열로 돌려줘요.
    public String lookupAll(Repository<Member> repo, List<Long> ids) {
        StringBuilder result = new StringBuilder();
        int attempts = 0;   // 시도 횟수 — finally 에서 한 건마다 +1

        for (Long id : ids) {
            try {
                Member member = repo.findById(id);
                result.append("회원: ").append(member.getUsername()).append("\n");
            } catch (IllegalArgumentException e) {
                // 없는 번호 — 멈추지 않고 건너뜀으로 표시한 뒤 다음 번호로 넘어가요.
                result.append("건너뜀: id ").append(id).append("\n");
            } finally {
                // 성공이든 건너뜀이든 무조건 실행 — 그래서 시도 횟수가 빠짐없이 세져요.
                attempts++;
            }
        }

        result.append("총 ").append(attempts).append("번 시도");
        return result.toString();
    }

    public static void main(String[] args) {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 90));

        // 있는 번호와 없는 번호를 섞은 목록 — 중간 실패에도 끝까지 진행돼요.
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(999L);
        ids.add(2L);

        BatchMemberLookup lookup = new BatchMemberLookup();
        System.out.println(lookup.lookupAll(repo, ids));
    }
}
