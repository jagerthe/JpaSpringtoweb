package com.alemonesLTDA.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alemonesLTDA.course.services.exceptions.DatabaseException;
import com.alemonesLTDA.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    //instanciando metodo ResponseEntity com parametro a classe StandardError, sendo definida por resourcerNotFound e recebendo de parametro
    //a classe resourcenotfound e HttpServletRequest para definir qual mensagem a requisiçao final deve mostrar
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        //String error vai exibir na busca final a mensagem desejada
        String error = "Resource not found";
        //Status é o objeto que vai mostrar no resultado final da busca o codigo de erro
        //como desejamos mostrar um 404, logo not found
        HttpStatus status = HttpStatus.NOT_FOUND;
        //instanciamos a classe standardError com o subnome de err, e dentro dela passamos todos os atributos/mensagem
        //na qual vai ser exibida na tela de resultado final
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        //Na ordem:      timestamp(formatado na classe standard), status(instanciado com value null, pois o mesmo precisa existir para o java reconhecer e atribuir o valor passado)
        //                  error recebendo a mensagem passada no primeiro atributo declarado dentro do metodo, e.getmessage(buscando o erro pelo atributo resourcenotfound, PELO ATRIBUTO, NAO PELA CLASSE),
        //                  e request.getRequestURI

        //retornando a response com o status(httpstatus) e o corpo do erro(tudo setado por nos no err)
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        //String error vai exibir na busca final a mensagem desejada
        String error = "DataBase Error";
        //Status é o objeto que vai mostrar no resultado final da busca o codigo de erro
        //como desejamos mostrar um 400, logo not found
        HttpStatus status = HttpStatus.BAD_REQUEST;
        //instanciamos a classe standardError com o subnome de err, e dentro dela passamos todos os atributos/mensagem
        //na qual vai ser exibida na tela de resultado final
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        //Na ordem:      timestamp(formatado na classe standard), status(instanciado com value null, pois o mesmo precisa existir para o java reconhecer e atribuir o valor passado)
        //                  error recebendo a mensagem passada no primeiro atributo declarado dentro do metodo, e.getmessage(buscando o erro pelo atributo resourcenotfound, PELO ATRIBUTO, NAO PELA CLASSE),
        //                  e request.getRequestURI

        //retornando a response com o status(httpstatus) e o corpo do erro(tudo setado por nos no err)
        return ResponseEntity.status(status).body(err);
    }
}
