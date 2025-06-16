package org.example.springtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.springtest.domain.post.PostEntity;
import org.example.springtest.dto.post.PostDTO;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostEntity> findAll();
    int delete(@Param("id") int id);
    List<PostDTO> findByCond(@Param("title") String title, @Param("content") String content);
    int save(PostEntity postEntity);
    void deleteAll();
    int saveForTest(PostDTO post);
    PostDTO findById(@Param("id") int id);
}
