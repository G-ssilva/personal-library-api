package br.com.gssilva.personallibraryapi.infra;

import br.com.gssilva.personallibraryapi.exceptions.RegraNegocioException;
import br.com.gssilva.personallibraryapi.infra.dto.RestErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private RestErrorDto regraNegocioHandler(RegraNegocioException e) {
        return new RestErrorDto(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private RestErrorDto entityNotFoundHandler(EntityNotFoundException e) {
        return new RestErrorDto(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        RestErrorDto beanValidationError = new RestErrorDto(HttpStatus.BAD_REQUEST, "Ocorreu um erro de validação do(s) campo(s)", errorList);
        return handleExceptionInternal(ex, beanValidationError, headers, beanValidationError.getStatus(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<RestErrorDto> dataIntegrityViolationHandler(DataIntegrityViolationException e) {
        if (e.getMessage().contains("duplicate key value")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new RestErrorDto(HttpStatus.UNPROCESSABLE_ENTITY, "A entidade já existe no banco de dados"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Ops! A integridade do banco de dados foi violada"));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private RestErrorDto unexpectedExceptionHandler(Exception e) {
        return new RestErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Ops! Ocorreu um erro inesperado");
    }
}
