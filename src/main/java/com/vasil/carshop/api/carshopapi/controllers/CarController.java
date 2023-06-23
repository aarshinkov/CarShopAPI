package com.vasil.carshop.api.carshopapi.controllers;

import com.vasil.carshop.api.carshopapi.requests.cars.CarCreateRequest;
import com.vasil.carshop.api.carshopapi.entities.cars.CarEntity;
import com.vasil.carshop.api.carshopapi.requests.*;
import com.vasil.carshop.api.carshopapi.services.*;
import java.util.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CarController
{

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final CarService carService;

  @GetMapping(value = "/api/cars", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CarEntity>> getCars()
  {
    List<CarEntity> cars = carService.getCars();

    return ResponseEntity.ok(cars);
  }

  @GetMapping(value = "/api/cars/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CarEntity> getCar(@PathVariable("carId") String carId)
  {

    CarEntity car = carService.getCar(carId);

    return ResponseEntity.ok(car);
  }
  
  @PostMapping(value = "/api/cars", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CarEntity> createCar(@RequestBody CarCreateRequest ccr) {
    
    CarEntity createdCar = carService.createCar(ccr);
    
    return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
  }
  
  @PutMapping(value = "/api/cars/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CarEntity> updateCar(@PathVariable("carId") String carId, @RequestBody CarCreateRequest ccr) {
    
    CarEntity updatedCar = carService.updateCar(carId, ccr);
    
    return new ResponseEntity<>(updatedCar, HttpStatus.OK);
  }
}
