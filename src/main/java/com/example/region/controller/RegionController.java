package com.example.region.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionController {

    @GetMapping("/")
    public ResponseEntity<?> defaultRest(){
        return ResponseEntity.ok("Default");
    }
}
