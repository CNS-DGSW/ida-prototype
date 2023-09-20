package kr.hs.dgsw.cns.aggregate.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendAuthMessageRequest {
    private String contact;
}
