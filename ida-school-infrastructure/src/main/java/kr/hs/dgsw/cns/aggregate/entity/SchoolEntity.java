package kr.hs.dgsw.cns.aggregate.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.entity.value.SchoolInfoVO;
import kr.hs.dgsw.cns.aggregate.entity.value.TeacherVO;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "school")
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchoolEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SchoolInfoVO schoolInfo;

    private TeacherVO teacher;
}
