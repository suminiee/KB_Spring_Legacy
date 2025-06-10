package org.example.springtest.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //모든 필드값을 받는 생성자를 만들어주는 어노테이션
public class MemberEntity {
    private Long id;
    private String name;
    private String email;
    private String grade;
    private Long asset;

}
