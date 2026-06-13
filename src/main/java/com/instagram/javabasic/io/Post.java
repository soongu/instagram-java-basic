package com.instagram.javabasic.io;

import java.io.Serializable;

// com/instagram/javabasic/io/Post.java
// 파일에 저장하고 불러올 게시물 한 건이에요. id·작성자·캡션 세 가지만 담은 단순한 record 예요.
//
// 객체 직렬화(ObjectOutputStream) 로 통째로 저장하려면 Serializable 을 구현해야 해요.
// Serializable 은 "이 객체는 바이트로 바꿔 저장해도 돼요" 라는 표시(메서드 없는 약속)예요.
//
//   serialVersionUID : 저장한 모양과 불러오는 모양이 같은 버전인지 확인하는 번호예요.
//
// record 의 접근자는 id() · author() · caption() 처럼 필드 이름 그대로예요(get 접두어가 없어요).
public record Post(long id, String author, String caption) implements Serializable {

    private static final long serialVersionUID = 1L;
}
