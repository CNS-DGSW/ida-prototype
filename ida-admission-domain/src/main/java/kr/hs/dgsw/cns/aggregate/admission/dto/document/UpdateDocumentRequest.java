package kr.hs.dgsw.cns.aggregate.admission.dto.document;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UpdateDocumentRequest {

    private String introduction;
    private String studyPlan;

    public Document toDomainVO() {
        return Document.builder()
                .introduction(introduction)
                .studyPlan(studyPlan)
                .build();
    }
}
