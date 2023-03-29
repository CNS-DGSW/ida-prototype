package kr.hs.dgsw.cns.aggregate.applicant.domain;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade.GedGrade;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class GedScore extends Score {

    private final List<GedGrade> gedScores;

    public GedScore(Long id) {
        super(id);
        this.gedScores = new LinkedList<>();
    }

    public GedScore(Long id, List<GedGrade> gedScores) {
        super(id);
        this.gedScores = gedScores;
    }
}
