package kr.hs.dgsw.cns.aggregate.admission.usecase.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.dto.document.DocumentResponse;
import kr.hs.dgsw.cns.aggregate.admission.dto.document.UpdateDocumentRequest;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.CommandAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DocumentUseCase {

    private final QueryAdmissionSpi queryAdmissionSpi;
    private final CommandAdmissionSpi commandAdmissionSpi;

    public void updateDocument(Long id, UpdateDocumentRequest documentRequest) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        admission.updateDocument(documentRequest.toDomainVO());
        commandAdmissionSpi.save(admission);
    }

    public DocumentResponse findDocument(Long id) {
        return new DocumentResponse(queryAdmissionSpi.findById(id)
                .orElseThrow().getDocument());
    }
}
