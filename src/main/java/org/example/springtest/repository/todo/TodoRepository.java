package org.example.springtest.repository.todo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.domain.todo.Todo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TodoRepository  {
    private final EntityManager em;

    public List<Todo> findAll() {
        String jpql = "SELECT t FROM Todo t"; //특정 쿼리를 사용하고싶다 하는 경우에 사용
        return em.createQuery(jpql, Todo.class).getResultList(); //리스트로 만들어서 리턴
    }

    public void save(Todo todo) {
        em.persist(todo); // 자동으로 저장되나봄...
    }

    public void delete(Integer id) {
        Todo todo = em.find(Todo.class, id); //Todo 테이블을 보고 id값인 것을 찾아주세요
        if (todo != null) { // Todo 테이블에 찾으려는 것이 존재한다면
            em.remove(todo);
        }
    }

    public Todo findById(Integer id) {
        return em.find(Todo.class, id);
    }

    public Optional<Todo> findById2(Integer id) {
        return Optional.ofNullable(em.find(Todo.class, id)); //null이 들어가는 것에 대한 처리를 해줘야 정상적으로 작동
    }

    public void updateDone(Todo todo) {
        todo.setDone(!todo.getDone());

//        em.merge(todo);
    }


}
