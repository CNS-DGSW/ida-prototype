package kr.hs.dgsw.cns.aggregate.secondary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ExcelRequestDto {
    private String fileName;
    private String title;
    private String body;
}
