package kr.hs.dgsw.cns.global.util;

import kr.hs.dgsw.cns.global.dto.FileRequest;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

public interface FileTransfer {

    /**
     * 인자값으로 들어온 경로로 파일을 전송합니다.
     * 대상 파일이 이미 존재하는 경우 삭제될 수 있습니다.
     * <p/>
     * 대상 파일이 파일 시스템에서 이동된 경우, 
     * 이후에 이 작업을 다시 호출할 수 없습니다.
     * 따라서 모든 스토리지 메커니즘을 사용하려면 이 메서드를 한 번만 호출하는 것을
     * 권장합니다.
     * <p/>
     * @param dest 저장할 파일 (일반적으로 절대 경로)
     * @throws IOException 파일 혹은 스트림을 읽는 도중 발생할 수 있습니다.
     * @throws IllegalStateException 파일이 이미 옮겨졌을 때 발생합니다.
     */
    void transferTo(FileRequest file, Path dest) throws IOException, IllegalStateException;

    default void write(final File output, final File file) throws Exception {
        if (file.exists() && !file.delete()) {
            throw new FileAlreadyExistsException(file.getName());
        }

        if (!output.renameTo(file)) {
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(output));
                out = new BufferedOutputStream(new FileOutputStream(file));
                FileUtils.copy(in, out);
            } finally {
                FileUtils.closeQuietly(in);
                FileUtils.closeQuietly(out);
            }
        }
    }
}
