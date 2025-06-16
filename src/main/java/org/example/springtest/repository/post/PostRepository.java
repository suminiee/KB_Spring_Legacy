package org.example.springtest.repository.post;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.post.PostEntity;
import org.example.springtest.dto.post.PostDTO;
import org.example.springtest.mapper.PostMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final PostMapper postMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public List<PostEntity> findAll() {
        return postMapper.findAll();
    }
    public int delete(int id) {return postMapper.delete(id);}
    public List<PostDTO> findByCond(String title, String content) { return postMapper.findByCond(title, content); }
    public int save(PostEntity postEntity) {return postMapper.save(postEntity);}
    public void deleteAll() {
        postMapper.deleteAll();
        redisTemplate.delete(redisTemplate.keys("post:*"));
    }
    public void saveForTest(PostDTO post) {
        postMapper.saveForTest(post);
        redisTemplate.opsForValue().set("post:" + post.getId(), post);
    }
    public PostDTO findById(int id) {
        return postMapper.findById(id);
    }
    public PostDTO findByIdFromRedis(int id) {
        Object obj = redisTemplate.opsForValue().get("post:" + id);

        if (obj instanceof PostDTO) {
            return (PostDTO) obj;
        }

        return null;
    }
}
