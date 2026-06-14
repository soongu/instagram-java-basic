package com.instagram.javabasic.design.behavioral.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfileExporterTest {

    @Test
    @DisplayName("일반 텍스트 형식은 고정된 순서로 프로필을 조립한다")
    void plainFollowsSkeleton() {
        ProfileExporter exporter = new PlainProfileExporter();

        assertEquals("[jaehoon]\njaehoon님, 환영해요\n— Instagram", exporter.export("jaehoon"));
    }

    @Test
    @DisplayName("마크다운 형식은 같은 순서에 머리말·본문만 다르게 채운다")
    void markdownFollowsSkeleton() {
        ProfileExporter exporter = new MarkdownProfileExporter();

        assertEquals("## minji\n> minji님, 환영해요\n— Instagram", exporter.export("minji"));
    }

    @Test
    @DisplayName("형식이 달라도 꼬리말은 부모가 정한 공통 단계라 똑같다")
    void sharesFooterStep() {
        String plain = new PlainProfileExporter().export("a");
        String markdown = new MarkdownProfileExporter().export("b");

        assertTrue(plain.endsWith("— Instagram"));
        assertTrue(markdown.endsWith("— Instagram"));
    }
}
