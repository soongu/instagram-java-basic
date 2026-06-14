package com.instagram.javabasic.design.behavioral.template;

// com/instagram/javabasic/design/behavioral/template/PlainProfileExporter.java

// 프로필을 "꾸밈없는 일반 텍스트" 로 내보내는 자식이에요.
// 큰 흐름(머리말→본문→꼬리말)은 부모(ProfileExporter)가 정해뒀으니, 빈칸 둘만 채워요.
public class PlainProfileExporter extends ProfileExporter {

    @Override
    protected String header(String username) {
        return "[" + username + "]";
    }

    @Override
    protected String body(String username) {
        return username + "님, 환영해요";
    }

    // footer 는 부모 것을 그대로 물려받아 써요 — "— Instagram"
}
