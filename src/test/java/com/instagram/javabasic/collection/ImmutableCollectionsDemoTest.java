package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImmutableCollectionsDemoTest {

    @Test
    @DisplayName("List.of / Set.of / Map.of 의 내용이 기대대로 담긴다")
    void contents() {
        ImmutableCollectionsDemo demo = new ImmutableCollectionsDemo();

        List<String> tags = demo.constantTags();
        assertEquals(3, tags.size());
        assertEquals("#daily", tags.get(0));

        Set<String> roles = demo.allowedRoles();
        assertTrue(roles.contains("ADMIN"));

        Map<String, String> labels = demo.gradeLabels();
        assertEquals("강력 추천", labels.get("S"));
    }

    @Test
    @DisplayName("불변 리스트를 수정하려 하면 UnsupportedOperationException 이 터진다")
    void modifyThrows() {
        ImmutableCollectionsDemo demo = new ImmutableCollectionsDemo();
        List<String> tags = demo.constantTags();

        assertThrows(UnsupportedOperationException.class,
                () -> demo.tryModify(tags));
    }
}
