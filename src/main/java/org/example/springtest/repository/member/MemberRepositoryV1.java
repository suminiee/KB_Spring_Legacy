package org.example.springtest.repository.member;

import org.example.springtest.domain.member.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepositoryV1 {
    public List<MemberEntity> getMemberList() {
        List<MemberEntity> memberList = new ArrayList<>();
        memberList.add(new MemberEntity(1L, "ronaldo@example.com", "호날두", "플레티넘", 123565L));
        memberList.add(new MemberEntity(2L, "woodz@example.com", "조승연", "아이언", 12356533L));
        memberList.add(new MemberEntity(3L, "youngk@example.com", "강영현", "브론즈", 1235634345L));

        return memberList;
    }
}
