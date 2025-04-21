package com.db.db_server.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    @Override
    public ResponseEntity<String> getUsers(){
        return ResponseEntity.ok("getUsers");
    }

}
