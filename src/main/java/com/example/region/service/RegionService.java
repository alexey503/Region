package com.example.region.service;

import com.example.region.data.entity.Region;
import com.example.region.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RegionService {

    @Autowired
    RegionMapper regionMapper;

    public ResponseEntity<Region> getRegionById(long id) {

        return regionMapper.getRegionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<Region> getRegionByAlias(String alias) {

        return regionMapper.getRegionByAlias(alias)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<List<Region>> regionSearch(String searchString) {

        List<Region> result = regionMapper.regionSearch(searchString);

        if (result == null || result.size() == 0) {

            return ResponseEntity.notFound().build();

        } else {

            return ResponseEntity.ok(result);

        }
    }

    public ResponseEntity<?> addRegion(String name, String alias) {

        if (regionMapper.getRegionByAlias(alias).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        regionMapper.addRegion(new Region(name, alias));

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateRegion(String name, String alias) {

        Optional<Region> optionalRegion = regionMapper.getRegionByAlias(alias);

        if (optionalRegion.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        regionMapper.update(optionalRegion.get().getId(), name);

        return ResponseEntity.ok().build();
    }
}
