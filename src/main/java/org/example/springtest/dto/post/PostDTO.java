package org.example.springtest.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDTO implements Serializable { //직렬화 및 역직렬화
    private Integer id;
    private String title;
    private String content;
}
