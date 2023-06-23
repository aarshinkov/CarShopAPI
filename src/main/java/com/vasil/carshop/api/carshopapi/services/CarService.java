package com.vasil.carshop.api.carshopapi.services;

import com.vasil.carshop.api.carshopapi.requests.cars.CarCreateRequest;
import com.vasil.carshop.api.carshopapi.entities.cars.CarEntity;
import java.util.*;

public interface CarService
{
  List<CarEntity> getCars();
  
  CarEntity getCar(String carId);
  
  CarEntity createCar(CarCreateRequest ccr);
  
  CarEntity updateCar(String carId, CarCreateRequest ccr);
}
