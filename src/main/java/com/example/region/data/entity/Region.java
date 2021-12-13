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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return name.equals(region.name) && alias.equals(region.alias);
    }
}
