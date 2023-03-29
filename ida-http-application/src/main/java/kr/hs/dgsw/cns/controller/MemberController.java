package kr.hs.dgsw.cns.controller;

import kr.hs.dgsw.cns.aggregate.member.dto.MemberRegisterRequest;
import kr.hs.dgsw.cns.aggregate.member.spi.service.MemberRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRegisterService registerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody MemberRegisterRequest registerRequest) {
        registerService.register(registerRequest);
    }


}
