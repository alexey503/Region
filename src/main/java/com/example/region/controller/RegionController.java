package com.example.region.controller;

import com.example.region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegionById(@PathVariable long id) {
        return regionService.getRegionById(id);
    }

    @GetMapping("/alias")
    public ResponseEntity<?> getRegionByAlias(@RequestParam String alias) {
        return regionService.getRegionByAlias(alias);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getRegionSearch(@RequestParam String searchString) {
        return regionService.regionSearch(searchString);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRegion(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "alias") String alias) {

        return regionService.addRegion(name, alias);

    }

    @PutMapping("/update")
    public ResponseEntity<?> changeRegion(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "alias") String alias) {

        return regionService.updateRegion(name, alias);

    }
}
