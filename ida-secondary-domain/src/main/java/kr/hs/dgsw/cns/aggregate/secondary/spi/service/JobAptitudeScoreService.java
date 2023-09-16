package kr.hs.dgsw.cns.aggregate.secondary.spi.service;

import kr.hs.dgsw.cns.global.dto.FileRequest;

import java.io.OutputStream;

public interface JobAptitudeScoreService {
    void uploadJobAptitude(FileRequest request);

    void getJobAptitude(OutputStream outputStream);

}
