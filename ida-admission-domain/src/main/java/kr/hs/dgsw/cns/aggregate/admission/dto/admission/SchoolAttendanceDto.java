package kr.hs.dgsw.cns.aggregate.admission.dto.admission;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class SchoolAttendanceDto {
    private List<AttendancePoint> attendancePoints;

}
