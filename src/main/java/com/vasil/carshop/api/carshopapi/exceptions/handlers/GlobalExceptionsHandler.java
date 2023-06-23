package com.vasil.carshop.api.carshopapi.exceptions.handlers;

import com.vasil.carshop.api.carshopapi.errors.ErrorResponse;
import com.vasil.carshop.api.carshopapi.exceptions.*;
import java.time.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

@ControllerAdvice
public class GlobalExceptionsHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(CSException.class)
    public ResponseEntity<ErrorResponse> errorHandler(CSException ex, WebRequest request) {
      
        ErrorResponse errors = new ErrorResponse();
        errors.setMessage(ex.getMessage());
        errors.setCode(ex.getCode());
        errors.setDetails(ex.getDetails());
        errors.setTimestamp(LocalDateTime.now());

        log.error("Error: " + errors.toString());

        return new ResponseEntity<>(errors, ex.getStatus());
    }

}
