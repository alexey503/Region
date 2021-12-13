package com.example.region;

import com.example.region.data.entity.Region;
import com.example.region.mapper.RegionMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegionMapperTests {

    @Autowired
    RegionMapper regionMapper;

    static final Region testRegion = new Region("TestRegion", "TestAlias");

    @Test
    @DisplayName("Db filling by liquibase test")
    void dbDataFillTest() {

        Assertions.assertTrue(regionMapper.getRegionsCount() > 1);

    }

    @Test
    @DisplayName("Db addRegion, getById, getByAlias test")
    void dbAddRegionTest() {

        long entitiesCount = regionMapper.getRegionsCount();

        regionMapper.addRegion(testRegion);

        assertEquals(regionMapper.getRegionsCount(), entitiesCount + 1);

        assertTrue(regionMapper.getRegionByAlias(testRegion.getAlias()).isPresent());
        assertTrue(regionMapper.getRegionByAlias(testRegion.getAlias()).get().equals(testRegion));

    }

    @Test
    @DisplayName("Db update entity")
    void dbUpdateRegionTest() {

        Assertions.assertTrue(regionMapper.getRegionById(1L).isPresent());
        Region oldRegion = regionMapper.getRegionById(1L).get();

        regionMapper.update(oldRegion.getId(), testRegion.getName());

        Region updatedRegion = regionMapper.getRegionById(oldRegion.getId()).get();

        assertEquals(updatedRegion.getName(), testRegion.getName());
        Assertions.assertNotEquals(updatedRegion.getName(), oldRegion.getName());

    }

    @Test
    @DisplayName("Db delete entity")
    void dbDeleteRegionTest() {

        long entitiesCount = regionMapper.getRegionsCount();

        Assertions.assertTrue(regionMapper.getRegionById(5L).isPresent());

        Region region = regionMapper.getRegionById(5L).get();

        regionMapper.delete(region.getId());

        assertEquals(regionMapper.getRegionsCount() + 1, entitiesCount);

        Assertions.assertTrue(regionMapper.getRegionById(5L).isEmpty());

    }

}
