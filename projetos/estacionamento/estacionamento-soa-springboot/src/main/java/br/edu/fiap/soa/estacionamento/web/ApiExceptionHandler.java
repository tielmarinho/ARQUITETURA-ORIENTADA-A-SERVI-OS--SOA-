package br.edu.fiap.soa.estacionamento.web;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.Map;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex){
        Map<String,Object> body = Map.of("timestamp", LocalDateTime.now().toString(), "error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
