package com.instagram.javabasic.nio.solution.day39;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

// com/instagram/javabasic/nio/solution/day39/FolderBackup.java
// 과제 3 (심화): 업로드 폴더를 백업 폴더로 통째로 복사하기.
//
// Files.walk 로 원본을 훑으며, 각 항목이 원본에서 떨어진 만큼(relativize) 백업 폴더 아래의
// 대응 경로(resolve)를 계산해요. 폴더면 createDirectories, 파일이면 copy 로 옮겨요.
public class FolderBackup {

    public void backup(Path sourceRoot, Path backupRoot) throws IOException {
        // walk 가 연 Stream 을 먼저 리스트로 받아 닫아요. (복사 중 파일 시스템을 계속 열어두지 않게)
        List<Path> all;
        try (Stream<Path> paths = Files.walk(sourceRoot)) {
            all = paths.toList();
        }

        for (Path source : all) {
            // 원본 루트에서 떨어진 상대 경로를 백업 루트 아래에 똑같이 붙여요.
            Path relative = sourceRoot.relativize(source);
            Path target = backupRoot.resolve(relative);

            if (Files.isDirectory(source)) {
                Files.createDirectories(target);
            } else {
                // 부모 폴더가 아직 없을 수 있으니 먼저 만들어요.
                Files.createDirectories(target.getParent());
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
