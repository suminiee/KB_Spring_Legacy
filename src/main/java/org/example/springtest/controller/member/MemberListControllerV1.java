package org.example.springtest.controller.member;

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

//    public MemberListControllerV1 memberListControllerV1(MemberServiceImplV1 memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/member/list")
    public String memberList(Model model) {
        model.addAttribute("memberList", memberService.getMemberList());
        return "member/list";
    }
}
