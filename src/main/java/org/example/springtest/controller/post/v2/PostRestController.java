package org.example.springtest.controller.post.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.dto.post.NewPostDTO;
import org.example.springtest.dto.post.PostDTO;
import org.example.springtest.service.post.PostService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post/v2")
@RequiredArgsConstructor
@Slf4j
public class PostRestController {
    private final PostService postService;

    @GetMapping("/list")
    public List<PostDTO> postList(HttpServletRequest req) {
        log.info("=============> 게시글 목록 페이지 호출, {}", req.getRequestURI());
        return postService.getPostList();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        log.info("=====================> 삭제 기능 호출, {}", "/post/delete");

        int affectedRows = postService.delete(id);

        if (affectedRows == 0) {
            log.error("삭제 실패");
        }
        return "redirect:/post/list";
    }

    @GetMapping("/search")
    public List<PostDTO> searchList(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "content", required = false) String content,
                                    HttpServletRequest req) {
        log.info("-----------------> 게시글 검색 기능 호출,{}", req.getRequestURI());
        return postService.findByCond(title, content);
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
