package kr.hs.dgsw.cns.aggregate.member.controller;

import kr.hs.dgsw.cns.aggregate.member.webadapter.MemberWebAdapter;
import kr.hs.dgsw.cns.aggregate.member.dto.MemberRequest;
import kr.hs.dgsw.cns.aggregate.member.ro.TokenRO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberWebAdapter webAdapter;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody MemberRequest registerRequest) {
        webAdapter.register(registerRequest);
    }

    @PostMapping("/login")
    public TokenRO login(@RequestBody MemberRequest memberRequest) {
        return webAdapter.login(memberRequest);
    }
}
