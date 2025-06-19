package org.example.springtest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFoundTodo(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(NoHandlerFoundException.class) //이 예외가 발생할 떄
    @ResponseStatus(HttpStatus.NOT_FOUND) //이 응답을 내려준다
    public String handle404(NoHandlerFoundException e) {
        return "/exception/404";
    }


    //위에서 처리하지 못한 예외가 남아있다면 아래 핸들러가 처리함
    @ExceptionHandler({Exception.class}) //전혀 예상하지 못한 예외는 얘가 처리를 함
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle500(Exception e, Model model) {
        log.error("==================> 500 에러, {}", e.getMessage());
        e.printStackTrace();

        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("stackTrace", e.getStackTrace());

        return "/exception/500";
    }
}
