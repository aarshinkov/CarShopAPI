package com.vasil.carshop.api.carshopapi.requests.cars;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarCreateRequest implements Serializable
{
  private String brand;
  private String model;
  private Double price;
  private Integer year;
  private Integer engineType;
  private Integer horsePowers;
  private Integer gearbox;
  private Integer category;
  private MileageCreateRequest mileage;
  private String color;
  private String userId;
}
