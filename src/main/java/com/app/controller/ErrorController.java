package com.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(IOException.class)
    public ModelAndView catchError(IOException ex) {
        return getErrorPage(ex, "Wystąpił błąd w połaczeniu, spróbuj ponownie za chwilke");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView catchClientError(HttpClientErrorException ex) {
        return getErrorPage(ex, "Wybrany zasób nie istnieje");
    }

    private ModelAndView getErrorPage(Exception e, String message) {
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("template");
        modelAndView.addObject("error", message);
        return modelAndView;
    }

}
