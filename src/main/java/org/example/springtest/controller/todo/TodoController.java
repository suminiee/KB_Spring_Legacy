package org.example.springtest.controller.todo;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.domain.todo.Todo;
import org.example.springtest.service.todo.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Todo 컨트롤러")
@Slf4j
@RequiredArgsConstructor //의존성 주입
@CrossOrigin("*")
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody Todo todo) {
        log.info("[TodoController] - todo = {}", todo);
        todoService.save(todo);
        return ResponseEntity.ok("저장 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateDone(@PathVariable Integer id) {
        todoService.updateDone(id);
        return ResponseEntity.ok().build();
    }

}
