package com.vasil.carshop.api.carshopapi.entities;

import com.fasterxml.jackson.annotation.*;
import java.io.*;
import java.sql.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity implements Serializable
{
  private String userId;
  private String email;
  @JsonIgnore
  private String password;
  private String firstName;
  private String lastName;
  private Timestamp createdOn;
  private Timestamp editedOn;
}
