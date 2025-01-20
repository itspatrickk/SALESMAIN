package com.restapi.cicd.services;

import com.restapi.cicd.payload.UserRequest;

public interface SqlService {
  void saveUser(UserRequest request);
  void logIn(UserRequest request);
}
