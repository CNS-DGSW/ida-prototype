package kr.hs.dgsw.cns.global.sms.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendMessageRequest {
    private String contact;

    public static SendMessageRequest of(
            String contact
    ) {
        return SendMessageRequest.builder()
                .contact(contact)
                .build();
    }
}
