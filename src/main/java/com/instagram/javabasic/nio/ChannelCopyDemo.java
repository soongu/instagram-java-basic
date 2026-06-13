package com.instagram.javabasic.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// com/instagram/javabasic/nio/ChannelCopyDemo.java
// 채널(Channel)과 버퍼(Buffer)는 nio 의 진짜 엔진이에요.
//
// 지난 시간 스트림은 데이터가 "한 방향으로 한 바이트씩" 흘렀어요.
// 채널은 "버퍼(통)" 를 사이에 두고 데이터를 덩어리째 양방향으로 옮겨요. 그래서 큰 파일에 빠르고 효율적이에요.
//
//   FileChannel.transferTo : 채널끼리 직접 옮겨 달라고 운영체제에 통째로 맡겨요. (가장 빠른 복사)
//   ByteBuffer             : 데이터를 잠깐 담는 통. read 로 채우고 → flip 으로 방향을 바꾸고 → write 로 비워요.
public class ChannelCopyDemo {

    // 채널끼리 통째로 옮겨요. 큰 미디어 파일을 복사할 때 가장 빠른 길이에요.
    public void copyViaChannel(Path src, Path dest) throws IOException {
        try (FileChannel in = FileChannel.open(src, StandardOpenOption.READ);
                FileChannel out = FileChannel.open(dest,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING)) {
            in.transferTo(0, in.size(), out);
        }
    }

    // 버퍼에 담아 한 덩어리씩 옮기는 모습. 채널이 속으로 하는 일을 직접 풀어 본 거예요.
    public void copyViaBuffer(Path src, Path dest) throws IOException {
        try (FileChannel in = FileChannel.open(src, StandardOpenOption.READ);
                FileChannel out = FileChannel.open(dest,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);   // 1KB 짜리 통
            while (in.read(buffer) != -1) {                  // 통을 채워요
                buffer.flip();                               // 채우기 → 비우기 모드로 전환
                out.write(buffer);                           // 통을 비워 파일에 써요
                buffer.clear();                              // 다시 채울 준비
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChannelCopyDemo demo = new ChannelCopyDemo();
        Path src = Files.createTempFile("video-", ".mp4");
        Files.writeString(src, "큰 동영상 데이터라고 상상해요".repeat(100));

        Path dest = Files.createTempFile("video-copy-", ".mp4");
        demo.copyViaChannel(src, dest);

        System.out.println("원본 용량 → " + Files.size(src));
        System.out.println("복사본 용량 → " + Files.size(dest));
        System.out.println("내용 같나? → " + Files.readString(src).equals(Files.readString(dest)));
    }
}
