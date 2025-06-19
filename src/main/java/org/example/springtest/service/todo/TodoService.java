package org.example.springtest.service.todo;


import org.example.springtest.domain.todo.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
    void save(Todo todo);
    void delete(Integer id);
    Todo findById(Integer id);
    void updateDone(Integer id);
    void updateDone2(Integer id);
}
