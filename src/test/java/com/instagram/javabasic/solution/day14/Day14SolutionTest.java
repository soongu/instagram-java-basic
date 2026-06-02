package com.instagram.javabasic.solution.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import com.instagram.javabasic.design.ocp.EmailChannel;
import com.instagram.javabasic.design.ocp.NotificationChannel;
import com.instagram.javabasic.design.ocp.Notifier;
import com.instagram.javabasic.design.ocp.PushChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day14SolutionTest {

    // ===== 과제 1: SRP — 데이터(PostData) 와 표시(PostCard) 의 책임 분리 =====

    @Test
    @DisplayName("과제1: PostData 는 게시물 데이터를 보관만 하고 getter 로 그대로 돌려준다")
    void postData_keepsDataAndExposesViaGetters() {
        PostData data = new PostData("minji", "오늘 점심 최고", 12);

        assertEquals("minji", data.getWriter());
        assertEquals("오늘 점심 최고", data.getContent());
        assertEquals(12, data.getLikeCount());
    }

    @Test
    @DisplayName("과제1: PostCard 는 화면 표시만 맡아 한 줄 카드 문구를 만든다")
    void postCard_formatsCardLine() {
        PostCard card = new PostCard("minji", "오늘 점심 최고", 12);

        assertEquals("@minji: 오늘 점심 최고 (좋아요 12)", card.formatCard());
    }

    // ===== 과제 2: OCP — 새 KakaoChannel 을 추가해도 기존 코드는 그대로 =====

    @Test
    @DisplayName("과제2: 새 KakaoChannel 을 끼워도 Notifier 수정 없이 3채널 모두 보낸다")
    void broadcast_worksWithNewKakaoChannelWithoutModifyingNotifier() {
        Notifier notifier = new Notifier();

        // 기존 Email·Push 는 그대로 두고, 새로 만든 Kakao 만 배열에 끼워넣어요
        NotificationChannel[] channels = {
                new EmailChannel(),
                new PushChannel(),
                new KakaoChannel()
        };

        String[] results = notifier.broadcast(channels, "새 댓글이 달렸어요");

        assertEquals(3, results.length);
        assertEquals("[Email] 새 댓글이 달렸어요", results[0]);
        assertEquals("[Push] 새 댓글이 달렸어요", results[1]);
        assertEquals("[Kakao] 새 댓글이 달렸어요", results[2]);
    }

    @Test
    @DisplayName("과제2: KakaoChannel.send 가 [Kakao] 접두사를 붙인다")
    void kakaoChannel_prefixesMessage() {
        KakaoChannel kakao = new KakaoChannel();

        assertEquals("[Kakao] 안녕", kakao.send("안녕"));
    }

    // ===== 과제 3: 불변 Tag — withUse() 는 새 인스턴스를 돌려주고 원본은 안 바뀐다 =====

    @Test
    @DisplayName("과제3: withUse() 는 useCount+1 인 '새 Tag' 를 돌려준다")
    void withUse_returnsNewTagWithIncreasedCount() {
        Tag original = new Tag("여행", 5);

        Tag used = original.withUse();

        assertEquals("여행", used.getName());
        assertEquals(6, used.getUseCount());
    }

    @Test
    @DisplayName("과제3: withUse() 를 불러도 원본 Tag 는 그대로다 (불변)")
    void withUse_doesNotMutateOriginal() {
        Tag original = new Tag("여행", 5);

        Tag used = original.withUse();

        // 새 인스턴스라서 원본과 같은 객체가 아니에요
        assertNotSame(original, used);
        // 원본의 사용 횟수는 흔들리지 않고 5 그대로예요
        assertEquals(5, original.getUseCount());
    }
}
