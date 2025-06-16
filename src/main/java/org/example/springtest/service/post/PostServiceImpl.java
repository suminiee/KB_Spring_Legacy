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
    public List<PostDTO> findByCond(String title, String content) {
        return postRepository.findByCond(title, content);
    }

    @Override
    public int save(NewPostDTO newPostDTO) {
        PostEntity postEntity = PostEntity.builder()
                .title(newPostDTO.getTitle())
                .content(newPostDTO.getContent())
                .build();
        return postRepository.save(postEntity);
    }

    @Override
    public void resetAndGeneratePosts(int count) {
        postRepository.deleteAll();
        for (int i = 1; i <= count; i++) {
            String title = "title" + i;
            String content = "content" + i;

            PostDTO post = new PostDTO();
            post.setTitle(title);
            post.setContent(content);

            postRepository.saveForTest(post);
        }
    }

    @Override
    public long testMysqlReadTime(int count) {
        long start = System.currentTimeMillis();

        for (int  i = 1; i <= count; i++) {
            postRepository.findById(i);
        }

        return System.currentTimeMillis() - start;
    }

    @Override
    public long testRedisReadTime(int count) {
        long start = System.currentTimeMillis();

        for (int  i = 1; i <= count; i++) {
            postRepository.findByIdFromRedis(i);
        }

        return System.currentTimeMillis() - start;
    }


}
