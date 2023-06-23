package com.vasil.carshop.api.carshopapi.errors;

import com.fasterxml.jackson.annotation.*;
import java.time.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse
{

  private Integer code;
  private String message;
  private String details;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime timestamp;
}
