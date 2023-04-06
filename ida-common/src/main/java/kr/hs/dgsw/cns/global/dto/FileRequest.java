package kr.hs.dgsw.cns.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@Getter
@RequiredArgsConstructor
public class FileRequest {

    private final String contentType;

    private final String filename;

    private final InputStream inputStream;

}
