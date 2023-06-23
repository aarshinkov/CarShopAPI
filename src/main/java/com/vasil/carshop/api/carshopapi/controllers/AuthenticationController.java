package com.vasil.carshop.api.carshopapi.controllers;

import com.vasil.carshop.api.carshopapi.entities.*;
import com.vasil.carshop.api.carshopapi.exceptions.*;
import com.vasil.carshop.api.carshopapi.requests.*;
import com.vasil.carshop.api.carshopapi.responses.*;
import com.vasil.carshop.api.carshopapi.services.*;
import com.vasil.carshop.api.carshopapi.utils.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController
{

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final UserService userService;

  @PostMapping("/api/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest)
  {

    UserEntity user = userService.getUserByEmail(loginRequest.getEmail());

    LoginResponse response = new LoginResponse();

    if (user == null)
    {
      throw new CSException(ResponseCodes.BAD_CREDENTIALS, "Bad credentials", "Invalid email or password", HttpStatus.BAD_REQUEST);
    }

    if (!loginRequest.getPassword().equals(user.getPassword()))
    {
      throw new CSException(ResponseCodes.BAD_CREDENTIALS, "Bad credentials", "Invalid email or password", HttpStatus.BAD_REQUEST);
    }

    response.setUserId(user.getUserId());
    response.setEmail(user.getEmail());
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());

    return response;
  }
}
