package com.example.shop.global.advice;

import com.example.shop.global.error.CustomError;
import com.example.shop.global.error.CustomErrorResponse;
import com.example.shop.global.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        List<CustomError> errorList = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;
            String invalidValue = "";

            if (field.getRejectedValue() != null) {
                invalidValue = field.getRejectedValue().toString();
            }

            errorList.add(CustomError.builder().field(field.getField())
                    .messege(field.getDefaultMessage())
                    .invalidValue(invalidValue)
                    .build());
        });

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("잘못된 입력")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .resultCode("FAIL").build();

        return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest) {
        List<CustomError> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            errorList.add(CustomError.builder()
                    .field(list.get(list.size() - 1).getName())
                    .messege(error.getMessage())
                    .invalidValue(error.getInvalidValue().toString()).build());
        });

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest) {
        List<CustomError> errorList = new ArrayList<>();

        errorList.add(CustomError.builder()
                .field(e.getParameterName())
                .messege(e.getMessage()).build());

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .resultCode("FAIL").build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity usernameNotFoundException(UsernameNotFoundException e, HttpServletRequest httpServletRequest){
        List<CustomError> errorList = new ArrayList<>();

        errorList.add(CustomError.builder().field("GET USER").messege(e.getMessage()).build());

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = CreateAdminFailException.class)
    public ResponseEntity createAdminFailException(CreateAdminFailException e, HttpServletRequest httpServletRequest){
        List<CustomError> errorList = new ArrayList<>();

        errorList.add( CustomError.builder().field("CREATE ADMIN").messege(e.getMessage()).build());

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = DuplicatedEmailException.class)
    public ResponseEntity duplicatedEmailException(DuplicatedEmailException e, HttpServletRequest httpServletRequest){
        List<CustomError> errorList = new ArrayList<>();

        errorList.add( CustomError.builder().field("CREATE ADMIN").messege(e.getMessage()).build());

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.CONFLICT.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(value = LoginFailException.class)
    public ResponseEntity loginFailException(LoginFailException e, HttpServletRequest httpServletRequest){
        List<CustomError> errorList = new ArrayList<>();

        errorList.add( CustomError.builder().field("LOGIN FAIL").messege(e.getMessage()).build());

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.CONFLICT.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseEntity emailNotFoundException(EmailNotFoundException e, HttpServletRequest httpServletRequest){
        List<CustomError> errorList = new ArrayList<>();

        errorList.add( CustomError.builder().field("GET EMAIL").messege(e.getMessage()).build());

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}