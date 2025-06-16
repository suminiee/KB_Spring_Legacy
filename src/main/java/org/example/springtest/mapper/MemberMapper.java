package org.example.springtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springtest.domain.member.MemberEntity;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberEntity> findAll();

    MemberEntity save(MemberEntity newMember);
}
