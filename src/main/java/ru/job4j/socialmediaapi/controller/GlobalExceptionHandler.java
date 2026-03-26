package ru.job4j.socialmediaapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public void catchDataIntegrityViolationException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        map.put("typeException", String.valueOf(e.getClass()));
        map.put("timestamp", String.valueOf(LocalDateTime.now()));
        map.put("path", request.getRequestURI());

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
