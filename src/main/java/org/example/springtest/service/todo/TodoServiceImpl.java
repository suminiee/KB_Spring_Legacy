package org.example.springtest.service.todo;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.todo.Todo;
import org.example.springtest.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void delete(Integer id) {
        todoRepository.delete(id);
    }

    @Override
    public Todo findById(Integer id) {
        return todoRepository.findById(id);
    }

    @Override
    public void updateDone(Integer id) {
        Todo todo = findById(id);

        if (todo == null) {
            throw new EntityNotFoundException("해당 Id를 가진 todo가 존재하지 않습니다");
        }

        todoRepository.updateDone(todo);
    }

    @Override
    public void updateDone2(Integer id) {
        Todo todo = todoRepository.findById2(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 가진 todo가 없습니다."));
        todoRepository.updateDone(todo);
    }
}
