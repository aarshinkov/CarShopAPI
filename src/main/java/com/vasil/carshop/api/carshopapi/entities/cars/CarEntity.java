package com.vasil.carshop.api.carshopapi.entities.cars;

import com.vasil.carshop.api.carshopapi.entities.UserEntity;
import java.io.*;
import java.sql.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarEntity implements Serializable
{
  private String carId;
  private String brand;
  private String model;
  private Integer year;
  private Integer horsePowers;
  private Double price;
  private Integer category;
  private Integer engineType;
  private Integer gearbox;
  private MileageEntity mileage;
  private String color;
  private Timestamp addedOn;
  private UserEntity owner;
}
