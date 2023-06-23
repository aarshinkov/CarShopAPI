package com.vasil.carshop.api.carshopapi.requests;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest implements Serializable
{

  private String email;
  private String password;
}
