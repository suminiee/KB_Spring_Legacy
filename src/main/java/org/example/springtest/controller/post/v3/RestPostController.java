package org.example.springtest.controller.post.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.dto.post.NewPostDTO;
import org.example.springtest.dto.post.PostDTO;
import org.example.springtest.service.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post/v3")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class RestPostController {
    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>> postList(HttpServletRequest req) {
        log.info("=============> 게시글 목록 페이지 호출, {}", req.getRequestURI());
        List<PostDTO> list = postService.getPostList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청을 처리할 수 없습니다.");
    }


    @PostMapping(value = "/delete", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> delete(@RequestParam("id") int id, HttpServletRequest req) {
        try {
            log.info("=====================> 삭제 기능 호출, {}", req.getRequestURI());

            int affectedRows = postService.delete(id);

            if (affectedRows == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제 대상을 찾을 수 없습니다.");
            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
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
    public ResponseEntity<?> addNew(@RequestBody NewPostDTO newPostDTO, HttpServletRequest req) {
        log.info("=====================> 게시글 추가 기능 호출, {}", req.getRequestURI());

        try {
            int result = postService.save(newPostDTO);
            if (result == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 저장 실패");
            }
            return ResponseEntity.ok("게시글 저장 성공");
        } catch (Exception e) {
            log.error("게시글 저장 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 저장 실패");
        }
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
