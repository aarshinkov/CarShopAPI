package com.vasil.carshop.api.carshopapi.requests.users;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateRequest implements Serializable
{

  private String email;
  private String password;
  private String firstName;
  private String lastName;
}
