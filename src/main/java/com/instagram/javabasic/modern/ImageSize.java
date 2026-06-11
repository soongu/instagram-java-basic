package com.instagram.javabasic.modern;

// com/instagram/javabasic/modern/ImageSize.java
// 위 OldImageSize 와 "똑같은 일" 을 하는 객체예요. 그런데 단 한 줄이에요.
// record 라고 적고, 괄호 안에 담을 값만 나열하면 —
// 생성자·접근자·equals·hashCode·toString 을 자바가 알아서 다 만들어줘요.
public record ImageSize(int width, int height) {
}
