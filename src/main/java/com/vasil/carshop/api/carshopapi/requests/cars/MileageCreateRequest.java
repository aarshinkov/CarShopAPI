package com.vasil.carshop.api.carshopapi.requests.cars;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MileageCreateRequest implements Serializable
{
  private Double mileage;
  private String unit;
}
