package com.instagram.javabasic.design.structural.decorator;

// com/instagram/javabasic/design/structural/decorator/UrgentChannel.java

import com.instagram.javabasic.design.ocp.NotificationChannel;

// 안쪽 채널이 만든 결과 뒤에 "(중요)" 표시 한 겹을 덧입히는 데코레이터예요.
// EmojiChannel 과 마찬가지로 뼈대(ChannelDecorator)를 물려받아 send 만 채워요.
public class UrgentChannel extends ChannelDecorator {

    // 어떤 채널을 감쌀지 받아서, 위 뼈대(부모)에게 넘겨요.
    public UrgentChannel(NotificationChannel wrapped) {
        super(wrapped);
    }

    @Override
    public String send(String message) {
        // 먼저 안쪽 채널에게 보내게 시키고, 그 결과 뒤에 "(중요)" 한 겹을 덧입혀요.
        return wrapped.send(message) + " (중요)";
    }
}
