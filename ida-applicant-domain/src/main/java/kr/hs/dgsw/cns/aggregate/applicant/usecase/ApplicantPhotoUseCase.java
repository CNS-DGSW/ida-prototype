package kr.hs.dgsw.cns.aggregate.applicant.usecase;

import kr.hs.dgsw.cns.aggregate.applicant.dto.photo.PhotoResponse;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.CommandApplicantSpi;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.QueryApplicantSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import kr.hs.dgsw.cns.global.util.FileTransfer;
import kr.hs.dgsw.cns.global.util.FileUtils;
import kr.hs.dgsw.cns.global.util.IdGenerator;
import lombok.RequiredArgsConstructor;

import java.io.File;

@UseCase
@RequiredArgsConstructor
public class ApplicantPhotoUseCase {

    private final QueryApplicantSpi queryApplicantSpi;
    private final CommandApplicantSpi commandApplicantSpi;
    private final FileTransfer fileTransfer;

    public void updatePhoto(MemberId memberId, FileRequest fileRequest) throws Exception {
        // hard-coded
        saveFile("images/", fileRequest);
    }

    public PhotoResponse findIdPhoto(MemberId memberId) {
        return null;
    }

    private String saveFile(String path, FileRequest request) throws Exception {
        final String absolutePath = new File("").getAbsolutePath() + "/";
        File file = new File(path);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }

        final String extension = FileUtils.checkImageContent(request.getContentType());
        final String filename = IdGenerator.generateUUIDWithString() + extension;

        final String pathname = absolutePath + path + filename;
        fileTransfer.transferTo(request, new File(pathname).toPath());
        return pathname;
    }
}
