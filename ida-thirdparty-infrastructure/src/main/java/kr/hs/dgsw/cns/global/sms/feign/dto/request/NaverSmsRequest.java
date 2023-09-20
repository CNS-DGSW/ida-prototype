package kr.hs.dgsw.cns.global.sms.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class NaverSmsRequest {
    private String type;
    private String from;
    private String content;
    private List<Message> messages;

    @Builder
    public static class Message{
        private String to;
        private String content;
    }
}
