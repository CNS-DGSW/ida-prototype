package kr.hs.dgsw.cns.aggregate.admission.usecase.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.GedScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.SchoolScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.dto.admission.GedDto;
import kr.hs.dgsw.cns.aggregate.admission.dto.admission.SchoolAttendanceDto;
import kr.hs.dgsw.cns.aggregate.admission.dto.admission.SchoolScoreDto;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.CommandScoreSpi;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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
    private ScoreCalculateUseCase scoreCalculateUseCase;

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
        else if (!(score instanceof GedScore)) {
            throw new RuntimeException();
        }

        return new GedDto(((GedScore) score).getGedScores());
    }

    public SchoolScoreDto findSchoolScore(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (score == null) {
            return new SchoolScoreDto(List.of());
        }
        else if (!(score instanceof SchoolScore)) {
            throw new RuntimeException();
        }

        return new SchoolScoreDto(((SchoolScore) score).getSchoolGrades());
    }

    public void editSchoolScore(Long id, SchoolScoreDto dto) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (score == null) {
            score = new SchoolScore(null, dto.getGrades(), new ArrayList<>(), new ArrayList<>(), null);
        }
        else if (!(score instanceof SchoolScore)) {
            // this admission's score isn't ged
            throw new RuntimeException();
        }
        else {
            ((SchoolScore) score).setSchoolGrades(dto.getGrades());
        }

        commandScoreSpi.save(score);
    }

    public List<AttendancePoint> findFreeSemester(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (!(score instanceof SchoolScore schoolScore)) {
            throw new RuntimeException();
        }

        return schoolScore.getAttendancePoints();
    }

    public void editFreeSemester(Long id, SchoolAttendanceDto request) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (score == null) {
            score = new SchoolScore(null, new ArrayList<>(), request.getAttendancePoints(),
                    new ArrayList<>(), null);
        }
        else if (!(score instanceof SchoolScore)) {
            // this admission's score isn't ged
            throw new RuntimeException();
        }
        else {
            ((SchoolScore) score).setAttendancePoints(request.getAttendancePoints());
        }

        commandScoreSpi.save(score);
    }

    public void editAllScore(Long id, SchoolAttendanceDto dto) {
        scoreCalculateUseCase.calculateUpdateScore(id);
        editFreeSemester(id, dto);
    }
}
