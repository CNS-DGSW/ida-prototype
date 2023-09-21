package kr.hs.dgsw.cns.aggregate.secondary;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.cns.aggregate.secondary.usecase.ComputingScoreUseCase;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/interview/computing")
@RequiredArgsConstructor
public class ComputingScoreController {

    private final ComputingScoreUseCase computingScoreUseCase;

    @PutMapping("/upload")
    public void uploadComputingScore(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return;
        }

        try {
            computingScoreUseCase.uploadComputingScore(new FileRequest(file.getContentType(),
                    file.getOriginalFilename(),
                    file.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/download")
    public void getInterview(HttpServletResponse response) {
        String fileNameOrg = URLEncoder.encode("심층면접2.xlsx", StandardCharsets.UTF_8);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileNameOrg);

        try {
            ServletOutputStream out = response.getOutputStream();
            computingScoreUseCase.getComputingScore(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
