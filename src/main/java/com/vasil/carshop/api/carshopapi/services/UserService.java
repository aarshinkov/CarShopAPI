package com.vasil.carshop.api.carshopapi.services;

import com.vasil.carshop.api.carshopapi.entities.*;
import com.vasil.carshop.api.carshopapi.requests.users.*;

public interface UserService
{
  UserEntity getUserByEmail(String email);
  
  UserEntity createUser(UserCreateRequest ucr);
}
