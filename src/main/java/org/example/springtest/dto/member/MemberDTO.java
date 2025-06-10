package org.example.springtest.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //getter, setter
public class MemberDTO {
    private String name;
    private String email;
}
