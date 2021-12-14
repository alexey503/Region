package com.example.region;

import com.example.region.controller.RegionController;
import com.example.region.data.entity.Region;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ControllerTests {

    @Autowired
    RegionController controller;

    private static Region testRegion;

    @BeforeAll
    static void init() {
        testRegion = new Region("Республика Адыгея", "RU-AD");
        testRegion.setId(1);
    }

    @Test
    @DisplayName("Get region by id")
    void getByIdTest() {

        Region region = (Region) controller.getRegionById(1).getBody();

        assertNotNull(region);
        assertEquals(region.getId(), testRegion.getId());
        assertEquals(region.getName(), testRegion.getName());
        assertEquals(region.getAlias(), testRegion.getAlias());
    }

    @Test
    @DisplayName("Get region by alias")
    void getByAliasTest() {

        Region region = (Region) controller.getRegionByAlias(testRegion.getAlias()).getBody();

        assertNotNull(region);

        assertEquals(region.getId(), testRegion.getId());
        assertEquals(region.getName(), testRegion.getName());
        assertEquals(region.getAlias(), testRegion.getAlias());

    }

    @Test
    @DisplayName("Get region by searchString in name")
    void getByStringNameTest() {

        List<Region> regions = (List<Region>) controller.getRegionSearch("стан").getBody();
        assertNotNull(regions);
        for (Region region : regions) {
            assertTrue(region.getName().contains("стан"));
            System.out.println(region.getName());
        }

    }

    @Test
    @DisplayName("Add region test")
    void addRegionTest() {

        Region newRegion = new Region("TestRegion", "new");

        assertEquals(controller.getRegionSearch(newRegion.getName()).getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(controller.getRegionByAlias(newRegion.getAlias()).getStatusCode(), HttpStatus.NOT_FOUND);

        controller.addRegion(newRegion.getName(), newRegion.getAlias());

        assertEquals(controller.getRegionSearch(newRegion.getName()).getStatusCode(), HttpStatus.OK);
        assertEquals(controller.getRegionByAlias(newRegion.getAlias()).getStatusCode(), HttpStatus.OK);

    }

    @Test
    @DisplayName("Update region name test")
    void updateRegionNameTest() {

        String newText = "New text for region with alias RU-AL";
        String alias = "RU-AL";

        assertEquals(controller.getRegionByAlias(alias).getStatusCode(), HttpStatus.OK);
        assertNotEquals(((Region) controller.getRegionByAlias(alias).getBody()).getName(), newText);

        controller.changeRegion(newText, alias);

        assertEquals(controller.getRegionByAlias(alias).getStatusCode(), HttpStatus.OK);
        assertEquals(((Region) controller.getRegionByAlias(alias).getBody()).getName(), newText);

    }

    @Test
    @DisplayName("Delete region test")
    void deleteRegionTest() {

        assertEquals(controller.getRegionById(1).getStatusCode(), HttpStatus.OK);

        controller.deleteRegion(1);

        assertEquals(controller.getRegionById(1).getStatusCode(), HttpStatus.NOT_FOUND);

    }

}
