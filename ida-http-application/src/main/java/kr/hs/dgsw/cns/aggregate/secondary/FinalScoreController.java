package kr.hs.dgsw.cns.aggregate.secondary;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.cns.aggregate.secondary.usecase.FinalScoreUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/final")
@RequiredArgsConstructor
public class FinalScoreController {
    private final FinalScoreUseCase finalScoreUseCase;

    @GetMapping("/download")
    public void getFinal(HttpServletResponse response) {
        String fileNameOrg = URLEncoder.encode("최종결과.xlsx", StandardCharsets.UTF_8);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileNameOrg);

        try {
            ServletOutputStream out = response.getOutputStream();
            finalScoreUseCase.getFinalScore(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
