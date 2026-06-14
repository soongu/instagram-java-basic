package com.instagram.javabasic.design.behavioral.template;

// com/instagram/javabasic/design/behavioral/template/ProfileExporter.java

// 프로필을 글로 "내보내는" 큰 흐름의 골격이에요.
// 머리말 → 본문 → 꼬리말 순서는 부모가 정해 고정하고, 그 안의 머리말·본문 내용만 자식이 채워요.
// 순서(틀) 자체는 자식이 못 바꾸게 export 를 final 로 잠가요 — 이게 "골격은 고정, 빈칸만 교체" 예요.
public abstract class ProfileExporter {

    // 템플릿 메서드 — 내보내는 "순서" 를 부모가 한 번 정해 잠가요(final).
    // 머리말·본문은 빈칸이라 자식이 채우고, 꼬리말은 모두 같아서 부모가 정해요.
    public final String export(String username) {
        return header(username) + "\n" + body(username) + "\n" + footer();
    }

    // 빈칸 — 머리말은 형식마다 다르니 자식이 채워요.
    protected abstract String header(String username);

    // 빈칸 — 본문도 형식마다 다르니 자식이 채워요.
    protected abstract String body(String username);

    // 공통 단계 — 꼬리말은 어느 형식이든 똑같아서 부모가 한 번만 정해 물려줘요.
    protected String footer() {
        return "— Instagram";
    }
}
