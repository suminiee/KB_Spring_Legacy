package org.example.springtest.service.post;


import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.post.PostEntity;
import org.example.springtest.dto.post.NewPostDTO;
import org.example.springtest.dto.post.PostDTO;
import org.example.springtest.repository.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    @Override
    public List<PostDTO> getPostList() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostDTO> dtoList = new ArrayList<>();

        for (PostEntity entity : entityList) {
            PostDTO dto = new PostDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public int delete(int id) {
        return postRepository.delete(id);
    }

    @Override
    public int save(NewPostDTO newPostDTO) {
        PostEntity postEntity = PostEntity.builder()
                .title(newPostDTO.getTitle())
                .content(newPostDTO.getContent())
                .build();
        return postRepository.save(postEntity);
    }


}
