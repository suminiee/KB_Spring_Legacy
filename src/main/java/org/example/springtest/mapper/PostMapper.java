package org.example.springtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.springtest.domain.post.PostEntity;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostEntity> findAll();
    int delete(@Param("id") int id);
    int save(PostEntity postEntity);
}
