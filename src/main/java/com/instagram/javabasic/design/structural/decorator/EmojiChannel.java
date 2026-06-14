package com.instagram.javabasic.design.structural.decorator;

// com/instagram/javabasic/design/structural/decorator/EmojiChannel.java

import com.instagram.javabasic.design.ocp.NotificationChannel;

// 안쪽 채널이 만든 결과 앞에 알림 이모지(🔔) 한 겹을 덧입히는 데코레이터예요.
// 감싸기의 뼈대(ChannelDecorator)를 그대로 물려받아서, send 만 채워 줘요.
public class EmojiChannel extends ChannelDecorator {

    // 어떤 채널을 감쌀지 받아서, 위 뼈대(부모)에게 넘겨요.
    public EmojiChannel(NotificationChannel wrapped) {
        super(wrapped);
    }

    @Override
    public String send(String message) {
        // 먼저 안쪽 채널에게 보내게 시키고, 그 결과 앞에 이모지 한 겹을 덧입혀요.
        return "🔔 " + wrapped.send(message);
    }
}
