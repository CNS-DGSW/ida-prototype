package kr.hs.dgsw.cns.aggregate.admission.usecase.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.GedScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.dto.admission.GedDto;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.CommandScoreSpi;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 검정고시 성적 조회 <br>
 * 검정고시 성적 수정 <br>
 * 성적 조회 <br>
 * 성적 전체 수정 <br>
 * 성적 수정 <br>
 * 자유 학기제 여부 조회 <br>
 * 자유 학기제 여부 수정 <br>
 */
@UseCase
@RequiredArgsConstructor
public class ScoreUseCase {

    private final QueryAdmissionSpi queryAdmissionSpi;
//    private final QueryScoreSpi queryScoreSpi;
    private final CommandScoreSpi commandScoreSpi;

    public void editGedScore(Long id, GedDto request) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (score == null) {
            score = new GedScore(null, request.getGrades());
        }
        else if (!(score instanceof GedScore)) {
            // this admission's score isn't ged
            throw new RuntimeException();
        }
        else {
            ((GedScore) score).setGedScores(request.getGrades());
        }

        commandScoreSpi.save(score);
    }

    public GedDto findGedScore(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (score == null) {
            return new GedDto(List.of());
        }
        else if (!(admission.getScore() instanceof GedScore)) {
            throw new RuntimeException();
        }

        return new GedDto(((GedScore) score).getGedScores());
    }

    // TODO: 앞으로 이 유스케이스와 성적 관련만 구현하면 끝.
    // NOTE: ged 처리는 제외
}
