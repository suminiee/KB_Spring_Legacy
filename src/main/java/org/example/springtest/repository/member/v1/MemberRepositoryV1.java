package org.example.springtest.repository.member.v1;

import lombok.Getter;
import org.example.springtest.domain.member.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class MemberRepositoryV1 {
//    private static MemberRepositoryV1 instance;
//    private MemberRepositoryV1(){}
//
//    public static MemberRepositoryV1 getInstance() {
//        if (instance == null) {
//            instance = new MemberRepositoryV1();
//        }
//        return instance;
//    }
    private final List<MemberEntity> memberList = new ArrayList<>();
    public MemberRepositoryV1() {
        memberList.add(new MemberEntity(1L,"호날두",  "ronaldo@example.com", "플레티넘", 123565L));
        memberList.add(new MemberEntity(2L, "조승연",  "woodz@example.com","아이언", 12356533L));
        memberList.add(new MemberEntity(3L, "강영현",  "youngk@example.com","브론즈", 1235634345L));
    }

    public void save(MemberEntity member) {
        member.setId((long) (memberList.size()+1));
        memberList.add(member);
    }
}
