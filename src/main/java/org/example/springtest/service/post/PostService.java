package org.example.springtest.service.post;

import org.example.springtest.dto.post.NewPostDTO;
import org.example.springtest.dto.post.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getPostList();
    int delete(int id);
    int save(NewPostDTO newPostDTO);
}
