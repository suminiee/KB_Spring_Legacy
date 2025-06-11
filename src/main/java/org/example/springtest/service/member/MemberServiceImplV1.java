package org.example.springtest.service.member;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.member.MemberEntity;
import org.example.springtest.dto.member.MemberDTO;
import org.example.springtest.repository.member.MemberRepositoryV1;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImplV1 implements MemberService{
    private static MemberServiceImplV1 instance;
    private final MemberRepositoryV1 memberRepositoryV1;

//    public MemberServiceImplV1(MemberRepositoryV1 memberRepositoryV1) {
//        this.memberRepositoryV1 = memberRepositoryV1;
//    }

    //memberServiceImpl 생성 시점에 MemberRepositoryV1의 인스턴스를 받아서 사용
//    private MemberServiceImplV1(){
//        this.memberRepositoryV1 = MemberRepositoryV1.getInstance();
//    }
//
//    public static MemberServiceImplV1 getInstance() {
//        if (instance == null) {
//            instance = new MemberServiceImplV1();
//        }
//        return instance;
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

    @Override
    public MemberDTO addMember(String name, String email) {
        MemberEntity newMember = new MemberEntity();
        newMember.setName(name);
        newMember.setEmail(email);
        newMember.setGrade("아이언");
        newMember.setAsset(1345367654L);
        memberRepositoryV1.save(newMember);
        return null;
    }
}
