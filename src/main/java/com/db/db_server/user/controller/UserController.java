package com.db.db_server.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
public interface UserController {
    @GetMapping("")
    ResponseEntity<String> getUsers();
}
