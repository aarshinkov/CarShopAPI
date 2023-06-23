package com.vasil.carshop.api.carshopapi.domain;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class App implements Serializable
{
  private String name;
  private String version;
}
