package com.github.satwiksanand.posSystem.exception.globalHandler;

import com.github.satwiksanand.posSystem.exception.CategoryException;
import com.github.satwiksanand.posSystem.exception.ProductException;
import com.github.satwiksanand.posSystem.exception.StoreException;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDto> handleUserException(UserException ex){
        ErrorDto err =ErrorDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ErrorDto> handleStoreException(StoreException ex){
        ErrorDto err = ErrorDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDto> handleProductException(ProductException ex){
        ErrorDto err = ErrorDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorDto> handleCategoryException(CategoryException ex){
        ErrorDto err = ErrorDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
