package com.instagram.javabasic.design.structural.decorator;

// com/instagram/javabasic/design/structural/decorator/ChannelDecorator.java

import com.instagram.javabasic.design.ocp.NotificationChannel;

// "감싸기" 의 뼈대가 되는 클래스예요.
// 자기도 NotificationChannel 약속을 지키면서, 안에 또 다른 NotificationChannel 을 하나 품어요.
// 그래서 안쪽 채널이 만든 결과에 한 겹을 덧입혀서 내보낼 수 있어요.
// 정작 무엇을 덧입힐지(send 본문)는 비워 두고, 자식 클래스가 채워요.
public abstract class ChannelDecorator implements NotificationChannel {

    // 내가 감싸고 있는 안쪽 채널이에요. 한 번 정해지면 바뀌지 않아서 final 로 둬요.
    protected final NotificationChannel wrapped;

    // 만들 때 "무엇을 감쌀지" 를 외부에서 받아요.
    // 안쪽이 또 다른 데코레이터여도 상관없어서, 겹겹이 감쌀 수 있어요.
    protected ChannelDecorator(NotificationChannel wrapped) {
        this.wrapped = wrapped;
    }

    // send 본문은 일부러 비워 둬요 — 한 겹을 어떻게 덧입힐지는 자식이 정해요.
}
