package org.example.springtest.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MemberHomeController {
    @GetMapping("/member")
    public String home() {
        log.info("================> '/member', member/index.jsp /");
        return "member/index";
    }
}
