package kr.hs.dgsw.cns.aggregate.admission.domain.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.GedGrade;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class GedScore extends Score {

    private List<GedGrade> gedScores;

    public void setGedScores(List<GedGrade> gedScores) {
        this.gedScores = gedScores;
    }

    public GedScore(ScoreId id) {
        super(id);
        this.gedScores = new LinkedList<>();
    }

    public GedScore(ScoreId id, List<GedGrade> gedScores) {
        super(id);
        this.gedScores = gedScores;
    }
}
