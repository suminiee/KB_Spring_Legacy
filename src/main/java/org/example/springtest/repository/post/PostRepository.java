package org.example.springtest.repository.post;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.post.PostEntity;
import org.example.springtest.mapper.PostMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final PostMapper postMapper;

    public List<PostEntity> findAll() {
        return postMapper.findAll();
    }
    public int delete(int id) {return postMapper.delete(id);}
    public int save(PostEntity postEntity) {return postMapper.save(postEntity);}
}
