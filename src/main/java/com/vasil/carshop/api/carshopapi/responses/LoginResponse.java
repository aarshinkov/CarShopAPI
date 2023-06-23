package com.vasil.carshop.api.carshopapi.responses;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse implements Serializable
{
  private String userId;
  private String email;
  private String firstName;
  private String lastName;
}
