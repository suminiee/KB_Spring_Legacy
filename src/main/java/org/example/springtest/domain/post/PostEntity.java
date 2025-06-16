package org.example.springtest.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //모든 필드값을 받는 생성자를 만들어주는 어노테이션
@NoArgsConstructor //Bean 생성자
@Builder
public class PostEntity {
    private Integer id;
    private String title;
    private String content;
}
