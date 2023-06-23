package com.vasil.carshop.api.carshopapi.controllers;

import com.vasil.carshop.api.carshopapi.domain.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<App> home()
  {
    App app = new App();
    app.setName("CarShop API");
    app.setVersion("1.0.0");

    return ResponseEntity.ok(app);
  }
}
