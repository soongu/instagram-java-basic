package com.instagram.javabasic.design.behavioral.template;

// com/instagram/javabasic/design/behavioral/template/MarkdownProfileExporter.java

// 같은 프로필을 "마크다운 형식" 으로 내보내는 자식이에요.
// 순서는 손대지 않고(부모가 잠갔어요), 머리말·본문을 마크다운 모양으로만 다르게 채워요.
public class MarkdownProfileExporter extends ProfileExporter {

    @Override
    protected String header(String username) {
        return "## " + username;
    }

    @Override
    protected String body(String username) {
        return "> " + username + "님, 환영해요";
    }
}
