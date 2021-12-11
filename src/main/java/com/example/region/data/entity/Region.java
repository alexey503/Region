package com.example.region.data.entity;

import lombok.Data;

@Data
public class Region {
    private long id;
    private String name;
    private String alias;

    public Region(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }
}
