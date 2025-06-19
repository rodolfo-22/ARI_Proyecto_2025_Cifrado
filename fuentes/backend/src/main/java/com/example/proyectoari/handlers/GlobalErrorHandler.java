package com.example.proyectoari.handlers;

import com.example.proyectoari.domain.dtos.GeneralResponse;
import com.example.proyectoari.utils.ErrorsTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.net.BindException;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    private final ErrorsTool errorsTool;

    public GlobalErrorHandler(ErrorsTool errorsTool) {
        this.errorsTool = errorsTool;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> GeneralHandler(Exception ex) {
        return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GeneralResponse> ResourceNotFoundHandler(NoResourceFoundException ex) {
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> BadRequestHandler(MethodArgumentNotValidException ex) {
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, errorsTool.mapErrors(ex.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<GeneralResponse> handleIOException(IOException ex) {
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Couldn't read archive: " + ex.getMessage());
    }
}
