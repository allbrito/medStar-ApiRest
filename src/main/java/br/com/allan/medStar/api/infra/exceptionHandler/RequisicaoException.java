package br.com.allan.medStar.api.infra.exceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequisicaoException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(DadosErrorValidacao::new).toList());
    }

    private record DadosErrorValidacao(String campo, String mensagem) {

        public DadosErrorValidacao(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
