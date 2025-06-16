package org.example.springtest.service.member;

import org.example.springtest.domain.member.MemberEntity;
import org.example.springtest.dto.member.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getMemberList();
    void addMember(String name, String email);

}
