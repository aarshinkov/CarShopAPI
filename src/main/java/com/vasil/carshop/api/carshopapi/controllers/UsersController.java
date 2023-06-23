package com.vasil.carshop.api.carshopapi.controllers;

import com.vasil.carshop.api.carshopapi.entities.*;
import com.vasil.carshop.api.carshopapi.requests.users.*;
import com.vasil.carshop.api.carshopapi.services.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsersController
{
  private final Logger log = LoggerFactory.getLogger(getClass());
  
  private final UserService userService;

  @PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserEntity> createUser(@RequestBody UserCreateRequest ucr)
  {

    UserEntity createdUser = userService.createUser(ucr);

    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
