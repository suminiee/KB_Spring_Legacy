package org.example.springtest.controller.post.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.dto.post.NewPostDTO;
import org.example.springtest.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public String postList(Model model) {
        model.addAttribute("postList", postService.getPostList());
        return "post/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        log.info("=====================> 삭제 기능 호출", "/post/delete");

        int affectedRows = postService.delete(id);

        if (affectedRows == 0) {
            log.error("삭제 실패");
        }
        return "redirect:/post/list";
    }

    @GetMapping("/search")
    public String searchList(@RequestParam("title") String title, @RequestParam("content") String content, Model model) {
        log.info("-----------------> 게시글 검색 기능 호출, /post/search");
        model.addAttribute("postList", postService.findByCond(title, content));
        return "post/list";
    }

    @GetMapping("/new")
    public String getNew() {
        return "post/post-new";
    }

    @PostMapping("/new")
    public String addNew(NewPostDTO newPostDTO) {
        postService.save(newPostDTO);
        return "redirect:/post/list";
    }

    @GetMapping("/compare")
    public String compare(Model model) {
        log.info("=================> DB 비교 기능 호출, /post/compare");

        int count = 1000;

        postService.resetAndGeneratePosts(count);
        model.addAttribute("count", count);
        model.addAttribute("mysqlTime", postService.testMysqlReadTime(count));
        model.addAttribute("redisTime", postService.testRedisReadTime(count));

        return "post/compare";
    }
}
