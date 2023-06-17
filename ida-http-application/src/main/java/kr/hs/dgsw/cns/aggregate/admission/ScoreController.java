package kr.hs.dgsw.cns.aggregate.admission;

import kr.hs.dgsw.cns.aggregate.admission.dto.admission.GedDto;
import kr.hs.dgsw.cns.aggregate.admission.usecase.score.ScoreUseCase;
import kr.hs.dgsw.cns.global.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admission/score")
public class ScoreController {

    private final ScoreUseCase scoreUseCase;

    @PutMapping("/ged")
    public void editAllGedScore(@AuthenticationPrincipal AuthUser authUser,
                                @RequestBody GedDto gedDto) {
        scoreUseCase.editGedScore(authUser.getId().getId(), gedDto);
    }

    @GetMapping("/ged")
    public GedDto getAllGedScore(@AuthenticationPrincipal AuthUser authUser) {
        return scoreUseCase.findGedScore(authUser.getId().getId());
    }
}
