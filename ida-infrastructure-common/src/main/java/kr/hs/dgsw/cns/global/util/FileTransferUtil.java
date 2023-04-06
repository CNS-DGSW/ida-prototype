package kr.hs.dgsw.cns.global.util;

import kr.hs.dgsw.cns.global.dto.FileRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileTransferUtil implements FileTransfer {

    @Override
    public void transferTo(FileRequest file, Path dest) throws IOException,
            IllegalStateException {
        FileUtils.copy(file.getInputStream(), Files.newOutputStream(dest));
    }
}
