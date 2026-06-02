package com.instagram.javabasic.domain.content;

// com/instagram/javabasic/domain/content/Content.java
// 인스타에 올라오는 콘텐츠의 "뼈대 부모" 예요. 이미지·영상·텍스트는 모두 콘텐츠지만,
// 종류마다 미리보기 모양이 다르죠. 그런데 "콘텐츠 자체" 만 따로 떼어 만들면(new Content(...))
// 종류를 알 수 없어 미리보기를 어떻게 보여줄지 정할 수 없어요. 그래서 이 클래스는
// abstract(추상)로 선언해요. abstract 클래스는 직접 객체를 만들 수 없고(자식만 만들 수 있어요),
// 공통으로 가질 것(작성자·좋아요)과 "자식이 반드시 채워야 할 빈칸(abstract 메서드)" 만 정해둬요.
public abstract class Content {

    // 모든 콘텐츠가 공통으로 갖는 정보 — 자식이 super(...) 로 채워요.
    // private 으로 숨기고, 자식은 부모가 열어둔 getter 로만 읽어요 (Member 와 같은 방식).
    private String authorName;   // 작성자 이름
    private int likeCount;       // 좋아요 수

    // 생성자 — abstract 클래스도 생성자를 가져요. 직접 new 는 못 하지만,
    // 자식이 super(...) 로 이 생성자를 불러 공통 필드를 채우는 통로예요.
    public Content(String authorName, int likeCount) {
        this.authorName = authorName;
        this.likeCount = likeCount;
    }

    // ===== abstract 메서드 — 본문이 없는 "빈칸" 이에요 (세미콜론으로 끝나요) =====
    // 부모는 "이런 메서드가 반드시 있어야 한다" 고 선언만 하고, 실제 내용은 자식이 채워요.
    // 자식이 이 둘을 구현하지 않으면 컴파일 에러가 나요 — 그게 abstract 의 강제력이에요.

    // 콘텐츠 종류를 글자로 — 자식마다 "이미지" / "영상" / "텍스트" 로 다르게 채워요.
    public abstract String getType();

    // 피드에 보일 짧은 미리보기 — 종류마다 보여줄 내용이 다르니 자식이 정해요.
    public abstract String preview();

    // ===== concrete 메서드 — 본문이 있는 "완성된" 메서드예요 =====

    // 좋아요 한 번 — 모든 콘텐츠가 똑같이 동작하니 부모가 한 번만 만들어 물려줘요.
    public void addLike() {
        this.likeCount++;
    }

    // 템플릿 메서드 — 피드 한 줄을 조립하는 "틀" 은 부모가 정하고,
    // 그 안에서 종류([이미지])와 미리보기 내용은 자식이 채운 getType()/preview() 에 맡겨요.
    // 자식은 render() 를 다시 만들지 않아도, 빈칸만 채우면 완성된 한 줄이 나와요.
    public String render() {
        return "[" + getType() + "] " + preview() + " (♥ " + likeCount + ")";
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
