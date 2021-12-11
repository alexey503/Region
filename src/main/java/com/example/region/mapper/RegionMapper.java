package com.example.region.mapper;

import com.example.region.data.entity.Region;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RegionMapper {

    @Insert("insert into regions(name, alias) values(#{name},#{alias})")
    void addRegion(Region region);

    @Select("select * from regions where id=#{regionId}")
    Optional<Region> getRegionById(Long id);

    @Select("select * from regions where alias=#{alias}")
    Optional<Region> getRegionByAlias(String alias);

    @Select("select * from regions where name like '%${searchString}%'")
    List<Region> regionSearch(String searchString);

    @Select("update regions set name = #{alias} where id = #{id}")
    void update(long id, String alias);
}
