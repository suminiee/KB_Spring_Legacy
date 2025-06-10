package org.example.springtest.service.member;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.member.MemberEntity;
import org.example.springtest.dto.member.MemberDTO;
import org.example.springtest.repository.member.MemberRepositoryV1;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImplV1 implements MemberService{
    private final MemberRepositoryV1 memberRepositoryV1;

//    public MemberServiceImplV1(MemberRepositoryV1 memberRepositoryV1) {
//        this.memberRepositoryV1 = memberRepositoryV1;
//    }

    public List<MemberDTO> getMemberList() {
        List<MemberEntity> entityList = memberRepositoryV1.getMemberList();
        List<MemberDTO> dtoList = new ArrayList<>();

        for (MemberEntity entity : entityList) {
            MemberDTO dto = new MemberDTO();
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
