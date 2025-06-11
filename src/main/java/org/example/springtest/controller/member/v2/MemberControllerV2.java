package org.example.springtest.controller.member.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/v2")
public class MemberControllerV2 {
    private final MemberService memberService;

    @GetMapping("/")
    public String home() {
        log.info("================> '/member', member/index.jsp /");
        return "member/index";
    }

    @GetMapping("/list")
    public String memberList(Model model) {
        model.addAttribute("memberList", memberService.getMemberList());
        return "member/list";
    }

    @GetMapping("/add")
    public String addMemberPage() {
        return "member/add";
    }

    @PostMapping("/add")
    public String addMember(@RequestParam("name") String name, @RequestParam("email") String email) {
        log.info("name={}, email={},", name, email);
        memberService.addMember(name, email);
        return "redirect:/member/list";
    }
}
