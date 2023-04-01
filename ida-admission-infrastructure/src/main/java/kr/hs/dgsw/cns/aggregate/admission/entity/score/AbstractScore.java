package kr.hs.dgsw.cns.aggregate.admission.entity.score;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorColumn
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractScore {

    @Id
    @GeneratedValue
    @Column(name = "score_id")
    private Long id;

}
