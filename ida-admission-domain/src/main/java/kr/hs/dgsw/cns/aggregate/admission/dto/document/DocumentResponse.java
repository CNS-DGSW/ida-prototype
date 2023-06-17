package kr.hs.dgsw.cns.aggregate.admission.dto.document;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.Document;
import lombok.Getter;

@Getter
public class DocumentResponse {

    private final String introduction;
    private final String studyPlan;

    public DocumentResponse(Document document) {
        if (document == null) {
            this.introduction = null;
            this.studyPlan = null;
        }
        else {
            this.introduction = document.getIntroduction();
            this.studyPlan = document.getStudyPlan();
        }
    }
}
