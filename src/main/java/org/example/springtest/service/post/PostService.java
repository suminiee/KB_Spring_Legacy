package org.example.springtest.service.post;

import org.example.springtest.dto.post.NewPostDTO;
import org.example.springtest.dto.post.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getPostList();
    int delete(int id);
    List<PostDTO> findByCond(String title, String content);
    int save(NewPostDTO newPostDTO);
    void resetAndGeneratePosts(int count);
    long testMysqlReadTime(int count);
    long testRedisReadTime(int count);

}
