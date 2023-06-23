package com.vasil.carshop.api.carshopapi.entities.cars;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MileageEntity implements Serializable
{
  private Double mileage;
  private String unit;
}
