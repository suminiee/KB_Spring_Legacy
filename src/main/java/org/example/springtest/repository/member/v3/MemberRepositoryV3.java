package org.example.springtest.repository.member.v3;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.member.MemberEntity;
import org.example.springtest.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryV3 {
    private final MemberMapper memberMapper;

    public List<MemberEntity> findAll() {
        return memberMapper.findAll();
    }

    public MemberEntity save(MemberEntity newMember) {
        return memberMapper.save(newMember);
    }
}
