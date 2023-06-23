package com.vasil.carshop.api.carshopapi.exceptions;

import lombok.*;
import org.springframework.http.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CSException extends RuntimeException
{

  private Integer code;
  private String message;
  private String details;
  private HttpStatus status;

  public CSException(String message)
  {
    super(message);
  }
}
