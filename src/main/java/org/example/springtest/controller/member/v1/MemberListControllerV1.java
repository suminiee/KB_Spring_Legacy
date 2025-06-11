package org.example.springtest.controller.member.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.service.member.MemberService;
import org.example.springtest.service.member.MemberServiceImplV1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberListControllerV1 {
    private final MemberService memberService;

    //스프링에 의한 의존성 주입이 아닌 직접 구현한 싱글톤의 인스턴스를 받아서 사용
//    private final MemberService  memberService2 = MemberServiceImplV1.getInstance();

    @GetMapping("/member/list")
    public String memberList(Model model) {
        model.addAttribute("memberList", memberService.getMemberList());
        return "member/list";
    }
}
